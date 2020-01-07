/**
 * 从整个待排序列中选出一个元素插入到已经有序的子序列中去，得到一个有序的、元素加一的子序列，直到整个序列的待插入元素为0，则整个序列全部有序。
 在实际的算法中，我们经常选择序列的第一个元素作为有序序列（因为一个元素肯定是有序的），
 我们逐渐将后面的元素插入到前面的有序序列中，直到整个序列有序
 */
public class InsertSort {
	public static int[] InsertSort(int []myarray){
		for(int i=1;i< myarray.length;i++){
			int j = i;
			int target = myarray[i];
			while(j>0 && target <myarray[j-1]){
				//局部排序，两两比较，交换 0 到 j
				int temp = myarray[j];
				myarray[j] = myarray[j-1];
				myarray[j-1] = temp;
				j--;
			}
		}
		return myarray;
	}

	public static void main(String[] args) {
	    int myarray[] = {3,5,7,2,1,6,7,9,0,3,10,4,8};
	    int[]a;
		a = InsertSort(myarray);
		for(int i = 0;i < a.length;i++){
			System.out.println(a[i]);
		}	
	}
}
