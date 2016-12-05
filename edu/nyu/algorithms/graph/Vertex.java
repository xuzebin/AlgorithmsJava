/**
 * Vertex of the graph.
 *
 * id is the name of the vertex.
 * d is the  distance from the source to the current Vertex / timestamp when a vertex is discovered.
 * color is the state of the vertex
 * t is the timestamp when a vertex is finished.
 * p is the predecessor of the current Vertex
 * neighbors is a list storing the adjacent vertices of a vertex.
 */

import java.util.List;
import java.util.LinkedList;

public class Vertex {

    final int id;

    int d;
    Color color;
    int f;
    Vertex p;
    List<Vertex> neighbors;
	
    public Vertex(int id) {
	this.id = id;
	this.d = -1;
	this.color = Color.WHITE;
	this.f = -1;
	this.p = null;
	this.neighbors = new LinkedList<>();
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