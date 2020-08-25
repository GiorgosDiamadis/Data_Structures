package com.Arrays;

public class ArrayUtils {

    public static void main(String[] args) {
        int[] array={10,20,15,30,40,28};
        reOrder(array);
        for(int i=0;i<array.length;i++)
            System.out.println(array[i]);
    }
    public static void cyclicRotateByOne(int[] array){
        int last=array[array.length-1];
        for(int i=array.length-2;i>=0;i--){
            array[i+1]=array[i];
        }
        array[0]=last;
    }

    public static int peakElement(int[] array){


        return 0;
    }

    public static void moveAllZeroesToEnd(int[] array){
        int count=0;
        for (int i = 0; i < array.length; i++) {
            if(array[i]!=0){
                array[count]=array[i];
                count++;
            }
        }
        while (count<array.length) {
            array[count] = 0;
            count++;
        }
    }

    public static void mulPrevNext(int[] array){
        int previous=0;
        int temp=0;
        for(int i=0;i<array.length;i++){

            if(i==0){
                previous=array[i];
                array[i]=array[i]*array[i+1];
            }else if(i==array.length-1){
                array[i]=array[i]*previous;
            }else{
                temp=array[i];
                array[i]=previous*array[i+1];
                previous=temp;
            }

        }
    }

    public static void reOrder(int[] array){    //Positive=>even position, Negative=>odd position
        int odd=1;
        for(int i=0;i<array.length;i++){
            if(i%2==0){                //even position
                if(array[i]<0){
                    while (odd<array.length-2 && array[odd]<0)
                        odd+=2;
                    int temp=array[odd];
                    array[odd]=array[i];
                    array[i]=temp;
                }
            }
        }
    }

}
