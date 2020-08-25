package com.Trie;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Trie {
    public static final int ALPHABET_SIZE=26;
    private TrieNode root;
    public Trie(){root=new TrieNode();}
    public Trie(File dictionary) {
        root=new TrieNode();
        try {
            Scanner scanner = new Scanner(dictionary);
            while(scanner.hasNext()) {
                String word=scanner.nextLine();
                add(word);
            }
            scanner.close();
        }catch (FileNotFoundException e){
            System.out.println("File not found!");
            return;
        }
    }
    public boolean add(String word){
        if(contains(word)) return false;
        TrieNode current=root;

        for(int i=0;i<word.length();i++){
            char character=word.charAt(i);
            int index=character-'a';

            if(current.getChild(index)==null)
                current.setChild(index);

            current=current.getChild(index);
        }
        current.setTerminal(true);
        return true;
    }

    public boolean contains(String word){
        TrieNode current=root;
        for(int i=0;i<word.length();i++){
            char character=word.charAt(i);
            int index=character-'a';

            if(current.getChild(index)!=null)
                current=current.getChild(index);
            else
                return false;
        }

        if(current.isTerminal())
            return true;
        else
            return false;
    }

    public int size(){
        return sizeRec(root);
    }

    private int sizeRec(TrieNode root){
        int count=0;
        if(root.isTerminal())
            count++;
        for(int i=0;i<ALPHABET_SIZE;i++) {
            if (root.getChild(i) != null)
                count += sizeRec(root.getChild(i));
        }
        return count;
    }

    public String[] wordsOfPrefix(String prefix){
        ArrayList<String> listOfWords=new ArrayList<>();
        TrieNode current=root;

        for(int i=0;i<prefix.length();i++){
            char character=prefix.charAt(i);
            int index=character-'a';
            current=current.getChild(index);
        }
        wordsOfPrefixRec(current,"",listOfWords,prefix);
        String[] array=listOfWords.toArray(String[]::new);
        return array;
    }

    private void wordsOfPrefixRec(TrieNode start, String word, ArrayList<String> list, String prefix){

        if(start.isTerminal()) {
            list.add(prefix + word);
        }
        for(int i=0;i<ALPHABET_SIZE;i++){
            if(start.getChild(i)!=null){
                char character=(char)(i+'a');
                word+=character;
                wordsOfPrefixRec(start.getChild(i),word,list,prefix);
                word=word.substring(0,word.length()-1);
            }
        }
    }

    public String[] differByOne(String word){
        Set<String> prefixedWords=new HashSet<>();
        ArrayList<String> wordsThatDifferByOne=new ArrayList<>();

        for(int i=0;i<word.length();i++){
            String[] words=wordsOfPrefix(word.substring(0,i));
            for(String s : words) {
                if (!s.equals(word)) {
                    prefixedWords.add(s);
                }
            }
        }
        for(String s : prefixedWords){
            int numberOfDifferentCharacters=0;

            if(s.length()!=word.length())continue;

            for(int i=0;i<s.length();i++){
                if(s.charAt(i)!=word.charAt(i)){
                        numberOfDifferentCharacters++;
                }
            }
            if(numberOfDifferentCharacters==1){
                wordsThatDifferByOne.add(s);
            }
        }
        return wordsThatDifferByOne.toArray(String[]::new);
    }

    public String[] differBy(String word,int max){
        Set<String> list=new HashSet<>();
        return differByRec(word,max,list);
    }

    public String[] differByRec(String word,int max,Set<String> list){

        String[] differedByOne = differByOne(word);
        max=max-1;
        for (String s : differedByOne) {
            if(max>0) {
                differByRec(s, max, list);
            }
        }
        for(String str : differedByOne) {
            list.add(str);
        }
        return list.toArray(String[]::new);
    }

    public String[] possibleWordsThatCanBeCreatedFrom(char[] characterArray){

        ArrayList<String> possibleWords=new ArrayList<>();
        for(int i=0;i<characterArray.length;i++){
            int index=characterArray[i] - 'a';
            if(root.getChild(index)!=null){
                String[] prefixedWords=wordsOfPrefix((char)(index+'a')+"");
                for (String word : prefixedWords) {
                    int j;
                    for(j=0;j<word.length();j++){
                        char character=word.charAt(j);
                        if(!characterExistsIn(characterArray,character))
                            break;
                    }
                    if(j==word.length())
                        possibleWords.add(word);
                }
            }
        }
        return possibleWords.toArray(String[]::new);
    }

    private boolean characterExistsIn(char[] characterArray,char character) {
        for(int i=0;i<characterArray.length;i++){
            if(characterArray[i]==character){
                return true;
            }
        }
        return false;
    }

    public void isPossibleToCreateUsingExistingWords(String word){
        class Pair{
            String left;
            String right;

            Pair(String left,String right){
                this.left=left;
                this.right=right;
            }

            @Override
            public int hashCode() {
                if(left.equals(right))
                    return 1;
                else
                    return super.hashCode();
            }

            @Override
            public boolean equals(Object obj) {
                Pair otherPair=(Pair)obj;
                return this.hashCode()==otherPair.hashCode();
            }

            @Override
            public String toString() {
                return "{"+left+","+right+"}";
            }
        }

        ArrayList<Pair> pairs=new ArrayList<>();

        for(int i=1;i<word.length();i++){
            String str1=word.substring(0,i);
            String str2=word.substring(i);
            if(contains(str1) && contains(str2)){
                Pair pair=new Pair(str1,str2);
                if(!pairs.contains(pair))
                    pairs.add(pair);
            }

        }
        if(pairs.isEmpty())
            System.out.println("No it is not!");
        else {
            System.out.println("Yes using : ");
            for(Pair p : pairs)
                System.out.println(p);
        }
    }
}