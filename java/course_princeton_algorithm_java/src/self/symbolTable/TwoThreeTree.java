package self.symbolTable;

public class TwoThreeTree<Key extends Comparable<Key>, Value> {
    private class Node {
        Key key;
        Value value;
        boolean dual = true;
        Node left, mid, right;
        public Node(Key k, Value v) {
            key = k;
            value = v;
        }
        public String toString() {
            return "!";
        }
    }
    private final boolean DUAL = true;
    private final boolean TRI = false;
    private Node root;

    /** Overloaded Constructors */
    public TwoThreeTree() {}
    public TwoThreeTree(Key key, Value value) {
        root = new Node(key, value);
    }
    private boolean isDual(Node node) {
        return node.dual == DUAL;
    }

    public static void main(String[] args) {

    }
}
