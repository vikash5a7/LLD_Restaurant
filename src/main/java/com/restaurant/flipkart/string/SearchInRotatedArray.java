package com.restaurant.flipkart.string;

public class SearchInRotatedArray {

//
    /**
     * This algorithm has a time complexity of O(log N), where N is the
     * number of elements in the array, as it performs a binary search.
     * https://www.ambitionbox.com/interviews/flipkart-interview-questions/software-developer/top-questions?page=3
     * @param nums
     * @param target
     * @return
     */

    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            // If the left half is sorted
            if (nums[left] <= nums[mid]) {
                // Check if the target lies in the left half
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // If the right half is sorted
            else {
                // Check if the target lies in the right half
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1; // Target not found
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 1, 2, 3}; // Example rotated sorted array
        int key = 2; // Example query
        int index = search(nums, key);
        System.out.println("Index of " + key + ": " + index); // Output: 3
    }
}

