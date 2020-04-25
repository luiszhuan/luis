package com.luis.leetcode;

import java.util.ArrayList;
import java.util.List;

public class PalindromePairs {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            nodes.add(new Node(words[i], i));
        }

        return null;
    }

    class Node implements Comparable {
        String original;
        String dictOrder;
        int index;

        public Node(String word, int index) {
            this.original = word;
            this.index = index;
        }

        @Override
        public int compareTo(Object o) {
            return dictOrder.compareTo(((Node) o).dictOrder);
        }
    }
}
