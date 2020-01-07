#include <iostream>
#include <vector>
using namespace std;

#define N 12
class Sort{
    public:
    void solution(){
        int a[]={3,5,7,2,1,6,7,9,0,3,10,4,8};
        //int a[] = {8,7,3,5,1};
        //selectionsort(a);
        //insertsort(a);
        //int *c = quicksort(a,0,N-1);
        mergesort(a,0,N-1);
        for(int i=0;i<N;i++){
            cout<< a[i] << endl;
            //cout<< c[i]<<endl;
        }
    }
    void selectionsort(int a[]){
        cout<< "start select sort: "<< endl;
        /**
         * 
         */
        for(int i=0;i< N-1;i++){
            int minindex = i;
            for(int j=i+1;j<N;j++){
                minindex = a[minindex]>a[j]? j:minindex;

            }
            cout<<"minindex = "<< minindex<<endl;
            int temp = a[minindex];
            a[minindex] = a[i];
            a[i] = temp;
        }
    }

    void insertsort(int a[]){
        cout<< "start insert sort : "<<endl;
        for(int i=1;i<N;i++){
            int j = i;
            int insertnumber = a[i];
            while(j>0 && insertnumber<a[j-1]){
                int temp = a[j];
                a[j] = a[j-1];
                a[j-1] = temp;
                j--;
            }
        }
    }

    int *quicksort(int a[],int low,int high){
        //cout<< "start quick sort : " <<endl;
        int i = low;
        int j = high;
        if(i>=j){
            return a;
        }
        int key = a[i];
        while(i<j){
            while(i<j && a[j]>=key){
                j = j-1;
            }
            a[i] = a[j];
            while (i<j && a[i]<=key)
            {
                i=i+1;
            }
            a[j] = a[i];
        }
        a[j] = key;
        quicksort(a,low,i-1);
        quicksort(a,j+1,high);
        return a;
    }
    void merge(int a[],int low,int mid,int high){
        int *temp = new int[high-low+1];
        int i = low;
        int j = mid +1;
        int k = 0;
        while(i<=mid && j<=high){
            if(a[i]<a[j]){
                temp[k++] = a[i++];
            }
            else{
                temp[k++] = a[j++];
            }
        }

        while(i<=mid){
            temp[k++] = a[i++];
        }

        while(j<=high){
            temp[k++] = a[j++];
        }


        for(int k2=0;k2<high-low+1;k2++){
            a[k2+low] = temp[k2];
        }

    }
    void mergesort(int a[],int low,int high){
        int mid = low+(high-low)/2;
        if(low<high){
            mergesort(a,low,mid);
            mergesort(a,mid+1,high);
            merge(a,low,mid,high);
        }

    }
};

int main(){
    Sort s;
    s.solution();
}
