class Solution {
    public String reverseOnlyLetters(String S) {
        int left = 0;
        int right = S.length()-1;
        char[]t = S.toCharArray();
        while (left<right){
            
            if(!Character.isLetter(t[left])){
                left++;
            }
            else if(!Character.isLetter(t[right])) {
                right--;
            }
            else{
                char temp = t[left];
                t[left++] = t[right];
                t[right--] = temp;
            
            }
        }
        return String.valueOf(t);
    }
}
//第一次写超时了用的ascii值判断是否是字母，第二次写用了现成的方法Character.isLetter