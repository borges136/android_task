import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class represents a Social Graph comprised of Nodes that wrap Profiles.
 * It implements the Graph interface.
 * An Adjacency Matrix is used to represent connections (friendships) between profiles.
 * The nodes are kept in a list.
 * In order to calculate path between two node and
 * implement friend request offers/accepts, we need the ability to find graph nodes
 * according to its content (either by profile id or by the Profile itself).
 * For this, we keep a hash map of profileId -> node index,
 * which allows for O(1) lookup of nodes by profileId, without the need to traverse the graph.
 *
 * */

public class SocialGraph implements Graph<Profile> {
    private ArrayList<Node<Profile>> nodes;
    private HashMap<Long, Integer> profileIdToNodeIndex;
    private AdjacencyMatrix adjMatrix;

    // Constructor that receives a collection of profiles
    public SocialGraph(Collection<Profile> profiles) {
        this.nodes = new ArrayList<Node<Profile>>(profiles.size());
        this.profileIdToNodeIndex = new HashMap<>();

        int profileCounter = 0;
        for (Profile p : profiles) {
            // for each received profile, add it to the list
            // and add an entry of its profileID -> node index to the map
            Node<Profile> profileNode = new Node<>(p);
            this.nodes.add(profileCounter, profileNode);
            this.profileIdToNodeIndex.put(p.getId(), profileCounter);
            profileCounter++;
        }
        this.adjMatrix = new AdjacencyMatrix(nodes.size(), nodes.size());
    }

    // find node by profileId
    private int nodeIndexFor(Long profileID) {
        return profileIdToNodeIndex.get(profileID);
    }

    // find node by profile itself
    private int nodeIndexFor(Profile profile) {
        return nodeIndexFor(profile.getId());
    }

    // represents a friendship offer. marks '1' at the appropriate position
    // in adjacency matrix
    public void offerFriendship(Profile offerer, Profile offeree) {
        Index index = new Index(nodeIndexFor(offerer), nodeIndexFor(offeree));
        adjMatrix.setValue(index, 1);
        System.out.println(adjMatrix);

    }

    // represents friendship acceptance. essentially a reverse friendship offer
    public void acceptFriendshipOffer(Profile accepter, Profile initiator) {
        offerFriendship(accepter, initiator);
    }


    // finds shortest path between two profiles. uses BFS Shortest path algorithm.
    // returns the connecting profiles, excluding the two in the beginning and the end
    public Collection<Profile> getConnectingProfiles(Long sourceProfileID, Long targetProfileID) {
        BfsShortestPath<Profile> algorithm = new BfsShortestPath<>();
        Set<Profile> connectingProfiles = algorithm.getPath(this, nodes.get(nodeIndexFor(sourceProfileID)), nodes.get(nodeIndexFor(targetProfileID)));
        //System.out.println("Connecting Profiles:\n" + connectingProfiles);
        return connectingProfiles;
    }

    // checks whether a path exists between two profiles
    public boolean doesPathExist(Long sourceProfileID, Long targetProfileID) {
        Collection<Profile> connectingProfiles =  getConnectingProfiles(sourceProfileID, targetProfileID);
        if (connectingProfiles == null) // no path found
            return false;
        return true;
    }

    // checks the length of the (shortest) path between two profiles,
    // by retrieving the Connecting Profiles collection between the two nodes,
    // and using the collection's size in order to calculate path lenth
    public Integer pathLength(Long sourceProfileID, Long targetProfileID) {
        Collection<Profile> connectingProfiles =  getConnectingProfiles(sourceProfileID, targetProfileID);
        if (connectingProfiles == null)
            return null;
        return connectingProfiles.size() + 2; //including start node and end node in count, as required
    }

    @Override
    // for Graph interface
    public Node<Profile> getRoot() {
        return nodes.get(ThreadLocalRandom.current().nextInt(nodes.size()));
    }

    @Override
    // finds all the nodes reachable from a specific node,
    // by checking the adjacency matrix and determining which of the neighboring
    // nodes are actually reachable

    public Collection<Node<Profile>> getReachableNodes(Node<Profile> aNode) {
        List<Node<Profile>> reachableNodes = new ArrayList<>();
        int nodeIndex = nodeIndexFor(aNode.getData());
        Index matrixIndex = new Index(nodeIndex, 0);
        for(Index neighboringMatrixIndex: adjMatrix.getNeighbors(matrixIndex)) {
            int neighboringNodeIndex = neighboringMatrixIndex.getColumn();
            Node<Profile> neighboringNode = nodes.get(neighboringNodeIndex);
            if (neighboringNode != aNode && adjMatrix.isValueReachable(neighboringMatrixIndex)) {
                reachableNodes.add(neighboringNode);
            }
        }
        return reachableNodes;
    }
}
