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
    public String getPlayerName()
    {
        return this.Playername;
    }
    
    public int getPlayerScore()
    {
        return this.PlayerScore;
    }
    
    public int getRank()
    {
        return this.Rank;
    }
    
    public boolean getPOI()
    {
        boolean currentState = this.PersonOfInterest;
        
        // Reset's it.
        if( this.PersonOfInterest == true )
            this.PersonOfInterest = false;
        
        return currentState;
    }
    
        // Setters
    public void setRank( int Rank )
    {
        this.Rank = Rank;
    }
    
    public void setPOI( boolean PersonOfInterestState )
    {
        this.PersonOfInterest = PersonOfInterestState;
    }
    
    public void setPlayerScore( int Score )
    {
        this.PlayerScore = Score;
    }
    
    public void setPlayerName( String name )
    {
        this.Playername = name;
    }
    
    // Init
    public HighscorePlayer()
    {
        this.setPlayerScore(0);
    }
    
    public HighscorePlayer( String Name )
    {
        this();
        
        this.setPlayerName( Name );
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
        return object.getPlayerScore() - this.getPlayerScore();
    }
    
} // End Class
