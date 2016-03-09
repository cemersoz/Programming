/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taylorcalc;
import java.util.Scanner;
/**
 *
 * @author cem
 */
public class TaylorCalc {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(derivativeCalc.derivate("-sin(x)"));
        Scanner console = new Scanner(System.in);
        System.out.println("enter function");
        String DirInput=console.nextLine();
        System.out.println("enter the value near which the polynomial will be constructed");
        String aVal=console.next();
        System.out.println("enter the value you wish to approximate");
        String xVal=console.next();
        System.out.println("enter the degree of the desired taylor polynomial");
        String nVal=console.next();
        System.out.println(doTaylor(DirInput,Double.parseDouble(xVal),Double.parseDouble(aVal),Integer.parseInt(nVal)));
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
    public static double doTaylor(String str,double x, double a, int n){
        double res=Double.parseDouble(calculator.calculate(calculator.deX(str, Double.toString(a))));
        System.out.println(res);
        String der=str;
        for(int i=1;i<=n;i++){
            der=derivativeCalc.derivate(der);
            System.out.println(der);
            res+=Math.pow(x-a,i)*Double.parseDouble(calculator.calculate(calculator.deX(der,Double.toString(a))))/calculator.factorial(i);
    }
        return res;
    }
}
