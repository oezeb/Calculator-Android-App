package com.oezeb.calculator.utils;

enum ElemType { num, op }

public class Elem {
    ElemType type;
    String value;

    public Elem() {
        this(ElemType.num, "");
    }

    public Elem(ElemType type, String value) {
        this.type = type;
        this.value = value;
    }

    public static boolean isOperand(char ch) {
        return
    isAdd(ch) || isSub(ch) || isMul(ch) || isDiv(ch) || isPercent(ch);}

    public static boolean isDigit(char ch) { return ch >= '0' && ch <= '9'; }

    public static boolean isNumber(String str) { return  str.matches("^[−|-]?\\d*\\.?\\d*"); }

    public static boolean isMul(char ch) { return ch == '×' || ch == 'x' || ch == 'X' || ch == '*'; }
    public static boolean isDiv(char ch) { return ch == '÷' || ch == '/'; }
    public static boolean isAdd(char ch) { return ch == '+'; }
    public static boolean isSub(char ch) { return ch == '−' || ch == '-'; }
    public static boolean isPercent(char ch) { return ch == '%'; }
    public static boolean isDot(char ch) {return ch == '.'; }
}

