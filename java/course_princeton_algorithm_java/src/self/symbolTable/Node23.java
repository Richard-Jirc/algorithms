package self.symbolTable;

import java.util.Objects;

public class Node23<Key extends Comparable<Key>, Value> {
    Key lKey, rKey;
    Value lVal, rVal;
    Node23<Key, Value> l, m, r, parent;
    public Node23(Key key, Value value) {
        lKey = key;
        lVal = value;
    }
    public boolean isDual() {
        return m == null;
    }
    public boolean isLeaf() {
        return l == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node23 node = (Node23) o;
        return Objects.equals(lKey, node.lKey);
    }
}
