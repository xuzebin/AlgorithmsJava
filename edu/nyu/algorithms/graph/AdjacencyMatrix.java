public class AdjacencyMatrix {

    //assume square matrix. No weights(only 1 or 0)
    //Time: O(V^2) where V is the number of vertices.
    public void transpose(int[][] m) {
	int v = m.length;
	for (int i = 0; i < v; ++i) {
	    for (int j = i + 1; j < v; ++j) {
		if (m[i][j] == 1 && m[j][i] == 0) {
		    m[i][j] = 0;
		    m[j][i] = 1;
		}
	    }
	}
    }
    
    //Universal Sink: a vertex with in-dgree |V| - 1 and out-degree 0.
    //Time O(V), given an adjacency matrix for G.
    public boolean containsUniversalSink(int[][] m) {
	if (m == null || m.length == 0 || m[0].length == 0) {
	    throw new IllegalArgumentException("Matrix object null");
	}
	int i = 0, j = 0;
	int v = m.length;
	while (i < v && j < v) {
	    if (m[i][j] == 1) {
		i++;
	    } else {
		j++;
	    }
	}
	if (i >= v) {
	    return false;
	} else {
	    return isSink(m, i);
	}
    }

    private boolean isSink(int[][] m, int k) {
	int n = m.length;
	if (m == null || n == 0) {
	    throw new IllegalArgumentException("Matrix object null");
	}
	for (int i = 0; i < n; ++i) {
	    if (m[k][i] == 1) {
		return false;
	    }
	}
	for (int i = 0; i < n; ++i) {
	    if (m[i][k] == 0 && i != k) {
		return false;
	    }
	}
	return true;
    }
}