package Streams.Interview.programs;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringProblems {


    // reverse a String with out using inbuilt function
    @Test
    void reverseString() {
        String a = "hello";
        for (int k = a.length() - 1; k >= 0; k--) {
            System.out.print(a.charAt(k));
        }
    }

    // Check if a string is a palindrome.
    @Test
    void checkPalindrome() {
        String a = "madam";
        StringBuilder str = new StringBuilder(a);
        if (str.reverse().toString().equals(a)) {
            System.out.println("it is a polindrome");
            ;
        } else {
            System.out.println("it is not a polindrome");
        }
    }

    // Count vowels, consonants, digits, and spaces in a string.
    @Test
    void countVowelsConsonantsDigitsSpaces() {
        String a = "hello 123$%#";

        int vowels = a.replaceAll("[^aeiouAEIOU]", "").length();
        int consonants = a.replaceAll("[^a-zA-Z]", "").replaceAll("[aeiouAEIOU]", "").length();
        int digits = a.replaceAll("[^0-9]", "").length();
        int spaces = a.replaceAll("[^ ]", "").length();
        int specialSymols = a.replaceAll("[a-zA-Z0-9 ]", "").length();

        System.out.println("vowels: " + vowels);
        System.out.println("consonants: " + consonants);
        System.out.println("digits: " + digits);
        System.out.println("spaces: " + spaces);
        System.out.println("specialSymols: " + specialSymols);
    }

    //Find the first non-repeating character in a string.
    @Test
    void firstNonRepeatingCharacter() {
        String a = "swiss";
        char ch = a.chars().mapToObj(e -> (char) e)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream().filter(e -> e.getValue() == 1L)
                .findFirst().map(Map.Entry::getKey).get().charValue();
        System.out.println(ch);
    }

    //Count occurrences of each character in a string.
    @Test
    void countOccurrencesOfEachCharacter() {
        String a = "hello world";
        Map<Character, Long> chrrMap = a.chars().mapToObj(e -> (char) e)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(chrrMap);

    }

    //Find duplicate characters in a string.
    @Test
    void findDuplicateCharacters() {
        String a = "pradeep mende";
        Map<Character, Long> repeatedValuesMap = a.chars().mapToObj(e -> (char) e)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream().filter(e -> e.getValue() > 1L)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        System.out.println(repeatedValuesMap);
    }

    // Remove all duplicate characters from a string.
    @Test
    void removeDuplicateCharacters() {
        // first way using Streams.
        String a = "programming";
        String result = a.chars().mapToObj(e -> (char) e)
                .distinct()
                .map(String::valueOf)
                .collect(Collectors.joining());
        System.out.println(result);

        // second way using HashSet
        System.out.println("------------------");

        Set<Character> ch = new HashSet<>();
        String removingDuplicates = "";
        for (int i = 0; i < a.length(); i++) {
            if (ch.add(a.charAt(i))) {
                removingDuplicates = removingDuplicates + a.charAt(i);
            }
        }
        System.out.println(removingDuplicates);
    }

    // Check if two strings are anagrams of each other.
    @Test
    void checkAnagrams() {
        String a = "listen";
        String b = "silent";

        boolean ch = a.chars().mapToObj(e -> (char) e)
                .sorted().map(String::valueOf).collect(Collectors.joining())
                .equals(b.chars().mapToObj(e -> (char) e).sorted().map(String::valueOf).collect(Collectors.joining()));

        String result = ch ? "is anagrams" : "is not anagrams";
        System.out.println(result);
    }

    // Find the maximum occurring character in a string.
    @Test
    void maxOccurringCharacter() {
        String a = "hello world";

        char ch = a.chars().mapToObj(e -> (char) e)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .findFirst().get().getKey().charValue();

        // here we are directly doing with the Desending order Sorted Map.

        System.out.println(ch);

        Map<Character, Long> map = a.chars().mapToObj(e -> (char) e)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (old, newValue) -> old, LinkedHashMap::new));

        // here we are collecting, All the Sorted Map with in Map.
        if (map.entrySet().stream().skip(1).findFirst().isPresent()) {
            System.out.println(map.entrySet().stream().skip(1).findFirst().get().getKey().charValue());
        }


    }

    // Remove all whitespace from a string.
    @Test
    void removeAllWhitespace() {
        // [^ ] then it will consider excet these items
        // [ ] then it will consider only for these items.

        String a = "@#$%^&5462978gdbfwter@#$%^&";
        // i want to replce every special Symbol with empty space or i want to remove all the Special Symbols
        System.out.println(a.replaceAll("[^0-9a-zA-Z]", " "));
        System.out.println(a.replaceAll("[^0-9a-zA-Z]", ""));

        // if i only want the Special Symbols
        System.out.println(a.replaceAll("[0-9a-zA-Z]", ""));
    }

    // Reverse each word in a string while keeping the order of words.
    @Test
    void reverseEachWordInString() {
        String a = "Hello World from Java";
        String result = Arrays.stream(a.split(" "))
                .map(e -> new StringBuilder(e).reverse().toString())
                .collect(Collectors.joining(" "));

        System.out.println(result);
    }

    //Print all permutations of a string.
    @Test
    void printAllPermutations() {
        String value = "ABC";
        List<String> result = new ArrayList<>();
        permute(value, "", result);
        System.out.println(result);
    }

    private void permute(String a, String s, List<String> result) {
        if (a.isEmpty()) {
            result.add(s);
            return;
        }
        for (int i = 0; i < a.length(); i++) {
            char ch = a.charAt(i);
            String leftsubArr = a.substring(0, i);
            String rightSubArr = a.substring(i + 1);
            String rest = leftsubArr + rightSubArr;
            permute(rest, s + ch, result);
        }

    }

    // check all or digits or not.
    @Test
    void digitTest() {
        String a = "2@#$567ertyu34156";
        if (a.replaceAll("[^0-9]", "").length() == a.length()) {
            System.out.println("all are digits");
        } else {
            System.out.println("all are not digits");
        }
    }

    // Find the longest substring without repeating characters.
    @Test
    void longestSubstringWithoutRepeatingCharacters() {
        String a = "abcabcbb";
        String result = "";
        String LongestSubString = "";

        for (int i = 0; i < a.length(); i++) {
            char ch = a.charAt(i);
            if (!result.contains(String.valueOf(ch))) {
                result = result + ch;
            } else {
                if (LongestSubString.length() < result.length()) {
                    LongestSubString = result;
                }
                result = String.valueOf(ch);
            }
        }

        if (LongestSubString.length() < result.length()) {
            LongestSubString = result;
        }

        System.out.println("longest Substring Without Repeating Characters is: " + LongestSubString);
    }

    // Find the longest common prefix among a set of strings.
    @Test
    void longestCommonPrefix() {
        String[] arr = {"flower", "flow", "fight"};
        String prefix = arr[0];

        for (int i = 1; i < arr.length; i++) {
            while (arr[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
            }
            if (prefix.isEmpty()) {
                System.out.println("there is no common prefix");
                return;
            }
        }
        System.out.println("longest common prefix is: " + prefix);
    }

    @Test
    void stringCompression() {
        String a = "aaabbc";
        StringBuilder result = new StringBuilder();
        a.chars().mapToObj(e -> (char) e)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .forEach((key, value) -> result.append(key).append(value));

        System.out.println(result);
    }

    @Test
    void leftAndRightRotationOfString() {
        String a = "abcdef";
        int leftRotations = 2;
        int rightRotations = 2;
        System.out.println(a.substring(leftRotations, a.length()).concat(a.substring(0, leftRotations)));
        System.out.println(a.substring(a.length() - rightRotations, a.length()).concat(a.substring(0, a.length() - rightRotations)));
    }


}
