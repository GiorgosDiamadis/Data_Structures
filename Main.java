package com;

import com.Graphs.UndirectedGraph;

public class Main {

    public static void main(String[] args) {
        UndirectedGraph g1 = new UndirectedGraph(5, false);
        g1.addEdge(1, 0, 1);
        g1.addEdge(0, 2, 1);
        g1.addEdge(2, 1, 1);
        g1.addEdge(0, 3, 1);
        g1.addEdge(3, 4, 1);

        g1.articulationPoints();
    }

}