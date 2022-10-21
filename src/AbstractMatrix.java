import java.util.Arrays;
import java.util.Random;

/*

Left almost as provided.
Added an abstract isValueReachable method to allow concrete classes to determine
whether a value at a certain index can be reached.

In general, an abstract class is a class that can declare and implement
common actions and data structures for extending concrete classes
enables avoiding duplicate code and encapsulation
On the other hand, it can declare abstract methods that must be implemented
in extending classes
 */
public abstract class AbstractMatrix implements Matrix{
    int[][] twoDimensionalArray;

    @Override
    public int getValue(Index index) {
        return twoDimensionalArray[index.getRow()][index.getColumn()];
    }
    public AbstractMatrix(int numOfRows,int numOfColumns){
        twoDimensionalArray = new int[numOfRows][numOfColumns];
    }


    public AbstractMatrix(int[][] array) {
        this.twoDimensionalArray = new int[array.length][];
        for (int row = 0; row < array.length; row++) {
            this.twoDimensionalArray[row] = new int[array[row].length];
            System.arraycopy(array[row], 0, this.twoDimensionalArray[row], 0,
            this.twoDimensionalArray[row].length);
        }
    }


    public void setValue(Index index, int value) {
        twoDimensionalArray[index.getRow()][index.getColumn()] = value;
    }

    @Override
    public String toString() {
        StringBuilder matrixSb = new StringBuilder();
        for(int[] row: twoDimensionalArray){
            matrixSb.append(Arrays.toString(row));
//            matrixSb.append(row.toString());
            matrixSb.append("\n");
        }
        return matrixSb.toString();
    }

    public int[][] getTwoDimensionalArray() {
        return twoDimensionalArray;
    }

    // can a value at a certain index be reached?
    public abstract boolean isValueReachable(Index index);
}
