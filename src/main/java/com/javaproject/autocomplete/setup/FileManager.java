package com.javaproject.autocomplete.setup;

import com.javaproject.autocomplete.suggestionobject.TrieAutocomplete;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.HashMap;
import java.util.Scanner;

public class FileManager {

    private static final String CHARSET = "UTF-8";

    private static File file;

    // display top k results

    public static void setFile(String filePath) {
        file=new File(filePath);
    }
    public static void scanFile(TrieAutocomplete trieAutoInstance) throws FileNotFoundException {
        // read in the data
        Scanner in;
        HashMap<String, String> casingMap = new HashMap<String, String> ();


            in = new Scanner(file, CHARSET);

            int N = Integer.parseInt(in.nextLine());
            String[] terms = new String[N];
            double[] weights = new double[N];

            for (int i = 0; i < N; i++) {
                String line = in.nextLine();
                int tab = line.indexOf('\t');
                weights[i] = Double.parseDouble(line.substring(0, tab).trim());
                casingMap.put(line.substring(tab + 1).toLowerCase(), line.substring(tab + 1));
                terms[i] = line.substring(tab + 1).toLowerCase();

            }


            trieAutoInstance.setNodes(terms, weights);
            trieAutoInstance.setCasingMap(casingMap);





    }
}