
public class BubbleSort {
	public static void main(String[]args){
		int a[] = {3,4,22,5,7,9,4,6,0,10,1};
		int i,j,tmp = 0;
		System.out.println("数组长度 :"+a.length);
		for (i = 0 ;i < a.length-1;i++){
			for (j = 0;j < a.length - 1;j++){
				if (a[j] < a[j+1]){
					tmp = a[j];
					a[j] = a[j+1];
					a[j+1] = tmp;
					
				}
			}
		}
		for(i = 0;i<a.length;i++){
			System.out.println(a[i]);
		}
	}

}
