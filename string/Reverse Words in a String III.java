
/*
仿照Reverse String这题的反转解法，不是最好的解法

*/

class Solution {
    public String reverseWords(String s) {
        String result = "";
        for(String i:s.split(" ")){
            char []temp = i.toCharArray();
            int left = 0, right = temp.length - 1;
            while (left < right) {
                char tmp = temp[left];
                temp[left++] = temp[right];
                temp[right--] = tmp;
            }
            result+=String.valueOf(temp)+" ";
        }
        return result.trim();
    }
}