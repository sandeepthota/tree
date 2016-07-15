/*
public class AVLTreeEventCounter {

}*/

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Sandeep Thota
 * This class uses the AVL Tree data structure defined in AVLTree.java to implement an event counter. The
 * event counter supports the below operations:
 * 1. Build(List)
 * 1. Increase(theID, m)
 * 2. Reduce(theID, m)
 * 3. Count(theID)
 * 4. InRange(ID1, ID2)
 * 5. Next(theID)
 * 6. Previous(theID)
 * @author Sandeep Thota
 */
public class AVLTreeEventCounter {
    AVLTree avlTree = new AVLTree();

    /**
     * Builds the event counter from the list of AVLTreeNode objects. This runs in O(n) time.
     * @param eventArrayList The list of events
     */
    public void buildEventCounter(ArrayList<Node> eventArrayList) {
    	if(eventArrayList != null && eventArrayList.size() > 0) {
            Iterator<Node> iterator = eventArrayList.iterator();
            avlTree.buildTreeFromSortedList(eventArrayList.size(), iterator);
        }
    }

    /**
     * Increases the count of the event ID by amount. If ID is not present, inserts it. Print the final count. This
     * runs in O(lg n) time.
     * @param ID The event ID whose count has to be increased.
     * @param amount The amount by which the count has to be increase.
     * @return The final count of the event ID after addition.
     */
    public int increase(int ID, int amount) {
        Node avlTreeNode = avlTree.search(ID);
        if (avlTreeNode != null) {
            avlTreeNode.setCount(avlTreeNode.getCount() + amount);
            return avlTreeNode.getCount();
        } else {
            avlTree.insert(ID, amount);
            return amount;
        }
    }

    /**
     * Decreases the count of the event ID by amount. If the ID's count becomes less than or equal to 0, removes the ID
     * from the counter. Prints the count of the ID after deletion or 0 if the ID is removed or is not present. This
     * runs in O(lg n) time.
     * @param ID The event ID whose count is to be decreased.
     * @param amount The amount by which to decrease.
     * @return The final count or 0.
     */
    public int reduce(int ID, int amount) {
        Node avlTreeNode = avlTree.search(ID);
        if(avlTreeNode != null) {
            if(amount >= avlTreeNode.getCount()) {
                avlTree.remove(avlTreeNode.ID);
                return 0;
            } else {
                avlTreeNode.setCount(avlTreeNode.getCount() - amount);
                return avlTreeNode.getCount();
            }
        } else {
            return 0;
        }
    }

    /**
     * Searches the counter for the event ID and returns the count. Returns 0, if not present.
     * @param ID The event ID whose count is to be returned.
     * @return The count of the event or 0.
     */
    public int count(int ID) {
        Node avlTreeNode = avlTree.search(ID);
        if(avlTreeNode != null) {
            return avlTreeNode.getCount();
        } else {
            return 0;
        }
    }

    /**
     * Returns the total count for IDs between ID1 and ID2.
     * @param ID1 The left limit of the range.
     * @param ID2 The right limit of the range.
     * @return The total count.
     */
    public ArrayList<Node> inRange(int ID1, int ID2) {
        Node rootNode = avlTree.root;
        ArrayList<Node> nodesInRange = new ArrayList<>();
        nodesInRange = avlTree.rangeSearch(rootNode, ID1, ID2, nodesInRange);
        return nodesInRange;
    }

    /**
     * Returns the event with the lowest ID that is greater than ID.
     * @param ID The event ID whose next is to be found.
     * @return The next event
     */
    public Node next(int ID) {
        return avlTree.treeSuccessor(avlTree.root, ID);
    }

    /**
     * Returns the event with the greatest ID that is less than ID.
     * @param ID The ID whose previous is to be found.
     * @return The previous event.
     */
    public Node previous(int ID) {
        return avlTree.treePredecessor(avlTree.root, ID);
    }
}
