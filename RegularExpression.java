class Solution {
    public boolean isMatch(String s, String p) {
        Map<String,Boolean> c = new HashMap<String,Boolean>();
        return isMatch(s, p, c);
    }
    
    private boolean cacheAndReturn(String s, String p, Map<String,Boolean> cache) {
        String key = s+":"+p;
        boolean result = isMatch(s,p, cache);
        cache.put(key, result);
        return result;
    }
    
    private boolean isMatch(String s, String p, Map<String,Boolean> cache) {
        String key = s+":"+p;
            if (cache.containsKey(key)) {
            return cache.get(key);
        }
    
        if(s.length()==0){
            if(p.length()==0) return true;
            if(p.length()==1) return false;
            if(p.charAt(1)=='*') {
                return cacheAndReturn(s,p.substring(2), cache);         
            } 
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
            return isMatch(fs,fp) && cacheAndReturn(s.substring(1),p.substring(1), cache);
        }else{
            if(isMatch(fs,fp)){
                return cacheAndReturn(s.substring(1),p.substring(0), cache)
                    || cacheAndReturn(s.substring(0),p.substring(2), cache);
            }else{
                return cacheAndReturn(s,p.substring(2), cache);
            }
        }
    }
    
    boolean isMatch(char s, char p){
        return p=='.' || s==p;
    }
}
