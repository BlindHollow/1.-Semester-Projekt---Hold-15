/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

/**
 *
 * @author Fract
 */
public class HighscoreContainer implements Comparable<HighscoreContainer>
{
    public HighscoreContainer( String Name )
    {
        this.PlayerName = Name;
        this.Score = 0;
    }
    
    // Internal Variables
    private int Score;
    private String PlayerName;
    
    // Accessors
        // Score number
            // Add & Remove from the Score
    public void AddToScore( int number )
    {
        Score = Score + number;
    }
    
    public void RemoveFromScore( int number )
    {
        Score = Score - number;
    }
    
    public int GetScore()
    {
        return Score;
    }
    
        //Playername 
    public void AssignName( String name )
    {
        this.PlayerName = name;
    }
    
    public String GetName()
    {
        return this.PlayerName;
    }
    
    // Returns true if the current object is bigger than another container 
    public boolean BiggerThan( HighscoreContainer container )
    {
        if( container.GetScore() >= this.GetScore() )
            return true;
        
        
        return false;
    }
    
    public boolean SmallerThan( HighscoreContainer container )
    {
        if( container.GetScore() <= this.GetScore() )
            return true;
        
        return false;
    }
    
    // Implementations
        @Override
    public int compareTo( HighscoreContainer object )
    {
        return this.GetScore() - object.GetScore();
    }
    
} // End Class
