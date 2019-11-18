package pl.waskicompany;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    private final static String morseCode = ".- -... -.-. -.. . ..-. --. .... .. .--- -.- .-.. -- -. --- .--. --.- .-. ... - ..- ...- .-- -..- -.-- --..";
    private final static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static Map<Character, String> m1 = new HashMap<>();
    private static Map<String, Integer> m2 = new HashMap<>();
    private static List<String> decodedWordList = new ArrayList<>();
    private static long amountOfMinuses = 0;
    private static long amountOfDots = 0;

    public static void main(String[] args) {
        fillMap();
//
//        smorse("sos");
//        smorse("daily");
//        smorse("programmer");
//        smorse("bits");
//        smorse("three");

        fillDecodedWordList();
        fillMap2();

//        System.out.println(amountOfDots);
//        System.out.println(amountOfMinuses);
//        System.out.println(decodedWordList.size());
//        System.out.println(m2);
        printMorseCodeByNumberOfAppends(13);
    }

    private static String smorse(String word) {
        StringBuilder newWord = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            newWord.append(m1.get(word.charAt(i)));
        }
        amountOfMinuses += newWord.toString().chars().filter(chr -> chr == '-').count();
        amountOfDots += newWord.toString().chars().filter(chr -> chr == '.').count();

        return newWord.toString();

    }

    private static void fillMap() {
        String[] letters = morseCode.split(" ");
        List<String> lettersList = Arrays.asList(letters);

        for (int i = 0; i < lettersList.size(); i++) {
            m1.put((alphabet[i]), lettersList.get(i));
        }
    }

    private static void fillDecodedWordList() {
        try {
            Files.readAllLines(Paths.get("enable1.txt")).forEach(word -> decodedWordList.add(smorse(word)));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void fillMap2() {
        for (String element : decodedWordList) {
            if (m2.get(element) == null) {
                m2.put(element, 1);
            } else if (m2.get(element) != null) {
                m2.replace(element, m2.get(element)+1);
            }
        }
    }
    private static void printMorseCodeByNumberOfAppends(int numberOfAppends){
        String result = m2.entrySet().stream()
                .filter(entry -> numberOfAppends == entry.getValue())
                .map(Map.Entry::getKey)
                .findFirst().get();
        System.out.println(result);
    }
    private static String findMorseCodeByNumberOfDashes(int numberOfDashes) {
        decodedWordList.forEach(word -> System.out.println(Arrays.toString(word.split("."))));
    }
}
