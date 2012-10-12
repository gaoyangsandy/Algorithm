public class RemoveDuplicates{
    public int removeDuplicates(int[] A) {
        if(A.length<=1) return A.length;
        
        //loop invariant
        // A[0],...,A[i] are sorted and not duplicated
        // A[i+1],...,A[j-1] are duplicates of A[0],...,A[i]
        // A[j],....,A[n-1] have not been examed.
        // i<j<=n
        
        int i=0;
        for(int j=1;j<A.length;j++){
            if(A[i]<A[j]){
                swap(A,++i,j);
            }
        }
        return i+1;
    }
    
    void swap(int[] a, int i, int j){
        int t=a[i];
        a[i]=a[j];
        a[j]=t;
    }
}