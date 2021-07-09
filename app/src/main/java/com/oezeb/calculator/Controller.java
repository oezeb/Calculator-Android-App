package com.oezeb.calculator;

import com.oezeb.calculator.utils.Calculator;
import com.oezeb.calculator.utils.Elem;

public class Controller {
    private String mem = "";
    private String memVal = "0";

    private String op = "";
    private String ans = "";

    String getOp() { return op; }

    String getAns() { return ans; }

    String getMem() { return mem; }

    String getMemVal() {return memVal; }

    public void memOp(String string) {
        switch (string) {
            case "mc":
                memVal = "0";
                mem = "";
                break;
            case "m+":
                if (!ans.isEmpty()) {
                    try {
                        memVal = Calculator.eval(memVal + '+' + ans).toString();
                    }
                    catch (Exception exception) {
                        System.out.println(exception.toString());
                        memVal = "0";
                    }
                    mem = "M";
                } else if (!op.isEmpty()) {
                    try {
                        memVal = Calculator.eval(memVal + '+' + op).toString();
                    }
                    catch (Exception exception) {
                        System.out.println(exception.toString());
                        memVal = "0";
                    }
                    mem = "M";
                } else {
                    memVal = "0";
                    mem = "M";
                }
                break;
            case "m−":
                if (!ans.isEmpty()) {
                    try {
                        memVal = Calculator.eval(memVal + '-' + ans).toString();
                    }
                    catch (Exception exception) {
                        System.out.println(exception.toString());
                        memVal = "0";
                    }
                    mem = "M";
                } else if (!op.isEmpty()) {
                    try {
                        memVal = Calculator.eval(memVal + '-' + op).toString();
                    }
                    catch (Exception exception) {
                        System.out.println(exception.toString());
                        memVal = "0";
                    }
                    mem = "M";
                } else {
                    memVal = "0";
                    mem = "M";
                }
                break;
            case "mr":
                if (mem.equals("M")) {
                    op = memVal;
                    ans = "";
                    mem = "M";
                }
                break;
        }
    }

    public void append(char curr) {
        if (op.isEmpty()) {
            if (Elem.isDigit(curr) || Elem.isSub(curr) || Elem.isDot(curr)) {
                op = curr + op;
            }
        } else {
            char prev = op.charAt(op.length() - 1);
            if (!Elem.isPercent(prev) && Elem.isOperand(prev)) {
                if (!Elem.isPercent(curr) && Elem.isOperand(curr)) {
                    if (Elem.isSub(curr) && (Elem.isMul(prev) || Elem.isDiv(prev))) {
                        op = op + curr;
                    } else {
                        op = op.substring(0,op.length()-1) + curr;
                    }
                } else {
                    op = op + curr;
                }
            } else {
                op = op + curr;
            }
        }
        answer();
    }

    public void backspace() {
        if (op.length() > 0) {
            op = op.substring(0,op.length()-1);
        }
        answer();
    }

    public void answer() {
        if (!op.isEmpty()) {
            int start = 0;
            int end = op.length() - 1;
            if (!Elem.isPercent(op.charAt(end)) && Elem.isOperand(op.charAt(end)))
                end--;
            if (Elem.isPercent(op.charAt(0))) start++;

            String validOp = op.substring(start, end + 1);

            if (!validOp.isEmpty()) {
                try {
                    if (!Elem.isNumber(validOp))
                        ans = Calculator.eval(validOp).toString();
                } catch (Exception exception) {
                    System.out.println(exception.toString());
                    ans = "Error";
                }
            }
        }
    }

    public void showResult() {
        op = ans;
        ans = "";
    }

    public void clear() {
        op = "";
        ans = "";
    }
}
