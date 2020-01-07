/**
 * 每一趟从待排序的记录中选出最小的元素，顺序放在已排好序的序列最后，直到全部记录排序完毕。
 * 也就是：每一趟在n-i+1(i=1，2，…n-1)个记录中选取关键字最小的记录作为有序序列中第i个记录。
 * 基于此思想的算法主要有简单选择排序、树型选择排序和堆排序。（这里只介绍常用的简单选择排序）
 */
public class SelectionSort {
	public static int[]SelectionSort(int[]myarray){
		int i = 0,j = 0;
		int min = 0;
		for(i = 0;i<myarray.length-1;i++){
			min = i;
			for(j = i+1;j<myarray.length;j++){
				if (myarray[j]< myarray[min]){
					min = j;
				}
			}
			if (i!=min){
			    int tmp = myarray[i];
			    myarray[i] = myarray[min];
			    myarray[min] = tmp;
			}

		}
		return myarray;
	}


	public static void selectSort(int[]arr){
		for(int i=0;i<arr.length-1;i++){
			int minIndex = i;
			for(int j=i+1;j< arr.length;j++){
				minIndex = arr[j]<arr[minIndex]?j:minIndex;
			}
			int tmp = arr[i];
			arr[i] = arr[minIndex];
			arr[minIndex] = tmp;
		}
	}

	public static void main(String[]args){
		int[]myarray = {3,5,7,2,1,6,7,9,0,3,10,4,8};
		int[]a;
//		a = SelectionSort(myarray);
//		for(int i = 0;i < a.length;i++){
//			System.out.println(a[i]);
//		}
		selectSort(myarray);
		for(int i = 0;i < myarray.length;i++){
			System.out.println(myarray[i]);
		}

	}


	

}
