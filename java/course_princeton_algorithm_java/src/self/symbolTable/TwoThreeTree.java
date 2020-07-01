package self.symbolTable;

public class TwoThreeTree<K extends Comparable<K>, V> {
    private Node23<K, V> root;

    public TwoThreeTree() { root = null; }
    public boolean isEmpty() { return root == null; }

    /** INSERTION */
    public void insert(K key, V value) {
        if (isEmpty()) root = new Node23<>(key, value);
        else {
            Node23<K, V> node = root;
            while(node != null) {
                if (node.check(key, value)) break; // update the existing.
                if (!node.isLeaf()) node = node.next(key); // if not leaf node, keep searching deeper.
                else if (node.isDual()) node.insertDual(key, value);
                else {

                }
            }
        }
    }

    /**SEARCH*/
    public V search(K key) {
        if (isEmpty()) return null;
        Node23 node = root;
        return null;
    }

    public static void main(String[] args) {

    }
}
