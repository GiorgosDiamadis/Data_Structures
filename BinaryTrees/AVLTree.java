package com.BinaryTrees;

public class AVLTree<T extends Comparable<T>> extends BinaryTree<T>{

    public AVLTree(){
        super();
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

        tempNode.setHeight(nodeHeight(tempNode));

        if(balanceFactor(tempNode)==2 && balanceFactor(tempNode.getLeftChild())==1){
            return LLRotation(tempNode);
        }else if(balanceFactor(tempNode)==2 && balanceFactor(tempNode.getLeftChild())==-1){
            return LRRotation(tempNode);
        }else if(balanceFactor(tempNode)==-2 && balanceFactor(tempNode.getRightChild())==-1){
            return RRRotation(tempNode);
        }else if(balanceFactor(tempNode)==-2 && balanceFactor(tempNode.getRightChild())==1){
            return RLRotation(tempNode);
        }

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

        currentNode.setHeight(nodeHeight(currentNode));

        if(balanceFactor(currentNode)==2 && balanceFactor(currentNode.getLeftChild())==1)
            return LLRotation(currentNode);
        else if(balanceFactor(currentNode)==2 && balanceFactor(currentNode.getLeftChild())==-1)
            return LRRotation(currentNode);
        else if(balanceFactor(currentNode)==2 && balanceFactor(currentNode.getLeftChild())==0)
            return LLRotation(currentNode);
        else if(balanceFactor(currentNode)==-2 && balanceFactor(currentNode.getLeftChild())==1)
            return RRRotation(currentNode);
        else if(balanceFactor(currentNode)==-2 && balanceFactor(currentNode.getLeftChild())==-1)
            return RLRotation(currentNode);
        else if(balanceFactor(currentNode)==-2 && balanceFactor(currentNode.getLeftChild())==0)
            return RRRotation(currentNode);

        return currentNode;
    }
    private Node<T> LLRotation(Node<T> tempNode){
        Node<T> tempL=tempNode.getLeftChild();
        Node<T> tempLR=tempL.getRightChild();

        tempL.setRightChild(tempNode);
        tempNode.setLeftChild(tempLR);
        tempNode.setHeight(nodeHeight(tempNode));
        tempL.setHeight(nodeHeight(tempL));

        if(root==tempNode)
            root=tempL;
        return tempL;
    }

    private Node<T> LRRotation(Node<T> tempNode){
        Node<T> tempL=tempNode.getLeftChild();
        Node<T> tempLR=tempL.getRightChild();

        tempL.setRightChild(tempLR.getLeftChild());
        tempNode.setLeftChild(tempLR.getRightChild());
        tempLR.setLeftChild(tempL);
        tempLR.setRightChild(tempNode);

        tempL.setHeight(nodeHeight(tempL));
        tempNode.setHeight(nodeHeight(tempNode));
        tempLR.setHeight(nodeHeight(tempLR));

        if(tempNode==root)
            root=tempLR;
        return tempLR;
    }
    private Node<T> RRRotation(Node<T> tempNode){
        Node<T> tempR=tempNode.getRightChild();
        Node<T> tempRL=tempR.getLeftChild();

        tempR.setLeftChild(tempNode);
        tempNode.setRightChild(tempRL);
        tempNode.setHeight(nodeHeight(tempNode));
        tempR.setHeight(nodeHeight(tempR));

        if(root==tempNode)
            root=tempR;
        return tempR;
    }
    private Node<T> RLRotation(Node<T> tempNode){
        Node<T> tempR=tempNode.getRightChild();
        Node<T> tempRL=tempR.getLeftChild();

        tempR.setLeftChild(tempRL.getRightChild());
        tempNode.setRightChild(tempRL.getLeftChild());
        tempRL.setRightChild(tempR);
        tempRL.setLeftChild(tempNode);

        tempR.setHeight(nodeHeight(tempR));
        tempNode.setHeight(nodeHeight(tempNode));
        tempRL.setHeight(nodeHeight(tempRL));

        if(tempNode==root)
            root=tempRL;
        return tempRL;
    }




    private int nodeHeight(Node<T> currentNode){
        int hl=0,hr=0;
        hl=currentNode!=null && currentNode.getLeftChild()!=null?currentNode.getLeftChild().getHeight():0;
        hr=currentNode!=null && currentNode.getRightChild()!=null?currentNode.getRightChild().getHeight():0;
        return hl>hr?hl+1:hr+1;
    }
    private int balanceFactor(Node<T> currentNode){
        int hl=0,hr=0;
        hl=currentNode!=null && currentNode.getLeftChild()!=null?currentNode.getLeftChild().getHeight():0;
        hr=currentNode!=null && currentNode.getRightChild()!=null?currentNode.getRightChild().getHeight():0;
        return hl-hr;
    }

}
