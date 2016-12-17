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

/**
 * From the viewpoint, it's usually a "active" player himself
 */
public class HighscoreUser 
{
    
    private boolean isPlayer = false;
    private HighscorePlayer player = new HighscorePlayer();
    
    public HighscoreUser()
    {
        
    }
    
    // Setters
    public void setPlayerName( String name )
    {
        player.setPlayerName( name );
    }
    
    public void setPlayerScore( int Score )
    {
        player.setPlayerScore( Score );
    }
    
    public void setIsPlayer( boolean inputState )
    {
        isPlayer = inputState;
    }
    
    // Getters
    public boolean getIsPlayer()
    {
        return isPlayer;
    }
    
    public String getPlayerName()
    {
        return player.getPlayerName();
    }
    
    public int getPlayerScore()
    {
        return player.getPlayerScore();
    }
    
    // Object
    public HighscorePlayer getPlayerObject()
    {
        return player;
    }
    
}
