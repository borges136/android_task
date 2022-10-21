import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class StandardMatrix extends AbstractMatrix {
    public StandardMatrix(int numOfRows, int numOfColumns) {
        super(numOfRows, numOfColumns);
    }

    public StandardMatrix(int[][] array) {
        super(array);
    }
    /*
    1 0 0
    1 1 0
    0 1 1
     */
    @Override
    public Collection<Index> getNeighbors(Index index) {
        // LinkedHashSet maintains insertion order and enables contains in O(1)
        Set<Index> neighbors = new LinkedHashSet<>();
        int currentValue;
        try{
            currentValue = this.twoDimensionalArray[index.getRow()-1][index.getColumn()];
            neighbors.add(new Index(index.getRow()-1, index.getColumn()));
        }catch(IndexOutOfBoundsException iobe){}
        try{
            currentValue = this.twoDimensionalArray[index.getRow()+1][index.getColumn()];
            neighbors.add(new Index(index.getRow()+1, index.getColumn()));
        }catch(IndexOutOfBoundsException iobe){}
        try{
            currentValue = this.twoDimensionalArray[index.getRow()][index.getColumn()-1];
            neighbors.add(new Index(index.getRow(), index.getColumn()-1));
        }catch(IndexOutOfBoundsException iobe){}
        try{
            currentValue = this.twoDimensionalArray[index.getRow()][index.getColumn()+1];
            neighbors.add(new Index(index.getRow(), index.getColumn()+1));
        }catch(IndexOutOfBoundsException iobe){}

        return neighbors;
    }

    @Override
    public String toString() {
        return super.toString() + "Standard";
    }

    @Override
    public boolean isValueReachable(Index index) {
        return getValue(index)==1;
    }
}
