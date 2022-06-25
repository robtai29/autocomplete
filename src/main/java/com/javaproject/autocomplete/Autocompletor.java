package com.javaproject.autocomplete;

/**
 *
 * @author Austin Lu of Duke University
 *
 */
public interface Autocompletor {

    /**
     * Returns the top k matching terms in descending order of weight. If there are fewer than k
     * matches, return all matching terms in descending order of weight. If there are no matches,
     * return an empty array.
     */
    public String[] topKMatches(String prefix, int k);

}

