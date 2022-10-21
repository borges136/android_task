import java.util.Collection;

public interface Matrix {
    /*
    Common functionality between all binary matrices:
    1. Given an Index object, get its value
    2. Given an Index object, get its neighbors
    Java includes a framework called the Collection framework
    In general, the Collection framework includes 4 main interfaces with concrete
    implementations:
    1. Set
    2. List
    3. Deque
    4. Map
     */
    public abstract int getValue(Index index);
    public abstract Collection<Index> getNeighbors(Index index);
}
