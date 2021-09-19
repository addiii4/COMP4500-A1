package assignment1;

import java.util.*;

public class TraderGraph {

    // adjacency list
    public HashMap<Trader, List<Trader>> adj;

    public TraderGraph() {
        adj = new HashMap<>();
    }

    // add a new vertex
    public void newVertex(Trader s) {
        adj.put(s, new LinkedList<Trader>());
    }

    // add a new edge
    public void newEdge(Trader src,
                        Trader dest) {

        // check if an edge is already there
        try {
            if (adj.get(src).contains(dest) && adj.get(dest).contains(src)) return;
        } catch (NullPointerException e) {
        }

        // add vertices if not present
        if (!adj.containsKey(src)) newVertex(src);
        if (!adj.containsKey(dest)) newVertex(dest);

        // add undirected edge
        adj.get(src).add(dest);
        adj.get(dest).add(src);
    }

    // BFS path exists
    public boolean pathExists(Trader src, Trader dest) {
        if (src == dest) return true;

        HashMap<Trader, Boolean> visited = new HashMap<Trader, Boolean>();
        LinkedList<Trader> queue = new LinkedList<Trader>();

        // enque current node
        visited.put(src, true);
        queue.add(src);

        while (queue.size() != 0) {
            src = queue.poll();

            // PATH EXISTS !!!
            if (src == dest) {
                return true;
            }

            // Get all adjacent
            // IF !visited, VISIT AND ENQUE
            Iterator<Trader> adjacents = adj
                    .get(src)
                    .listIterator();

            while (adjacents.hasNext()) {
                Trader adjacentTrader = adjacents.next();
                try {
                    if (!visited.get(adjacentTrader)) {
                        visited.put(adjacentTrader, true);
                        queue.add(adjacentTrader);
                    }
                } catch (Exception e) {
                    visited.put(adjacentTrader, true);
                    queue.add(adjacentTrader);
                }
            }
        }

        return false;
    }


}
