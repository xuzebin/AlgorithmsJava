import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AdjacencyMatrixTest {
    @Test public void testTranspose() {
	AdjacencyMatrix am = new AdjacencyMatrix();
	/**
	 * 1 0 0 1
	 * 0 1 1 0
	 * 0 0 1 0
	 * 1 0 0 0
	 */
	int[][] mat = { {1, 0, 0, 1}, {0, 1, 1, 0}, {0, 0, 1, 0}, {1, 0, 0, 0}};
	am.transpose(mat);
	int[][] transMat = { {1, 0, 0, 1}, {0, 1, 0, 0}, {0, 1, 1, 0}, {1, 0, 0, 0} };
	assertEquals(mat, transMat);
    }

    @Test public void testUniversalSinkFalse() {
	AdjacencyMatrix am = new AdjacencyMatrix();
	/**
	 * 0 1 1 1 1
	 * 0 0 0 1 1
	 * 0 1 0 1 1
	 * 0 1 0 0 0
	 * 0 0 0 1 0
	 */
	int[][] mat = { {0,1,1,1,1}, {0,0,0,1,1}, {0,1,0,1,1}, {0,1,0,0,0},{0,0,0,1,0} };
	boolean hasSink = am.containsUniversalSink(mat);
	assertEquals(hasSink, false);
    }

    @Test public void testUniversalSinkTrue() {
	AdjacencyMatrix am = new AdjacencyMatrix();
	/**
	 * 0 1 1 1 1
	 * 0 0 0 1 1
	 * 0 1 0 1 1
	 * 0 0 0 0 0
	 * 0 0 0 1 0
	 */
	int[][] mat = { {0,1,1,1,1}, {0,0,0,1,1}, {0,1,0,1,1}, {0,0,0,0,0}, {0,0,0,1,0} };
	boolean hasSink = am.containsUniversalSink(mat);
	assertEquals(hasSink, true);
    }
}