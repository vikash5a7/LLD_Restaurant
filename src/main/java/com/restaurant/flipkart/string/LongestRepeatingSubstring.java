package com.restaurant.flipkart.string;

import java.util.*;

public class LongestRepeatingSubstring {
    public static int characterReplacement(String s, int k) {
        int[] count = new int[26];
        int maxCount = 0; // to keep track of the most frequent character in the current window
        int left = 0; // left pointer
        int maxLength = 0; // maximum length of the substring

        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            count[ch - 'A']++; // increment the frequency of the current character
            maxCount = Math.max(maxCount, count[ch - 'A']); // update maxCount

            // If the total characters in the current window - frequency of most common character > k,
            // we need to shrink the window by moving the left pointer forward
            while (right - left + 1 - maxCount > k) {
                char leftChar = s.charAt(left);
                count[leftChar - 'A']--; // decrement frequency of left character
                left++; // move left pointer forward
            }

            // Update the maximum length of the substring
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        String s = "ABAB";
        int k = 2;
        System.out.println(characterReplacement(s, k)); // Output: 4
    }
}

