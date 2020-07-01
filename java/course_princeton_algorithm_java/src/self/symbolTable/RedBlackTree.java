package self.symbolTable;


/** Left-Leaning Red-Black Tree:
 *  trying out the version mentioned in the course.
 */
public class RedBlackTree<K extends Comparable<K>, V> {
    private final boolean RED = true;
    private final boolean BLACK = false;

    private class Node {
        private K key;
        private V val;
        private Node left, right, parent;
        private boolean color; // color of link that points to its parent.
        public Node(K k, V v, boolean col) {
            key = k;
            val = v;
            color = col;
        }
        public boolean isRED() { return color == RED; }
        public void makeRED() { color = RED; }
    }
    private Node root;

    public RedBlackTree() {}
    public boolean isEmpty() { return root == null; }

    /**GET method
     * @param key to search for
     * @return value of corresponding key, {@code null} if key is {@code null} or do not exist.
     */
    public V get(K key) {
        if (key == null) return null;
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.val;
        }
        return null;
    }

    /**INSERTION method.
     * with a helper recursive function put().
     * @param key to insert
     * @param value to insert
     */
    public void insert(K key, V value) {
        if (key == null) throw new IllegalArgumentException("null key to insert");
        root = put(root, key, value);
    }
    /**WONDERFUL recursive function to handle all insertion cases!*/
    private Node put(Node node, K key, V value) {
        if (node == null) return new Node(key, value, RED); // endpoint for insert recursion, return a new node with a RED link.
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = put(node.left, key, value);
        else if (cmp > 0) node.right = put(node.right, key, value);
        else node.val = value; // if exists, update the value.

        // if a node survives until here, next step is to fix links to become a valid LLRB BST!
        // TODO: copy the code and how these cases match different cases!


        return node; // return a reference to a node and whatever connecting to it.
    }

    /**ROTATE methods: helper function.
     * Rotates a right-leaning red link to a left-leaning one, and vice versa.
     * When using,
     * @param head {@code Node} connected by a RED link to its {@code left/right} child.
     * @return NEW {@code Node} to be connected to previous parent
     */
    private Node rotateLeft(Node head) {
        assert head.right.isRED();
        Node r = head.right;
        head.right = r.left;
        r.left = head;
        r.color = head.color;
        head.color = RED;
        return r;
    }
    private Node rotateRight(Node head) {
        assert head.left.isRED();
        Node l = head.left;
        head.left = l.right;
        l.right = head;
        l.color = head.color;
        head.color = RED;
        return l;
    }

    /**FLIP COLOR method: helper function.
     * the only situation when the height of the tree increases!
     * @param head {@code Node} connected by two RED links. head's {@code left} and {@code right} should not be {@code null}.
     */
    private void flipColor(Node head) {
        head.color = RED;
        head.left.color = BLACK;
        head.right.color = BLACK;
    }

    public static void main(String[] args) {

    }
}
