/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author cem
 */
public class Calculator {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner console=new Scanner(System.in);
        String DirInput=console.nextLine();
        String formatted=format(DirInput);
        System.out.println(calculate(formatted));
    }
    public static String calculate(String str){
        if(str.length()==0)
            return "";
        if(str.indexOf('(')==-1)
            return (String)(Ord3(Ord2(Ord1(Ord0(separate(str))))).get(0));
        else 
            return calculate(str.substring(0,str.indexOf('('))+calculate(str.substring(str.indexOf('(')+1,findMatch(str.indexOf('('),str)))+str.substring(findMatch(str.indexOf('('),str)+1,str.length()));
    }
    public static int findMatch(int pos, String str){
        int open=0;
        for(int i=pos; i<str.length();i++){
            if(str.charAt(i)=='(')
                open++;
            else if(str.charAt(i)==')')
                open--;
            if(open==0)
                return i;
        }
        return -1;
    }
    public static boolean hasPar(ArrayList list){
        boolean a=false;
        for(int i=0;i<list.size();i++){
            if(list.get(i).equals("(")){
                a=true;
                break;
            }
        }
        return a;
    }
    public static ArrayList Ord0(ArrayList list){
        for (int i=0;i<list.size();i++){
            if(list.get(i).equals("sin")){
                list.set(i,Double.toString(Math.sin(Double.parseDouble((String)(list.get(i+1))))));
                list.remove(i+1);
            }
            else if(list.get(i).equals("cos")){
                list.set(i,Double.toString(Math.cos(Double.parseDouble((String)(list.get(i+1))))));
                list.remove(i+1);
            }
            else if(list.get(i).equals("tan")){
                list.set(i,Double.toString(Math.tan(Double.parseDouble((String)(list.get(i+1))))));
                list.remove(i+1);
            }
            else if(list.get(i).equals("cot")){
                list.set(i,Double.toString(1/Math.tan(Double.parseDouble((String)(list.get(i+1))))));
                list.remove(i+1);
            }
            else if(list.get(i).equals("sec")){
                list.set(i,Double.toString(1/Math.cos(Double.parseDouble((String)(list.get(i+1))))));
                list.remove(i+1);
            }
            else if(list.get(i).equals("csc")){
                list.set(i,Double.toString(1/Math.sin(Double.parseDouble((String)(list.get(i+1))))));
                list.remove(i+1);
            }
        }
        return list;
    }
    public static ArrayList Ord1(ArrayList list){
        for(int i=0;i<list.size();i++){
            if(list.get(i).equals('^')){
                String one=(String) list.get(i-1);
                String two=(String) list.get(i+1);
                list.set(i-1,Double.toString(Math.pow(Double.parseDouble(one),Double.parseDouble(two))));
                list.remove(i);
                list.remove(i);
                i--;
            }
        }
        return list;
    }
    public static ArrayList Ord2(ArrayList list){
        for(int i=0;i<list.size();i++){
            if(list.get(i).equals('*')){
                String one=(String) list.get(i-1);
                String two=(String) list.get(i+1);
                list.set(i-1,Double.toString(Double.parseDouble(one)*Double.parseDouble(two)));
                list.remove(i);
                list.remove(i);
                i--;
            }
            else if(list.get(i).equals('/')){
                String one=(String) list.get(i-1);
                String two=(String) list.get(i+1);
                list.set(i-1,Double.toString(Double.parseDouble(one)/Double.parseDouble(two)));
                list.remove(i);
                list.remove(i);
                i--;
            }
            else if(list.get(i).equals('%')){
                String one=(String) list.get(i-1);
                String two=(String) list.get(i+1);
                list.set(i-1,Double.toString(Double.parseDouble(one)%Double.parseDouble(two)));
                list.remove(i);
                list.remove(i);
                i--;
            }
        }
        return list;
    }
    
    public static ArrayList Ord3(ArrayList list){
        for(int i=0;i<list.size();i++){
            if(list.get(i).equals('+')){
                String one=(String) list.get(i-1);
                String two=(String) list.get(i+1);
                list.set(i-1,Double.toString(Double.parseDouble(one)+Double.parseDouble(two)));
                list.remove(i);
                list.remove(i);
                i--;
            }
            else if(list.get(i).equals('-')){
                String one=(String) list.get(i-1);
                String two=(String) list.get(i+1);
                list.set(i-1,Double.toString(Double.parseDouble(one)-Double.parseDouble(two)));
                list.remove(i);
                list.remove(i);
                i--;
            }
        }
        return list;
    }
    public static ArrayList separate(String str){
        int lastInd=0;
        ArrayList list=new ArrayList();
        if(str.charAt(0)=='+')
            str=str.substring(1,str.length());
        int a=0;    
        if(str.charAt(0)=='-')
                a++;
        for(int i=a;i<str.length();i++){
            if(isOperant(str.charAt(i))){
                list.add(str.substring(lastInd, i));
                list.add(str.charAt(i));
                lastInd=i+1;
            }
            else if(str.length()-i>3&&((str.substring(i,i+3)).equals("sin") || (str.substring(i,i+3)).equals("cos") || (str.substring(i,i+3)).equals("tan") || (str.substring(i,i+3)).equals("cot") ||(str.substring(i,i+3)).equals("sec") ||(str.substring(i,i+3)).equals("csc"))){
                //list.add(str.substring(lastInd, i));
                list.add(str.substring(i,i+3));
                lastInd=i+3;
                System.out.println(list);
            }
        }
        for(int i=str.length()-1;i>0;i--){
            if(isOperant(str.charAt(i))){
                list.add(str.substring(i+1,str.length()));
                break;
            }
            else if(str.length()-i>3 &&((str.substring(i,i+3)).equals("sin") || (str.substring(i,i+3)).equals("cos") || (str.substring(i,i+3)).equals("tan") || (str.substring(i,i+3)).equals("cot") ||(str.substring(i,i+3)).equals("sec") ||(str.substring(i,i+3)).equals("csc"))){
                list.add(str.substring(i+3,str.length()));
                System.out.println(list);
                break;
            }
            
        }
        return list;
    }
    public static boolean isOperant(char b){
        return (b=='+'||b=='*'||b=='-'||b=='/'||b=='^'||b=='%');
    }
    public static String format(String str){
        str=str.toLowerCase();
        String spaceless = "";
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) != ' ')
               spaceless += str.charAt(i);
        }
        String corrections = "";
        for (int i = 0; i < spaceless.length(); i++) {
            if(spaceless.charAt(i) == 'x')
                if (i == 0 || !isNumeric(spaceless.charAt(i-1)+""))
                    corrections += "1";
            corrections += spaceless.charAt(i);
            if(spaceless.charAt(i) == 'x')
                if (i == spaceless.length() - 1 || spaceless.charAt(i+1) != '^')
                    corrections += "^1";
        }
        return "0"+corrections;
    }
    public static boolean isNumeric(String str)  {  
        try  {  
            int d = Integer.parseInt(str);  
        }  
        catch(NumberFormatException nfe) {  
            return false;  
        }  
        return true;  
    }
    public static int findMatch(String str, int pos) {
        int open = 1;
        for (int i = pos + 1; i < str.length(); i++) {
            if (str.charAt(i) == '(')
                open++;
            if (str.charAt(i) == ')')
                open--;
            if (open == 0)
                return i;
        }
        return pos + 2;
    }
}


