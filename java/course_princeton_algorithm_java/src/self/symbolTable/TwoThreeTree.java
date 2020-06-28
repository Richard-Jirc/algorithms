package self.symbolTable;

public class TwoThreeTree<Key extends Comparable<Key>, Value> {
    private class Node {
        Key leftKey, rightKey;
        Value leftVal, rightVal;
        boolean dual = true;
        Node left, mid, right;
        public Node(Key key, Value value) {
            leftKey = key;
            leftVal = value;
        }

        public boolean isDual() {
            boolean DUAL = true;
            return dual == DUAL;
        }
        public void insertTwo(Key key, Value value) {
            assert dual;
            if (leftKey.compareTo(key) == 0) leftVal = value;
            else {
                if (leftKey.compareTo(key) < 0) {
                    rightKey = key;
                    rightVal = value;
                } else {
                    rightKey = leftKey;
                    rightVal = leftVal;
                    leftKey = key;
                    leftVal = value;
                }
                dual = false;
            }
        }
        public String toString() {
            return "!";
        }
    }

    private Node root;

    /** Overloaded Constructors */
    public TwoThreeTree(Key key, Value value) {
        root = new Node(key, value);
    }
    public boolean isEmpty() {
        return root == null;
    }

    public Value search(Key key) {
        Node node = root;
        while (node != null) {
            if (node.isDual()) {
                if (key.compareTo(node.leftKey) < 0) node = node.left;
                if (key.compareTo(node.leftKey) > 0) node = node.right;
                else return node.leftVal;
            } else {
                if (key.compareTo(node.leftKey) < 0) node = node.left;
                if (key.compareTo(node.leftKey) == 0) return node.leftVal;
                if (key.compareTo(node.rightKey) < 0) node = node.mid;
                if (key.compareTo(node.rightKey) == 0) return node.rightVal;
                if (key.compareTo(node.rightKey) > 0) node = node.right;
            }
        }
        return null;
    }

    public void insert(Key key, Value value) {
        Node node = root;

        if (node.isDual()) node.insertTwo(key, value);
        else {
            
        }
    }

    public static void main(String[] args) {

    }
}
