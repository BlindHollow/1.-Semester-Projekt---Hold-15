/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.score;

import java.util.ArrayList;
import java.util.Collections;

/**

 * @author Bytoft, Mikkel
 * @author Christensen, Martin Steen
 * @author Hansen, Søren Vest
 * @author Johansen, Emil Højgaard
 * @author Madsen, Kent vejrup
 * @author Thy, Mads Heimdal
 */

public class HighscoreSystem
{
    private HighscoreUser user = new HighscoreUser();
    private ArrayList<HighscorePlayer> listOfPriorUserScores = new ArrayList();
    
    private int totalAmountOfUsers = 0;
    
    /**
     * 
     * @param CurrentPlayername 
     */
    public HighscoreSystem( )
    {
        user.setPlayerName( "Player" );
        user.setIsPlayer( true );
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
            listOfPriorUserScores.add( player );
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
        if( Name.equalsIgnoreCase( user.getPlayerName() ) )
            return true;
        
        for( HighscorePlayer current : listOfPriorUserScores )
        {
            if( Name.equalsIgnoreCase( current.getPlayerName() ) )
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
        user.setPlayerScore( getCurrentPlayerPoints() + Number );
    }
    
    /**
     * 
     * @param Number 
     */
    public final void setCurrentPlayerPoints( int Number )
    {
        user.setPlayerScore( Number );
    }
    
    /**
     * Remove points for the Current Player
     * @param Number 
     */
    public final void decreaseCurrentPlayerPoints( int Number )
    {
        user.setPlayerScore( getCurrentPlayerPoints() - Number );
        
    }
    
    /**
     * Retrieve Points for the current Player
     * @return 
     */
    public final int getCurrentPlayerPoints()
    {
        return user.getPlayerScore();
    }
    
    public final void setCurrentPlayerName( String name )
    {
        user.setPlayerName( name );
    }
    
    public final String getCurrentPlayerName()
    {
        return user.getPlayerName();
    }
    
    public final int getTotalAmountOfHighscores()
    {
        return totalAmountOfUsers;
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
        retList.addAll( listOfPriorUserScores );
        retList.add( user.getPlayerObject() );
        
        // Sortere Listen
        Collections.sort( retList );
        
        return retList;
    }
    
}
