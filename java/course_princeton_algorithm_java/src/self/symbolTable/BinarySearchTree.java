package self.symbolTable;

public class BinarySearchTree<Key extends Comparable<Key>, Value> {
    Node root;
    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        public Node() {

        }
    }
    public BinarySearchTree() {

    }
    public boolean isEmpty() {
        return root == null;
    }

    /**GET method
     * perform binary search for a certain key.
     * @return Value if found, null if not exist.
     */
    public Value get(Key key) {
        
    }


    public static void main(String[] args) {

    }
}
