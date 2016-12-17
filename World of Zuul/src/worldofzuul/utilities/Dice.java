/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.utilities;

import java.util.Random;

/**
 * Dice class that returns a number between the "begin" interval and the "end" interval
   Edit(begin, end), adds new "limits",
   You can use the get & set's too.
 
 * @author Bytoft, Mikkel
 * @author Christensen, Martin Steen
 * @author Hansen, Søren Vest
 * @author Johansen, Emil Højgaard
 * @author Madsen, Kent vejrup
 * @author Thy, Mads Heimdal
 */
public class Dice 
{
    private Integer Begin, End;
    
    public Dice( int begin, int end )
    {
        edit( begin, end );
    }
    
    public final void edit( int begin, int end )
    {
        setBegin( begin );
        setEnd( end );
    }
    
    // Public Accessors
        // Getters ------------------------------------------------------------>
    public int getBegin()
    {
        return Begin;
    }
    
    public int getEnd()
    {
        return End;
    }
        // Setters ------------------------------------------------------------>
    public void setEnd( int End )
    {
        this.End = End;
    }
    
    public void setBegin( int Begin )
    {
        this.Begin = Begin;
    }
    
    /**
     * Returns a value between begin & end
     * @return random Integer Value
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
