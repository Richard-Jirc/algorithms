package self.symbolTable;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/** Unbalanced Normal BST
 *
 * @param <Key> extends Comparable.
 * @param <Value>
 */

public class BinarySearchTree<Key extends Comparable<Key>, Value> {
    private Node root;
    private class Node implements Comparable<Node> {
        Key key;
        Value value;
        Node left;
        Node right;
        int count;
        public Node(Key k, Value v) {
            key = k;
            value = v;
        }
        public int compareTo(Node node) {
            return this.key.compareTo(node.key);
        }
        public String toString() {
            StringBuilder string = new StringBuilder();
            if (left != null) string.append(left.key);
            else string.append("-");
            string.append(" <= ");
            string.append("K:");
            string.append(key);
            string.append(" V:");
            string.append(value);
            string.append(" => ");
            if (right != null) string.append(right.key);
            else string.append("-");
            return string.toString();
        }
    }
    public BinarySearchTree() {
    }
    public boolean isEmpty() {
        return root == null;
    }

    /**GET method:
     * perform binary search for a certain key.
     * @return Value if found, null if not exist.
     */
    public Value get(Key key) {
        Node node = root;
        while (node != null) {
            StdOut.println(node.toString());
            if (node.key.compareTo(key) == 0) return node.value;
            if (node.key.compareTo(key) < 0) node = node.right;
            else node = node.left;
        }
        return null;
    }

    /**PUT method:
     * perform binary search,
     * if no matching key found then create a new one,
     * update if found.
     */
    public void put(Key key, Value value) {
        Node node = root;
        Node newNode = new Node(key, value);
        while (node != null) {
            if (node.key.compareTo(key) == 0) {
                node.value = value;
                break;
            }
            if (node.key.compareTo(key) > 0) {
                if (node.left == null) {
                    node.left = newNode;
                    break;
                }
                node = node.left;
            } else {
                if (node.right == null) {
                    node.right = newNode;
                    break;
                }
                node = node.right;
            }
        }
        if (this.isEmpty()) root = newNode;
    }

    /**DELETE method:
     * perform binary search,
     * @return null if not found, Value if found.
     */
    public Value delete(Key key) {
        Node node = root;
        while (node != null) {
            if (node.key.compareTo(key) == 0) {
                Value result = node.value;
                if (node.left != null && node.right != null) {
                    Node successor = node.right;
                    while (successor.compareTo(node.left) < 0) {
                        if (successor.left != null) successor = successor.left;

                    }
                }
                else if (node.left == null && node.right == null) node = null;
                else if (node.left != null) node = node.left;
                else node = node.right;
                return result;
            }
            if (node.key.compareTo(key) > 0) node = node.left;
            if (node.key.compareTo(key) < 0) node = node.right;
        }
        return null;
    }

    /**SORTED ITERABLE:
     * use queue to store values in a FIFO order*/
    public Iterable<Value> iterable() {
        Queue<Value> queue = new Queue<>();
        collect(queue, root);
        return queue;
    }

    /** Iterable HELPER
     */
    private void collect(Queue<Value> q, Node node) {
        if (node == null) return;
        collect(q, node.left);
        q.enqueue(node.value);
        collect(q, node.right);
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer, String> test = new BinarySearchTree<>();
        test.put(5, "5!");
        test.put(10, "10!");
        test.put(2, "2!");
        test.put(6, "6!");
        test.put(6, "?");
        Iterable<String> peek = test.iterable();
        for (String i : peek) {
            System.out.println(i);
        }
        System.out.println(test.get(6));

    }
}
