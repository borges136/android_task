import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/*
    A concrete class that extends Abstract Matrix.
    Adjacency matrices are generally used for representing edges between nodes
    in a graph. For instance, if an adjacency matrix is
       A B C
     A 0 1 0
     B 1 0 0
     C 1 0 0
     there will be edges A->B, B->A, C->A

 */
public class AdjacencyMatrix extends AbstractMatrix {
    public AdjacencyMatrix(int numOfRows, int numOfColumns) {
        super(numOfRows, numOfColumns);
    }

    public AdjacencyMatrix(int[][] array) {
        super(array);
    }

    @Override
    public Collection<Index> getNeighbors(Index index) { //implementing abstract methods
        // LinkedHashSet maintains insertion order and enables contains in O(1)
        Set<Index> neighbors = new LinkedHashSet<>();
        int currentValue;
        //add all indexes in same row
        for (int i=0; i < this.twoDimensionalArray.length; i++) {
            if (i != index.getRow()) //do not add self
                neighbors.add(new Index(index.row, i));
        }

        return neighbors;
    }

    @Override
    public String toString() {
        return "Adjacency Matrix: \n" + super.toString();
    }

    @Override
    public boolean isValueReachable(Index index) {
        // reachable only when reciprocal - both the value at index AND the value
        // for the reversed index must be equal to one
       return getValue(index)==1 && getValue(index.reversed()) == 1;
    }
}