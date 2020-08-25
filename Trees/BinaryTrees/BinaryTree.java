package com.Trees.BinaryTrees;

import java.util.*;

public class BinaryTree<T extends Comparable<T>> {

   static class TreeNode<T> {
        T data;
        int height;
        TreeNode<T> leftChild;
        TreeNode<T> rightChild;
        TreeNode<T> nextRight;

        public TreeNode(T data){
            this.data=data;
            height=1;
            leftChild=rightChild=null;
        }

        public boolean isLeaf(){
            return leftChild==null && rightChild==null;
        }
        private String printList(){
            String list="";
            for (TreeNode<T> temp = nextRight; temp!=null; temp=temp.nextRight)
                list+=temp.data+" ";
            return list;
        }
        public String toString(){
            return data.toString()+":"+printList();
        }

   }

    protected TreeNode<T> root;

    public BinaryTree(){
        root=null;
    }

    public BinaryTree(ArrayList<T> nodes,boolean isBST){
        if(isBST)
            Collections.sort(nodes);
        root= createBSTFromList(nodes,nodes.iterator(),nodes.size());
    }

    public BinaryTree(T[] nodes,boolean isBST){
        if(isBST)
            root= createBSTFromArray(nodes,0,nodes.length-1);
        else
            root=createTree(nodes,root,0);
    }

    protected TreeNode<T> createBSTFromList(ArrayList<T> list, Iterator<T> iterator, int n) {
        //InOrder
        if(n<=0) return null;

        TreeNode<T> left= createBSTFromList(list,iterator,n/2);

        TreeNode<T> root=new TreeNode<>(iterator.next());
        root.leftChild=left;


        TreeNode<T> right= createBSTFromList(list,iterator,n-n/2-1);
        root.rightChild=right;

        return root;
    }
    protected TreeNode<T> createBSTFromArray(T[] array, int start, int end){
        //Pre order
        if(start>end) return null;

        int mid=(start+end)/2;
        T middle=array[mid];

        TreeNode<T> root=new TreeNode<>(middle);

        root.leftChild= createBSTFromArray(array,start,mid-1);
        root.rightChild= createBSTFromArray(array,mid+1,end);

        return root;
    }

    private TreeNode<T> createTree(T[] array, TreeNode<T> current, int index){
        if(index<array.length){
            TreeNode<T> temp=new TreeNode<>(array[index]);
            current=temp;
            current.leftChild=createTree(array,current.leftChild,2*index+1);
            current.rightChild=createTree(array,current.rightChild,2*index+2);
        }
        return current;
    }


    public int size(){
        return count(root);
    }

    protected int count(TreeNode<T> currentNode){
        int x=0,y=0;
        if(currentNode!=null){
            x=count(currentNode.leftChild);
            y=count(currentNode.rightChild);
            return x+y+1;
        }
        return 0;
    }

    public int height(){
        return treeHeight(root);
    }

    protected int treeHeight(TreeNode<T> currentNode){
        int x=0,y=0;
        if(currentNode!=null){
            x=treeHeight(currentNode.leftChild);
            y=treeHeight(currentNode.rightChild);
            if(x>y)
                return x+1;
            else
                return y+1;
        }
        return 0;

    }



    protected TreeNode<T> inOrderPredecessor(TreeNode<T> currentNode){
        while(currentNode!=null&&currentNode.rightChild!=null){
            currentNode=currentNode.rightChild;
        }
        return currentNode;
    }
    protected TreeNode<T> inOrderSuccessor(TreeNode<T> currentNode){
        while(currentNode!=null&&currentNode.leftChild!=null){
            currentNode=currentNode.leftChild;
        }
        return currentNode;
    }



    public void levelOrderLineByLine(){
        levelOrderLL(root);
    }
    protected void levelOrderLL(TreeNode<T> root){
        ArrayList<TreeNode<T>> queue=new ArrayList<>();

        queue.add(root);
        while (queue.size()!=0){
            TreeNode<T> dequeued=queue.get(0);
            queue.remove(0);
            System.out.print(dequeued.data+" ");
            if (isRightView(dequeued,root))
                System.out.print("$ ");

            if(dequeued.leftChild!=null)
                queue.add(dequeued.leftChild);

            if(dequeued.rightChild!=null)
                queue.add(dequeued.rightChild);
        }
    }
    protected boolean isRightView(TreeNode<T> node,TreeNode<T> start){
        for(TreeNode<T> temp=start;temp!=null;temp=temp.rightChild){
            if(temp.data.equals(node.data))
                return true;
        }
        return false;
    }

    public void mirror(){
        mirrorTree(root);
    }
    protected void mirrorTree(TreeNode<T> current){
        if(current==null)return;

        if(!current.isLeaf()) {
            if(current.leftChild!=null && current.rightChild!=null) {
                TreeNode<T> temp=current.leftChild;
                current.leftChild = current.rightChild;
                current.rightChild = temp;
            }else if(current.leftChild!=null && current.rightChild==null){
                current.rightChild=current.leftChild;
                current.leftChild=null;
            }else if(current.leftChild==null && current.rightChild!=null){
                current.leftChild=current.rightChild;
                current.rightChild=null;
            }
        }

        mirrorTree(current.leftChild);
        mirrorTree(current.rightChild);

    }
    public void printPostOrder() {
        postOrder(root);
    }
    public boolean isIdenticalTo(BinaryTree<T> otherTree){
        return checkIdentical(root,otherTree.root);
    }
    protected boolean checkIdentical(TreeNode<T> tree1,TreeNode<T> tree2){
        if(tree1==null && tree2==null)return true;

        if(tree1!=null && tree2!=null)
            return tree1.data.equals(tree2.data)
            && checkIdentical(tree1.rightChild,tree2.rightChild)
            && checkIdentical(tree1.leftChild,tree2.leftChild);
        return false;
    }
    public void printPreOrder(){
        preOrder(root);

    }
    protected void preOrder(TreeNode<T> currentNode){
        if(currentNode==null)return ;
        System.out.println(currentNode.data);
        preOrder(currentNode.leftChild);
        preOrder(currentNode.rightChild);
    }
    public void printInOrder(){
        inOrder(root);
    }
    public void printLevelOrder(){levelOrder(root);}

    public void printVerticalOrder()
    {
        TreeMap<Integer,Vector<T>> map=new TreeMap<>();
        verticalOrder(root,0,map);
        for(Map.Entry<Integer,Vector<T>> e : map.entrySet())
            System.out.println(e.getValue());
    }

    public void kDistanceFromRoot(int k){
        Vector<T> vector=new Vector<>();
        nodesWithKDistance(root,vector,0,k);
        System.out.println(vector);
    }

    public void nodesWithNoSibling(){
        ArrayList<TreeNode<T>> queue=new ArrayList<>();
        queue.add(root);
        while (queue.size()!=0){
            TreeNode<T> dequeued=queue.get(0);
            queue.remove(0);

            if(dequeued.leftChild!=null && dequeued.rightChild==null)
                System.out.println(dequeued.leftChild.data);

            if(dequeued.rightChild!=null && dequeued.leftChild==null)
                System.out.println(dequeued.rightChild.data);

            if(dequeued.leftChild!=null)
                queue.add(dequeued.leftChild);

            if(dequeued.rightChild!=null)
                queue.add(dequeued.rightChild);
        }
    }

    protected void nodesWithKDistance(TreeNode<T> current,Vector<T> vector,int distance,int k){
        if(current==null)return;

        if(distance==k || distance==-k)
            vector.add(current.data);

        nodesWithKDistance(current.leftChild,vector,distance-1,k);
        nodesWithKDistance(current.rightChild,vector,distance+1,k);

    }

    protected void verticalOrder(TreeNode<T> root, Integer distance, TreeMap<Integer,Vector<T>> map){
        if(root==null)return;
        Vector<T> vector=map.get(distance);

        if(vector==null){
            vector=new Vector<>();
            vector.add(root.data);
        }else{
            vector.add(root.data);
        }
        map.put(distance,vector);
        verticalOrder(root.leftChild,distance-1,map);
        verticalOrder(root.rightChild,distance+1,map);

    }
    protected void levelOrder(TreeNode<T> root){
        ArrayList<TreeNode<T>> queue=new ArrayList<>();
        queue.add(root);
        while (queue.size()!=0){
            TreeNode<T> dequeued=queue.get(0);
            queue.remove(0);
            System.out.print(dequeued.data+" ");
            if(dequeued.leftChild!=null)
                queue.add(dequeued.leftChild);
            if(dequeued.rightChild!=null)
                queue.add(dequeued.rightChild);
        }
    }

    protected void postOrder(TreeNode<T> currentNode){
        if(currentNode==null)return ;
        postOrder(currentNode.leftChild);
        postOrder(currentNode.rightChild);
        System.out.print(currentNode.data+" ");
    }

    protected void inOrder(TreeNode<T> currentNode){
        if(currentNode==null)return ;
        inOrder(currentNode.leftChild);
        System.out.print(currentNode.data+" ");
        inOrder(currentNode.rightChild);
    }
}
