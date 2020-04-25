package com.luis.stream;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileStream {
    //static String path = "/home/luis/IdeaProjects/Java8Demo/pom.xml";
    static String path = "./pom.xml";

    public static void main(String[] args) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(path), Charset.forName("utf-8"));) {
            //long wordsCount = lines.flatMap(line -> Arrays.stream(line.split("[<>/ \\n]"))).distinct().count();
            //System.out.println(wordsCount);
            String words = lines.flatMap(line -> Arrays.stream(line.split("[^a-zA-Z0-9]")))
                    .distinct().filter(word -> word.length() > 0).sorted().collect(Collectors.joining(",", "[", "]"));
            System.out.println(words);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(new File(".").getCanonicalPath());s
    }
}
