class Solution {
    public String toLowerCase(String str) {
        String s = "";
        char[]temp = str.toCharArray();
        for(char r:temp){
            if(r>='A'&& r<='Z'){
                r = (char)(Integer.valueOf(r)+'a'-'A');
                s+=r;
            }
            else {
                s += r;
            }
        }
        return s;
    }
}