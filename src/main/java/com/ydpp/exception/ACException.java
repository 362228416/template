package com.ydpp.exception;

/**
 * 方法访问异常
 * Created by 16 on 2015/4/20.
 */
public class ACException extends RuntimeException {

    private int code;

    public ACException(String msg) {
        this(0, msg);
    }

    public ACException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "ACException{" +
                "code=" + code +
                '}';
    }
}
