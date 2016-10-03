package worldofzuul;

// TODO: Write Documentation
public enum CommandWord
{
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?"), SNEAK("sneak"), GRAB("grab"), ATTACK("attack"), BLOCK("block"), DROP("drop"), STATUS("status"), USE("use");

    private String commandString;

    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }

    public String toString()
    {
        return commandString;
    }
} // Class CommandWord
