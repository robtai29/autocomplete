package com.javaproject.autocomplete.suggestionobject;

import com.javaproject.autocomplete.setup.FileManager;
import com.javaproject.autocomplete.userinterface.AutocompleteGUI;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;


/**
 * TrieAutocomplete Class contains Node and Node Tree manipulation Methods
 */
public class TrieAutocomplete {

    /** root is instance of node. */
    private static Node root;
    /** CasingMap contains keys of movie titles in lowerCase and values of the correct casing for the title from FileManage */
    private static HashMap<String, String> casingMap = new HashMap<String, String>();

    /**
     * TrieAutocomplete Constructor initializes TrieAutocomplete and instantiate filemanager with movie filepath.
     * @param filePath
     * @throws FileNotFoundException
     */

    public TrieAutocomplete(String filePath) throws FileNotFoundException {
    root=new Node();
    FileManager filemanager = new FileManager(filePath, this);
    }

    /**
     * setNodes creates trie.
     * @param terms
     * @param weights
     */
    public void setNodes(String[] terms, double[] weights) {
        if (terms == null || weights == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < terms.length; i++) {
            add(terms[i], weights[i]);
        }
    }

    /**
     * add adds char to current Node.
     * @param word
     * @param weight
     */
    private void add(String word, double weight) {
        if (word == null) {
            throw new NullPointerException();
        }

        Node current = root;

        for (char ch : word.toCharArray()) {
            if (!current.getLeaves().containsKey(ch)) {
                current.getLeaves().put(ch, new Node());
            }
            current = current.getLeaves().get(ch);
        }
        current.setWord(word);
        current.setWeight(weight);
    }

    /**
     * topKMatches returns String array of k size from prefix String inputted.
     * @param prefix
     * @param k
     * @return
     */
    public String[] topKMatches(String prefix, int k) {
        if (prefix == null) {
            throw new NullPointerException();
        }
        if (k == 0) {
            throw new IllegalArgumentException();
        }
        //get the node at prefix
        Node current = root;
        for (char ch : prefix.toCharArray()) {
            if (!current.getLeaves().containsKey(ch)) {
                return new String[]{};
            }
            current = current.getLeaves().get(ch);
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(current);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.getWeight() > 0) {
                pq.add(node);
            }
            for (char child : node.getLeaves().keySet()) {
                queue.add(node.getLeaves().get(child));
            }
        }
        while (pq.size() > k) {
            pq.poll();
        }
        //return string array in descending order of weight
        String[] res = new String[Math.min(k, pq.size())];
        for (int i = res.length - 1; i >= 0; i--) {
            res[i] = pq.poll().getWord();
        }
        return res;
    }

    /**
     * setCasingMap sets casingMap of lowercase letters
     * @param casingMap
     */
    public void setCasingMap(HashMap<String, String> casingMap) {
        TrieAutocomplete.casingMap = casingMap;
    }

    /**
     * getCasing returns casingMap of lowercase word
     * @param word
     * @return
     */
    public String getCasing(String word) {
        return casingMap.get(word);
    }

    /**
     * invokeUserInterface invokes runnable to initiate GUI user interface.
     * @param QUANTITY_DISPLAY_TYPE
     */
    public void invokeUserInterface(int QUANTITY_DISPLAY_TYPE) {
        TrieAutocomplete trieAuto = this;
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new AutocompleteGUI(trieAuto, QUANTITY_DISPLAY_TYPE).setVisible(true);
                } catch (ClassNotFoundException | NoSuchMethodException | FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
