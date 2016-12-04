/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

import java.util.*;

/**
 *
 * @author Fract
 */

public class HighscoreSystem
{
    private HighscorePlayer CurrentPlayer = new HighscorePlayer();
    private ArrayList<HighscorePlayer> ListOfPriorUserScores = new ArrayList();
    
    /**
     * 
     * @param CurrentPlayername 
     */
    public HighscoreSystem( )
    {
        CurrentPlayer.SetPlayerName( "Player" );
        CurrentPlayer.SetIsPlayer( true );
    }
    
    /**
     * Add Player's to compare againts 
     * @param Name
     * @return 
     */
    protected boolean AddPlayers( String Name )
    {
        return AddPlayers( Name, 0 );
    }
    
    /**
     * 
     * @param Name
     * @param Score
     * @return 
     */
    protected boolean AddPlayers( String Name, int Score )
    {
        HighscorePlayer player = new HighscorePlayer( Name, 
                                                      Score, 
                                                      false );
        
        if( ExactUser( Name ) == true )
        {
            return false;
        }
        else
        {
            ListOfPriorUserScores.add( player );
            return true;
        }
        
    }
    
    /**
     * Returns true if a player with the same name, show up. avoid duplicates
     * @param Name
     * @return 
     */
    private boolean ExactUser( String Name )
    {
        if( Name.toLowerCase() == CurrentPlayer.GetPlayerName().toLowerCase() )
            return true;
        
        for( HighscorePlayer current : ListOfPriorUserScores )
        {
            if( Name.toLowerCase() == current.GetPlayerName().toLowerCase() )
            {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Add points for the Current Player
     * @param Number 
     */
    public void AddCurrentPlayerPoints( int Number )
    {
        CurrentPlayer.SetPlayerScore( GetCurrentPlayerPoints() + Number );
    }
    
    
    /**
     * 
     * @param Number 
     */
    public void SetCurrentPlayerPoints( int Number )
    {
        CurrentPlayer.SetPlayerScore( Number );
    }
    
    /**
     * Remove points for the Current Player
     * @param Number 
     */
    public void RemoveCurrentPlayerPoints( int Number )
    {
        CurrentPlayer.SetPlayerScore( GetCurrentPlayerPoints() - Number );
        
    }
    
    
    /**
     * Retrieve Points for the current Player
     * @return 
     */
    public int GetCurrentPlayerPoints()
    {
        return CurrentPlayer.GetPlayerScore();
    }
    
    public void SetCurrentPlayerName( String name )
    {
        CurrentPlayer.SetPlayerName( name );
    }
    
    public String GetCurrentPlayerName()
    {
        return CurrentPlayer.GetPlayerName();
    }
    
    /**
     * Returns a sorted list, with the player and other players
     * @return 
     */
    public ArrayList<HighscorePlayer> OrderedListOfPlayers()
    {
        // ReturnList
        ArrayList<HighscorePlayer> retList = new ArrayList();
        
        // Adds Everybody
        retList.addAll( ListOfPriorUserScores );
        retList.add( CurrentPlayer );
        
        // Sortere Listen
        Collections.sort( retList );
        
        return retList;
    }
    
}
