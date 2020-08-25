package com.Trees.BinaryTrees.Heap;


public class BinaryHeap<T extends Comparable<T>> {

    private T[] heap;


    public BinaryHeap(T[] array){//MAX HEAP
        heap=array;
        makeHeap();
    }
    private void makeHeap(){
        for(int i=heap.length-1;i>=0;i--){

            if((2*i+1)>heap.length-1 && (2*i+2)>heap.length-1)
                continue;
            T current=heap[i];
            T left=null;
            T right=null;
            T bigger;

            if((2*i+1)<heap.length)
                left=heap[2*i+1];
            if((2*i+2)<heap.length)
                right=heap[2*i+2];


            if(left==null){
                bigger=right;
            }else if (right==null){
                bigger=left;
            }else{
                if(right.compareTo(left)>0){
                    bigger=right;
                }
                else{
                    bigger=left;
                }
            }


            if(bigger==left){
                heap[i]=heap[2*i+1];
                heap[2*i+1]=current;
            }else{
                heap[i]=heap[2*i+2];
                heap[2*i+2]=current;
            }
        }
    }
    public T[] heapSort(){
        for(int i=heap.length-1;i>=1;i--)
            delete(heap,i);
        return heap;
    }

    private void delete(T[] array,int index){
        int i,j;

        T deleted=array[0];

        array[0]=array[index];
        array[index]=deleted;

        i=0;
        j=1;

        while(j<=index-1){

            if(j!=index-1 && array[j+1].compareTo(array[j])>0){
                j=j+1;
            }
            if(array[i].compareTo(array[j])<0){
                T temp=array[i];
                array[i]=array[j];
                array[j]=temp;
                i=j;
                j=j*2;
            }else
                break;
        }
    }

}
