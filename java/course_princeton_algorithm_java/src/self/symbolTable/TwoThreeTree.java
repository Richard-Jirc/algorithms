package self.symbolTable;

public class TwoThreeTree<K extends Comparable<K>, V> {

    private Node23<K, V> root;

    public TwoThreeTree(K key, V value) {
        root = new Node(key, value);
    }

    public V search(K key) {
        Node node = root;
        while (node != null) {
            if (node.isDual()) {
                if (key.compareTo(node.lKey) < 0) node = node.l;
                if (key.compareTo(node.lKey) > 0) node = node.r;
                else return node.lVal;
            } else {
                if (key.compareTo(node.lKey) < 0) node = node.l;
                if (key.compareTo(node.lKey) == 0) return node.lVal;
                if (key.compareTo(node.rKey) < 0) node = node.m;
                if (key.compareTo(node.rKey) == 0) return node.rVal;
                if (key.compareTo(node.rKey) > 0) node = node.r;
            }
        }
        return null;
    }

    public void insert(K key, V value) {
    }

    public static void main(String[] args) {

    }
}
