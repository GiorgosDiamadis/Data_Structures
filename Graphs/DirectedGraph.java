package com.Graphs;

import com.Queue.Queue;

import java.util.Vector;
import java.util.function.Predicate;

public class DirectedGraph extends Graph {

    public DirectedGraph(int numberOfVerteces,boolean generateRandom){
        super(numberOfVerteces,generateRandom);
    }

    @Override
    public void addEdge(int vertex1, int vertex2, int weight) {
        edges.add(new Edge(vertex1, vertex2,weight));
        adjacencyMatrix[vertex1][vertex2]=weight;
    }

    @Override
    public void removeEdge(int vertex1, int vertex2) {
        edges.removeIf(new Predicate<Edge>() {
            @Override
            public boolean test(Edge edge) {
                return edge.startVertex== vertex1 && edge.endVertex== vertex2;
            }
        });
        adjacencyMatrix[vertex1][vertex2]=0;
    }

    public DirectedGraph transpose(){
        DirectedGraph transposed=new DirectedGraph(adjacencyMatrix.length,false);
        for(Edge e : edges){
            transposed.addEdge(e.endVertex,e.startVertex,e.weight);
        }
        return transposed;
    }

    public boolean isStronglyConnected(){
        if(!depthFirstSearch(0))
            return false;

        DirectedGraph transpose=transpose();

        return transpose.depthFirstSearch(0);
    }

    public boolean pathExists(int src,int dest){
        Queue<Integer> queue=new Queue<>();
        queue.enqueue(src);
        boolean[] isVisited=new boolean[adjacencyMatrix.length];
        isVisited[src]=true;
        while (!queue.isEmpty()){
            int vertex=queue.dequeue();
            for(int i=0;i<adjacencyMatrix.length ;i++){
                if(adjacencyMatrix[vertex][i]!=0 && !isVisited[i]){
                    queue.enqueue(i);
                    if(i==dest)
                        return true;
                    isVisited[i]=true;
                }
            }
        }
        return false;
    }



    public void allPathsFromTo(int src, int dest){
        boolean[] isVisited=new boolean[adjacencyMatrix.length];
        Vector<Integer> path=new Vector<>();
        paths(src,dest,isVisited,path);
    }

    private void paths(int src, int dest, boolean[] isVisited, Vector<Integer> path){
        if(!isVisited[src]){
            path.add(src);
            isVisited[src]=true;
            for(int i=0;i<adjacencyMatrix.length;i++){
                if(adjacencyMatrix[src][i]==1 && !isVisited[i]) {
                    paths(i,dest, isVisited,path);
                    isVisited[i]=false;
                    if(i==dest)
                        System.out.println(path);
                    path.remove(path.size()-1);
                }
            }
        }
    }

    public int outDegreeOf(int i) {
        int degree=0;
        for(int l=0;l<adjacencyMatrix.length;l++){
            if(adjacencyMatrix[i][l]!=0)
                degree++;
        }
        return degree;
    }
    public int inDegreeOf(int i){
        int degree=0;
        for(int l=0;l<adjacencyMatrix.length;l++){
            if(adjacencyMatrix[l][i]!=0)
                degree++;
        }
        return degree;
    }
}
