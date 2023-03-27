package Structures.Graphs;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class AdjacencyListGraph<TKey> implements Graph<TKey> {

  private Map<Vertex<TKey>, Set<Vertex<TKey>>> adjacencyList;

  public AdjacencyListGraph() {
    this.adjacencyList = new HashMap<>();
  }

  public void addAdjacent(Vertex<TKey> from, Vertex<TKey> adjacent) {

    if (!adjacencyList.containsKey(from)) {
      adjacencyList.put(from, new HashSet<>());
    }

    adjacencyList.get(from).add(adjacent);
  }

  public void removeAdjacent(Vertex<TKey> from, Vertex<TKey> adjacent) {
    // TODO: check if from exists (?)
    adjacencyList.get(from).remove(adjacent);
  }

  @Override
  public Iterator<Vertex<TKey>> getVertexesIterator() {
    return adjacencyList.keySet().iterator();
  }

  @Override
  public Iterator<Edge<TKey>> getEdgesIterator() {
    
    Set<Edge<TKey>> edges = new HashSet<>();

    for (Vertex<TKey> from : adjacencyList.keySet()) {
      for (Vertex<TKey> to : adjacencyList.get(from)) {
        edges.add(new Edge<>(from, to));
      }
    }

    return Collections.unmodifiableCollection(edges).iterator();
  }

  @Override
  public Vertex<TKey> findVertex(Object key) {

    for (Vertex<TKey> vertex : adjacencyList.keySet()) {
      if (vertex.getKey().equals(key)) {
        return vertex;
      }
    }

    return null;
  }

  @Override
  public Edge<TKey> findEdge(Object keyFrom, Object keyTo) {

    for (Vertex<TKey> from : adjacencyList.keySet()) {
      if (from.getKey().equals(keyFrom)) {
        for (Vertex<TKey> to : adjacencyList.get(from)) {
          if (to.getKey().equals(keyTo)) {
            return new Edge<TKey>(from, to);
          }
        }
      }
    }

    return null;
  }
  
}
