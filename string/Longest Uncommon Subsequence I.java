class Solution {
    public int findLUSlength(String a, String b) {
        if (a.equals(b))
            return -1;
        return Math.max(a.length(), b.length());
    }
}

//題意大概是a和b相等就是沒有最長的串
//沒看懂題意。。。