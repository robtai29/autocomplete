package com.javaproject.autocomplete.client;

import com.javaproject.autocomplete.suggestionobject.TrieAutocomplete;

import java.io.FileNotFoundException;

/**
 * AutocompleteMain contains main method for Autocomplete program.
 * The application opens a user Interface and suggests movies based on users input
 * Once the movie title is selected, browser opens with Amazon Prime Video
 * With the selected movie.
 *
 * @author Robert Tai
 * @author James Northup
 * @author Jacques Jean-Gilles
 */
public class AutocompleteMain {

    /* Modify quantityDisplayed as necessary */
    private final static int QUANTITY_DISPLAY_TYPE = 10;

    /* Movie data source path*/
    private final static String MOVIE_DATA_TEXT_PATH = "src/main/resources/movies.txt";

    /**
     * Main method generates data tree and invokes GUI.
     *
     * @param args
     * @throws FileNotFoundException
     */

    public static void main(String[] args) throws FileNotFoundException {

        // Generate Data structure from movie text file.
        TrieAutocomplete trieAuto = new TrieAutocomplete(MOVIE_DATA_TEXT_PATH);
        trieAuto.invokeUserInterface(QUANTITY_DISPLAY_TYPE);

    }
}




    