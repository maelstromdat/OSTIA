package eu.ostia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DirectGraph<V> {
    private Map<V, List<Edge<V>>> neighbors = new HashMap<V, List<Edge<V>>>();

    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append("digraph G {");
        for (V v : neighbors.keySet()) {
            if (neighbors.get(v).size() > 0)
                 for (Edge edge : neighbors.get(v)) {
                     s.append("\n  " + v + " -> " + edge);
                 }
            else {
                continue;
            }
        }
        s.append("\n}");
        return s.toString();
    }

    public void add(V vertex) {
        if (neighbors.containsKey(vertex))
            return;
        neighbors.put(vertex, new ArrayList<Edge<V>>());
    }

    public static class Edge<V> {
        private V vertex;
        private String label;

        public Edge(V v, String l) {
            vertex = v;
            label = l;
        }

        @Override
        public String toString() {
            return vertex + " [label=\"" + label + "\"];";
        }
    }
}
