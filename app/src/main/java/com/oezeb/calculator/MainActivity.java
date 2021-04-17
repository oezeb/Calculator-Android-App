package com.oezeb.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Stack;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText in = findViewById(R.id.in);
        TextView out = findViewById(R.id.out);

        //Disable default keyboard
        in.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        //evaluate expression when entry
        in.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() == 0) {
                    out.setText("");
                    return;
                }
                try {
                    Double dVal = Calculator.eval(s.toString());
                    Integer iVal = dVal.intValue();
                    out.setText((dVal-iVal == 0.0) ? iVal.toString() : dVal.toString());
                }
                catch (Exception ex) {
                    out.setText("Error");
                }
            }
        });

        //get data from buttons
        findViewById(R.id.b0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in.append("0");
            }
        });

        findViewById(R.id.b1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in.append("1");
            }
        });

        findViewById(R.id.b2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in.append("2");
            }
        });

        findViewById(R.id.b3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in.append("3");
            }
        });

        findViewById(R.id.b4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in.append("4");
            }
        });

        findViewById(R.id.b5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in.append("5");
            }
        });

        findViewById(R.id.b6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in.append("6");
            }
        });

        findViewById(R.id.b7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in.append("7");
            }
        });

        findViewById(R.id.b8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in.append("8");
            }
        });

        findViewById(R.id.b9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in.append("9");
            }
        });

        findViewById(R.id.open_brace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in.append("(");
            }
        });

        findViewById(R.id.close_brace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in.append(")");
            }
        });

        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in.append("+");
            }
        });

        findViewById(R.id.sub).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in.append("-");
            }
        });

        findViewById(R.id.mul).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in.append("*");
            }
        });

        findViewById(R.id.div).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in.append("/");
            }
        });

        findViewById(R.id.clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in.setText("");
            }
        });

        findViewById(R.id.del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = in.getText().toString();
                in.setText(str.substring(0,str.length()-1));
            }
        });
    }
}


class Calculator {
    static Double eval(String str) {
        Calculator cal = new Calculator();
        return cal.evalPolish(cal.getPolish(new Stream(cal.mulsym(str))));
    }

    Double evalPolish(ArrayList<Data> tokens) {
        Stack<Double> stk = new Stack<>();
        for (Data data: tokens) {
            if(data.num == null && data.sym == null) {
                return null; //throw exception
            }
            else if(data.sym == null) {
                stk.push(data.num);
            }
            else if(stk.size() >= 2) {
                stk.push(calculate(data.sym, stk.pop(), stk.pop()));
            }
        }
        return stk.pop();
    }

    ArrayList<Data> getPolish(Stream in) {
        Stack<Character> stk = new Stack<>();
        ArrayList<Data> ans = new ArrayList<>();
        while(in.hasNext()) {
            Data data = new Data();
            data.num = in.nextDouble();
            if(data.num == null) {
                Character ch = in.nextChar();
                if(ch == '(') {
                    ans.add(new Data(evalPolish(getPolish(in)),null));
                    if(!stk.empty() && (stk.peek() == '*' || stk.peek() == '/')) {
                        ans.add(new Data(null,stk.pop()));
                    }
                }
                else if(ch == ')') {
                    break;
                }
                else if(in.hasNext()) {
                    stk.push(ch);
                }
            }
            else {
                ans.add(data);
                if(!stk.empty() && (stk.peek() == '*' || stk.peek() == '/')) {
                    ans.add(new Data(null,stk.pop()));
                }
                stk.push('+');
            }
        }

        while(!stk.empty()) {
            ans.add(new Data(null,stk.pop()));
        }
        return ans;
    }

    Double calculate(Character sym, Double a, Double b) {
        if(a == null || b == null || sym == null) return null;
        switch(sym) {
            case '+': return b+a;
            case '-': return b-a;
            case '*': return b*a;
            case '/': return b/a;
        }
        return null; //throw exception
    }

    String mulsym(String str) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            ans.append(str.charAt(i));
            if(i < str.length()-1) {
                Character curr = str.charAt(i);
                Character next = str.charAt(i+1);
                if((curr == ')' || isNumber(curr)) && next == '(') {
                    ans.append('*');
                }
            }
        }
        return ans.toString();
    }

    public static boolean isNumber(Character ch) {
        return ch >= '0' && ch <= '9';
    }
}

class Data {
    public Double num;
    public Character sym;

    public Data() {
        this(null,null);
    }

    public Data(Double num, Character sym) {
        this.num = num;
        this.sym = sym;
    }
}

class Stream {
    private char[] buff;
    private int index;

    public Stream(String str) {
        this.buff = str.toCharArray();
        this.index = 0;
    }

    public boolean hasNext() {
        return index < buff.length;
    }

    public Character nextChar() {
        return hasNext() ? buff[index++] : null;
    }

    public Integer nextInt() {
        Character sign = null;
        if(buff[index] == '-' || buff[index] == '+') {
            sign = buff[index++];
        }

        Integer ans = null;
        while(hasNext() &&  Calculator.isNumber(buff[index])) {
            Integer curr = buff[index++]-'0';
            ans = (ans == null) ?  curr : ans*10 + curr;
        }

        if(ans == null && sign != null) {
            index--;
        }
        else if(sign != null && sign == '-') {
            ans= -ans;
        }
        return ans;
    }

    public Double nextDouble() {
        Integer num = nextInt();
        if(num == null) return null;
        Double ans = (double)num;
        if(hasNext() && buff[index] == '.') {
            index++;
            num = 10;
            while(hasNext() && Calculator.isNumber(buff[index])) {
                Double curr = (double)(buff[index]-'0')/num;
                ans = (ans == null) ?  curr : ans + curr;
                num*=10;
            }
        }
        return ans;
    }
}

