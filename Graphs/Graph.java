package com.Graphs;

import com.Queue.Queue;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public abstract class Graph {
    class Edge implements Comparable<Edge> {
        int startVertex;
        int endVertex;
        int weight;

        public Edge(int startVertex,int endVertex,int weight){
            this.startVertex=startVertex;
            this.endVertex=endVertex;
            this.weight=weight;
        }


        @Override
        public int hashCode() {
            return Objects.hash(startVertex, endVertex, weight);
        }

        @Override
        public boolean equals(Object obj) {
            Edge other=(Edge)obj;
            return startVertex==other.startVertex && endVertex==other.endVertex;
        }

        @Override
        public int compareTo(Edge otherEdge) {
            if(weight-otherEdge.weight<0)
                return -1;
            else if(weight-otherEdge.weight>0)
                return 1;
            else
                return 0;
        }
    }
    protected ArrayList<Edge> edges=new ArrayList<>();
    protected int[][] adjacencyMatrix;
    public abstract void addEdge(int vertex1,int vertex2,int weight);
    public abstract void removeEdge(int vertex1,int vertex2);

    public Graph(int numberOfVerteces,boolean generateRandom){
        adjacencyMatrix=new int[numberOfVerteces][numberOfVerteces];
        for(int i=0;i<adjacencyMatrix.length;i++){
            for(int j=0;j<adjacencyMatrix.length;j++){
                if(generateRandom){
                    Random random=new Random();
                    adjacencyMatrix[i][j]=random.nextInt(2);
                    addEdge(i,j,1);
                }else{
                    adjacencyMatrix[i][j]=0;
                }
            }

        }
    }
/**    A mother vertex in a graph G = (V,E) is a vertex v such that
   all other vertices in G can be reached by a path from v.**/
    public void findMotherVerteces(){
        for(int i=0;i<adjacencyMatrix.length;i++){
            if(breadthFirstSearch(i))
                System.out.println(i);
        }
    }

    public boolean breadthFirstSearch(int startingVertex){
        boolean[] isVisited=new boolean[adjacencyMatrix.length];

        Queue<Integer> queue=new Queue<>();
        queue.enqueue(startingVertex);
        isVisited[startingVertex]=true;

        while (!queue.isEmpty()){
            int vertex=queue.dequeue();
//            System.out.print(vertex+" ");
            for(int j=0;j<adjacencyMatrix.length;j++){
                if(adjacencyMatrix[vertex][j]!=0 && !isVisited[j]){
                    queue.enqueue(j);
                    isVisited[j]=true;
                }
            }
        }

        for(int i=0;i<isVisited.length;i++)
            if(!isVisited[i])
                return false;

        return true;
    }



    public boolean depthFirstSearch(int startingVertex){
        boolean[] isVisited=new boolean[adjacencyMatrix.length];
        dfs(startingVertex,isVisited);
        for(int i=0;i<isVisited.length;i++)
            if(!isVisited[i])
                return false;

        return true;
    }

    private void dfs(int startingVertex,boolean[] isVisited){
        if(!isVisited[startingVertex]){
            System.out.print(startingVertex+" ");
            isVisited[startingVertex]=true;
            for(int i=0;i<adjacencyMatrix.length;i++){
                if(adjacencyMatrix[startingVertex][i]==1 && !isVisited[i]) {
                    dfs(i, isVisited);
                }
            }
        }
    }

    public void printEdges(){
        for(Edge e : edges)
            System.out.println(e.startVertex+" "+e.endVertex);
    }
}
