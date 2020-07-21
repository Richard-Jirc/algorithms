/* AlgorithmII-CHAPTER 1 ASSIGNMENT: WordNet */

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdIn;

public class WordNet {

    RedBlackBST<Integer, String> word;
    RedBlackBST<Integer, Bag<Integer>> connection;

    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null) throw new IllegalArgumentException("constructor argument null");
        word = new RedBlackBST<>();
        connection = new RedBlackBST<>();
        StdIn.hasNextLine()
    }
    
    public boolean isNoun(String word) {
        if (word == null) throw new IllegalArgumentException("isNoun null");
        return false;
    }
    public static void main(String[] args) {

    }
}
