import java.util.Arrays;

public class MergeSort {

    /**
     * 2.再合并数列就完成了归并排序
     *
     * 首先考虑下如何将将二个有序数列合并。这个非常简单，只要从比较二个数列的第一个数，谁小就先取谁，
     * 取了后就在对应数列中删除这个数。然后再进行比较，如果有数列为空，那直接将另一个数列的数据依次取出即可。
     * @param a
     * @param low
     * @param mid
     * @param high
     */
    public static void merge(int[] a, int low, int mid, int high) {
        //理解两个数组1和2，合到temp数组里，但是temp数组长度动态
        //两个数组 d和b 也是动态的

        System.out.println("low = "+low+" mid = "+mid+" high = "+high+"\n");
        //第一次进入 low=0,mid = 0,high = 1
        int[] temp = new int[high - low + 1];
        int i = low;// 左指针 数组1 指向第一个
        int j = mid + 1;// 右指针 数组2 指向第一个
        int k = 0;
        // 把较小的数先移到新数组中
        while (i <= mid && j <= high) {
            if (a[i] < a[j]) {
                //a[i] 是 数组1
                //a[j] 是 数组2
                //只不过放到同一个数组里处理了，分治的时候其实是看做两个数组
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
        // 把左边剩余的数移入数组
        while (i <= mid) {
            temp[k++] = a[i++];
        }
        // 把右边边剩余的数移入数组
        while (j <= high) {
            temp[k++] = a[j++];
        }


        //其实可以return temp这个数组就是结果，但是考虑，这只是其中一步，就替换原有数组
        // 把新数组中的数覆盖nums数组
        for (int k2 = 0; k2 < temp.length; k2++) {
            a[k2 + low] = temp[k2];
        }
    }

    /**
     * 1.先递归的分解数列
     * @param a
     * @param low
     * @param high
     */
    public static void mergeSort(int[] a, int low, int high) {
        int mid = low + (high-low) / 2;//避免加法溢出，low 和 high都是很大的数的时候
        if (low < high) {
            // 左边
            mergeSort(a, low, mid);
            // 右边
            mergeSort(a, mid + 1, high);
            // 左右归并
            merge(a, low, mid, high);
            System.out.println(Arrays.toString(a));
            System.out.println("一次merge结束\n");

        }

    }

    public static void main(String[] args) {
        int a[] = { 51, 46, 20, 18, 65, 97, 82, 30, 77, 50 };
        mergeSort(a, 0, a.length - 1);
        System.out.println("排序结果：" + Arrays.toString(a));
    }
}