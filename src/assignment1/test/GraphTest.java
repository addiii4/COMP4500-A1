package assignment1.test;

import assignment1.ItemType;
import assignment1.Trader;
import assignment1.TraderGraph;
import org.junit.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

public class GraphTest {

    static TraderGraph g;
    static List<Trader> traders;

    @BeforeClass
    public static void setUp() throws Exception {
        g = new TraderGraph();

        // initialize 5 traders
        traders = new ArrayList<>();
        for(int i=1; i<8; i++) {
            HashSet<ItemType> tradableItems = new HashSet<ItemType>();
            tradableItems.add(new ItemType(1));
            traders.add(
                    new Trader("t"+Integer.valueOf(i), new ItemType(1), tradableItems)
            );
        }

        // add 5 vertices
        for(int i=1; i<7; i++) {
            g.newVertex(traders.get(i));
        }

        // add connections
        g.newEdge(traders.get(1), traders.get(2));
        g.newEdge(traders.get(1), traders.get(3));
        g.newEdge(traders.get(2), traders.get(5));
        g.newEdge(traders.get(4), traders.get(6));
    }

    @Test
    public void addEdge() {
        // add connections
        System.out.println(
                g
        );

        System.out.println(
                traders
        );

        g.newEdge(traders.get(1), traders.get(2));
        g.newEdge(traders.get(1), traders.get(3));
        g.newEdge(traders.get(2), traders.get(5));
        g.newEdge(traders.get(4), traders.get(6));
    }

    @Test
    public void pathExists() {
        assertTrue(g.findPath(traders.get(1), traders.get(2)));
        assertTrue(g.findPath(traders.get(1), traders.get(3)));
        assertTrue(g.findPath(traders.get(2), traders.get(5)));
        assertTrue(g.findPath(traders.get(4), traders.get(6)));
        assertTrue(g.findPath(traders.get(1), traders.get(5)));
        assertTrue(g.findPath(traders.get(3), traders.get(5)));
        assertTrue(g.findPath(traders.get(3), traders.get(2)));
        assertFalse(g.findPath(traders.get(1), traders.get(4)));
        assertFalse(g.findPath(traders.get(2), traders.get(6)));
    }
}