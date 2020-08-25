package com.Trie;

import java.util.Arrays;

public class TrieNode {
    private TrieNode[] children;
    private boolean isTerminal;

    public TrieNode() {
        children=new TrieNode[Trie.ALPHABET_SIZE];
        isTerminal=false;
    }
    public void setChild(int childIndex){
        children[childIndex]=new TrieNode();
    }
    public TrieNode getChild(int index){
        return children[index];
    }
    public boolean isTerminal() {
        return isTerminal;
    }
    public void setTerminal(boolean terminal) {
        isTerminal = terminal;
    }
}
