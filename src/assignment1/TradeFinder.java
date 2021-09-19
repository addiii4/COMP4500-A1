package assignment1;

import java.util.*;

public class TradeFinder {

    /**
     * @require The set of traders is non-null, and does not contain null traders. The
     * given trader, t, is non-null and is included in the set of traders. The
     * given item type, g, is non-null and is included in the set of types of
     * items that trader t is willing to trade.
     * @ensure Returns true if and only if there exists a (possibly empty) sequence of
     * agreements between traders in the given set of traders that can be formed,
     * such that after those agreements are formed, trader t can trade items of
     * type g; otherwise the algorithm should return false.
     * <p>
     * The method should not modify the set of traders provided as input, nor
     * should it modify trader t, or item type g.
     * <p>
     * (See the assignment handout for details and examples.)
     */
    public static boolean canTrade(Set<Trader> traders, Trader t, ItemType g) {
        // make a graph from the given set of traders
        TraderGraph traderGraph = new TraderGraph();
        for (Trader x : traders) {
            for (Trader y : traders) {

                // if same trader skip
                if(x.equals(y)) continue;

                // if both the traders are tradable
                if (
                        x.willingToTrade(y.getProducedItem())
                                && y.willingToTrade(x.getProducedItem())
                ) {
                    // add edge if not already present
                    traderGraph.newEdge(x, y);
                }
            }
        }

        // find if traders are connected through BFS
        for (Trader trader : traderGraph.adj.keySet()) {
            if (trader.getProducedItem().equals(g)) {
                // find if a path exists to possible trader
                if (traderGraph.findPath(t, trader)) {
                    return true;
                }
            }
        }

        return false;
    }
}

final class TraderGraph {
    // adjacency list
    public HashMap<Trader, List<Trader>> adj;
    public TraderGraph() {
        adj = new HashMap<>();
    }

    // find BFS path (true)
    public boolean findPath(Trader src, Trader dest) {
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

    // add a new vertex
    public void newVertex(Trader vertex) {
        adj.put(vertex, new ArrayList<>());
    }

    // add a new edge
    public void newEdge(Trader x,
                        Trader y) {

        // check if an edge is already there
        try {
            if (adj.get(x).contains(y) && adj.get(y).contains(x)) return;
        } catch (NullPointerException e) {
        }

        // add vertices if not present
        if (!adj.containsKey(x)) newVertex(x);
        if (!adj.containsKey(y)) newVertex(y);

        // add undirected edge
        adj.get(x).add(y);
        adj.get(y).add(x);
    }

}

