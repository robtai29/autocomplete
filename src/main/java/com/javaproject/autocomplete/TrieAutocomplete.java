package com.javaproject.autocomplete;

import javax.print.attribute.standard.PrinterMessageFromOperator;
import java.util.*;

public class TrieAutocomplete implements Autocompletor {
    private final Node root;

    public TrieAutocomplete(String[] terms, double[] weights) {
        if (terms == null || weights == null)
            throw new NullPointerException();
        //dummy root
        root = new Node('\0');

        for (int i = 0; i < terms.length; i++) {
            add(terms[i], weights[i]);
        }
    }

    private void add(String word, double weight) {
        if (word == null) {
            throw new NullPointerException();
        }

        Node current = root;

        for (char ch : word.toCharArray()) {
            if (!current.leaves.containsKey(ch)){
                current.leaves.put(ch, new Node(ch));
            }
            current = current.leaves.get(ch);
        }

        current.word = word;
        current.weight = weight;
    }


    public String[] topKMatches(String prefix, int k) {
        if (prefix == null){
            throw new NullPointerException();
        }

        if (k == 0){
            throw new IllegalArgumentException();
        }

        //get the node at prefix
        Node current = root;
        for (char ch : prefix.toCharArray()){
            if (!current.leaves.containsKey(ch)){
                return new String[]{};
            }
            current = current.leaves.get(ch);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(current);

        while (!queue.isEmpty()){
            Node node = queue.poll();
            if (node.weight > 0){
                pq.add(node);
            }

                for (char child : node.leaves.keySet()) {
                    queue.add(node.leaves.get(child));
                }
        }

        while (pq.size() > k){
            pq.poll();
        }

        //return string array in descending order of weight
        String[] res = new String[Math.min(k, pq.size())];
        for (int i = res.length-1; i >=0; i--){
            res[i] = pq.poll().word;
        }

        return res;
    }



    /**
     * Used to debugging
     */
    private double weightOf(String term) {
        if (term == null) {
            return 0.0;
        }
        Node current = root;
        for (char ch : term.toCharArray()) {
            if (!current.leaves.containsKey(ch)){
                return 0;
            }
            current = current.leaves.get(ch);
        }

        return current.weight;
    }
    private static class Node implements Comparable<Node> {

        private String word = "";
        double weight = -1;
    
        Map<Character, Node> leaves;
        public Node(char character) {
            leaves = new HashMap<Character, Node>();
        }
    
    
        @Override
        public int compareTo(Node other) {
            // Sort in weight ascending && if weight are the same, return lexicographically
            return weight == other.weight ? (word.compareTo(other.word)) : (int)(weight - other.weight);
        }


        private boolean isLeave(){
            return leaves.isEmpty();
        }

        private boolean isLeave(){
            return leaves.isEmpty();
        }
    }
}
