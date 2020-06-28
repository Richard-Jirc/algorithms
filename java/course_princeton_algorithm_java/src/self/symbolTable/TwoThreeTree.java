package self.symbolTable;

public class TwoThreeTree<K extends Comparable<K>, V> {

    private Node23<K, V> root;

    public TwoThreeTree() { root = null; }
    public TwoThreeTree(K key, V value) {
        root = new Node23<>(key, value);
    }

    public V search(K key) {

    }


    public static void main(String[] args) {

    }
}
