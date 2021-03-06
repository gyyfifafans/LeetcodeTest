/*
这道题定义了一种好数字，就是把每位上的数字自身翻转一下，
能得到一个不同的数字。翻转规则定义为，0，1，和8翻转后还为其本身，
2和5，6和9可以互相翻转。然后给了一个数字N，问 [1, N] 区间内共有多少个这样的好数字。
就是一个数字一个数字的验证呗，对于每个数字，再一位一位的验证呗。将验证某个数字是否 Good 的操作写到一个子函数中，遍历数字的每一位的方法，
可以通过不停的除以 10 来获得，也可以像博主这样通过转变为字符串，再遍历字符即可。
翻转规则中没有提到的数字有三个，3，4，和7，说明这三个数字无法翻转，若一旦被翻转，则无法形成 valid 的数字，
所以只要一旦遇到这三个数字中的一个，直接返回 false 即可。还有要注意的是，0，1，和8这三个数字翻转后还是其本身，
由于好数字的定义是需要翻转一个不同的数字，所以若都是由这三个数字组成，翻转后不会产生不同的数字，也不符合题意。
所以需要2，5，6，和9这四个数字中至少出现一个，用一个 flag 来标记出现过这些数字，最后只要 check 这个 flag 变量即可
*/

class Solution {
    public int rotatedDigits(int N) {
        int res = 0;
        for(int i=1;i<=N;i++){
            if(check(i)) res++;
        }
        return res;
    }
    
    public static boolean check(int k){
        String str = String.valueOf(k);
        boolean flag = false;
        for(char c:str.toCharArray()){
            if(c=='3'||c=='4'||c=='7') return false;
            if(c=='2'||c=='5'||c=='6'||c=='9') flag = true;

        }
        return flag;
    }
}
//自己的解法多出好多
//        int count = 0;
//        for(int i=100;i<N+1;i++) {
//            int temp = i;
//            while (temp / 10 != 0) {
//                if (temp == 3 || temp == 4 || temp == 7) {
//                    count--;
//                }
//                if (temp % 10 == 2 || temp % 10 == 5 || temp % 10 == 6 || temp % 10 == 9) {
//                    count++;
//                }
//                temp /= 10;
//            }
//            if (i == 2 || i == 5 || i == 6 || i == 9) {
//                count++;
//            }
//        }



/*
其实这道题还有更好的解法呢，使用动态规划 Dynamic Programming 来做的，思路非常巧妙，博主深为叹服。
定义了一个长度为 N+1 的一维布尔型 DP 数组，其中 dp[i] 表示数字i的三种状态，0表示数字i翻转后不合法，1表示数字i翻转后和原数相同，
2表示数字i翻转后形成一个不同的数字。那么根据题目中的定义可知，只有当 dp[i]=2 的时候才是好数字。那么下面来看状态转移方程吧，
如果数字只有1位的话，那么判断起来很简单，如果是 0，1，和8中的一个，则 dp[i]=1，如果是 2，5，6，和9中的一个，则 dp[i]=2，并且结果 res 自增1。
如果是剩下的三个数字 3，4，7中的一个不用更新，因为dp数组初始化就为0。下面来看数字i大于 10 的情况，非常的经典，dp[i] 的值其实可以从 dp[i/10] 和 dp[i%10] 这两个状态值转移而来，
由于更新的顺序是从小到大，所以当要更新 dp[i] 的时候，dp[i/10] 和 dp[i%10] 这两个状态值已经算过了。为啥 dp[i] 的值是由这两个状态值决定的呢？
因为每个数字都是相互独立的翻转，比如四位数字 abcd，可以拆分为三位数 abc，和个位数d，如果 abc 翻转后仍是 abc，d翻转后仍是d，说明 abcd 翻转后仍是 abcd，
所以 dp[i]=1，只要其中有一个大于1了，另外一个至少是1的话，那么说明可以翻转成不同的数字，dp[i]=2，并且结果 res 自增1

class Solution {
public:
    int rotatedDigits(int N) {
        int res = 0;
        vector<int> dp(N + 1);   
        for (int i = 0; i <= N; ++i) {
            if (i < 10) {
                if (i == 0 || i == 1 || i == 8) dp[i] = 1;
                else if (i == 2 || i == 5 || i == 6 || i == 9) {
                    dp[i] = 2; ++res;
                }
            } else {
                int a = dp[i / 10], b = dp[i % 10];
                if (a == 1 && b == 1) dp[i] = 1;
                else if (a >= 1 && b >= 1) {
                    dp[i] = 2; ++res;
                }
            }
        }
        return res;
    }
};

*/