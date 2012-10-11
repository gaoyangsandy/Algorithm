public class RegularExpression {
    public boolean isMatch(String s, String p) {
        if(s.length()==0){
            if(p.length()==0) return true;
            if(p.length()==1) return false;
            if(p.charAt(1)=='*') return isMatch(s,p.substring(2));
            return false;
        }
        if(p.length()==0){
            if(s.length()==0) return true;
            return false;
        }
        
        char fs=s.charAt(0);
        char fp=p.charAt(0);
        boolean isStar = p.length()>1 && p.charAt(1)=='*';
        if(!isStar){
            return isMatch(fs,fp) && isMatch(s.substring(1),p.substring(1));
        }else{
            if(isMatch(fs,fp)){
                return isMatch(s.substring(1),p.substring(0))
                    || isMatch(s.substring(0),p.substring(2));
            }else{
                return isMatch(s,p.substring(2));
            }
        }
    }
    
    boolean isMatch(char s, char p){
        return p=='.' || s==p;
    }
}