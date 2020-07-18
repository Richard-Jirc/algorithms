/* AlgorithmII-CHAPTER 1 ASSIGNMENT: WordNet */

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.RedBlackBST;

public class WordNet {

    RedBlackBST<Integer, Bag<Integer>> connection;

    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null) throw new IllegalArgumentException("constructor argument null");
        connection = new RedBlackBST<>();

    }

    public boolean isNoun(String word) {
        return false;
    }
    public static void main(String[] args) {

    }
}
