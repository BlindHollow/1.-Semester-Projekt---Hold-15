package worldofzuul;

/**
 *
 * @author
 */
class Application {

    private static Game detvirker;

    // Entry function, for the Java Program
    public static void main(String[] Args) {

        createGame();

    }

    public static void createGame() {
        detvirker = new Game();
        detvirker.newGame(); //Creates a new game and starts it.
    }

} // Class Application
