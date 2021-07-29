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
public class Expression {
    public String valueSymbol;
    public char typeSymbol;
    public int precedenceSymbol;
    
    Expression(String vs, char ts, int ps)
    {        
        this.valueSymbol = vs;
        this.typeSymbol = ts;
        this.precedenceSymbol = ps;
    }
}