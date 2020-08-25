package com.Arrays;

public class Array<T> {
    private final Object[] arr;
    private final int length;

    public Array(int length){
        arr = new Object[length];
        this.length = length;
    }
    public int length(){
        return length;
    }
    public T get(int i) {
        final T data = (T)arr[i];
        return data;
    }
    public void set(int i, T data) {
        arr[i] = data;
    }

}