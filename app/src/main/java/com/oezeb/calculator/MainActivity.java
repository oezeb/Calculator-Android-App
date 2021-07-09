package com.oezeb.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.oezeb.calculator.utils.Elem;

public class MainActivity extends AppCompatActivity {

    void onClick(View v, Controller controller, EditText in, EditText out, TextView mem) {
        String value = ((TextView)v).getText().toString();
        char sym = value.charAt(0);

        if(Elem.isDigit(sym) || Elem.isDot(sym) || Elem.isOperand(sym)) {
            controller.append(sym);
        }
        else if(sym == 'C'){
            controller.clear();
        }
        else if(value.equals("DEL")) {
            controller.backspace();
        }
        else if(sym == '=') {
            controller.showResult();
        }
        else {
            controller.memOp(value);
        }

        in.setText(controller.getOp());
        in.setSelection(in.length());
        out.setText(controller.getAns());
        mem.setText(controller.getMem());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get UI elements
        EditText in = findViewById(R.id.in);
        EditText out = findViewById(R.id.out);
        TextView mem = findViewById(R.id.mem);

        TextView[] buttons = {
                findViewById(R.id.b0),
                findViewById(R.id.b1),
                findViewById(R.id.b2),
                findViewById(R.id.b3),
                findViewById(R.id.b4),
                findViewById(R.id.b5),
                findViewById(R.id.b6),
                findViewById(R.id.b7),
                findViewById(R.id.b8),
                findViewById(R.id.b9),
                findViewById(R.id.add),
                findViewById(R.id.sub),
                findViewById(R.id.mul),
                findViewById(R.id.div),
                findViewById(R.id.percent),
                findViewById(R.id.C),
                findViewById(R.id.del),
                findViewById(R.id.equal),
                findViewById(R.id.mc),
                findViewById(R.id.m_plus),
                findViewById(R.id.m_sub),
                findViewById(R.id.mr),
        };

        Controller controller = new Controller();

        for (TextView textView : buttons) {
            textView.setOnClickListener( v -> onClick(v, controller, in, out, mem));
        }
    }

}


        //Disable default keyboard
//        in.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });