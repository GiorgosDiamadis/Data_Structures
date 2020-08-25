package com.Trees.BinaryTrees;
import com.LinkedList.DoublyLinkedList;
import com.Queue.Queue;
import java.util.ArrayList;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

    public BinarySearchTree() { super();}

    public BinarySearchTree(T[] array){
        super(array,true);
    }

    public BinarySearchTree(ArrayList<T> list){
        super(list,true);
    }
    public void connectNodesOfSameLevel(){
        ArrayList<TreeNode<T>> levelOrder=new ArrayList<>();
        ArrayList<TreeNode<T>> nodesLevelOrder=new ArrayList<>();

        levelOrder.add(root);
        while (levelOrder.size()!=0){
            TreeNode<T> dequeued=levelOrder.get(0);
            levelOrder.remove(0);
            nodesLevelOrder.add(dequeued);
            if(dequeued.leftChild!=null)
                levelOrder.add(dequeued.leftChild);
            if(dequeued.rightChild!=null)
                levelOrder.add(dequeued.rightChild);
        }

        for(int i=0;i<nodesLevelOrder.size();i++){
            if(i+1<nodesLevelOrder.size()) {

                TreeNode<T> nextRight = nodesLevelOrder.get(i + 1);
                if (isRightView(nodesLevelOrder.get(i),root)) {
                    nodesLevelOrder.get(i).nextRight=null;
                    continue;
                }
                nodesLevelOrder.get(i).nextRight=nextRight;
            }
        }
    }

    public DoublyLinkedList<T> treeToAscendingSortedList(){
        DoublyLinkedList<T> list=new DoublyLinkedList<>();
        createListRec(list,root);
        return list;
    }

    private void createListRec(DoublyLinkedList list,TreeNode<T> current){
        if(current==null)
            return;
        createListRec(list,current.leftChild);
        list.insert(current.data);
        createListRec(list,current.rightChild);
    }

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
        return tempNode;
    }

    protected TreeNode<T> search(T data, TreeNode<T> currentNode){
        if(currentNode==null)return null;
        while(currentNode!=null){
            if(currentNode.data.equals(data))
                return currentNode;
            else if(currentNode.data.compareTo(data)>0)
                currentNode=currentNode.leftChild;
            else
                currentNode=currentNode.rightChild;
        }
        return null;
    }

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
        return currentNode;
    }

    public void constructFromLevelOrder(T[] levelOrder) {
        Queue<TreeNode<T>> queue=new Queue<>();
        for(int i=levelOrder.length-1;i>=0;i--){
            if(2*i+1>=levelOrder.length && 2*i+2>=levelOrder.length) {
                queue.enqueue(new TreeNode<>(levelOrder[i]));
                continue;
            }
            TreeNode<T> parent=new TreeNode<>(levelOrder[i]);
            parent.rightChild=queue.dequeue();
            parent.leftChild=queue.dequeue();
            queue.enqueue(parent);
        }
        root=queue.dequeue();
    }


}
