package com.BinaryTrees;

public class BinaryTree<T extends Comparable<T>> {

    protected Node<T> root;

    public BinaryTree(){
        root=null;
    }
    public int size(){
        return count(root);
    }

    private int count(Node<T> currentNode){
        int x=0,y=0;
        if(currentNode!=null){
            x=count(currentNode.getLeftChild());
            y=count(currentNode.getRightChild());
            return x+y+1;
        }
        return 0;
    }

    public int height(){
        return treeHeight(root);
    }

    private int treeHeight(Node<T> currentNode){
        int x=0,y=0;
        if(currentNode!=null){
            x=treeHeight(currentNode.getLeftChild());
            y=treeHeight(currentNode.getRightChild());
            if(x>y)
                return x+1;
            else
                return y+1;
        }
        return 0;

    }



    protected Node<T> inOrderPredecessor(Node<T> currentNode){
        while(currentNode!=null&&currentNode.getRightChild()!=null){
            currentNode=currentNode.getRightChild();
        }
        return currentNode;
    }
    protected Node<T> inOrderSuccessor(Node<T> currentNode){
        while(currentNode!=null&&currentNode.getLeftChild()!=null){
            currentNode=currentNode.getLeftChild();
        }
        return currentNode;
    }

    private Node<T> search(T data,Node<T> currentNode){
        if(currentNode==null)return null;
        while(currentNode!=null){
            if(currentNode.getData().equals(data))
                return currentNode;
            else if(currentNode.getData().compareTo(data)>0)
                currentNode=currentNode.getLeftChild();
            else
                currentNode=currentNode.getRightChild();
        }
        return null;
    }
    public void printPostOrder() {
        postOrder(root);
    }
    public void printPreOrder(){
        preOrder(root);
    }
    public void printInOrder(){
        inOrder(root);
    }
    private void preOrder(Node<T> currentNode){
        if(currentNode==null)return;
        System.out.print(currentNode.getData()+"! ");
        preOrder(currentNode.getLeftChild());
        preOrder(currentNode.getRightChild());
    }
    private void postOrder(Node<T> currentNode){
        if(currentNode==null)return ;
        postOrder(currentNode.getLeftChild());
        postOrder(currentNode.getRightChild());
        System.out.print(currentNode.getData()+"! ");
    }

    private void inOrder(Node<T> currentNode){
        if(currentNode==null)return ;
        inOrder(currentNode.getLeftChild());
        System.out.print(currentNode.getData()+"! ");
        inOrder(currentNode.getRightChild());
    }
}
