class Solution 
{
    public int numSpecialEquivGroups(String[] A) 
    {
        Set<String> seen = new HashSet<>();
        
        for(String str: A)
        {
            String evenStr = "";
            String oddStr = "";
            
            for(int i = 0; i < str.length(); i++)
            {
                if(i % 2 == 0) // even index
                {
                    evenStr += str.charAt(i);
                }
                else // odd index
                {
                    oddStr += str.charAt(i);
                }
            }
            
            seen.add(sortString(evenStr) + sortString(oddStr));
            
        }
        
        return seen.size();
    }
    
    private String sortString(String s)
    {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        
        return new String(arr);
    }
}

/*
题目大意
可以对一个字符串的所有奇数位置或者偶数位置进行任意的调换顺序。如果两个字符串在经历了上面的操作之后，可以做到完全相等，那么就属于题目中的一个组。现在就要我们求最终分为几个组。
题目可以让在 偶数位置的 chars 互换， 也可以让 在 奇数位置的 chars 互换。

　所以为了 return 正确的 group 数量，需要把 那些重复的 给排除掉。

　可以把在 偶数位置的 chars 都拿出来 组成一个 string a， 同样的把 在奇数位置上的 chars 都拿出来组成一个 string b，分别把a 和 b 排序一下，再把两个a 和 b组合并且存入 HashSet。

　最后只要返回 HashSet 的 size 就可以了。
//python verion
class Solution(object):
    def numSpecialEquivGroups(self, A):
        """
        :type A: List[str]
        :rtype: int
        """
        B = set()
        for a in A:
            B.add(''.join(sorted(a[0::2])) + ''.join(sorted(a[1::2])))
        return len(B)


*/