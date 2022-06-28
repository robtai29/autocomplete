package setup;

import com.javaproject.autocomplete.TrieAutocomplete;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class FileManager {




    public static final String CHARSET = "UTF-8";

    public static File file;

    // display top k results

    public static void setFile(String filePath) {
        file=new File(filePath);
    }
    public static void scanFile(String filePath,TrieAutocomplete trieAutoInstance) throws IllegalArgumentException, SecurityException,FileNotFoundException {
        // read in the data
        Scanner in;
        HashMap<String, String> casingMap = new HashMap<String, String> ();

        try {
            in = new Scanner(new File(filePath), CHARSET);

            int N = Integer.parseInt(in.nextLine());
            String[] terms = new String[N];
            double[] weights = new double[N];

            for (int i = 0; i < N; i++) {
                String line = in.nextLine();
                int tab = line.indexOf('\t');
                weights[i] = Double.parseDouble(line.substring(0, tab).trim());
                casingMap.put(line.substring(tab + 1).toLowerCase(), line.substring(tab + 1));
                terms[i] = line.substring(tab + 1).toLowerCase();
                // create the autocomplete object
            }
            System.out.println(47);
//            System.out.println(Arrays.toString(terms));
//            System.out.println(Arrays.toString(weights));
             for (int i=0; i < N; i++) {
                 System.out.println("term: " + terms[i] + "\t weights: " + weights[i] + "\t i: " + i);
             }
            System.out.println(Arrays.toString(weights));
            trieAutoInstance.setNodes(terms, weights);
            System.out.println(49);
            trieAutoInstance.setCasingMap(casingMap);
            System.out.println(50);

            } catch (IllegalArgumentException
                     | SecurityException e1) {
                e1.printStackTrace();
                System.exit(1);
            } catch (FileNotFoundException e2) {
                System.out.println("Cannot read file " + filePath);
                System.exit(1);

            }



    }
}