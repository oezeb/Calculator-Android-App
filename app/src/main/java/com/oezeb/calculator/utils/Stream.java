package com.oezeb.calculator.utils;

import java.util.Stack;

public class Stream {
    private char[] buffer;
    private int index;
    private Stack<Elem> stack;

    public Stream() {
        this("");
    }

    public Stream(String buffer) {
        this.buffer = buffer.toCharArray();
        this.index=0;
        this.stack = new Stack<>();
    }

    public boolean hasNext() { return  index < buffer.length || !stack.empty(); }

    public Elem next() {
        if (!hasNext()) throw new RuntimeException("End Of Stream");

        if (!stack.empty()) return stack.pop();

        if (Elem.isSub(buffer[index])) {
            // is -
            if (index > 0) {
                char ch = buffer[index - 1];
                if (!Elem.isMul(ch) && !Elem.isDiv(ch)) {
                    // not * or /
                    stack.push(new Elem(ElemType.num, nextNum()));
                    return new Elem(ElemType.op, "+");
                }
            }
            return new Elem(ElemType.num, nextNum());
        } else if (Elem.isOperand(buffer[index])) // is + or * or / or %
            return new Elem(ElemType.op, String.valueOf(buffer[index++]));
    else
        return new Elem(ElemType.num, nextNum());
    }

    private String  nextNum() {
        StringBuilder val = new StringBuilder();

        if (Elem.isSub(buffer[index])) {
            val.append('-');
            index++;
        }

        while (index < buffer.length) {
            if (Elem.isDigit(buffer[index]) || Elem.isDot(buffer[index]))
                val.append(buffer[index++]);
            else
                break;
        }
        return val.toString();
    }
}

