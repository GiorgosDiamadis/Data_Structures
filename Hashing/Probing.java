package com.Hashing;

import com.Arrays.Array;

public class Probing<T extends Comparable<T>> {
    private final Array<T> hashTable;
    private final int size;

    public Probing(int size){
        hashTable=new Array<>(2*size);
        this.size=2*size;
    }

    public void insert(T data){
        int index=hashFunction(data);
        if(hashTable.get(index)!=null)
            index=probe(data);
        hashTable.set(index, data);
    }
    public int search(T data){
        int index=hashFunction(data);
        int i=0;

        while(hashTable.get((index+i)%size)!=data && hashTable.get((index+i)%size)!=null){
            i++;
        }

        if(hashTable.get((index+i)%size)==null)return -1;

        return (index+i)%size;
    }
    private int hashFunction(T data){
        int index=Math.abs(data.hashCode())%size;
        return index;
    }

    private int probe(T data){
        int index=Math.abs(data.hashCode())%size;
        int i=0;
        while(hashTable.get((index+i)%size)!=null){
            i++;
        }
        return (index+i)%size;
    }

    public T get(int index){return hashTable.get(index);}
}
