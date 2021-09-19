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
        // O(n^2)
        TraderGraph traderGraph = getTraderGraph(traders);

        // find if any possible trader is reachable for the item
        // O(n)
        boolean traderReachable = false;

        for (Trader trader : traderGraph.adj.keySet()) {
            // find if the trader produces item 'g'
            if (trader.getProducedItem().equals(g)) {
                // find if a path exists to possible trader
                if (traderGraph.pathExists(t, trader)) {
                    traderReachable = true;
                }
            }
        }

        return traderReachable;
    }

    /**
     * Make a graph from the given set of traders
     * complexity: O(n^2)
     *
     * @param traders
     * @return
     */
    public static TraderGraph getTraderGraph(Set<Trader> traders) {
        TraderGraph traderGraph = new TraderGraph();

        for (Trader trader : traders) {
            for (Trader trader1 : traders) {
                if (
                        trader.willingToTrade(trader1.getProducedItem())
                                && trader1.willingToTrade(trader.getProducedItem())
                                && !trader.equals(trader1)
                ) {
                    // add edge if not already present
                    traderGraph.newEdge(trader, trader1);
                }
            }
        }
        return traderGraph;
    }
}
