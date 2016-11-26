import java.util.Queue;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

public class Graph {

    public static void main(String[] args) {
	Map<Vertex, List<Vertex>> adjacencyLists = new HashMap<>();
	Vertex source = initAdjacencyLists(adjacencyLists);
	Graph graph = new Graph(adjacencyLists);
	
	bfs(graph, source);
    }

    /**
     * Adjacency List:
     * 1 -> 2 -> 5
     * 2 -> 1 -> 5 -> 3 -> 4
     * 3 -> 2 -> 4
     * 4 -> 2 -> 5 -> 3
     * 5 -> 4 -> 1 -> 2
     * 
     * return source of the graph
     */
    private static Vertex initAdjacencyLists(Map<Vertex, List<Vertex>> adjacencyLists) {
	Vertex v1 = new Vertex(1);
	Vertex v2 = new Vertex(2);
	Vertex v3 = new Vertex(3);
	Vertex v4 = new Vertex(4);
	Vertex v5 = new Vertex(5);

	List<Vertex> list1 = addToList(v2, v5);
	List<Vertex> list2 = addToList(v1, v5, v3, v4);
	List<Vertex> list3 = addToList(v2, v4);
	List<Vertex> list4 = addToList(v2, v5, v3);
	List<Vertex> list5 = addToList(v4, v1, v2);

	adjacencyLists.put(v1, list1);
	adjacencyLists.put(v2, list2);
	adjacencyLists.put(v3, list3);
	adjacencyLists.put(v4, list4);
	adjacencyLists.put(v5, list5);

	return v1;//return the first as the source.
    }

    private static List<Vertex> addToList(Vertex ... vs) {
	if (vs == null || vs.length == 0) {
	    throw new IllegalArgumentException("Vertex objects null or empty");
	}
	List<Vertex> list = new ArrayList<>(vs.length);
	for (Vertex v : vs) {
	    list.add(v);
	}
	return list;
    }


    private static enum Color {
	WHITE,
	GRAY,
        BLACK
    }

    /**
     * @id give each vertex an id for printing
     * @d distance from the source to the current Vertex
     * @color the color of the vertex
     * @p predecessor of the current Vertex
     */
    private static class Vertex {
	final int id;
	int d;
	Color color;
	Vertex p;

	public Vertex(int id) {
	    this.id = id;
	    this.d = Integer.MAX_VALUE;
 	    this.color = Color.WHITE;
	    this.p = null;
	}

	@Override public boolean equals(Object o) {
	    if (this == o) {
		return true;
	    }
	    if (o == null || (getClass() != o.getClass())) {
		return false;
	    }
	    Vertex other = (Vertex) o;
	    return (id == other.id);
	}

	@Override public int hashCode() {
	    int result = 17;
 	    result = result * 31 + id;
	    return result;
	}
    }
    
    public final Map<Vertex, List<Vertex>> adj;

    public Graph(Map<Vertex, List<Vertex>> adj) {
	this.adj = adj;
    }

    public static void bfs(Graph g, Vertex s) {
	if (g == null || s == null) {
	    throw new IllegalArgumentException("Graph object or Vertex object null");
	}
	s.color = Color.GRAY;
	s.d = 0;
	s.p = null;

	Queue<Vertex> q = new LinkedList<>();
	q.add(s);
	while (!q.isEmpty()) {
	    Vertex u = q.poll();
	    System.out.println(u.id);
	    List<Vertex> adjVertices = g.adj.get(u);
	    for (Vertex v : adjVertices) {
		if (v.color == Color.WHITE) {
		    v.color = Color.GRAY;
		    v.d = u.d + 1;
		    v.p = u;
		    q.add(v);
		}
	    }
	    u.color = Color.BLACK;
	}
    }
}