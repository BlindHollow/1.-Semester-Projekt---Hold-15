/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.util.Random;

/**
 * Dice class that returns a number between the "begin" interval and the "end" interval
 * @author Fract
 */
public class Dice 
{
    private Integer Begin, 
                    End;
    
    public Dice( int begin,
                 int end )
    {
        edit( begin, end );
    }
    
    public final void edit( int begin, 
                            int end )
    {
        this.Begin = begin;
        this.End = end;
    }
    
    public int getBegin()
    {
        return Begin;
    }
    
    public int getEnd()
    {
        return End;
    }
    
    /**
     * 
     * @return value between begin and end interval
     * @return -1 : Error, else random value between @param Begin and @param End
     */
    public final int calculate()
    {
        Random r = new Random();
        
        int r_value = r.nextInt( ( this.End - this.Begin ) ) + this.Begin;
        
        // Error
        return r_value; 
    }
    
}
