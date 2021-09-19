package assignment1;

import java.util.*;

public class Graph<T> {

    /**
     * Store the edges of the graph
     */
    public Map<T, List<T>> map = new HashMap<>();

    /**
     * Add a new vertex
     *
     * @param s
     */
    public void addVertex(T s) {
        map.put(s, new LinkedList<T>());
    }

    /**
     * Add an edge between 2 vertices
     *
     * @param source
     * @param destination
     * @param bidirectional
     */
    public void addEdge(T source,
                        T destination,
                        boolean bidirectional) {

        // return if an edge already exists
        if(hasEdge(source, destination, bidirectional)) return;

        if (!map.containsKey(source))
            addVertex(source);

        if (!map.containsKey(destination))
            addVertex(destination);

        map.get(source).add(destination);
        if (bidirectional == true) {
            map.get(destination).add(source);
        }
    }

    /**
     * give number of vertices
     */
    // might be useful
    public int getVertexCount() {
        return map.keySet().size();
    }

    /**
     * give number of edges
     * @param bidirection (whether the graph is bidirectional)
     */
    // might be useful
    public int getEdgesCount(boolean bidirection) {
        int count = 0;
        for (T v : map.keySet()) {
            count += map.get(v).size();
        }
        if (bidirection == true) {
            count = count / 2;
        }
        return count;
    }

    /**
     * tell whether a vertex is present
     */
    // might be useful
    public boolean hasVertex(T s) {
        return map.containsKey(s);
    }

    /**
     * tell whether an edge is present
     */
    public boolean hasEdge(T s, T d, boolean bidirectional) {
        try {
            if(bidirectional) return (map.get(s).contains(d) && map.get(d).contains(s));
            return map.get(s).contains(d);
        }
        catch(NullPointerException e) {
            return false;
        }
    }

    /**
     * Finds if a path exists from a source to destination
     *
     * @param source
     * @param destination
     */
    public boolean pathExists(T source, T destination) {
        if (source == destination) return true;

        // Mark all the vertices as not visited(By default
        // set as false)
        HashMap<T, Boolean> visited = new HashMap<T, Boolean>();

        // Create a queue for BFS
        LinkedList<T> queue = new LinkedList<T>();

        // Mark the current node as visited and enqueue it
        visited.put(source, true);
        queue.add(source);

        while (queue.size() != 0) {
            // Dequeue a vertex from queue and print it
            source = queue.poll();

            // if path exists
            if (source == destination) {
                return true;
            }

            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            Iterator<T> i = map.get(source).listIterator();
            while (i.hasNext()) {
                T n = i.next();
                try {
                    if (!visited.get(n)) {
                        visited.put(n, true);
                        queue.add(n);
                    }
                } catch (NullPointerException e) {
                    visited.put(n, true);
                    queue.add(n);
                }
            }
        }

        return false;
    }


}
