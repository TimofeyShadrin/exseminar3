package ru.gb.exseminars.exseminarThree.exception;

public class FailedValidation extends IllegalStateException{
    public FailedValidation(String s) {
        super(s);
    }
}
