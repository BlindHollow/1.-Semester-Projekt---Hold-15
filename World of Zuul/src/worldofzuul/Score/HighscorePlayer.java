/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.Score;

/**
 *
 * @author Bytoft, Mikkel
 * @author Christensen, Martin Steen
 * @author Hansen, Søren Vest
 * @author Johansen, Emil Højgaard
 * @author Madsen, Kent vejrup
 * @author Thy, Mads Heimdal
 */

// Implements Comparable. used to sort a array
public final class HighscorePlayer implements Comparable<HighscorePlayer>
{
    private String Playername = null;
    
    private boolean PersonOfInterest = false;
    
    private int PlayerScore = 0;
    private int Rank        = 0;
    
        // Accessors
            // Getters
    public String GetPlayerName()
    {
        return this.Playername;
    }
    
    public int GetPlayerScore()
    {
        return this.PlayerScore;
    }
    
    public int GetRank()
    {
        return this.Rank;
    }
    
    public boolean GetPOI()
    {
        boolean currentState = this.PersonOfInterest;
        
        // Reset's it.
        if( this.PersonOfInterest == true )
            this.PersonOfInterest = false;
        
        return currentState;
    }
    
        // Setters
    public void SetRank( int Rank )
    {
        this.Rank = Rank;
    }
    
    public void SetPOI( boolean PersonOfInterestState )
    {
        this.PersonOfInterest = PersonOfInterestState;
    }
    
    public void SetPlayerScore( int Score )
    {
        this.PlayerScore = Score;
    }
    
    public void SetPlayerName( String name )
    {
        this.Playername = name;
    }
    
    // Init
    public HighscorePlayer()
    {
        this.SetPlayerScore(0);
    }
    
    public HighscorePlayer( String Name )
    {
        this();
        
        this.SetPlayerName( Name );
    }
    
    public HighscorePlayer( String Name, int Score )
    {
        this( Name );
        
        PlayerScore = Score;
    }
    
    // Implementations
    @Override
    public int compareTo( HighscorePlayer object )
    {
        return object.GetPlayerScore() - this.GetPlayerScore();
    }
    
} // End Class
