package com.Sorting;

import com.Arrays.Array;

public class SortingMethods<T extends Comparable<T>> {

    public void selectionSort(T [] A){
        int i,j,k;
        for(i=0;i<A.length-1;i++){
            for(j=k=i;j<A.length;j++){
                if(A[j].compareTo(A[k])<0)
                    k=j;
            }
            swap(A,i, k);
        }
    }

    public void bubbleSort(T [] A){
        for(int i=0;i<A.length;i++){
            for(int j=0;j<A.length-1-i;j++){
                if(A[j].compareTo(A[j+1])>0){
                    swap(A,j,j+1);
                }
            }
        }
    }

    public void insertionSort(T [] A){
        for(int i=1;i<A.length;i++){
            int j=i-1;
            T x=A[i];
            while(j>-1 && A[j].compareTo(x)>0){
                A[j+1]=A[j];
                j--;
            }
            A[j+1]=x;
        }
    }

    private void merge(T [] A,int low, int mid, int high)
    {

        int n1 = mid - low + 1;
        int n2 = high - mid;
        int i, j,k=low;
        Array<T> leftSubArray=new Array<>(n1);
        Array<T> rightSubArray=new Array<>(n2);

        for (i=0; i<n1; ++i)
            leftSubArray.set(i, A[low + i]) ;

        for (j=0; j<n2; ++j)
            rightSubArray.set(j, A[mid + 1+ j]);

        i=j=0;

        while (i < n1 && j < n2)
        {
            if (leftSubArray.get(i).compareTo(rightSubArray.get(j)) <= 0)
                A[k++] = leftSubArray.get(i++);
            else
                A[k++] = rightSubArray.get(j++);
        }
        while (i < n1)
            A[k++] = leftSubArray.get(i++) ;
        while (j < n2)
            A[k++] = rightSubArray.get(j++);
    }
    public void mergeSort(T [] A,int low, int high)
    {
        if (low < high)
        {
            int mid = (low+high)/2;
            mergeSort(A,low, mid);
            mergeSort(A,mid+1, high);
            merge(A,low, mid, high);
        }
    }
    public void quickSort(T [] A,int l,int h){
        int j;
        if(l<h){
            j=partition(A,l, h);
            quickSort(A,l, j);
            quickSort(A,j+1, h);
        }
    }

    private int partition(T [] A,int l,int h){
        int i=l,j=h;
        T pivot=A[l];
        do{

            do{i++;}while(i<A.length && A[i].compareTo(pivot)<=0);
            do{j--;}while(j>-1 && A[j].compareTo(pivot)>0);
            if(i<j)swap(A,i, j);
        }while(i<j);

        swap(A,l, j);
        return j;
    }

    private void swap(T [] A,int element1, int element2) {
        T holder=A[element2];
        A[element2]=A[element1];
        A[element1]=holder;
    }


    public void countSort(int [] A){
        int max=max(A);
        int i;
        int k=0;
        int[] C=new int[max+1];
        for(i=0;i<C.length;i++)
            C[i]=0;

        for(i=0;i<A.length;i++)
            C[A[i]]++;
        i=0;
        while(i<C.length){
            if(C[i]>0){
                A[k++]=i;
                C[i]--;
            }else{
                i++;
            }
        }

    }

    private int max(int [] A){
        int max=-1;
        for(int i=0;i<A.length;i++)
            if(A[i]>max)
                max=A[i];

        return max;
    }
}
