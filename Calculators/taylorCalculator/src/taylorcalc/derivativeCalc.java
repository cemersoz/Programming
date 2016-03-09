/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package taylorcalc;

import java.util.Scanner;

/**
 *
 * @author cem
 */
public class derivativeCalc {
    public static String derivate(String unFormatted){
        if(unFormatted.charAt(0)=='-'&&!isNumeric(Character.toString(unFormatted.charAt(1)))){
            unFormatted="-1*"+unFormatted.substring(1,unFormatted.length());
        }
        String str=unFormatted;
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
        return format(derive(corrections));
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
            if(i<str.length()-8&&str.substring(i,i+8).equals(" * (1.0)"))
                i+=9;
            if(i<str.length()-6&&str.substring(i,i+6).equals("*(1.0)"))
                i+=7;
            if(i+1 < str.length() && str.charAt(i) == '1' && (str.charAt(i+1) == 'x' && (i - 1 < 0 || (i - 1 >= 0 && !isNumeric(str.substring(i-1,i))))) ) {
                i+=1;
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
                i+=7;
            }
            if(i < str.length()) {
                formatted += str.charAt(i);
            }
        }
        return formatted;
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
