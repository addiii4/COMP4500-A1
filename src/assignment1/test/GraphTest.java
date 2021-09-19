package assignment1.test;

import assignment1.Graph;
import org.junit.*;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

public class GraphTest {

    static Graph<Integer> graph;

    @BeforeClass
    public static void setUp() throws Exception {
        graph = new Graph<>();

        // add 5 vertices
        for(int i=1; i<5; i++) {
            graph.addVertex(i);
        }

        // add connections
        graph.addEdge(1, 2, true);
        graph.addEdge(2, 3, true);
        graph.addEdge(4, 5, true);
    }

    @AfterClass
    public static void tearDown() throws Exception {

    }

    @Test
    public void addEdge() {
        graph.addEdge(1, 2, true);
        graph.addEdge(1, 2, true);
        graph.addEdge(1, 2, true);
        System.out.println(
                graph.toString()
        );
    }

    @Test
    public void pathExists() {
        System.out.println(
                graph.pathExists(1, 2)
                // should return TRUE
        );
        assertTrue(graph.pathExists(1, 2));

        System.out.println(
                graph.pathExists(1, 3)
                // should return TRUE
        );
        assertTrue(graph.pathExists(1, 3));

        System.out.println(
                graph.pathExists(1, 4)
                // should return false
        );
        assertFalse(graph.pathExists(1, 4));
    }
}