package worldofzuul;

/**
 * Contains a list of all allowed commandwords
 *
 *
 */
public enum CommandWord {
    GO       ( "go" ), 
    QUIT     ( "quit" ), 
    LOAD     ( "load" ), 
    HELP     ( "help" ), 
    UNKNOWN  ( "?" ), 
    SAVE     ( "save" ), 
    ZIPLINE  ( "zipline" ), 
    SNEAK    ( "sneak" ), 
    GRAB     ( "grab" ), 
    ATTACK   ( "attack" ), 
    BLOCK    ( "block" ), 
    DROP     ( "drop" ), 
    STATUS   ( "status" ), 
    USE      ( "use" ), 
    SEARCH   ( "search" ), 
    INVENTORY( "inventory" ), 
    SUICIDE  ( "suicide" );

    private String commandString;

    CommandWord(String commandString) {
        this.commandString = commandString;
    }

    public String toString() {
        return commandString;
    }
} // Class CommandWord
