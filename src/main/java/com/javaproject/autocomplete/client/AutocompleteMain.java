package com.javaproject.autocomplete.client;

import com.javaproject.autocomplete.suggestionobject.TrieAutocomplete;
import com.javaproject.autocomplete.suggestionobject.TrieAutocomplete;

import java.io.FileNotFoundException;

/**
 * Main class for Autocomplete program.
 * The user inputs text and application suggests movies based on input
 * Once the movie title is selected, Amazon with searched movie title opens in Browser.
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
    /*
     *Instance trieAuto contains movie data structure.
     */
    private static TrieAutocomplete trieAuto;

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




    