/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebastrings;

/**
 *
 * @author DEV_OOP01
 */
public class EngineConverter {
    private Expression[] expArr;
    
    private Boolean isOperator(char c)
    {
	if(c=='+'||c=='-'||c=='*'||c=='/'||c=='^')
	{
		return true;
	}
	else
	{
		return false;
	}
    }
    
    int precedence(char c) 
    { 
        if(c == '^') 
          return 3; 
        else if(c == '*' || c == '/') 
          return 2; 
        else if(c == '+' || c == '-') 
          return 1; 
        else
          return -1; 
    } 
    
    java.util.Stack<Expression> getInfixObject(String infix)
    {
       java.util.Stack<Expression> exp = null;
       String temp = "";
       int tam = infix.length();
        
       if(tam>0)
        {
            exp = new java.util.Stack<Expression>();
              for(int i=0;i<tam;i++)
                {           
                    char c = infix.charAt(i);
                    if(c>='0' && c<='9')
                    {
                        temp+=infix.charAt(i);
                        if(i==tam-1&&temp.length()>0)
                        {
                            //numbers.push(temp);
                            exp.push(new Expression(temp,'N',-1));
                            temp="";
                        }
                    }
                    else
                    {
                        if(temp.length()>0){
                            //numbers.push(temp);
                            exp.push(new Expression(temp,'N',-1));
                            temp="";
                        }
                        //simbols.push(String.valueOf(c));   

                        int val=-1;
                        if(c == '^') 
                            val= 3; 
                          else if(c == '*' || c == '/') 
                            val= 2; 
                          else if(c == '+' || c == '-') 
                            val= 1;  

                        exp.push(new Expression(String.valueOf(c),'O',val));
                    }                           
                }
        }
       return exp;
    }
    
    java.util.Stack<Expression> getPostFix(java.util.Stack<Expression> exp)
    {
        java.util.Stack<Expression> tmp = exp;
        java.util.Stack<Expression> val = null;
        java.util.Stack<Character> symbol = new java.util.Stack<>();
        
        if(tmp.size()>0)
        {
            val = new java.util.Stack<Expression>();
            char type;
            String value;
            int preceed;
            for(int i=0;i<tmp.size();i++)
            {
                type = tmp.get(i).typeSymbol;
                value = tmp.get(i).valueSymbol;
                preceed = tmp.get(i).precedenceSymbol;
                
                if(type=='N') //si es un numero
                {
                    val.push(new Expression(value,type,0));
                }else if(type=='O') //si es un operador
                {
                    if(value.equals("("))
                    {
                        symbol.push(value.charAt(0));
                    }
                    else if(value.equals(")"))
                    {
                        while((symbol.peek()!='(')&&(!symbol.empty()))
                        {
                            Character temp=symbol.peek();
                            val.push(new Expression(temp.toString(),type,0));
                            symbol.pop();
                        }
                        if(symbol.peek()=='(')
                        {
                            symbol.pop();
                        }
                    }
                    else if(isOperator(value.charAt(0)))//si es un operador
                    {
                        if(symbol.empty())
                        {
                            symbol.push(value.charAt(0));
                        }else{
                            if(preceed>precedence(symbol.peek()))
                            {
                                symbol.push(value.charAt(0));
                            }
                            else if((preceed==precedence(symbol.peek())&&(value=="^")))
                            {
                                symbol.push(value.charAt(0));
                            }else
                            {
                                while((!symbol.empty()) && (preceed<=precedence(symbol.peek())))
                                {
                                    val.push(new Expression(symbol.peek().toString(),type,0));
                                    symbol.pop();
                                }
                                symbol.push(value.charAt(0));
                            }
                        }
                    }                 
                }
            }            
        }
        while(!symbol.empty())
        {
            val.push(new Expression(symbol.peek().toString(),'O',0));
            symbol.pop();
        }
        return val;
    }
    
    double getPostFixEvaluation(java.util.Stack<Expression> exp)
    {
        double value = 0; //valor a devolver
        double lval = 0;
        double rval = 0;
        
        java.util.Stack<Double> numbers = new java.util.Stack<>();
        
         char type;
         String values;
         
         for(int i=0;i<exp.size();i++)
         {
            type = exp.get(i).typeSymbol;
            values = exp.get(i).valueSymbol;
             if(type=='N')
             {
                numbers.push(Double.parseDouble(values));
             }
             else if(type=='O')
             {
                 rval=numbers.pop();
                 lval=numbers.pop();
                 if(values.charAt(0)=='+')
                 {
                     value = lval+rval;
                 }
                 else if(values.charAt(0)=='-')
                 {
                     value = lval-rval;
                 }
                 else if(values.charAt(0)=='*')
                 {
                     value = lval*rval;
                 }
                 else if(values.charAt(0)=='/')
                 {
                    if(rval!=0.0)
                    {
                        value=lval/rval;
                    }else{
                        value=-1000000000;
                    }
                 }else{
                     value = Math.pow(lval, rval);
                 }
                 numbers.push(value);                 
             }
             
         }
         value = numbers.pop(); //the last element in the stack is our answer
        return value;
    }
}
