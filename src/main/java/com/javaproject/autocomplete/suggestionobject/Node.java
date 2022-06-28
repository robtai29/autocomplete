package com.javaproject.autocomplete.suggestionobject;

import java.util.HashMap;
import java.util.Map;


public class Node implements Comparable<Node> {

    private String character = "";
    private double weight = -1;

    private Map<Character, Node> leaves;


    public Node() {
        leaves = new HashMap<Character, Node>();
    }


    public String getWord() {

        return character;
    }

    public void setWord(String word) {

        this.character = word;
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

    @Override
    public int compareTo(Node other) {
        // Sort in weight ascending && if weight are the same, return lexicographically
        return weight == other.weight ? (character.compareTo(other.character)) : (int) (weight - other.weight);
    }

}

