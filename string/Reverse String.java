class Solution {
    public void reverseString(char[] s) {
        int left = 0, right = s.length - 1;
        while (left < right) {
            char tmp = s[left];
            s[left++] = s[right];//s[left] = s[right]; left++;
            s[right--] = tmp;//s[right] = tmp; right--;
        }
    }
}

/*
Time complexity : O(N) to swap N/2N/2 element.

Space complexity : O(1), it's a constant space solution.

*/