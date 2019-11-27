class Solution {
    public int maxNumberOfBalloons(String text) {
        if (text == "" || text.length() < 7) {
        return 0;
    }
    int len = text.length();
    int[] arr = new int[26];
    for (int i=0; i<len; i++) {
        arr[text.charAt(i)-'a']++;
    }
    int min = Math.min(arr[0], arr[1]); //a b
    min = Math.min(min, arr[13]); // n
    min = Math.min(min, arr[11]/2); // l
    min = Math.min(min, arr[14]/2); // o
    return min;
    }
}

/*
题目的意思是要在一个给定的字符串中，找到能够组成字符串"balloon"的最大字符对数，本质上和木桶装水的容量由短板决定类似。

直接遍历text字符串中的字符，对字母a、b、l、n、o的出现次数计数，因为l和o是需要两个，在计数完后，需要对l和o的次数除2，然后比较5个字母出现次数的最小值，因为只有出现次数最小的那个字母才能最终决定组成多少个"balloon"。
*/