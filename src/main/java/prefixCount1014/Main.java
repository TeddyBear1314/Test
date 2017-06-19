package prefixCount1014;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by hz on 2017/5/17.
 */
public class Main {
    private static final int MAX_WORD_NUM = 10;
    public static void main(String[] args) throws URISyntaxException {
        Scanner in = new Scanner(System.in);
        int wordNum = in.nextInt();
        char[][] words = new char[wordNum][];
        for(int i = 0 ;i <wordNum; i++) {
            words[i] = in.next().toCharArray();
        }

        int askNum = in.nextInt();
        char[][] askPrefixes = new char[askNum][];
        for (int i = 0; i < askNum; i++) {
            askPrefixes[i] = in.next().toCharArray();
        }
        TrieNode root = new TrieNode();
        for (int i = 0; i < words.length; i++) {
            buildTrie(words[i], root);
        }
        int [] results = new int[askNum];
        for (int i = 0; i <askNum; i++) {
            results[i] = countPrefix(root, askPrefixes[i]);
        }
        for (int i = 0; i < askNum; i++) {
            System.out.println(results[i]);
        }

    }
    private static void buildTrie(char[] words, TrieNode root) {
        TrieNode curNode = root;
        for (char word : words) {
            if (curNode.getChildren().get(word) == null) {
                TrieNode child = new TrieNode();
                curNode.getChildren().put(word, child);
            }
            curNode = curNode.getChildren().get(word);
            curNode.incPrefixNum();
        }
    }

    private static int countPrefix(TrieNode root, char[] askPrefixes) {
        TrieNode curNode = root;
        for (char word: askPrefixes) {
            curNode = curNode.getChildren().get(word);
            if (curNode == null) return 0;
        }
        return curNode.getPrefixNum();
    }



    private static class TrieNode {
        private int prefixNum = 0;
        //the max word num is 26
        private Map<Character, TrieNode> children = new HashMap<Character, TrieNode>(32);

        public int getPrefixNum() {
            return prefixNum;
        }

        public void incPrefixNum() {
            this.prefixNum++;
        }

        public Map<Character, TrieNode> getChildren() {
            return children;
        }
    }
}

