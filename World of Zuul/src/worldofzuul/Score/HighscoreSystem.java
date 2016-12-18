/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.score;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Bytoft, Mikkel
 * @author Christensen, Martin Steen
 * @author Hansen, Søren Vest
 * @author Johansen, Emil Højgaard
 * @author Madsen, Kent vejrup
 * @author Thy, Mads Heimdal
 */
public class HighscoreSystem {

    private ArrayList<HighscorePlayer> listOfPriorUserScores = new ArrayList();

    /**
     *
     * @param CurrentPlayername
     */
    public HighscoreSystem() {
        
    }

    /**
     * Add Player's to compare againts
     *
     * @param Name
     * @return
     */
    protected boolean addPlayer(String Name) {
        return addPlayers(Name, 0);
    }

    /**
     *
     * @param Name
     * @param Score
     * @return
     */
    protected boolean addPlayers(String Name, int Score) {
        HighscorePlayer player = new HighscorePlayer(Name,
                Score);

        if (exactUser(Name) == true) {
            return false;
        } else {
            listOfPriorUserScores.add(player);
            return true;
        }

    }

    /**
     * Returns true if a player with the same name, show up. avoid duplicates
     *
     * @param Name
     * @return
     */
    private boolean exactUser(String Name) {
        
        for (HighscorePlayer current : listOfPriorUserScores) {
            if (Name.equalsIgnoreCase(current.getPlayerName())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns a sorted list, with the player and other players
     *
     * @return
     */
    public ArrayList<HighscorePlayer> orderedListOfPlayers() {
        // ReturnList
        ArrayList<HighscorePlayer> retList = new ArrayList();

        // Adds Everybody
        retList.addAll(listOfPriorUserScores);

        // Sortere Listen
        Collections.sort(retList);

        return retList;
    }

}
