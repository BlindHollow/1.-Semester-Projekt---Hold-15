package worldofzuul;

/**
 *
 * @author
 */
class Application {

    private static Game WorldOfZuul;

    // Entry function, for the Java Program
    public static void main( String[] Args ) 
    {
        createGame();
    }

    public static void createGame() 
    {
        //Creates a new game and starts it.
        WorldOfZuul = new Game();
        WorldOfZuul.play(); 
    }

} // Class Application
