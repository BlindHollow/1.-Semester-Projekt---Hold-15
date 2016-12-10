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

    private static Game detvirker;

    // Entry function, for the Java Program
    public static void main(String[] Args) throws IOException {
        
        System.out.println("Input filename: ");
        Scanner keyboard = new Scanner(System.in);
        String filename = keyboard.nextLine();
        File file = new File(filename);
        loadGame(file);

    }

    public static void newGame() {
        detvirker = new Game();
        Scanner keyboard = new Scanner(System.in);
        String playerName = keyboard.nextLine();
        detvirker.newGame(playerName); //Creates a new game and starts it.
    }

    public static void loadGame(File file) throws IOException {
        detvirker = new Game();
        detvirker.loadGame(file);
    }

} // Class Application
