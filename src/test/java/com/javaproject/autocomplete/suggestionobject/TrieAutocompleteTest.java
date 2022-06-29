package com.javaproject.autocomplete.suggestionobject;

import com.javaproject.autocomplete.suggestionobject.TrieAutocomplete;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TrieAutocompleteTest {
    private static final String FILE_PATH = "src/test/resources/movies.txt";
    private static final String[] MOVIE_LIST = {"avatar (2009), abba: the movie (1977), abcd (2005), 101 dalmatians (1996), 102 dalmatians (2000), the dark knight rises (2012)"};
    private static final String[] ALPHA = {"abba: the movie (1977), abcd (2005)"};
    private static final String[] NUM = {"101 dalmatians (1996), 102 dalmatians (2000)"};

    @Test
    public void orderByWeight() throws FileNotFoundException {
        TrieAutocomplete trieAutocomplete = new TrieAutocomplete(FILE_PATH);
        assertEquals(Arrays.toString(MOVIE_LIST), Arrays.toString(trieAutocomplete.topKMatches("", 6)));
    }

    @Test
    public void sameWeight_alpha() throws FileNotFoundException {
        TrieAutocomplete trieAutocomplete = new TrieAutocomplete(FILE_PATH);
        assertEquals(Arrays.toString(ALPHA), Arrays.toString(trieAutocomplete.topKMatches("ab", 6)));
    }

    @Test
    public void sameWeight_num() throws FileNotFoundException {
        TrieAutocomplete trieAutocomplete = new TrieAutocomplete(FILE_PATH);
        assertEquals(Arrays.toString(NUM), Arrays.toString(trieAutocomplete.topKMatches("1", 6)));
    }

    @Test
    public void invalidFile() throws FileNotFoundException {
        assertThrows(FileNotFoundException.class, () -> {
            TrieAutocomplete trieAutocomplete = new TrieAutocomplete("?(");
        });
    }
}