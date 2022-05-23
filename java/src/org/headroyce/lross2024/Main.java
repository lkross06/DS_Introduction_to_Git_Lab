package org.headroyce.lross2024;

import java.util.HashMap;

public class Main {

    /**
     * checks if two Strings are anagrams of each other
     * @param a first String
     * @param b second String
     * @return true if Strings are anagrams, false otherwise
     */
    public static boolean problem1(String a, String b){

        boolean rtn = true;
        //HashMap key contains each character in String a
        //HashMap value is how many duplicates of the character there are (0, 1, 2, etc.)
        HashMap<String, Integer> hash = new HashMap<>();

        //split String into an array, where each element is one character of the string (in order)
        String[] arrA = a.split("");
        String[] arrB = b.split("");

        //check if inputs are valid, if not return false
        if ((a.length() != b.length()) || (a == null || b == null)){
            rtn = false;
        }

        //runs in O(a) time, defines HashMap keys and values
        for (String A : arrA){
            int dupe = 0;
            if (hash.containsKey(A)){
                dupe = hash.get(A) + 1;
            }
            hash.put(A, dupe);

        }

        //runs in O(b) time, compares each character in String b to HashMap keys. If no matches occur, return false.
        for (String B : arrB){
            if (hash.containsKey(B)){
                if (hash.get(B) > 0) {
                    hash.put(B, hash.get(B) - 1);
                } else {
                    hash.remove(B);
                }
            } else {
                rtn = false;
            }
        }

        return rtn;
    }

    /**
     * given a positively-sorted array, searches for value
     * @param arr positively-sorted array of integers
     * @param val integer value to search for
     * @return index of value if found, -1 otherwise
     */
    public static int problem2(int[] arr, int val){
        //default return value is -1
        int rtn = -1;

        //keep track of start, end, and midpoint value indexes in array
        int start = 0;
        int end = arr.length - 1;
        int mid = (start + end) / 2;

        boolean indexfound = false;

        //perform a binary search, where you cut the possible values in half every iteration until a value is found or it is proven that none exists.
        while (!indexfound){
            if (start == mid || end == mid) {
                //value is not in array because possible values does not fit criteria
                indexfound = true;
                //check if midpoint is off by 1 value (because integer division rounds down)
                if (val == arr[end]){
                    rtn = end;
                }
            }
            //cut the possible values in half depending on relationship between midpoint and wanted value
            if (val > arr[mid]){
                start = mid;
            } else if (val < arr[mid]){
                end = mid;
            } else {
                indexfound = true;
                rtn = mid;

            }

            mid = (start + end) / 2;

        }

        return rtn;
    }

    public static void main(String[] args) {
        problem1("anagram", "naragam");
        problem2(new int[]{1, 2, 5, 9, 11}, 5);
    }
}