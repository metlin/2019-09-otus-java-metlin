package ru.otus.homework;

import com.google.common.base.Splitter;
import java.util.List;

public class HelloOtus {
    public static void main(String[] args) {
        List<String> wordList = Splitter.on('+')
                .splitToList("hello+otus+java");

        System.out.println(wordList);
    }
}
