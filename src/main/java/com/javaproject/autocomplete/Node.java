package com.javaproject.autocomplete;

import java.util.HashMap;
import java.util.Map;



    public class Node implements Comparable<Node> {

        private String word = "";
        private double weight = -1;

        private Map<Character, Node> leaves;
        public Node(){}
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

        public String getWord() {

            return word;
        }

        public void setWord(String word) {

            this.word = word;
        }

        public double getWeight() {

            return weight;
        }

        public void setWeight(double weight) {

            this.weight = weight;
        }

        public Map<Character, Node> getLeaves() {

            return leaves;
        }

        public void setLeaves(Map<Character, Node> leaves) {

            this.leaves = leaves;
        }
    }

