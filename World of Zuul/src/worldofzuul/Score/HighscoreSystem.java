/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.Score;

import java.util.*;

/**
 *
 * @author Bytoft, Mikkel
 * @author Christensen, Martin Steen
 * @author Hansen, Søren Vest
 * @author Johansen, Emil Højgaard
 * @author Madsen, Kent vejrup
 * @author Thy, Mads Heimdal
 */

public class HighscoreSystem
{
    private HighscoreUser User = new HighscoreUser();
    private ArrayList<HighscorePlayer> ListOfPriorUserScores = new ArrayList();
    
    private int TotalAmountOfUsers = 0;
    
    /**
     * 
     * @param CurrentPlayername 
     */
    public HighscoreSystem( )
    {
        User.SetPlayerName( "Player" );
        User.SetIsPlayer( true );
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
                                                      Score );
        
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
        if( Name.equalsIgnoreCase( User.GetPlayerName() ) )
            return true;
        
        for( HighscorePlayer current : ListOfPriorUserScores )
        {
            if( Name.equalsIgnoreCase( User.GetPlayerName() ) )
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
    public final void AddCurrentPlayerPoints( int Number )
    {
        User.SetPlayerScore( GetCurrentPlayerPoints() + Number );
    }
    
    
    /**
     * 
     * @param Number 
     */
    public final void SetCurrentPlayerPoints( int Number )
    {
        User.SetPlayerScore( Number );
    }
    
    /**
     * Remove points for the Current Player
     * @param Number 
     */
    public final void RemoveCurrentPlayerPoints( int Number )
    {
        User.SetPlayerScore( GetCurrentPlayerPoints() - Number );
        
    }
    
    
    /**
     * Retrieve Points for the current Player
     * @return 
     */
    public final int GetCurrentPlayerPoints()
    {
        return User.GetPlayerScore();
    }
    
    public final void SetCurrentPlayerName( String name )
    {
        User.SetPlayerName( name );
    }
    
    public final String GetCurrentPlayerName()
    {
        return User.GetPlayerName();
    }
    
    public final int GetTotalAmountOfHighscores()
    {
        return TotalAmountOfUsers;
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
        retList.add( User.GetPlayerObject() );
        
        // Sortere Listen
        Collections.sort( retList );
        
        return retList;
    }
    
}
