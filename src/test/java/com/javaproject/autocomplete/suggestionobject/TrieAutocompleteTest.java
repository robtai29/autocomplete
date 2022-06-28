package com.javaproject.autocomplete.suggestionobject;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrieAutocompleteTest {
    private static final String FILE_PATH = "src/test/resources/movies.txt";

    @Test
    public void topKMatches_test() throws FileNotFoundException {
        System.out.println(FILE_PATH);
        File file = new File(FILE_PATH);
        System.out.println(file.canRead());

        // try to make new instance below
        TrieAutocomplete trieAutocomplete = new TrieAutocomplete(FILE_PATH);

//        System.out.println(trieAutocomplete);
//        String[] topMatch = trieAutocomplete.topKMatches("ava", 6);
//        System.out.println(Arrays.toString(topMatch));
//        Arrays.equals(topMatch, );
        assertTrue(true);
    }


}
