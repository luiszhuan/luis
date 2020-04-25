package com.luis.leetcode;

//https://leetcode-cn.com/problems/longest-palindromic-substring/
public class LongestPalindromicSubstring {

    public String longestPalindrome2(String s) {
        int len = s.length();
        boolean dp[][] = new boolean[len][len];
        int maxLength = 1;
        int startIndex = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                dp[i][j] = false;
            }
        }
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
            if (i + 1 < len - 1 && s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                maxLength = 2;
                startIndex = i;
            }
        }

        for (int curLen = maxLength; curLen <= len; curLen++) {
            for (int i = 0; i < len - curLen; i++) {
                if (dp[i + 1][i + curLen - 1] && s.charAt(i) == s.charAt(i + curLen)) {
                    dp[i][i + curLen] = true;
                    if (curLen > maxLength) {
                        maxLength = curLen;
                        startIndex = i;
                    }
                }
            }
        }
        return s.substring(startIndex, startIndex + maxLength);
    }

    public String longestPalindrome(String s) {

        int start = 0;
        int maxLen = 1;

        if (s == null || s.length() == 0) {
            return "";
        }
        boolean dp[][] = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
            if ((i < s.length() - 1) && s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                start = i;
                maxLen = 2;
            }
        }

//        for (int k = 0; k < s.length(); k++) {
//            for (int len = 3; len <= s.length(); len++) {
//                int j1 = k + len - 1;
//                if (dp[k + 1][j1 - 1] && s.charAt(k) == s.charAt(j1)) {
//                    dp[k][j1] = true;
//                    if (len > maxLen) {
//                        maxLen = len;
//                        start = j1;
//                    }
//                } else {
//                    dp[k][j1] = false;
//                }
//            }
//        }

        for (int curLen = 3; curLen <= s.length(); curLen++) {
            for (int i = 0; i < s.length() - curLen + 1; i++) {
                int j = i + curLen - 1;
                if (dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = true;
                    if (curLen > maxLen) {
                        maxLen = curLen;
                        start = i;
                    }
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return s.substring(start, start + maxLen);
    }

    public static void main(String[] args) {
        System.out.println(new LongestPalindromicSubstring().longestPalindrome("abcba"));
    }

}
