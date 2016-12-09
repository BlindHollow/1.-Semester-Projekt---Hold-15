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
public class HighscoreUser 
{
    
    private boolean IsPlayer = false;
    private HighscorePlayer Player = new HighscorePlayer();
    
    public HighscoreUser()
    {
        
    }
    
    // Setters
    public void SetPlayerName( String name )
    {
        Player.SetPlayerName( name );
    }
    
    public void SetPlayerScore( int Score )
    {
        Player.SetPlayerScore( Score );
    }
    
    public void SetIsPlayer( boolean inputState )
    {
        IsPlayer = inputState;
    }
    
    public void SetRank( int r )
    {
        Player.SetRank( r );
    }
    
    // Getters
    public boolean GetIsPlayer()
    {
        return IsPlayer;
    }
    
    public String GetPlayerName()
    {
        return Player.GetPlayerName();
    }
    
    public int GetPlayerScore()
    {
        return Player.GetPlayerScore();
    }
    
    public int GetPlayerRank()
    {
        return Player.GetRank();
    }
    
    // Object
    public HighscorePlayer GetPlayerObject()
    {
        return Player;
    }
    
}
