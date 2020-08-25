package com.Queue;

import com.Arrays.Array;
import java.util.HashMap;
import java.util.Map;

public class Priority<T> {

    private Array<Queue<T>> queues;
    private Map<T,Integer> tasks;

    public Priority(int size){
        queues=new Array(size);
        tasks=new HashMap<>();

        for(int i=0;i<size;i++){
            queues.set(i,new Queue<>());
        }
    }

    public void enqueue(Map.Entry<T,Integer> entry){
        tasks.put(entry.getKey(),entry.getValue());

        Queue<T> queue=queues.get(entry.getValue());
        T task=entry.getKey();

        queue.enqueue(task);
    }

    public T dequeue(){

        for(int i=0;i<queues.length();i++){
            if(!queues.get(i).isEmpty())
                return queues.get(i).dequeue();
        }

        return null;
    }
}
