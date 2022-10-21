import java.util.*;

public class SocialDemo {
    public static void main(String[] args) {
        Collection<Profile> profiles = new LinkedHashSet<>();
        // create some profiles
        Profile pAlice = new Profile("Alice", "Cooper",  70D);
        Profile pBob = new Profile("Bob", "Dylan",  70D);
        Profile pCarole = new Profile("Carole", "King",  70D);
        Profile pDiddy = new Profile("Puff", "Daddy",  70D);
        Profile pEric = new Profile("Eric", "Clapton",  70D);
        profiles.add(pAlice);
        profiles.add(pBob);
        profiles.add(pCarole);
        profiles.add(pDiddy);
        profiles.add(pEric);

        // create a social graph from these profiles
        SocialGraph g = new SocialGraph(profiles);

        // let's get the friendship train going
        g.offerFriendship(pAlice, pCarole);
        g.offerFriendship(pBob, pCarole);
        g.offerFriendship(pDiddy, pCarole);
        g.offerFriendship(pEric, pDiddy);
        g.acceptFriendshipOffer(pCarole, pAlice);
        g.acceptFriendshipOffer(pCarole, pBob);
        g.acceptFriendshipOffer(pDiddy, pEric);

        // Now, Alice is friends with Carole;
        // Bob is friends with Carole
        // Carole is friends with Alice and Bob
        // P Diddy offered friendship to Carole, but she hasn't accepted it, so they are not friends
        // And Eric is friends with P Diddy

        // Check for a path between Alice and Eric
        System.out.println("Path exists?: " + g.doesPathExist(pAlice.getId(), pEric.getId()));
        //        Path exists?: false
        System.out.println("Path length: " + g.pathLength(pAlice.getId(), pEric.getId()));
        //        Path length: null
        System.out.println("Connecting profiles: " + g.getConnectingProfiles(pAlice.getId(), pEric.getId()));
        //       Connecting profiles: null
        // as of now, there is no path between Alice and Eric

        // let us add a friendship between Bob and Diddy
        g.offerFriendship(pBob, pDiddy);
        g.acceptFriendshipOffer(pDiddy, pBob);
        // Check for a path between Alice and Eric
        System.out.println("Path exists?: " + g.doesPathExist(pAlice.getId(), pEric.getId()));
        //        Path exists?: true
        System.out.println("Path length: " + g.pathLength(pAlice.getId(), pEric.getId()));
        //        Path length: 5
        System.out.println("Connecting profiles: " + g.getConnectingProfiles(pAlice.getId(), pEric.getId()));
        // Connecting profiles: [Profile{firstName='Puff', surName='Daddy', age=70.0, id=3, uuid=3d11096c-3eff-3f6d-8fbc-8d101e7dd454}, Profile{firstName='Bob', surName='Dylan', age=70.0, id=1, uuid=d58fee37-73b4-3c54-a43c-367c7f13f9eb}, Profile{firstName='Carole', surName='King', age=70.0, id=2, uuid=7617621b-69ad-3e17-bae2-895259b35e05}]
        // (reversed)
        // so now the path is Alice -> Carole -> Bob -> PDiddy -> Eric


        // finally, Carole responds and accepts P Diddy's friendship offer
        g.acceptFriendshipOffer(pCarole, pDiddy);
        // Check for a path between Alice and Eric
        System.out.println("Path exists?: " + g.doesPathExist(pAlice.getId(), pEric.getId()));
        //        Path exists?: true
        System.out.println("Path length: " + g.pathLength(pAlice.getId(), pEric.getId()));
        //        Path length: 4
        System.out.println("Connecting profiles: " + g.getConnectingProfiles(pAlice.getId(), pEric.getId()));
        // Connecting profiles: [Profile{firstName='Puff', surName='Daddy',
        // age=70.0, id=3, uuid=3d11096c-3eff-3f6d-8fbc-8d101e7dd454},
        // Connecting profiles: [Profile{firstName='Puff', surName='Daddy', age=70.0, id=3, uuid=3d11096c-3eff-3f6d-8fbc-8d101e7dd454}, Profile{firstName='Carole', surName='King', age=70.0, id=2, uuid=7617621b-69ad-3e17-bae2-895259b35e05}]
        // (reversed)
        // so now the path is Alice -> Carole -> PDiddy -> Eric
        // without having to go through Bob

    }
}