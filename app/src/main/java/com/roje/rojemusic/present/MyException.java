package com.roje.rojemusic.present;


public class MyException extends Exception {
    private int code;
    public MyException(int code,String message){
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
