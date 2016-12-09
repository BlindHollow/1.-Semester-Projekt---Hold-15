package worldofzuul;

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
    public static void main(String[] Args) {

        createGame();

    }

    public static void createGame() {
        game = new Game();
        game.newGame(); //Creates a new game and starts it.
    }

} // Class Application
