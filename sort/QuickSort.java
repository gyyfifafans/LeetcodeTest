public class QuickSort {
	public static  int[] QuickSort(int[]myarray,int low,int high){
		int i = low;
		int j = high;
		//不写的话递归的时候会越界
		if (i>=j){
			return myarray;
		}
		int key = myarray[i];
		while (i<j){
			while(i<j && myarray[j]>=key){
				j = j-1;
			}
			myarray[i] = myarray[j];
			while(i<j && myarray[i]<=key){
				i = i+1;
			}
			myarray[j] = myarray[i];
		}
		//i == j, i的位置左边都比 i所对应的值小，i的位置右边都比 i所对应的值大
		//接下来i == j这个位置就确定了，再继续就是分别 low 到 i-1 同理
		// j+1 到 high 同理

		//此時i==j
		//下面兩種都一樣
		//myarray[j] = key;
		//myarray[i] = key;
		myarray[j] = key;
		//System.out.println("i = "+i+" j = "+j+"\n");
		QuickSort(myarray,low,i-1);
		QuickSort(myarray,j+1,high);
		return myarray;	
	}


	public static void main(String[]args){
		int  myarray[] = {3,5,7,2,1,6,7,9,0,3,10,4,8};
		int a[];
		a = QuickSort(myarray,0,12);
		for(int i = 0;i<a.length;i++){
			System.out.println(a[i]);
		}
	}

}
