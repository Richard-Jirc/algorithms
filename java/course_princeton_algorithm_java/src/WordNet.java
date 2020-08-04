/* AlgorithmII-CHAPTER 1 ASSIGNMENT: WordNet */

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdIn;

public class WordNet {

    RedBlackBST<String, Integer> word; //
    Digraph connection;

    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null) throw new IllegalArgumentException("constructor argument null");
        word = new RedBlackBST<>();
        connection = new Digraph(5);
    }
    
    public boolean isNoun(String noun) {
        if (noun == null) throw new IllegalArgumentException("isNoun null");
        return word.contains(noun);
    }

    /**
     * {@code synset} as common ancestor of {@code nounA & nounB}
     * in shortest ancestral path.
     * @return {@code String of synset}
     */
    public String sap(String nounA, String nounB) {

        return "test";
    }
    public static void main(String[] args) {
        WordNet test = new WordNet(args[0], args[1]);
    }
}
