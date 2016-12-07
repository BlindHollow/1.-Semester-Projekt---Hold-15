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
// Implements Comparable. used to sort a array
public final class HighscorePlayer implements Comparable<HighscorePlayer>
{
    private String Playername;
    private boolean isPlayer;
    
    private int PlayerScore;
    private int Rank;
    
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
    
    public boolean GetIsPlayer()
    {
        return isPlayer;
    }
    
        // Setters
    public void SetRank( int Rank )
    {
        this.Rank = Rank;
    }
    
    public void SetPlayerScore( int Score )
    {
        this.PlayerScore = Score;
    }
    
    public void SetPlayerName( String name )
    {
        this.Playername = name;
    }
    
    public void SetIsPlayer( boolean isPlayer )
    {
        this.isPlayer = isPlayer;
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
    
    public HighscorePlayer(String Name, int Score, boolean isPlayer)
    {
        this( Name, Score );
        
        this.isPlayer = isPlayer;
    }
    
    
    // Implementations
    @Override
    public int compareTo( HighscorePlayer object )
    {
        return object.GetPlayerScore() - this.GetPlayerScore();
    }
    
} // End Class
