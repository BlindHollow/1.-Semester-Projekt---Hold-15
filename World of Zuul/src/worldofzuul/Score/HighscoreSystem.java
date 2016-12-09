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
    protected boolean addPlayers( String Name )
    {
        return addPlayers( Name, 0 );
    }
    
    /**
     * 
     * @param Name
     * @param Score
     * @return 
     */
    protected boolean addPlayers( String Name, int Score )
    {
        HighscorePlayer player = new HighscorePlayer( Name, 
                                                      Score );
        
        if( exactUser( Name ) == true )
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
    private boolean exactUser( String Name )
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
    public final void addCurrentPlayerPoints( int Number )
    {
        User.SetPlayerScore( getCurrentPlayerPoints() + Number );
    }
    
    
    /**
     * 
     * @param Number 
     */
    public final void setCurrentPlayerPoints( int Number )
    {
        User.SetPlayerScore( Number );
    }
    
    /**
     * Remove points for the Current Player
     * @param Number 
     */
    public final void removeCurrentPlayerPoints( int Number )
    {
        User.SetPlayerScore( getCurrentPlayerPoints() - Number );
        
    }
    
    
    /**
     * Retrieve Points for the current Player
     * @return 
     */
    public final int getCurrentPlayerPoints()
    {
        return User.GetPlayerScore();
    }
    
    public final void setCurrentPlayerName( String name )
    {
        User.SetPlayerName( name );
    }
    
    public final String getCurrentPlayerName()
    {
        return User.GetPlayerName();
    }
    
    public final int getTotalAmountOfHighscores()
    {
        return TotalAmountOfUsers;
    }
    
    /**
     * Returns a sorted list, with the player and other players
     * @return 
     */
    public ArrayList<HighscorePlayer> orderedListOfPlayers()
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
