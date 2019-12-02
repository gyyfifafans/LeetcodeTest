//记录防止遗忘
class Solution {
    public String toGoatLatin(String S) {
        String s = "";
        String[]t = S.split(" ");
        for(int i=0;i<t.length;i++){
            char temp = t[i].charAt(0);
            if(temp=='a' || temp=='A'||temp=='e'||temp=='E'||temp=='i'||temp=='I'||temp=='o'
                  ||temp=='O'||temp=='u'||temp=='U'  ){
                t[i]+="ma";
                for(int j=0;j<i+1;j++){
                    t[i]+='a';
                }
            }
            else {
                t[i] = t[i].substring(1,t[i].length())+temp+"ma";
                for(int j=0;j<i+1;j++){
                    t[i]+='a';
                }
            }
            s+=t[i]+" ";

        }
        return s.trim();
    }
}
