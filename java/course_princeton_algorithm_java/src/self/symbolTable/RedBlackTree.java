package self.symbolTable;


/** Left-Leaning Red-Black Tree:
 *  trying out the version mentioned in the course.
 */
public class RedBlackTree<Key extends Comparable<Key>, Value> {
    private class Node {
        Key key;
        Value value;
        public Node(Key k, Value v) {
            key = k;
            value = v;
        }
    }

    private final boolean RED = true;
    private final boolean BLACK = false;

    public RedBlackTree() {

    }

    public static void main(String[] args) {

    }
}
