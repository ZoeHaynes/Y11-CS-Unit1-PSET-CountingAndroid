package com.example.countinglab;

import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadWord {
    private File readFile;
    private ArrayList<String> wordList;

    public ReadWord(AssetManager assetManager, String fileName) throws IOException {
        InputStream inputStream = assetManager.open(fileName);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        wordList = new ArrayList<>();
        String line;
        while ((line = bufferedReader.readLine())!= null) {
            for (String word: line.split(" ")){
                if (word.length() != 0) {
                    word = filterWord(word);
                    wordList.add(word);
                }
            }
        }

        //System.out.println(wordList.toString());

    }

    private String filterWord(String word){
        word = word.toLowerCase();
        while(word.length() != 0 && !Character.isLetter(word.charAt(0))){
            word = word.substring(1);
        }

        while(word.length() != 0 && !Character.isLetter(word.charAt(word.length()-1))){
            word = word.substring(0, word.length()-1);
        }
        return word;

    }

    public ArrayList<String> getWords() {
        return wordList;
    }
}