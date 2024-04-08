package com.restaurant.flipkart.string;

class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty())
            return "";
        String longestPalindrome = "";
        for (int i = 0; i < s.length(); i++) {

            // Expand around center for odd length palindromes
            String palindrome1 = expandAroundCenter(s, i, i);

            // Expand around center for even length palindromes
            String palindrome2 = expandAroundCenter(s, i, i + 1);

            // Update longest palindrome found
            if (palindrome1.length() > longestPalindrome.length())
                longestPalindrome = palindrome1;

            if (palindrome2.length() > longestPalindrome.length())
                longestPalindrome = palindrome2;
        }

        return longestPalindrome;
    }

    private String expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left + 1, right);
    }

    public static void main(String[] args) {
        String word ="babad";
        LongestPalindromicSubstring l = new LongestPalindromicSubstring();
        String s = l.longestPalindrome(word);
        System.out.println("output   -> "+s);
    }
}

