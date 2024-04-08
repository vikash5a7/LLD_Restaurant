package com.restaurant.flipkart.string;

public class ValidAnagram {
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false; // Anagrams must have the same length
        }

        // Create an array to store character frequencies
        int[] charCounts = new int[26]; // Assuming input consists of lowercase English letters

        // Increment frequency for characters in string s
        for (char ch : s.toCharArray()) {
            charCounts[ch - 'a']++;
        }

        // Decrement frequency for characters in string t
        for (char ch : t.toCharArray()) {
            charCounts[ch - 'a']--;
            // If the count becomes negative, it means the characters in t are not an exact match for s
            if (charCounts[ch - 'a'] < 0) {
                return false;
            }
        }

        return true; // If frequencies are equal, it's an anagram
    }

    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        System.out.println(isAnagram(s, t)); // Output: true
    }
}

