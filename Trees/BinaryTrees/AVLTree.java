package com.Trees.BinaryTrees;

public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T>{

    public AVLTree(){
        super();
    }

    @Override
    public void addKey(T data){
        insert(data,root);
    }

    private TreeNode<T> insert(T data, TreeNode<T> tempNode){
        if(root==null){
            root=new TreeNode<>(data);
            return root;
        }
        if(tempNode==null){
            tempNode=new TreeNode<>(data);
            return tempNode;
        }
        if(tempNode.data.compareTo(data)>0)
            tempNode.leftChild=insert(data, tempNode.leftChild);
        else if(tempNode.data.compareTo(data)<0)
            tempNode.rightChild=insert(data, tempNode.rightChild);

        tempNode.height=nodeHeight(tempNode);

        if(balanceFactor(tempNode)==2 && balanceFactor(tempNode.leftChild)==1){
            return LLRotation(tempNode);
        }else if(balanceFactor(tempNode)==2 && balanceFactor(tempNode.leftChild)==-1){
            return LRRotation(tempNode);
        }else if(balanceFactor(tempNode)==-2 && balanceFactor(tempNode.rightChild)==-1){
            return RRRotation(tempNode);
        }else if(balanceFactor(tempNode)==-2 && balanceFactor(tempNode.rightChild)==1){
            return RLRotation(tempNode);
        }

        return tempNode;
    }
    @Override
    public void deleteKey(T data){
        delete(data,root);
    }

    private TreeNode<T> delete(T data, TreeNode<T> currentNode){
        if(currentNode==null)
            return null;
        if(currentNode.isLeaf()){
            if(currentNode==root)
                root=null;
            currentNode=null;
            return null;
        }
        if(currentNode.data.compareTo(data)>0)
            currentNode.leftChild=delete(data, currentNode.leftChild);
        else if(currentNode.data.compareTo(data)<0)
            currentNode.rightChild=delete(data, currentNode.rightChild);
        else{
            TreeNode<T> tempNode=inOrderSuccessor(currentNode.rightChild);
            currentNode.data=tempNode.data;
            currentNode.rightChild=delete(data, currentNode.rightChild);
        }

        currentNode.height=nodeHeight(currentNode);

        if(balanceFactor(currentNode)==2 && balanceFactor(currentNode.leftChild)==1)
            return LLRotation(currentNode);
        else if(balanceFactor(currentNode)==2 && balanceFactor(currentNode.leftChild)==-1)
            return LRRotation(currentNode);
        else if(balanceFactor(currentNode)==2 && balanceFactor(currentNode.leftChild)==0)
            return LLRotation(currentNode);
        else if(balanceFactor(currentNode)==-2 && balanceFactor(currentNode.leftChild)==1)
            return RRRotation(currentNode);
        else if(balanceFactor(currentNode)==-2 && balanceFactor(currentNode.leftChild)==-1)
            return RLRotation(currentNode);
        else if(balanceFactor(currentNode)==-2 && balanceFactor(currentNode.leftChild)==0)
            return RRRotation(currentNode);

        return currentNode;
    }
    private TreeNode<T> LLRotation(TreeNode<T> tempNode){
        TreeNode<T> tempL=tempNode.leftChild;
        TreeNode<T> tempLR=tempL.rightChild;

        tempL.rightChild=tempNode;
        tempNode.leftChild=tempLR;
        tempNode.height=nodeHeight(tempNode);
        tempL.height=nodeHeight(tempL);

        if(root==tempNode)
            root=tempL;
        return tempL;
    }

    private TreeNode<T> LRRotation(TreeNode<T> tempNode){
        TreeNode<T> tempL=tempNode.leftChild;
        TreeNode<T> tempLR=tempL.rightChild;

        tempL.rightChild=tempLR.leftChild;
        tempNode.leftChild=tempLR.rightChild;
        tempLR.leftChild=tempL;
        tempLR.rightChild=tempNode;

        tempL.height=nodeHeight(tempL);
        tempNode.height=nodeHeight(tempNode);
        tempLR.height=nodeHeight(tempLR);

        if(tempNode==root)
            root=tempLR;
        return tempLR;
    }
    private TreeNode<T> RRRotation(TreeNode<T> tempNode){
        TreeNode<T> tempR=tempNode.rightChild;
        TreeNode<T> tempRL=tempR.leftChild;

        tempR.leftChild=tempNode;
        tempNode.rightChild=tempRL;
        tempNode.height=nodeHeight(tempNode);
        tempR.height=nodeHeight(tempR);

        if(root==tempNode)
            root=tempR;
        return tempR;
    }
    private TreeNode<T> RLRotation(TreeNode<T> tempNode){
        TreeNode<T> tempR=tempNode.rightChild;
        TreeNode<T> tempRL=tempR.leftChild;

        tempR.leftChild=tempRL.rightChild;
        tempNode.rightChild=tempRL.leftChild;
        tempRL.rightChild=tempR;
        tempRL.leftChild=tempNode;

        tempR.height=nodeHeight(tempR);
        tempNode.height=nodeHeight(tempNode);
        tempRL.height=nodeHeight(tempRL);

        if(tempNode==root)
            root=tempRL;
        return tempRL;
    }




    private int nodeHeight(TreeNode<T> currentNode){
        int hl=0,hr=0;
        hl=currentNode!=null && currentNode.leftChild!=null?currentNode.leftChild.height:0;
        hr=currentNode!=null && currentNode.rightChild!=null?currentNode.rightChild.height:0;
        return hl>hr?hl+1:hr+1;
    }
    private int balanceFactor(TreeNode<T> currentNode){
        int hl=0,hr=0;
        hl=currentNode!=null && currentNode.leftChild!=null?currentNode.leftChild.height:0;
        hr=currentNode!=null && currentNode.rightChild!=null?currentNode.rightChild.height:0;
        return hl-hr;
    }

}
