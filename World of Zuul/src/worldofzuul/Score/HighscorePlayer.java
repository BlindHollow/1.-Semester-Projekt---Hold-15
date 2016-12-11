/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.score;

/**

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
    private String playerName = null;
    
    private boolean personOfInterest = false;
    
    private int playerScore = 0;
    private int rank        = 0;
    
        // Accessors
            // Getters
    public String getPlayerName()
    {
        return this.playerName;
    }
    
    public int getPlayerScore()
    {
        return this.playerScore;
    }
    
    public int getRank()
    {
        return this.rank;
    }
    
    public boolean getPOI()
    {   
        return this.personOfInterest;
    }
    
        // Setters
    public void setRank( int Rank )
    {
        this.rank = Rank;
    }
    
    public void setPOI( boolean PersonOfInterestState )
    {
        this.personOfInterest = PersonOfInterestState;
    }
    
    public void setPlayerScore( int Score )
    {
        this.playerScore = Score;
    }
    
    public void setPlayerName( String name )
    {
        this.playerName = name;
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
        
        playerScore = Score;
    }
    
    // Implementations
    @Override
    public int compareTo( HighscorePlayer object )
    {
        return object.getPlayerScore() - this.getPlayerScore();
    }
    
} // End Class
