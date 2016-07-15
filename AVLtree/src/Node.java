

/**
 * 
 * AVL tree Node
 *
 **/

public class Node {
	/** The ID stored in the node. */
	public int ID;

	/** The count stored in the node. */
	public int count;
	
	/** The node's parent. */
	public Node parent;
	
	/** The node's left child. */
	public Node left;

	/** The node's right child. */
	public Node right;
	
	/** The Node's height. */
	public int height;
	
	/** Balance Variable */
	public int balance;

	/**
	 * Initializes a node with the key and the data and makes other pointers
	 * null. The Balance Factor is initialized to be 0.
	 *
	 * @param ID
	 *            ID of the new node.
	 * @param count
	 *            Count of the node.
	 */
	public Node(int ID, int count) {
		this.ID = ID;
		this.count = count;
		this.parent = null;
		this.left = null;
		this.right = null;
		//sandy added
		balance = 0;
	} // Node

	public boolean isLeaf() {
		return left == null && right == null;
	}
	
	// Setter functions to set the count
	public void setCount(int count) {
        this.count = count;
    }
	
	// Setter functions to set the ID
	public void setID(int ID) {
        this.ID = ID;
    }
	
	/**
     * Returns the ID of a node.
     * @return The ID
     */
    public int getID() {
        return ID;
    }

    /**
     * Returns the count value stored in the node.
     * @return The value of the node
     */
    public int getCount() {
        return count;
    }
	
//	/**
//	 * Returns the node as a <code>String</code>.
//	 */
//	@Override
//	public String toString() {
//		return ID+ " ("+height+")";
//	}// printNode
}