package com.javaproject.autocomplete;

import com.javaproject.autocomplete.AutocompleteGUI;

import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;

/**
 * Main class for Autocomplete program.
 * The user inputs text and application suggests movies based on input
 * Once the movie title is selected, Amazon with searched movie title opens in Browser.
 *
 *
 * @author Robert Tai
 * @author James Northup
 * @author Jacques Jean-Gilles
 */
public class AutocompleteMain {

    /* Modify quantityDisplayed as necessary */
    final static int quantityDisplayed = 10;

    /* Movie data source path*/
    final static String MOVIE_DATA_TEXT_PATH = "src/main/resources/movies.txt";

    /**
     * Main method generates data tree and invokes GUI.
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {

        // Generate Data structure from movie text file.
        TrieAutocomplete trieAuto = new TrieAutocomplete(MOVIE_DATA_TEXT_PATH);

        /**
         * Invoke runnable to initiate GUI user interface.
         */
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new AutocompleteGUI(trieAuto, quantityDisplayed).setVisible(true);
                } catch (ClassNotFoundException | NoSuchMethodException | FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}




    