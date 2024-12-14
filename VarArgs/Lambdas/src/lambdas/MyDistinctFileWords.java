package lambdas;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MyDistinctFileWords {

    public List<String> getDistinctWordList(String fileName) throws FileNotFoundException {
        List<String> wordList = new ArrayList<>();

        final FileInputStream fis = new FileInputStream(fileName);
        final DataInputStream dis = new DataInputStream(fis);
        final BufferedReader  br  = new BufferedReader(new InputStreamReader(dis));

        try (fis;dis;br) {
            String line = null;
            while ((line = br.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, " ,.;:\"");
                while (tokenizer.hasMoreTokens()) {
                    String token = tokenizer.nextToken().toLowerCase();
                    if (!wordList.contains(token)) {
                        wordList.add(token);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordList;
    }

    public static void main(String[] a) throws FileNotFoundException {
        MyDistinctFileWords distinctWords = new MyDistinctFileWords();
        List<String> wordList = distinctWords.getDistinctWordList("data1.txt");
        for (String s : wordList) {
            System.out.printf("%n%s", s);
        }
    }
}
