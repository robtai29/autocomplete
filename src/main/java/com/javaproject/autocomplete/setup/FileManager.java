package com.javaproject.autocomplete.setup;

import com.javaproject.autocomplete.suggestionobject.TrieAutocomplete;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;


/**
 * FileManager Class accesses file from filePath, iterates through the movie file data and stores terms and weights
 * into the node tree.
 *
 * @author Robert Tai
 * @author James Northup
 * @author Jacques Jean-Gilles
 */
public class FileManager {

    // Instance field CHARSET variable sets the file text type used for scanner.
    private static final String CHARSET = "UTF-8";

    // DELIMITER defines the parsing of text file data selected
    private static final String DELIMITER = "\t";

    /**
     * FileManager Constructor creates file from filepath and calls on scanFile to read file data into node tree.
     *
     * @param filePath         Path to the movie data file.
     * @param trieAutoInstance Object that Contains root node and movie data
     * @throws FileNotFoundException May throw FileNotFoundException if the file from filePath is non-existent.
     */
    public FileManager(String filePath, TrieAutocomplete trieAutoInstance) throws FileNotFoundException {

        scanFile(new File(filePath), trieAutoInstance);
    }

    /**
     * scanFile Scans the file given and reads data into node tree.
     *
     * @param file             File generated from file path.
     * @param trieAutoInstance Object that Contains root node and movie data
     * @throws FileNotFoundException May throw FileNotFoundException if the file from filePath is non-existent.
     */

    public void scanFile(File file, TrieAutocomplete trieAutoInstance) throws FileNotFoundException {

        /* CasingMap contains keys of movie titles in lowerCase and values of the correct casing for the title
        *  CasingMap is used to convert user input in any casing to the correct casing that shows in user interface.
         */

        HashMap<String, String> casingMap = new HashMap<String, String>();

        // Set Scanner to given file with CHARSET ='UTF-8'
        Scanner scanner = new Scanner(file, CHARSET);

        // Stores the initial integer from file, representing size of movie list.
        int movie_List_Length = Integer.parseInt(scanner.nextLine());

        // Terms represents each movie titles located in file data.
        String[] terms = new String[movie_List_Length];

        // Weights represent the popularity of the movie, used for ranking suggestions.
        double[] weights = new double[movie_List_Length];

        // For loop parses data into terms and weights
        for (int i = 0; i < movie_List_Length; i++) {
            String line = scanner.nextLine();
            int tab = line.indexOf(DELIMITER);
            weights[i] = Double.parseDouble(line.substring(0, tab).trim());
            casingMap.put(line.substring(tab + 1).toLowerCase(), line.substring(tab + 1));
            terms[i] = line.substring(tab + 1).toLowerCase();

        }

        // Set terms and weights into Node Tree structure by calling on trieAutocomplete methods.
        trieAutoInstance.setNodes(terms, weights);
        trieAutoInstance.setCasingMap(casingMap);

    }
}