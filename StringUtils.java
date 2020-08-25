package com;

import java.util.ArrayList;

public class StringUtils {


    public static ArrayList<Character> findDuplicates(String str){

        int[] hashTable=new int[26];
        ArrayList<Character> duplicates=new ArrayList<>();

        for(int i=0;i<str.length();i++){
            char character=str.charAt(i);
            int index=character-'a';
            hashTable[index]++;
        }

        for(int i=0;i<hashTable.length;i++){
            if(hashTable[i]>1)
                duplicates.add((char)(i+97));
        }

        return duplicates;
    }

    public static boolean isAnagram(String str1,String str2){
        if(str1.length()!=str2.length())return false;
        int[] hashTable=new int[26];

        for(int i=0;i<str1.length();i++){
            char character=str1.charAt(i);
            int index=character-'a';
            hashTable[index]++;
        }
        for(int i=0;i<str2.length();i++){
            char character=str2.charAt(i);
            int index=character-'a';
            hashTable[index]--;
            if(hashTable[index]<0)
                return false;
        }
        return true;
    }
    public static void findPermutations(String str,char[] perm,boolean[] characterIsUsed,int k){
        if(k==str.length()) {
            System.out.println(perm);
        }
        else {
            for (int i = 0; i < str.length(); i++) {
                if(!characterIsUsed[i]) {
                    perm[k] = str.charAt(i);
                    characterIsUsed[i]=true;
                    findPermutations(str, perm, characterIsUsed, k + 1);
                    characterIsUsed[i]=false;
                }
            }
        }
    }
}
