package com.restaurant.flipkart.string;

import java.util.*;

public class GroupAnagrams {

    /**
     * Time Complexity: O(n * k log k)
     * Space Complexity: O(n * k) (worst case), O(k) (best case)
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        // Create a hashmap to group anagrams
        Map<String, List<String>> anagramGroups = new HashMap<>();

        // Iterate through each word in the input array
        for (String word : strs) {
            // Sort the characters of the word
            char[] charArray = word.toCharArray();
            Arrays.sort(charArray);
            String sortedWord = new String(charArray);

            // Check if the sorted word exists as a key in the hashmap
            if (!anagramGroups.containsKey(sortedWord)) {
                // If not, add a new entry with the sorted word as key and an empty list as value
                anagramGroups.put(sortedWord, new ArrayList<>());
            }
            // Add the original word to the list corresponding to its sorted characters
            anagramGroups.get(sortedWord).add(word);
        }

        // Return the values of the hashmap (lists of anagrams grouped together)
        return new ArrayList<>(anagramGroups.values());
    }

    /***
     * Time Complexity: O(n * k)
     * Space Complexity: O(n * k) (worst case), O(k) (best case)
     * @param strs
     * @return
     */

    public static List<List<String>> groupAnagrams2(String[] strs) {
        // Create a hashmap to group anagrams based on character counts
        Map<String, List<String>> anagramGroups = new HashMap<>();

        // Iterate through each word in the input array
        for (String word : strs) {
            // Count the frequency of each character in the word
            int[] charCounts = new int[26]; // Assuming input consists of lowercase English letters
            for (char ch : word.toCharArray()) {
                charCounts[ch - 'a']++;
            }

            // Construct a unique string representation of the character counts
            StringBuilder keyBuilder = new StringBuilder();
            for (char ch : word.toCharArray()) {
               int f = charCounts[ch-'a'];
               keyBuilder.append(ch).append(f);
            }

            String key = keyBuilder.toString();

            // Check if the key exists in the hashmap
            if (!anagramGroups.containsKey(key)) {
                // If not, add a new entry with the key and an empty list as value
                anagramGroups.put(key, new ArrayList<>());
            }
            // Add the original word to the list corresponding to its character counts
            anagramGroups.get(key).add(word);
        }

        // Return the values of the hashmap (lists of anagrams grouped together)
        return new ArrayList<>(anagramGroups.values());
    }

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result = groupAnagrams2(strs);
        System.out.println(result); // Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
    }
}

