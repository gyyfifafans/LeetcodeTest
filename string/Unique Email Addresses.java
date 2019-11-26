/*
题意：计算给定列表中不同邮件地址的数量；其中，每个邮件的本地名和域名中可能包含符号‘.’和‘+’，对于本地名有如下规则：

如果包含符号‘.’，则连接符合‘.’后的连续字母
如果包含符号‘+’，则对于此字符之后的所有字符都不连接
解法：要计算有多少个不同的邮件地址，我们可以用一个集合来存储，这样最后返回集合的长度就是不同邮件地址的个数了；因此，最重要的一个步骤就是根据上面的规则得出邮件地址，我们只需要处理的是本地名即可；

还有用到hashset的不重复性
*/

class Solution {
    public int numUniqueEmails(String[] emails) {
        Set<String> table = new HashSet<>();
        for (String email : emails) {
            table.add(getEmail(email));
        }
        return table.size();
    }
    
    private String getEmail(String email) {
        StringBuilder sb = new StringBuilder();
        int index = email.indexOf('@');
        for (int i = 0; i < index; i++) {
            if (email.charAt(i) == '.') continue;
            if (email.charAt(i) == '+') break;
            sb.append(email.charAt(i));
        }
        sb.append(email.substring(index));
        return sb.toString();
    }
}