package com.javaproject.autocomplete.suggestionobject;

import java.util.HashMap;
import java.util.Map;

/**
 * Node Class used to generate Node Tree Structure containing movie titles characters and weights.
 * Implements Comparable
 *
 * @author Robert Tai
 * @author James Northup
 * @author Jacques Jean-Gilles
 */

public class Node implements Comparable<Node> {

    /**
     * leaves is a Map that contains characters associated by movie title.
     */
    private final Map<Character, Node> leaves;

    // character is the character within a movie title.
    private String character = "";

    // weight is given from text file, default is -1.
    private double weight = -1;

    /**
     * Node Constructor initializes leaves HashMap.
     */

    public Node() {
        leaves = new HashMap<Character, Node>();
    }

    /**
     * Returns character associated with movie title tree branch.
     *
     * @return character
     */
    public String getWord() {

        return character;
    }

    // getWeight returns weight.
    public double getWeight() {

        return weight;
    }

    // Returns all next nodes associated with movie titles.
    public Map<Character, Node> getLeaves() {

        return leaves;
    }

    // Sets word in instance field.
    public void setWord(String word) {

        this.character = word;
    }

    // setWeight in instance field.
    public void setWeight(double weight) {

        this.weight = weight;
    }

    // compareTo methods places precedence on weight then order alphabet
    @Override
    public int compareTo(Node other) {
        // Sort in weight ascending && if weight are the same, return lexicographically
        return weight == other.weight ? (other.character.compareTo(character)) : (int) (weight - other.weight);
    }

}

