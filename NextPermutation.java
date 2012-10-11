public class NextPermutation {
    public void nextPermutation(int[] num) {
        int n = num.length;
        if(n <= 1) return;
        
        // find the larget i such taht num[i]<num[i+1]
        int i = n-2;
        while(i>=0 && num[i]>=num[i+1]) i--;
        if(i>=0){
            // we are going to replace n[i] with the something in num[i+1],...,num[n-1];    
            // find the smallest num[k] such that i<k<n and num[i]<num[k]
            int k = i+1;
            while(k+1<n && num[k+1]>num[i]) k++;
            swap(num,i,k);
        }
        
        // a[i+1],...,a[n] should now be revers sorted, so reverse them
        reverse(num,i+1,n-1);
    }
    
    // pre: 0<= i <= j <= n-1
    void reverse(int[] a, int i, int j){
        while(i<=j){
            swap(a,i++,j--);
        }
    }
    
    // pre: 0<= i <= j <= n-1
    void swap(int[] a, int i, int j){
        int t=a[i];
        a[i]=a[j];
        a[j]=t;
    }
}