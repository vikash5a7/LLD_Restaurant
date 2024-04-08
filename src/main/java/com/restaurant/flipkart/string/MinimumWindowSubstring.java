package com.restaurant.flipkart.string;

import java.util.*;

public class MinimumWindowSubstring {
    public static String minWindow(String s, String t) {

        if (s == null || t == null || s.length() == 0 || t.length() == 0) {
            return "";
        }

        // Create frequency map for characters in string t
        Map<Character, Integer> targetFreqMap = new HashMap<>();
        for (char ch : t.toCharArray()) {
            targetFreqMap.put(ch, targetFreqMap.getOrDefault(ch, 0) + 1);
        }

        // Initialize pointers and counters
        int left = 0, right = 0;
        int minLen = Integer.MAX_VALUE;
        int minStart = 0;
        int count = t.length();

        while (right < s.length()) {
            char ch = s.charAt(right);
            if (targetFreqMap.containsKey(ch)) {
                if (targetFreqMap.get(ch) > 0) {
                    count--;
                }
                targetFreqMap.put(ch, targetFreqMap.get(ch) - 1);
            }

            // If all characters from t are found in the current window
            while (count == 0) {
                // Update the minimum window length and start index
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minStart = left;
                }

                char leftCh = s.charAt(left);
                if (targetFreqMap.containsKey(leftCh)) {
                    targetFreqMap.put(leftCh, targetFreqMap.get(leftCh) + 1);
                    if (targetFreqMap.get(leftCh) > 0) {
                        count++;
                    }
                }
                left++;
            }
            right++;
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s, t)); // Output: "BANC"
    }
}

