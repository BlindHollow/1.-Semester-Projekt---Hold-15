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
public class HighscorePlayer implements Comparable<HighscorePlayer>
{
    private String Playername;
    private boolean isPlayer;
    private int PlayerScore;
    private int Rank;
    
        // Accessors
            // Getters
    public final String GetPlayername()
    {
        return this.Playername;
    }
    
    public final int GetPlayerScore()
    {
        return this.PlayerScore;
    }
    
    public final int GetRank()
    {
        return this.Rank;
    }
    
    public final boolean GetIsPlayer()
    {
        return isPlayer;
    }
    
        // Setters
    public final void SetRank( int Rank )
    {
        this.Rank = Rank;
    }
    
    public final void SetPlayerScore( int Score )
    {
        this.PlayerScore = Score;
    }
    
    public final void SetPlayerName( String name )
    {
        this.Playername = name;
    }
    
    public final void SetIsPlayer( boolean isPlayer )
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
    
    // Implementations
    @Override
    public int compareTo( HighscorePlayer object )
    {
        return object.PlayerScore - this.GetPlayerScore();
    }
    
} // End Class
