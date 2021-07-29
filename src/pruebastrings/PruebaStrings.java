/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pruebastrings;
import java.util.Scanner;  // Import the Scanner class
/**
 *
 * @author DEV_OOP01
 */
public class PruebaStrings {

  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
       
       java.util.Stack<Expression> expression = null;
       java.util.Stack<Expression> postExpression = null;
       EngineConverter engine = new EngineConverter();
       Scanner myObj = new Scanner(System.in);  // Create a Scanner object
       String infix="";
       System.out.println("Ejemplo 1: CALC>> 7+3");  
       System.out.println("Ejemplo 2: CALC>> (100/2)-(18/2)");  
       System.out.println("Ejemplo: CALC>> (10^2)*(7+3)-5");  
       System.out.println("Para Salir escriba: quit");  
       System.out.println();  
       do{       
            System.out.println("CALC>> ");        
            infix = myObj.next();  // Read user input
            if(!infix.equals("quit"))
            {
             expression=engine.getInfixObject(infix);
             postExpression=engine.getPostFix(expression);                     
             double ans = engine.getPostFixEvaluation(postExpression);
             System.out.println(ans);  
            }
       }while(!infix.equals("quit"));
       System.out.println("Gracias por usar la CALC");  
    }  
    
}
