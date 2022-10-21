import java.io.Serializable;
import java.util.*;

public class BfsShortestPath<T> implements Serializable {
    private Queue<Node<T>> workingQueue;
    private Set<Node<T>> finished;
    private LinkedHashSet<Node<T>> visited;

    public BfsShortestPath(){
        workingQueue = new LinkedList<>();
        finished = new LinkedHashSet<>();
        visited = new LinkedHashSet<>();
    }

    // Find shortest path on a graph between startNode and endNode
    // Complexity is O(V +E)
    public Set<T> getPath(Graph<T> aGraph, Node<T> startNode, Node<T> endNode) {
        // Traverse in a fashion similar to common BFS traversal, but stop when
        // end node (our target) is reached
        Set<T> shortestPathSet = new LinkedHashSet<>();
        visited.add(startNode);
        workingQueue.add(startNode);
        while(!workingQueue.isEmpty()) {
            Node<T> workingNode = workingQueue.poll();
            Collection<Node<T>> reachableNodes = aGraph.getReachableNodes(workingNode);
            for (Node<T> node : reachableNodes) {
                if (!visited.contains(node)) {
                    node.setParent(workingNode);
                    visited.add(node);
                    if (node == endNode)
                        break;
                    else
                        workingQueue.add(node);
                }
            }
        }

        if (visited.contains(endNode)) {
            Node<T> visitedNode = endNode;
            // recreate shortest path parent by parent
            while((visitedNode = visitedNode.getParent()) != null) {
                shortestPathSet.add(visitedNode.getData());
            }
        }
        visited.clear();
        workingQueue.clear();
        if (shortestPathSet.isEmpty())
            return null; // as per requirements
        // if shortest path exists, we need it without the starting node, so
        // we remove it. (the end node was never added to the list)
        shortestPathSet.remove(startNode.getData());
        return shortestPathSet;
    }

    // General BFS traversal - left here for reference
    public Set<T> traverse(Graph<T> aGraph){
        workingQueue.add(aGraph.getRoot());
        while (!workingQueue.isEmpty()){
            Node<T> removed = workingQueue.remove();
            finished.add(removed);
            Collection<Node<T>> reachableNodes = aGraph.getReachableNodes(removed);
            for(Node<T> reachableNode :reachableNodes){
                if (!finished.contains(reachableNode) &&
                        !workingQueue.contains(reachableNode)){
                    workingQueue.add(reachableNode);
                }
            }
        }
        Set<T> blackSet = new LinkedHashSet<>();
        for (Node<T> node: finished)
            blackSet.add(node.getData());
        finished.clear();
        return blackSet;
    }
}
