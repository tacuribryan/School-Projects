package todo;

public class LetterManipulation {
    
    public LetterManipulation(){
        
    }
    
    public static String toTitleCase(String input){
        StringBuffer sb = new StringBuffer();
        String[] p = input.split(" ");
        for(int i = 0; i < p.length; i++){
            char a = p[i].toUpperCase().charAt(0);
            sb.append(a);
            String b = p[i].substring(1, p[i].length());
            sb.append(b);
            if(p.length > 1){
                sb.append(" ");
            }
        }
        return sb.toString();
    }
    
    public static String toSentenceCase(String input){
        StringBuffer sb = new StringBuffer();
        char a = input.toUpperCase().charAt(0);
        sb.append(a);
        String b = input.substring(1, input.length());
        sb.append(b);
        return sb.toString();
    }
}
