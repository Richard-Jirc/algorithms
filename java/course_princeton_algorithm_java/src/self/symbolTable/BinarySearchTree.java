package self.symbolTable;

import edu.princeton.cs.algs4.Queue;

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
        Node result = search(root, key);
        if (result != null) return result.value;
        else return null;
    }
    /**GET method HELPER
     */
    private Node search(Node node, Key key) {
        if (node == null) return null;
        if (node.key.compareTo(key) == 0) return node;
        if (node.key.compareTo(key) < 0) return search(node.left, key);
        else return search(node.right, key);
    }

    public void put(Key key, Value value) {

    }
    private void allocate(Node node, Key key, Value value) {

    }

    public Iterable<Value> iterable() {
        Queue<Value> queue = new Queue<>();
        
    }


    public static void main(String[] args) {

    }
}
