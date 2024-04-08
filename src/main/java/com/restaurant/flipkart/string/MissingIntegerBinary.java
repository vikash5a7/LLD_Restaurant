package com.restaurant.flipkart.string;

import java.util.HashSet;

public class MissingIntegerBinary {

    public static String findMissingInteger(String[] binaryNums) {
        HashSet<Integer> numsSet = new HashSet<>();
        int max = 0;
        for (String num : binaryNums) {
            int value = Integer.parseInt(num, 2);
            numsSet.add(value);
            max = Math.max(max, value);
        }

        // Step 2: Find the Missing Integer
        for (int i = 0; i <= max + 1; i++) {
            if (!numsSet.contains(i)) {
                // Step 3: Convert missing integer to binary without leading zero
                return Integer.toBinaryString(i);
            }
        }

        return null;
    }

    public static void main(String[] args) {
        // Example usage:
        String[] binaryNums = {"0", "10", "11"};
        System.out.println("out put "+findMissingInteger(binaryNums)); // Output: "1"
    }
}

