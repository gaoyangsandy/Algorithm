public class Power {
    public double pow(double x, int n) {
        if( x==0){
            return n==0?1:0;
        }
        if(n==0 || x==1) return 1;
        if(x==-1) return n%2==0 ? 1:-1;
        if(n<0) return 1/pow(x,-n);
        
        double sqrt = pow(x,n/2);
        if(n%2==0){
            return sqrt*sqrt;
        }else{
            return sqrt*sqrt*x;
        }
    }
}