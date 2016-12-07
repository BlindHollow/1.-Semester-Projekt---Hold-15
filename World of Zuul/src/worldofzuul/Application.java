package worldofzuul;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author
 */
class Application {

    private static Game detvirker;

    // Entry function, for the Java Program
    public static void main(String[] Args) throws IOException {

        Scanner keyboard = new Scanner(System.in);
        String filename = keyboard.nextLine();
        File file = new File(filename);
        loadGame(file);

    }

    public static void newGame() {
        detvirker = new Game();
        detvirker.newGame(); //Creates a new game and starts it.
    }

    public static void loadGame(File file) throws IOException {
        detvirker = new Game();
        detvirker.loadGame(file);

    }

} // Class Application
