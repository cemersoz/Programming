/*
*Taylor Approximation Calculator
*by Cem Ersöz
*Hisar Schools
 */
package taylorapproximation;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * @author cem
 */
public class TaylorApproximation {
    /**
     * @param args the command line arguments
     */
    /*public static void main(String[] args) {
        Scanner console=new Scanner(System.in);
        System.out.println("enter function");
        String DirInput=console.nextLine();
        System.out.println("enter the value near which the polynomial will be constructed");
        String aVal=console.next();
        System.out.println("enter the value you wish to approximate");
        String xVal=console.next();
        System.out.println("enter the degree of the desired taylor polynomial");
        String nVal=console.next();
        System.out.println(writeTaylor(Double.parseDouble(xVal),Double.parseDouble(aVal),Double.parseDouble(nVal)));
        System.out.println("result: "+ doTaylor(correct(DirInput),Double.parseDouble(xVal),Double.parseDouble(aVal),Integer.parseInt(nVal),1));
        String formatted=correct(DirInput);
        //System.out.println("Derivative: "+derive(formatted));
        //System.out.println("Der. at x: "+calcDer(formatted,xVal));
    }*/
    public double doTaylor(String str,double x, double a, int n, int b){
        if(b==n)
            return Double.parseDouble(calculate(deX(str,Double.toString(a))));
        return Double.parseDouble(calculate(deX(str,Double.toString(a)))+doTaylor(deX(derivate(str),Double.toString(a)),x,a,n,b+1));         
    }
    public static String writeTaylor(double x, double a, double n){
        String str="p("+x+")= f("+a+")";
        if(n<5){
            for(int i=1;i<=n;i++){
                str+="+ f";
                for(int q=1;q<=i;q++){
                    str+="'";
                }
                str+=("("+a+")*(("+x+"-"+a+")^"+i+")/"+i+"!");
            }
        }
        return str;
    }
    public static double taylor(String str, String x, double a, int n){
        double poly=Double.parseDouble(calculate(deX(str,x)));
        for(int i=0;i<n;i++){
            str=derivate(str);
            poly+=(calcDer(str,x)*Math.pow((Double.parseDouble(x)-a),i)/factorial(i));
        }
        return poly;
    }
    public static double factorial(int i){
        if(i>1)
            return i*factorial(i-1); 
        return 1;
    }
    public static double calcDer(String derivative,String X){
        return round(Double.parseDouble(calculate(deX(derivative,X))),5);
    }
    public static String derivate(String str){
        return format(derive(correct(str)));
    }
    public static String correct(String str){
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
        return corrections;
    }
    public static String deE(String str){
        String corrections="";;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='e'){
                if(i!=0)
                    corrections+="*"+round(Math.E,6);
                else
                    corrections+=round(Math.E,6);
            }
            else
                corrections+=str.charAt(i);
        }
        return corrections;
    }
    public static boolean hasX(String str){
        return !(str.indexOf("x")==-1);
    }
    public static String deX(String str,String X){
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='x'){
                str=str.substring(0,i)+X+str.substring(i+1,str.length());
            if(i>0 && isNumeric(str.substring(i-1,i)))
                str=str.substring(0,i)+"*"+str.substring(i,str.length());
            }}
        return str;
    }
    public static boolean isNumeric(String str)  {  
        try  {  
            double d = Double.parseDouble(str);  
        }  
        catch(NumberFormatException nfe) {  
            return false;  
        }  
        return true;  
    }
    public static String derive(String str){
        if (str.isEmpty())
            return "";
        if (str.indexOf("x") == -1)
            return "0";
        if (str.indexOf("+") != -1 && (str.indexOf("(")== -1 || (str.indexOf("(") > str.indexOf("+") || findMatch(str,str.indexOf("(")) < str.indexOf("+"))) && (str.indexOf("-") <= 0 || str.indexOf("-") > str.indexOf("+")))
            return derive(str.substring(0,str.indexOf("+")))+" + " +derive(str.substring(str.indexOf("+")+1));
        if (str.substring(str.indexOf("^")+2).indexOf("-") == 0|| str.substring(str.indexOf("^")+2).indexOf("-") > 0 && (isNumeric(str.substring(str.indexOf("^")+2).charAt(str.substring(str.indexOf("^")+2).indexOf("-")-1)+"") || str.substring(str.indexOf("^")+2).charAt(str.substring(str.indexOf("^")+2).indexOf("-")-1) == ')') && (str.substring(str.indexOf("^")+2).indexOf("(")== -1 || (str.substring(str.indexOf("^")+2).indexOf("(") > str.substring(str.indexOf("^")+2).indexOf("-") || findMatch(str,str.substring(str.indexOf("^")+2).indexOf("(")) < str.substring(str.indexOf("^")+2).indexOf("-"))))
            return derive(str.substring(0,str.substring(str.indexOf("^")+2).indexOf("-")+str.substring(0,str.indexOf("^")+2).length()))+" - " +derive(str.substring(str.substring(str.indexOf("^")+2).indexOf("-")+str.substring(0,str.indexOf("^")+2).length()+1) );
        if (str.indexOf("*") != -1 && (str.indexOf("(")== -1 || (str.indexOf("(") > str.indexOf("*") || findMatch(str,str.indexOf("(")) < str.indexOf("*")))) //product
            return derive(str.substring(0,str.indexOf("*")))+"*"+str.substring(str.indexOf("*")+1) +" + " +str.substring(0,str.indexOf("*")) +"*"+derive(str.substring(str.indexOf("*")+1));
        else if (str.indexOf("/") != -1 && (str.indexOf("(")== -1 || (str.indexOf("(") > str.indexOf("/") || findMatch(str,str.indexOf("(")) < str.indexOf("/")))) //quotient
            return "("+derive(str.substring(0,str.indexOf("/")))+"*"+str.substring(str.indexOf("/")+1) +" - " +str.substring(0,str.indexOf("/")) +"*"+derive(str.substring(str.indexOf("/")+1))+") / (" +str.substring(str.indexOf("/")+1)+")^2" ;
        else if (str.indexOf("sin(") != -1 && (str.indexOf("(")== -1 || str.indexOf("(") > str.indexOf("sin("))) //sin
            return "cos("+(str.substring(str.indexOf("(")+1,findMatch(str,str.indexOf("(")))) +") * (" +derive(str.substring(str.indexOf("(")+1,findMatch(str,str.indexOf("("))))+")";
        else if (str.indexOf("cos(") != -1 && (str.indexOf("(")== -1 || str.indexOf("(") > str.indexOf("cos("))) //cos
            return "-sin("+(str.substring(str.indexOf("(")+1,findMatch(str,str.indexOf("(")))) +") * (" +derive(str.substring(str.indexOf("(")+1,findMatch(str,str.indexOf("("))))+")";
        else if (str.indexOf("tan(") != -1 && (str.indexOf("(")== -1 || str.indexOf("(") > str.indexOf("tan("))) //tan
            return "sec^2("+(str.substring(str.indexOf("(")+1,findMatch(str,str.indexOf("(")))) +") * (" +derive(str.substring(str.indexOf("(")+1,findMatch(str,str.indexOf("("))))+")";
        else if (str.indexOf("cot(") != -1 && (str.indexOf("(")== -1 || str.indexOf("(") > str.indexOf("cot("))) //cot
            return "-csc^2("+(str.substring(str.indexOf("(")+1,findMatch(str,str.indexOf("(")))) +") * (" +derive(str.substring(str.indexOf("(")+1,findMatch(str,str.indexOf("("))))+")";
        else if (str.indexOf("e^(") != -1 && (str.indexOf("(")== -1 || str.indexOf("(") > str.indexOf("e")))
            return str.substring(0,findMatch(str,str.indexOf("("))+1)+" * ("+derive(str.substring(str.indexOf("(")+1,findMatch(str, str.indexOf("("))))+")";
        else if (str.indexOf("ln(") != -1 && (str.indexOf("(")== -1 || str.indexOf("(") > str.indexOf("ln("))) /* ln x */
            return "1 / ("+str.substring(str.indexOf("ln(") + 3,findMatch(str,str.indexOf("(")))+") * ("+derive(str.substring(str.indexOf("(")+1,findMatch(str,str.indexOf("("))))+")";
        else if (str.indexOf("log(") != -1 && (str.indexOf("(")== -1 || str.indexOf("(") > str.indexOf("log("))) /* log x */
            return "1 / ("+str.substring(str.indexOf("(") + 1,findMatch(str,str.indexOf("(")))+"*ln(10)) * ("+derive(str.substring(str.indexOf("(")+1,findMatch(str,str.indexOf("("))))+")";
        else if (str.indexOf("sqrt(") != -1 && (str.indexOf("(")== -1 || str.indexOf("(") > str.indexOf("sqrt("))) /* sqrt x */
            return "("+str.substring(str.indexOf("(") + 1,findMatch(str,str.indexOf("(")))+")^(-1/2) * ("+derive(str.substring(str.indexOf("(")+1,findMatch(str,str.indexOf("(")))) +")";
        else if(str.indexOf("^(") != -1 && str.indexOf("(") > str.indexOf("^(") )
            return str.substring(0,findMatch(str,str.indexOf("("))+1)+" * ln("+str.substring(0, str.indexOf("^")) +") * ("+derive(str.substring(str.indexOf("(")+1,findMatch(str, str.indexOf("("))))+")";
        else if(str.indexOf("(") == 0)
            return derive(str.substring(str.indexOf("(")+1,findMatch(str,str.indexOf("("))));
        else if(str.indexOf("^") != -1 && str.indexOf("^") + 2 < str.length() && str.charAt(str.indexOf("^") - 1) == 'x' && str.charAt(str.indexOf("^") + 2) == 'x') //x^x
            return "(ln(x) + 1)x^(x)" +derive(str.substring(str.indexOf("x")+6));
        else if (str.indexOf("^") != -1 && str.indexOf("^") - str.indexOf("x") == 1 && (str.indexOf("(") == -1 || str.indexOf("(") > str.indexOf("^"))) 
            return (Double.parseDouble(str.substring(0,str.indexOf("x")))*Double.parseDouble((str.substring(str.indexOf("^")+1))) + "x^(" + (Double.parseDouble(str.substring(str.indexOf("^")+1))-1))+")";
        else if (str.indexOf("^") != -1)
            return str.substring(str.indexOf("^")+1,str.indexOf("("))+" * "+(str.substring(0,str.indexOf("^")) +str.substring(str.indexOf("("),findMatch(str,str.indexOf("("))+1)) +"^(" +(Double.parseDouble(str.substring(str.indexOf("^")+1,str.indexOf("("))) - 1) +") * ("+derive(str.substring(0,str.indexOf("^")) +str.substring(str.indexOf("("),findMatch(str,str.indexOf("("))+1)) +")";
        return str;
    }
    public static String format(String str) {
      String formatted = "";
        for (int i = 0; i < str.length(); i++) {
            if(i+1 < str.length() && str.charAt(i) == '1' && (str.charAt(i+1) == 'x' && (i - 1 < 0 || (i - 1 >= 0 && !isNumeric(str.substring(i-1,i))))) ) {
                i++;
            }
            if (i+1 < str.length() && str.charAt(i) == '^' && (str.charAt(i+1) == '1' &&(i + 2 >= str.length() || (str.charAt(i+2) != 'x' && !isNumeric(str.substring(i+2,i+3)))))) {
                i+=2;
            }
            if (i+2 < str.length() && str.charAt(i) == '^' && ( str.charAt(i+2) == '1' && str.charAt(i+3) == ')')) {
                i+=4;
            }
            if (i+3 < str.length() && str.charAt(i) == 'x' && (str.charAt(i+1) == '^' && str.charAt(i+3) == '0')) {
                if (!((i - 1 < 0 || (i - 1 >= 0 && isNumeric(str.substring(i-1,i)) && str.charAt(i-1) != '1'))))
                    formatted += "1";
                i+=5;
            }
            if(i < str.length()) {
                formatted += str.charAt(i);
            }
        }
        return formatted;
    }
    public static String calculate(String str){
        str=deE(str);
        System.out.println(str);
        if(str.length()==0)
            return "";
        if(str.indexOf('(')==-1)
            return (String)(Ord3(Ord2(Ord1(Ord0(separate(str))))).get(0));
        else 
            return calculate(str.substring(0,str.indexOf('('))+calculate(str.substring(str.indexOf('(')+1,findMatch(str,str.indexOf('('))))+str.substring(findMatch(str,str.indexOf('('))+1,str.length()));
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
            else if(list.get(i).equals("arcsin")){
                list.set(i,Double.toString(Math.asin(Double.parseDouble((String)(list.get(i+1))))));
                list.remove(i+1);
            }
            else if(list.get(i).equals("arccos")){
                list.set(i,Double.toString(Math.acos(Double.parseDouble((String)(list.get(i+1))))));
                list.remove(i+1);
            }
            else if(list.get(i).equals("arctan")){
                list.set(i,Double.toString(Math.atan(Double.parseDouble((String)(list.get(i+1))))));
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
            else if (list.get(i).equals("e")){
                list.set(i,Double.toString(round(Math.E,6)));
            }
        }
        return list;
    }
    public static double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    long factor = (long) Math.pow(10, places);
    value = value * factor;
    long tmp = Math.round(value);
    return (double) tmp / factor;
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
        if(isNumeric(str)){
            list.add(str);
            return list;
        }
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
                list.add(str.substring(i,i+3));
                lastInd=i+3;
            }
            else if(str.length()-i>6&&((str.substring(i,i+6)).equals("arcsin") || (str.substring(i,i+6)).equals("arccos") || (str.substring(i,i+6)).equals("arctan"))){
                list.add(str.substring(i,i+3));
                lastInd=i+6;
            }}
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
            else if(str.length()-i>6&&((str.substring(i,i+6)).equals("arcsin") || (str.substring(i,i+6)).equals("arccos") || (str.substring(i,i+6)).equals("arctan"))){
                list.add(str.substring(i,i+3));
                break;
            } }
        return list;
    }
    public static boolean isOperant(char b){
        return (b=='+'||b=='*'||b=='-'||b=='/'||b=='^'||b=='%');
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