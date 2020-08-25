package com.BinaryTrees;
import java.util.ArrayList;
import java.util.Iterator;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree {

    public BinarySearchTree() { super();}

    public BinarySearchTree(T[] array){
        super();
        root= createBSTFromArray(array,0,array.length-1);
    }

    public BinarySearchTree(ArrayList<T> list){
        super();
        root=createBSTFromList(list,list.iterator(),list.size());
    }

    private Node<T> createBSTFromList(ArrayList<T> list,Iterator<T> iterator,int n) {
        //InOrder
        if(n<=0) return null;

        Node<T> left=createBSTFromList(list,iterator,n/2);

        Node<T> root=new Node<>(iterator.next());
        root.setLeftChild(left);


        Node<T> right=createBSTFromList(list,iterator,n-n/2-1);
        root.setRightChild(right);

        return root;
    }

    private Node<T> createBSTFromArray(T[] array, int start, int end){
        //Pre order
        if(start>end) return null;

        int mid=(start+end)/2;
        T middle=array[mid];

        Node<T> root=new Node<>(middle);

        root.setLeftChild(createBSTFromArray(array,start,mid-1));
        root.setRightChild(createBSTFromArray(array,mid+1,end));

        return root;
    }

    public void addKey(T data){

        insert(data,root);
    }

    private Node<T> insert(T data,Node<T> tempNode){
        if(root==null){
            root=new Node<>(data);
            return root;
        }
        if(tempNode==null){
            tempNode=new Node<>(data);
            return tempNode;
        }
        if(tempNode.getData().compareTo(data)>0)
            tempNode.setLeftChild(insert(data, tempNode.getLeftChild()));
        else if(tempNode.getData().compareTo(data)<0)
            tempNode.setRightChild(insert(data, tempNode.getRightChild()));
        return tempNode;
    }



    public void deleteKey(T data){
        delete(data,root);
    }

    private Node<T> delete(T data,Node<T> currentNode){
        if(currentNode==null)
            return null;
        if(currentNode.isLeaf()){
            if(currentNode==root)
                root=null;
            currentNode=null;
            return null;
        }
        if(currentNode.getData().compareTo(data)>0)
            currentNode.setLeftChild(delete(data, currentNode.getLeftChild()));
        else if(currentNode.getData().compareTo(data)<0)
            currentNode.setRightChild(delete(data, currentNode.getRightChild()));
        else{
            Node<T> tempNode=inOrderSuccessor(currentNode.getRightChild());
            currentNode.setData(tempNode.getData());
            currentNode.setRightChild(delete(data, currentNode.getRightChild()));
        }
        return currentNode;
    }
}
