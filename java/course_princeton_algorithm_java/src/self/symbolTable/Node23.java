package self.symbolTable;

public class Node23<Key extends Comparable<Key>, Value> {
    Key lKey, rKey;
    Value lVal, rVal;
    Node23<Key, Value> l, m, r, parent;
    public Node23(Key key, Value value) {
        lKey = key;
        lVal = value;
    }
    public boolean isRoot() { return parent == null; }
    public boolean isDual() {
        return rKey == null;
    }
    public boolean isLeaf() {
        return l == null;
    }
    public void insertDual(Key k, Value v) {
        if (lKey.compareTo(k) < 0) {
            rKey = k;
            rVal = v;
        } else if (lKey.compareTo(k) > 0) {
            rKey = lKey;
            rVal = lVal;
            lKey = k;
            lVal = v;
        } else {
            lVal = v;
        }
    }
    public Node23<Key,Value> next(Key k) {
        if (k.compareTo(lKey) < 0) return l;
        else if (isDual()) return r;
        else {
            if (k.compareTo(rKey) < 0) return m;
            else return r;
        }
    }
    public boolean check(Key k, Value v) {
        if (lKey != k && rKey != k) return false;
        if (lKey == k) lVal = v;
        if (rKey == k) rVal = v;
        return true;
    }
}
