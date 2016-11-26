import java.util.Queue;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

/**
 * The Graph structure represented as adjacency lists.
 */
public class AdjacencyListGraph {

    public static void main(String[] args) {
	if (args == null || args.length != 2) {
	    throw new IllegalArgumentException("Please input 2 arguments for source and destination indices(1 - 5)");
	}

	System.out.printf("An undirected graph:%n1------2%n|     /|\\%n|   /  | 3%n| /    |/%n5------4%n");

	Map<Vertex, List<Vertex>> adjacencyLists = new HashMap<>();

	Vertex[] vertices = initAdjacencyLists(adjacencyLists);
	AdjacencyListGraph graph = new AdjacencyListGraph(adjacencyLists);
	
	int srcId = Integer.parseInt(args[0]);
	Vertex src = vertices[srcId - 1];
	bfs(graph, src);
	
	int destId = Integer.parseInt(args[1]);
	Vertex dest = vertices[destId - 1];
 	System.out.printf("Path from %s to %s: %n", src, dest);
	printPath(graph, src, dest);
    }
    /**
     * An undirected graph:
     * 1------2
     * |     /|\
     * |   /  | 3
     * | /    |/
     * 5------4
     *
     * The adjacency Lists of the above undirected graph:
     * 1 -> 2 -> 5
     * 2 -> 1 -> 5 -> 3 -> 4
     * 3 -> 2 -> 4
     * 4 -> 2 -> 5 -> 3
     * 5 -> 4 -> 1 -> 2
     * 
     * @adjacencyLists a hashtable that maps the vertex to its adjacency list
     * @return array of Vertex
     */
    private static Vertex[] initAdjacencyLists(Map<Vertex, List<Vertex>> adjacencyLists) {
	Vertex[] vs = new Vertex[5];
	for (int i = 0; i < 5; ++i) {
	    vs[i] = new Vertex(i + 1);
	}
	List<Vertex> list1 = addToList(vs, 2, 5);
	List<Vertex> list2 = addToList(vs, 1, 5, 3, 4);
	List<Vertex> list3 = addToList(vs, 2, 4);
	List<Vertex> list4 = addToList(vs, 2, 5, 3);
	List<Vertex> list5 = addToList(vs, 4, 1, 2);

	adjacencyLists.put(vs[0], list1);
	adjacencyLists.put(vs[1], list2);
	adjacencyLists.put(vs[2], list3);
	adjacencyLists.put(vs[3], list4);
	adjacencyLists.put(vs[4], list5);
	return vs;
    }

    private static List<Vertex> addToList(Vertex[] vs, Integer ... ids) {
	if (vs == null || vs.length == 0 || ids == null || ids.length == 0) {
	    throw new IllegalArgumentException("Input null or empty");
	}
	List<Vertex> list = new ArrayList<>(vs.length);
	for (Integer id : ids) {
	    list.add(vs[id - 1]);
	}
	return list;
    }


    /**
     * @id id of each vertex for printing
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
	    this.d = -1;
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

	@Override public String toString() {
	    return String.valueOf(id);
	}
    }

    private static enum Color {
	WHITE,
	GRAY,
        BLACK
    }
    
    public final Map<Vertex, List<Vertex>> adj;

    public AdjacencyListGraph(Map<Vertex, List<Vertex>> adj) {
	this.adj = adj;
    }

    public static void bfs(AdjacencyListGraph g, Vertex s) {
	if (g == null || s == null) {
	    throw new IllegalArgumentException("Graph object or Vertex object null");
	}
	s.color = Color.GRAY;
	s.d = 0;
	s.p = null;

	Queue<Vertex> q = new LinkedList<>();
	q.add(s);
	System.out.printf("BFS: ");
	while (!q.isEmpty()) {
	    Vertex u = q.poll();
	    System.out.printf("%s ", u);
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
	System.out.printf("%n");
    }

    public static void printPath(AdjacencyListGraph g, Vertex s, Vertex v) {
	if (v == s) {
	    System.out.printf("%s%n", s);
	} else if (v.p == null) {
	    System.out.printf("no path from %d to %d exists.%n", s.id, v.id);
	} else {
	    printPath(g, s, v.p);
	    System.out.printf("%s%n", v);
	}
	
    }
}