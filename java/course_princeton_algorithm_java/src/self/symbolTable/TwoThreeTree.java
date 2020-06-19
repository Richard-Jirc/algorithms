package self.symbolTable;

public class TwoThreeTree<Key extends Comparable<Key>, Value> {
    private class Node {
        Key key1, key2;
        Value value1, value2;
        boolean dual = true;
        Node left, mid, right;
        public Node(Key key, Value value) {
            key1 = key;
            value1 = value;
        }
        public String toString() {
            return "!";
        }
        public boolean isDual() {
            boolean DUAL = true;
            return dual == DUAL;
        }
        public void insertTwo(Key key, Value value) {
            assert dual;
            if (key1.compareTo(key) == 0) value1 = value;
            else {
                if (key1.compareTo(key) < 0) {
                    key2 = key;
                    value2 = value;
                } else {
                    key2 = key1;
                    value2 = value1;
                    key1 = key;
                    value1 = value;
                }
                dual = false;
            }
        }
    }

    private Node root;

    /** Overloaded Constructors */
    public TwoThreeTree() {}
    public TwoThreeTree(Key key, Value value) {
        root = new Node(key, value);
    }


    public Value search(Key key) {
        Node node = root;
        while (node != null) {
            if (node.isDual()) {
                if (key.compareTo(node.key1) < 0) node = node.left;
                if (key.compareTo(node.key1) > 0) node = node.right;
                else return node.value1;
            } else {
                if (key.compareTo(node.key1) < 0) node = node.left;
                if (key.compareTo(node.key1) == 0) return node.value1;
                if (key.compareTo(node.key2) < 0) node = node.mid;
                if (key.compareTo(node.key2) == 0) return node.value2;
                if (key.compareTo(node.key2) > 0) node = node.right;
            }
        }
        return null;
    }

    public void insert(Key key, Value value) {
        Node node = root;
    }

    public static void main(String[] args) {

    }
}
