package com.GenericArray;

public class GenericArray<T> {
    private final Object[] arr;
    public final int length;

    public GenericArray(int length)
    {
        arr = new Object[length];
        this.length = length;
    }

    public T get(int i) {
        final T data = (T)arr[i];
        return data;
    }
    public void set(int i, T data) {
        arr[i] = data;
    }

}