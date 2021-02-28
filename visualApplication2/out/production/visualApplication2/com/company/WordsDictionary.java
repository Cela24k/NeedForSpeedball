package com.company;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class WordsDictionary {

    private static ArrayList<String> words = new ArrayList<>();

    public WordsDictionary()
    {
        try {
            loadDictionary();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadDictionary () throws Exception
    {
        FileReader fr = new FileReader("C:\\Users\\alece\\IdeaProjects\\visualApplication2\\src\\com\\company\\1000_parole_italiane_comuni.txt");

        int i;
        int c = 0;
        String tmp = "";

        while ((i=fr.read()) != -1)
        {
            if((char) i != '\n')
                tmp = tmp + (char) i;
            else
            {
                words.add(c,tmp);
                tmp = "";
                c++;
            }
        }
    }

    public static String getSingleWord()
    {
        Random r = new Random();
        return words.get(r.nextInt(999));
    }
}
