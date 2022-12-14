import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;

public class DfsVisit<T> implements Serializable {
    // left as is for reference

    private Stack<Node<T>> workingStack;
    private Set<Node<T>> finished;

    public DfsVisit(){
        workingStack = new Stack<>();
        finished = new LinkedHashSet<>();
    }

    public Set<T> traverse(Graph<T> aGraph){
        workingStack.push(aGraph.getRoot());
        while (!workingStack.empty()){
            Node<T> removed = workingStack.pop();
            finished.add(removed);
             Collection<Node<T>> reachableNodes = aGraph.getReachableNodes(removed);
             for(Node<T> reachableNode :reachableNodes){
                 if (!finished.contains(reachableNode) &&
                 !workingStack.contains(reachableNode)){
                     workingStack.push(reachableNode);
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
