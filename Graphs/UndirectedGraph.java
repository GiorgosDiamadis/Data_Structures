package com.Graphs;

import com.Queue.Queue;
import com.Trees.BinaryTrees.Heap.BinaryHeap;

import java.util.*;
import java.util.function.Predicate;

public class UndirectedGraph extends Graph {

    public UndirectedGraph(int numberOfVerteces, boolean generateRandom) {
        super(numberOfVerteces, generateRandom);
    }

    @Override
    public void addEdge(int vertex1, int vertex2, int weight) {
        edges.add(new Edge(vertex1, vertex2, weight));
        edges.add(new Edge(vertex2, vertex1, weight));
        adjacencyMatrix[vertex1][vertex2] = weight;
        adjacencyMatrix[vertex2][vertex1] = weight;
    }

    @Override
    public void removeEdge(int vertex1, int vertex2) {
        edges.removeIf(new Predicate<Edge>() {
            @Override
            public boolean test(Edge edge) {
                return edge.startVertex == vertex1 && edge.endVertex == vertex2;
            }
        });
        edges.removeIf(new Predicate<Edge>() {
            @Override
            public boolean test(Edge edge) {
                return edge.endVertex == vertex1 && edge.startVertex == vertex2;
            }
        });
        adjacencyMatrix[vertex1][vertex2] = 0;
        adjacencyMatrix[vertex2][vertex1] = 0;

    }

    public void numberOfEdgesBetween(int vertex1, int vertex2) {
        boolean[] isVisited = new boolean[adjacencyMatrix.length];

        Queue<Integer> queue = new Queue<>();
        Map<Integer, Integer> vertexDistance = new HashMap<>();
        queue.enqueue(vertex1);
        vertexDistance.put(vertex1, 0);
        isVisited[vertex1] = true;

        while (!queue.isEmpty()) {
            int vertex = queue.dequeue();

            for (int j = 0; j < adjacencyMatrix.length; j++) {
                if (adjacencyMatrix[vertex][j] != 0 && !isVisited[j]) {
                    queue.enqueue(j);

                    int distance = vertexDistance.get(vertex) + 1;
                    vertexDistance.put(j, distance);

                    if (j == vertex2) {
                        System.out.println(vertexDistance.get(vertex2));
                        return;
                    }
                    isVisited[j] = true;
                }
            }
        }
    }

    public BitSet biconnectedComponents(){


        return  null;
    }

    public BitSet nodesWithUpToKDistanceFrom(Set<Integer> nodes, int k) {

        Map<Integer, BitSet> kDistanceNodes = new HashMap<>();
        Integer[] n = nodes.toArray(Integer[]::new);

        for (int i = 0; i < n.length; i++) {
            kDistanceNodes.put(n[i], new BitSet(adjacencyMatrix.length));
        }

        Queue<Integer> queue = new Queue<>();
        Map<Integer, Integer> vertexDistance = new HashMap<>();
        boolean[] isVisited = new boolean[adjacencyMatrix.length];

        for (int j = 0; j < n.length; j++) {

            queue.enqueue(n[j]);
            vertexDistance.put(n[j], 0);
            BitSet vertexBitset = kDistanceNodes.get(n[j]);

            while (!queue.isEmpty()) {

                int vertex = queue.dequeue();

                for (int i = 0; i < adjacencyMatrix.length; i++) {
                    if (adjacencyMatrix[vertex][i] != 0 && !isVisited[i]) {

                        queue.enqueue(i);
                        int distance = vertexDistance.get(vertex) + 1;
                        vertexDistance.put(i, distance);
                        isVisited[i] = true;

                        if (distance <= k)
                            vertexBitset.set(i);
                    }
                }
            }

            queue.clearQueue();
            vertexDistance.clear();
            Arrays.fill(isVisited, false);
        }

        BitSet result = kDistanceNodes.get(n[0]);
        for (Map.Entry<Integer, BitSet> m : kDistanceNodes.entrySet()) {
            if (!m.getValue().equals(result)) {
                result.and(m.getValue());
            }
        }

        return result;
    }

    public void articulationPoints() {
        boolean[] isVisited = new boolean[adjacencyMatrix.length];
        boolean[] articPoint=new boolean[adjacencyMatrix.length];
        int[] discT = new int[adjacencyMatrix.length];
        int[] low = new int[adjacencyMatrix.length];
        int[] parent = new int[adjacencyMatrix.length];

        Arrays.fill(parent, -1);

        findArticulationPoints(isVisited, 0, discT, low, parent,articPoint, 1);

        for(int i=0;i<articPoint.length;i++){
            if(articPoint[i])
                System.out.println(i);
        }
    }

    private void findArticulationPoints(boolean[] isVisited,
                                        int startingVertex,
                                        int[] discT,
                                        int[] low,
                                        int[] parent,
                                        boolean[] articPoint,
                                        int time) {

        int children=0;
        if (!isVisited[startingVertex]) {
            isVisited[startingVertex] = true;

            discT[startingVertex] = low[startingVertex] = time;

            for (int i = 0; i < adjacencyMatrix.length; i++) {
                if (adjacencyMatrix[startingVertex][i] != 0) {

                    if (!isVisited[i]) {
                        parent[i] = startingVertex;
                        children=children+1;
                        findArticulationPoints(isVisited, i, discT, low, parent, articPoint,time + 1);
                        low[startingVertex] = Math.min(low[startingVertex], low[i]);

                        if( parent[startingVertex]!=-1 && low[i]>=discT[startingVertex]) {
                            articPoint[startingVertex]=true;
                        }else if(parent[startingVertex]==-1 && children>1){
                            articPoint[startingVertex]=true;
                        }

                    } else if(i!=parent[startingVertex]) {
                        low[startingVertex] = Math.min(low[startingVertex], discT[i]);
                    }

                }
            }
        }


    }

    public void spanningTreeKruskal() {
        Edge[] array = edges.toArray(new Edge[edges.size()]);
        BinaryHeap<Edge> heap = new BinaryHeap<>(array);

        array = heap.heapSort();
        int[] set = new int[adjacencyMatrix.length];
        Arrays.fill(set, -1);
        ArrayList<Edge> spanningTree = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
            Edge minimum = array[i];
            if (find(minimum.startVertex, set) != find(minimum.endVertex, set)) {
                spanningTree.add(minimum);
                union(find(minimum.startVertex, set), find(minimum.endVertex, set), set);
            }
        }

        System.out.println("=======Spanning Tree========");
        int cost = 0;
        for (Edge spTreeEdge : spanningTree) {
            System.out.println(spTreeEdge.startVertex + " " + spTreeEdge.endVertex);
            cost += spTreeEdge.weight;
        }
        System.out.println("With cost: " + cost);
    }

    private int find(int u, int[] set) {
        int x = u;
        while (set[x] > 0) {
            x = set[x];
        }
        return x;
    }

    private void union(int u, int v, int[] set) {
        if (set[u] < set[v]) {
            set[u] = set[u] + set[v];
            set[v] = u;
        } else {
            set[v] += set[u];
            set[u] = v;
        }
    }

    public void spanningTreePrim() {

        ArrayList<Edge> spanningTree = new ArrayList<>();

        Edge minimumWeightEdge = null;
        int min = Integer.MAX_VALUE;

        for (Edge e : edges) {
            if (e.weight < min) {
                min = e.weight;
                minimumWeightEdge = e;
            }
        }

        spanningTree.add(minimumWeightEdge);
        ArrayList<Edge> neighbours = new ArrayList<>();

        while (spanningTree.size() != adjacencyMatrix.length - 1) {

            for (Edge edge : edges) {
                for (Edge spTreeEdge : spanningTree) {
                    if (spanningTree.contains(edge)) {
                        continue;
                    }
                    if (spTreeEdge.startVertex == edge.endVertex || spTreeEdge.endVertex == edge.startVertex) {
                        neighbours.add(edge);
                    }
                }
            }
            min = Integer.MAX_VALUE;
            for (Edge e : neighbours) {
                if (e.weight < min) {
                    min = e.weight;
                    minimumWeightEdge = e;
                }
            }
            spanningTree.add(minimumWeightEdge);
            neighbours.clear();
        }

        int cost = 0;
        System.out.println("=======Spanning Tree========");

        for (Edge sp : spanningTree) {
            System.out.println(sp.startVertex + " " + sp.endVertex);
            cost += sp.weight;
        }

        System.out.println("With cost : " + cost);
    }


    public int degreeOf(int i) {
        int degree = 0;
        for (int l = 0; l < adjacencyMatrix.length; l++) {
            if (adjacencyMatrix[i][l] != 0)
                degree++;
        }
        return degree;
    }
}
