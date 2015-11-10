package eu.ostia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DirectGraph<V> {
    private Map<V, List<Edge<V>>> neighbors = new HashMap<V, List<Edge<V>>>();

    public String toString() {
        StringBuffer s = new StringBuffer();
        for (V v : neighbors.keySet())
            s.append("\n    " + v + " -> " + neighbors.get(v));
        return s.toString();
    }

    public void add(V vertex) {
        if (neighbors.containsKey(vertex))
            return;
        neighbors.put(vertex, new ArrayList<Edge<V>>());
    }

    public int getNumberOfEdges() {
        int sum = 0;
        for (List<Edge<V>> outBounds : neighbors.values()) {
            sum += outBounds.size();
        }
        return sum;
    }

    public boolean contains(V vertex) {
        return neighbors.containsKey(vertex);
    }

    public void add(V from, V to, String label) {
        this.add(from);
        this.add(to);
        neighbors.get(from).add(new Edge<V>(to, label));
    }

    public static class Edge<V> {
        private V vertex;
        private String label;

        public Edge(V v, String l) {
            vertex = v;
            label = l;
        }

        public V getVertex() {
            return vertex;
        }

        public String getLabel() {
            return label;
        }

        @Override
        public String toString() {
            return "Edge [vertex=" + vertex + ", label=" + label + "]";
        }
    }
}
