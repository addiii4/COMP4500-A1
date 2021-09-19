package assignment1.test;

import assignment1.*;

import java.util.*;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Basic tests for the {@link TradeFinder} implementation class.
 * <p>
 * We will use a much more comprehensive test suite to test your code, so you should add
 * your own tests to this test suite to help you to debug your implementation.
 */
public class TradeFinderTest {

    static List<Trader> traders = new ArrayList<>();
    static ItemType[] items;

    @BeforeClass
    public static void beforeAll() {
        /* Initialise parameters to the test. */

        // ItemTypes
        int numItemTypes = 9;
        items = new ItemType[numItemTypes];
        for (int i = 0; i < items.length; i++) {
            items[i] = new ItemType(i);
        }

        //
        ItemType producedItem;
        HashSet<ItemType> tradableItems;

        // trader t0: (g0, [g0, g1, g2, g7, g8])
        producedItem = items[0];
        tradableItems = new HashSet<>();
        tradableItems.add(items[0]);
        tradableItems.add(items[1]);
        tradableItems.add(items[2]);
        tradableItems.add(items[7]);
        tradableItems.add(items[8]);
        traders.add(new Trader("t0", producedItem, tradableItems));

        // trader t1: (g1, [g1, g5])
        producedItem = items[1];
        tradableItems = new HashSet<>();
        tradableItems.add(items[1]);
        tradableItems.add(items[5]);
        traders.add(new Trader("t1", producedItem, tradableItems));

        // trader t2: (g2, [g0, g2, g3, g4])
        producedItem = items[2];
        tradableItems = new HashSet<>();
        tradableItems.add(items[2]);
        tradableItems.add(items[0]);
        tradableItems.add(items[3]);
        tradableItems.add(items[4]);
        traders.add(new Trader("t2", producedItem, tradableItems));

        // trader t3: (g3, [g1, g2, g3, g4])
        producedItem = items[3];
        tradableItems = new HashSet<>();
        tradableItems.add(items[3]);
        tradableItems.add(items[1]);
        tradableItems.add(items[2]);
        tradableItems.add(items[4]);
        traders.add(new Trader("t3", producedItem, tradableItems));

        // trader t4: (g4, [g4, g6])
        producedItem = items[4];
        tradableItems = new HashSet<>();
        tradableItems.add(items[4]);
        tradableItems.add(items[6]);
        traders.add(new Trader("t4", producedItem, tradableItems));

        // trader t5: (g5, [g1, g4, g5])
        producedItem = items[5];
        tradableItems = new HashSet<>();
        tradableItems.add(items[5]);
        tradableItems.add(items[1]);
        tradableItems.add(items[4]);
        traders.add(new Trader("t5", producedItem, tradableItems));

        // trader t6: (g6, [g0, g4, g6])
        producedItem = items[6];
        tradableItems = new HashSet<>();
        tradableItems.add(items[6]);
        tradableItems.add(items[0]);
        tradableItems.add(items[4]);
        traders.add(new Trader("t6", producedItem, tradableItems));

        // trader t7: (g7, [g7, g8])
        producedItem = items[7];
        tradableItems = new HashSet<>();
        tradableItems.add(items[7]);
        tradableItems.add(items[8]);
        traders.add(new Trader("t7", producedItem, tradableItems));

        // trader t8: (g8, [g7, g8])
        producedItem = items[8];
        tradableItems = new HashSet<>();
        tradableItems.add(items[8]);
        tradableItems.add(items[7]);
        traders.add(new Trader("t8", producedItem, tradableItems));
    }

    @Test
    public void allPossibleTests() {
        List<Pair<Integer, Integer>> tradablePairs = new ArrayList<Pair<Integer, Integer>>() {{
            add(new Pair<Integer, Integer>(1, 5));
            add(new Pair<Integer, Integer>(4, 6));
            add(new Pair<Integer, Integer>(7, 8));
            add(new Pair<Integer, Integer>(0, 2));
            add(new Pair<Integer, Integer>(0, 3));
            add(new Pair<Integer, Integer>(2, 3));
        }};

//        for(Pair pair : tradablePairs) {
//            Assert.assertTrue(testPair((Integer)pair.getKey(),(Integer)pair.getValue()));
//        }
//
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.println(
                        i + " " + j + " " + testPair(i, j)
                );

                // if same
                if (i == j) {
                    Assert.assertTrue(testPair(i, j));
                    continue;
                }

                // if reachable assert true
                if (tradablePairs.contains(new Pair<Integer, Integer>(i, j))
                || tradablePairs.contains(new Pair<Integer, Integer>(j, i))) {
                    Assert.assertTrue(testPair(i, j));
                    continue;
                }

//                 assert false in every other case


                Assert.assertFalse(testPair(i, j));
            }
        }
    }

    @Test
    public void quickTest() {
        /* The trader chosen for the test: t = t0 */
        Trader t = traders.get(0);
        /*
         * The items that trader t can trade after valid sequences of trade agreements
         * have been formed: [g0, g1, g2]
         */
        Set<ItemType> expected = new HashSet<>();
        expected.add(items[0]);
        expected.add(items[2]);
        expected.add(items[3]);

        /* Run method and compare expected and actual answers */
        for (ItemType item : t.getTradableItems()) {
            Assert.assertEquals(expected.contains(item),
                    TradeFinder.canTrade(new HashSet<Trader>(traders), t, item));
        }
    }

    boolean testPair(int trader, int itemtype) {
        if (
                TradeFinder.canTrade(new HashSet<>(traders), traders.get(trader), new ItemType(itemtype))
                        && TradeFinder.canTrade(new HashSet<>(traders), traders.get(itemtype), new ItemType(trader))
        ) {
            return true;
        }
        return false;
    }
}
