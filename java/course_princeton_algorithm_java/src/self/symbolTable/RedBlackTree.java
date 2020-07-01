package self.symbolTable;


/** Left-Leaning Red-Black Tree:
 *  trying out the version mentioned in the course.
 */
public class RedBlackTree<Key extends Comparable<Key>, Value> {
    private final boolean RED = true;
    private final boolean BLACK = false;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private boolean color; // color of link that points to its parent.
        public Node(Key k, Value v) {
            key = k;
            val = v;
            color = BLACK;
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
    public Value get(Key key) {
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

    public void insert(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("null key to insert");
        Node x = root;
        while (x.left != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else {
                x.val = value;
                break;
            }
        }
    }

    /**ROTATE methods: helper function.
     * Rotates a right-leaning red link to a left-leaning one, and vice versa.
     * When using,
     * @param head {@code Node} connected by a RED link to its {@code left/right} child.
     * @return NEW {@code Node} to be connected to previous parent
     */
    private Node rotateLeft(Node head) {
        Node r = head.right;
        head.right = r.left;
        r.left = head;
        r.color = head.color;
        head.color = RED;
        return r;
    }
    private Node rotateRight(Node head) {
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
