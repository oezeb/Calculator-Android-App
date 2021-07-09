package com.oezeb.calculator.utils;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Calculator {
    public static Number eval(String string) throws Exception {
        Queue<Elem> queue = reversePolish(string);
        Stack<Number> stk = new Stack<>();
        while (!queue.isEmpty()) {
            Elem elem = queue.poll();
            if (elem.type == ElemType.num) {
                stk.push(parseNumber(elem.value));
            } else if (stk.size() >= 2) {
                Number num2 = stk.pop();
                Number num1 = stk.pop();
                stk.push(calculate(num1, num2, elem.value.charAt(0)));
            } else {
                throw new Exception();
            }
        }

        if (stk.size() != 1) throw new Exception();

        return stk.pop();
    }

    public static Queue<Elem> reversePolish(String string) {
        return reversePolish(new Stream(string));
    }

    private static Queue<Elem> reversePolish(Stream stream) {
        Stack<Elem> stk = new Stack<>();
        Queue<Elem> queue = new LinkedList<>();

        while (stream.hasNext()) {
            Elem elem = stream.next();
            if (elem.value.equals("%")) {
                queue.add(new Elem(ElemType.num, "100"));
                queue.add(new Elem(ElemType.op, "÷"));
            } else if (elem.type == ElemType.num) {
                queue.add(elem);

                if (!stk.empty()) {
                    char sym = stk.peek().value.charAt(0);
                    if (Elem.isMul(sym) || Elem.isDiv(sym))
                        queue.add(stk.pop());
                }
            } else {
                stk.push(elem);
            }
        }

        while (!stk.empty()) queue.add(stk.pop());

        return queue;
    }

    public static Number calculate(Number a, Number b, char sym)  {
        if(Elem.isAdd(sym)) {
            if (a instanceof Integer && b instanceof Integer)
                return a.intValue() + b.intValue();
            else
                return a.doubleValue() + b.doubleValue();
        }
        else if(Elem.isMul(sym)) {
            if (a instanceof Integer && b instanceof Integer)
                return a.intValue() * b.intValue();
            else
                return a.doubleValue() * b.doubleValue();
        }
        else if(Elem.isDiv(sym)) {
            return a.doubleValue() / b.doubleValue();
        }
        else {
            throw new IllegalArgumentException("Unknown symbol");
        }
    }

    public static Number parseNumber(String string) {
        if(string.contains(".")) return Double.parseDouble(string);
        else return  Integer.parseInt(string);
    }
}

