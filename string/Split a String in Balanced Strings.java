class Solution {
    public int balancedStringSplit(String s) {
        int rCount = 0;
        int lCount = 0;
        int count = 0;
        //R和L总数必定相等
        //而且从头开始对应相等
        char[] temp = s.toCharArray();
        for(char r:temp){
            if(r=='R'){
                rCount+=1;
            }
            if(r=='L'){
                lCount+=1;
            }
            if(rCount==lCount){
                count+=1;
                lCount=rCount=0;
            }
        }
        return count;
    }
}