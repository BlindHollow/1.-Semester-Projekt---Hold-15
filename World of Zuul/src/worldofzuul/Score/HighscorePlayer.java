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

public final class HighscorePlayer implements Comparable<HighscorePlayer>
{
    // Implements Comparable. used to sort a arraylist of players, by their highscore
    
    // Private Variables
    private String playerName = null;
    
    private boolean personOfInterest = false;
    
    private int playerScore = 0;
    
        // Accessors
            // Getters
    /**
     * Get the currentplayers Name
     * @return 
     */
    public String getPlayerName()
    {
        return this.playerName;
    }
    
    /**
     * Get the currentPlayers highscore
     * @return 
     */
    public int getPlayerScore()
    {
        return this.playerScore;
    }
    
    /**
     * Depends on the context, as an example if your looking for a player, in the list
     * When it's sorted for you.
     * You get, if the currentObject is an interest to you
     * @return 
     */
    public boolean getPOI()
    {   
        return this.personOfInterest;
    }
    
        // Setters
    /**
     * Depends on the context, as an example if your looking for a player, in the list
     * When it's sorted for you.
     * You set if the currentObject is an interest to you
     * @param PersonOfInterestState 
     */
    public void setPOI( boolean PersonOfInterestState )
    {
        this.personOfInterest = PersonOfInterestState;
    }
    
    /**
     * Sets the currentPlayers score
     * @param Score 
     */
    public void setPlayerScore( int Score )
    {
        this.playerScore = Score;
    }
    
    /**
     * Sets the currentPlayers name
     * @param name 
     */
    public void setPlayerName( String name )
    {
        this.playerName = name;
    }
    
    // Init
    /**
     * Constructor
     */
    public HighscorePlayer()
    {
        this.setPlayerScore(0);
    }
    
    /**
     * Constructor
     * @param Name 
     */
    public HighscorePlayer( String Name )
    {
        this();
        
        this.setPlayerName( Name );
    }
    
    /**
     * Constructor
     * @param Name
     * @param Score 
     */
    public HighscorePlayer( String Name, int Score )
    {
        this( Name );
        
        playerScore = Score;
    }
    
    // Implementations
    /**
     * Used to sort a array, of characters depended on their highscore.
     */
    @Override
    public int compareTo( HighscorePlayer object )
    {
        return object.getPlayerScore() - this.getPlayerScore();
    }
    
} // End Class
