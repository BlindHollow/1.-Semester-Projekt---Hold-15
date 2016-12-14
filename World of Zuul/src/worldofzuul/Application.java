package worldofzuul;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 
 * @author Bytoft, Mikkel
 * @author Christensen, Martin Steen
 * @author Hansen, Søren Vest
 * @author Johansen, Emil Højgaard
 * @author Madsen, Kent vejrup
 * @author Thy, Mads Heimdal
 */
class Application {

    private static Game game;

    // Entry function, for the Java Program
    public static void main(String[] Args) throws IOException {
        
        System.out.println("Input filename: ");
        Scanner keyboard = new Scanner(System.in);
        String filename = keyboard.nextLine();
        File file = new File(filename);
        loadGame(file);

        //newGame();
    }

    public static void newGame() {
        game = new Game();
        Scanner keyboard = new Scanner(System.in);
        String playerName = keyboard.nextLine();
        game.newGame(playerName); //Creates a new game and starts it.
    }

    public static void loadGame(File file) throws IOException {
        game = new Game();
        game.loadGame(file);
    }

} // Class Application
