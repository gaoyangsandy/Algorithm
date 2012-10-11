public class NQueen {
    public int totalNQueens(int n) {
        int[] pos = new int[n];
        return count(n,pos,0);
    }
    
    // rows are number 0,..,n-1
    // pos[k] represents where queen is at on row k. 0<=pos[k]<=n-1
    // depth represents pos[0],...,pos[depth-1] are alredy determined,
    // and it's time to determin what pos[depth] should be
    
    // pre: 0<=depth<=n and pos.length == n and n>0
    //      pos[0],...,pos[depth-1] have no conflict
    // returns the count of valid configurations without changing
    // pos[0],...,pos[depth-1]
    int count(int n, int[] pos, int depth){
        if(depth == n){
            return 1;
        }
        int sum = 0;
        for(int i=0;i<n;i++){
            if(valid(n,pos,depth,i)){
                pos[depth]=i;
                sum+=count(n,pos,depth+1);
            }
        }
        return sum;
    }
    
    // pre: 0<=depth<n and pos.length == n and n>0
    //      pos[0],...,pos[depth-1] have no conflict
    // returns true if pos[0],...,pos[depth-1],pos[depth] has no coflicts
    // where pos[depth] = newpos;
    boolean valid(int n,int[] pos, int depth, int newpos){
        // check columns
        for(int row=0;row<depth;row++){
            if(pos[row] == newpos) return false;
        }
        
        // check diagnoals
        for(int row=0;row<depth;row++){
            if(Math.abs(pos[row]- newpos) == depth - row) return false;
        }

        return true;
    }
}
