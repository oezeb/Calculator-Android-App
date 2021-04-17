package com.oezeb.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Stack;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText in = findViewById(R.id.in);
        TextView out = findViewById(R.id.out);

        in.setFocusable(false);

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
                in.setSelection(s.length());
                if(s.length() == 0) {
                    out.setText("");
                    return;
                }
                try {
                    BigDecimal dVal = Calculator.eval(s.toString()); // get eval(String) into dVal
                    BigDecimal iVal = BigDecimal.valueOf(dVal.intValue()); // iVal = (int)dVal
                    out.setText((dVal.subtract(iVal)).equals(BigDecimal.ZERO) ? iVal.toString() : dVal.toString()); // setTExt(dVal-iVal == 0 ? iVal : dVal)
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
                if(in.getVisibility() == View.GONE) {
                    showIn();
                }
                in.append("0");
            }
        });

        findViewById(R.id.b1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(in.getVisibility() == View.GONE) {
                    showIn();
                }
                in.append("1");
            }
        });

        findViewById(R.id.b2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(in.getVisibility() == View.GONE) {
                    showIn();
                }
                in.append("2");
            }
        });

        findViewById(R.id.b3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(in.getVisibility() == View.GONE) {
                    showIn();
                }
                in.append("3");
            }
        });

        findViewById(R.id.b4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(in.getVisibility() == View.GONE) {
                    showIn();
                }
                in.append("4");
            }
        });

        findViewById(R.id.b5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(in.getVisibility() == View.GONE) {
                    showIn();
                }
                in.append("5");
            }
        });

        findViewById(R.id.b6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(in.getVisibility() == View.GONE) {
                    showIn();
                }
                in.append("6");
            }
        });

        findViewById(R.id.b7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(in.getVisibility() == View.GONE) {
                    showIn();
                }
                in.append("7");
            }
        });

        findViewById(R.id.b8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(in.getVisibility() == View.GONE) {
                    showIn();
                }
                in.append("8");
            }
        });

        findViewById(R.id.b9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(in.getVisibility() == View.GONE) {
                    showIn();
                }
                in.append("9");
            }
        });

        findViewById(R.id.open_brace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(in.getVisibility() == View.GONE) {
                    showIn();
                }
                in.append("(");
            }
        });

        findViewById(R.id.close_brace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(in.getVisibility() == View.GONE) {
                    showIn();
                }
                in.append(")");
            }
        });

        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(in.getVisibility() == View.GONE) {
                    showIn();
                }
                in.append("+");
            }
        });

        findViewById(R.id.sub).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(in.getVisibility() == View.GONE) {
                    showIn();
                }
                in.append("-");
            }
        });

        findViewById(R.id.mul).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(in.getVisibility() == View.GONE) {
                    showIn();
                }
                in.append("x");
            }
        });

        findViewById(R.id.div).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(in.getVisibility() == View.GONE) {
                    showIn();
                }
                in.append("÷");
            }
        });

        findViewById(R.id.clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(in.getVisibility() == View.GONE) {
                    showIn();
                }
                in.setText("");
            }
        });

        findViewById(R.id.del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(in.getVisibility() == View.GONE) {
                    showIn();
                }
                String str = in.getText().toString();
                in.setText(str.substring(0,str.length()-1));
            }
        });

        findViewById(R.id.dot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(in.getVisibility() == View.GONE) {
                    showIn();
                }
                in.append(".");
            }
        });

        findViewById(R.id.equal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideIn();
            }
        });

        findViewById(R.id.percentage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(in.getVisibility() == View.GONE) {
                    showIn();
                }
                in.append("%");
            }
        });
    }

    void hideIn() {
        findViewById(R.id.in).setVisibility(View.GONE);
        TextView out = findViewById(R.id.out);
        out.setTextSize(TypedValue.COMPLEX_UNIT_SP,40);
    }

    void showIn() {
        EditText in = findViewById(R.id.in);
        in.setVisibility(View.VISIBLE);
        in.setText("");
        TextView out = findViewById(R.id.out);
        out.setTextSize(TypedValue.COMPLEX_UNIT_SP,24);
    }
}

class Calculator {
    static BigDecimal eval(String str) throws Exception {
        Calculator cal = new Calculator();
        try {
            return cal.evalPolish(cal.getPolish(new Stream(cal.mulSym(str))));
        }
        catch (Exception ex) {
            throw ex;
        }
    }

    BigDecimal evalPolish(ArrayList<Data> tokens) throws Exception {
        // evaluate given Polish expression
        // Data = { number, Symbol }
        Stack<BigDecimal> stk = new Stack<>();
        for (Data data: tokens) {
            if(data.isValid()) {
                if(data.num != null) {
                    stk.push(data.num);
                }
                else if(stk.size() >= 2) {
                    stk.push(calculate(data.sym, stk.pop(), stk.pop()));
                }
            }
            else {
                throw new Exception("bad tokens");
            }
        }
        return stk.pop();
    }

    ArrayList<Data> getPolish(Stream in) throws Exception {
        Stack<Character> stk = new Stack<>(); //store symbols
        ArrayList<Data> ans = new ArrayList<>();
        while(in.hasNext()) {
            Data data = new Data();
            data.num = in.nextBigDecimal();
            if(data.num == null) {
                // get symbol
                Character ch = in.nextChar();
                if(ch == '(') {
                    // evaluate expression inside the braces
                    try {
                        ans.add(new Data(evalPolish(getPolish(in)), null));
                    }
                    catch (Exception ex) {
                        throw ex;
                    }
                    // '*' and '/' priority
                    if(!stk.empty() && (stk.peek() == '*' || stk.peek() == 'x' || stk.peek() == '/' || stk.peek() == '÷')) {
                        ans.add(new Data(null,stk.pop()));
                    }
                }
                else if(ch == ')') {
                    break;
                }
                else if(ch == '%') {
                    Data last = ans.get(ans.size()-1);
                    if(last.sym != null || last.num == null) {
                        throw new Exception("Error near %");
                    }
                    BigDecimal HUNDRED = (BigDecimal.TEN).multiply(BigDecimal.TEN);
                    last.num.divide(HUNDRED);
                }
                else if(in.hasNext()) { //push symbol into stack
                    stk.push(ch);
                }
            }
            else {  // data != null
                ans.add(data);
                // '*' and '/' priority
                if(!stk.empty() && (stk.peek() == '*' || stk.peek() == 'x' || stk.peek() == '/' || stk.peek() == '÷')) {
                    ans.add(new Data(null,stk.pop()));
                }
                // + and - symbol are consumed by nextBigDecimal
                // so we add + symbol between them
                // a-b --> (a) + (-b)
                stk.push('+');
            }
        }

        while(!stk.empty()) { // append remaining symbol
            ans.add(new Data(null,stk.pop()));
        }
        return ans;
    }

    BigDecimal calculate(Character sym, BigDecimal a, BigDecimal b) {
        if(a == null || b == null || sym == null) return null;
        switch(sym) {
            case '+': return b.add(a);      // b+a
            case '-': return b.subtract(a); // b-a
            case '*':
            case 'x': return b.multiply(a); // b*a
            case '/':
            case '÷': return b.divide(a);   // b/a
        }
        return null; //throw exception
    }

    String mulSym(String str) {
        // add * symbol
        // a(b+c)     --> a*(a+b)
        // (a+b)(b+c) --> (a+b)*(b+c)
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            ans.append(str.charAt(i));
            if(i < str.length()-1) {
                Character curr = str.charAt(i);
                Character next = str.charAt(i+1);
                if((curr == ')' || isDigit(curr)) && next == '(') {
                    ans.append('*');
                }
            }
        }
        return ans.toString();
    }

    public static boolean isDigit(Character ch) {
        return ch >= '0' && ch <= '9';
    }
}

class Data {
    // Use Data to make expression tokens
    // expression:  (     1     +     2     )     *     3
    // number    : null   1    null   2    null  null   3
    // symbol    :  (    null   +    null   )     *    null
    public BigDecimal num; //number
    public Character sym; //symbol

    public Data() {
        this(null,null);
    }

    public Data(BigDecimal num, Character sym) {
        this.num = num;
        this.sym = sym;
    }

    public boolean isValid() {
        return (num == null && sym != null) || (num != null && sym == null);
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
        // check if the number carry a sign (+a, -a)
        Character sign = null;
        if(buff[index] == '-' || buff[index] == '+') {
            sign = buff[index++];
        }

        // get number absolute part
        Integer ans = null;
        while(hasNext() &&  Calculator.isDigit(buff[index])) {
            Integer curr = buff[index++]-'0';
            ans = (ans == null) ?  curr : ans*10 + curr;
        }


        if(ans == null && sign != null) {
            // number has no absolute part so put the sign back in the buff
            index--;
        }
        else if(sign != null && sign == '-') {
            ans= -ans;
        }
        return ans;
    }

    public Double nextDouble() {
        // get integer part
        Integer iVal = nextInt();
        if(iVal == null) return null;

        // get decimal part
        Double dVal = iVal.doubleValue();
        if(hasNext() && buff[index] == '.') {
            index++;
            int num = 10;
            while(hasNext() && Calculator.isDigit(buff[index])) {
                Double curr = (double)(buff[index++]-'0')/num;
                dVal = (dVal == null) ?  curr : dVal + curr;
                num*=10;
            }
        }
        return dVal;
    }

    public BigInteger nextBigInt() {
        // check if the number carry a sign (+a, -a)
        Character sign = null;
        if(buff[index] == '-' || buff[index] == '+') {
            sign = buff[index++];
        }

        // get number absolute part
        BigInteger ans = null;
        while(hasNext() &&  Calculator.isDigit(buff[index])) {
            BigInteger curr = BigInteger.valueOf(buff[index++]-'0');
            ans = (ans == null) ?  curr : (ans.multiply(BigInteger.TEN)).add(curr);
        }

        if(ans == null && sign != null) {
            // number has no absolute part so put the sign back in the buff
            index--;
        }
        else if(sign != null && sign == '-') {
            ans= ans.negate();
        }
        return ans;
    }

    public BigDecimal nextBigDecimal() {
        // get integer part
        BigInteger iVal = nextBigInt();
        if(iVal == null) {
            if(buff[index] == '.') {
                iVal = BigInteger.valueOf(0);
            }
            else {
                return null;
            }
        }

        // get decimal part
        BigDecimal ans = new BigDecimal(iVal);
        if(hasNext() && buff[index] == '.') {
            index++;
            BigDecimal num = BigDecimal.TEN; // num = 10
            while(hasNext() && Calculator.isDigit(buff[index])) {
                BigDecimal curr = new BigDecimal(buff[index++]-'0');
                curr = curr.divide(num); // curr/=num
                ans = (ans == null) ?  curr : ans.add(curr); // ans = ans==null ? curr : ans+curr
                num.multiply(BigDecimal.TEN); //num*=10
            }
        }
        return ans;
    }
}
