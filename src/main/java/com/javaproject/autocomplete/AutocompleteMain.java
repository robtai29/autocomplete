package com.javaproject.autocomplete;

import com.javaproject.autocomplete.AutocompleteGUI;

import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;


/**
 * Main class for the Autocomplete program.
 *
 * @author Austin Lu
 */
public class AutocompleteMain {

    /* Modify K as necessary */
    final static int K = 10;

    final static String BRUTE_AUTOCOMPLETE = "BruteAutocomplete";
    final static String BINARY_SEARCH_AUTOCOMPLETE = "BinarySearchAutocomplete";
    final static String MOVIE_DATA_TEXT_PATH = "src/main/resources/movies.txt";
    final static String TRIE_AUTOCOMPLETE = "com.javaproject.autocomplete.TrieAutocomplete";

    /* Modify name of com.javaproject.autocomplete.Autocompletor implementation as necessary */
    final static String AUTOCOMPLETOR_CLASS_NAME = TRIE_AUTOCOMPLETE;

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new AutocompleteGUI(MOVIE_DATA_TEXT_PATH, K).setVisible(true);
                } catch (ClassNotFoundException | NoSuchMethodException | FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}




    