package worldofzuul; //NETBEANS

/**
 * This class holds information about the game state. Upon creating a Game
 * object, a parser, a player and an amount of Rooms are created. The play()
 * method contains the main loop of the game, repeatedly checking for
 * commandwors from the user. As long as the command is not quit and the player
 * is not dead (ie. player.schrodinger evaluates to FALSE) the game does not
 * end.
 */
public class Game {
    
    private Parser parser;
    private Room currentRoom;
    private Player player;
    private Room outside1, outside2, helipad, hospital, policestation, grocerystore, firestation, house1, house2, drugstore, pub, gasstation;

    public Game() {
        createRooms();
        createItems();
        player = new Player();
        parser = new Parser();
        
        double[] values = {30.0f, 25.0f, 20.0f, 10.0f, 5.0f};
        int[] ids = {0, 1, 2, 3, 4};
        Chances Chance = new Chances(values, ids);
    }

    /**
     * Creates the rooms the game is set in. Neighbours are set using
     * Room.setExit(direction) Descriptions created on creation of the rooms.
     */
    private void createRooms() { //TODO: Possibly randomize neighbouring rooms.

        outside1 = new Room("on westside of the mainstreet");
        outside2 = new Room("on the eastside of the mainstreet");
        helipad = new Room("on a helipad");
        hospital = new Room("in a hospital");
        policestation = new Room("in the policestation");
        grocerystore = new Room("in the grocerystore");
        firestation = new Room("in the firestation");
        house1 = new Room("in the red house");
        house2 = new Room("in the blue house");
        drugstore = new Room("in the drugstore");
        pub = new Room("in the pub");
        gasstation = new Room("in the gasstation");

        hospital.setExit("east", outside1);

        outside1.setExit("northwest", grocerystore);
        outside1.setExit("northeast", house1);
        outside1.setExit("southwest", firestation);
        outside1.setExit("southeast", pub);
        outside1.setExit("west", hospital);

        drugstore.setExit("south", outside2);

        house1.setExit("south", outside1);
        house1.setExit("east", house2);

        firestation.setExit("north", outside1);

        pub.setExit("north", outside1);

        house2.setExit("west", house1);
        house2.setExit("south", outside2);

        grocerystore.setExit("south", outside1);

        outside2.setExit("northwest", house2);
        outside2.setExit("northeast", drugstore);
        outside2.setExit("southwest", policestation);
        outside2.setExit("southeast", gasstation);
        outside2.setExit("east", helipad);

        policestation.setExit("north", outside2);

        gasstation.setExit("north", outside2);

        helipad.setExit("west", outside2);

        currentRoom = hospital; //Sets the games starting Room.
    }

//Creates items and places them in rooms
    private void createItems() {
        Weapons fireaxe, policegun, shotgun;
        Food energybar, energydrink, cannedtuna, rum;
        Sustain medKit, vaccination;

        fireaxe = new Weapons("fireaxe", 10, true);
        policegun = new Weapons("policegun", 30, false);
        shotgun = new Weapons("shotgun", 20, false);

        energybar = new Food("energybar", 30, 0);
        energydrink = new Food("energydrink", 0, 30);
        cannedtuna = new Food("cannedtuna", 50, 0);
        rum = new Food("rum", 0, 20);

        medKit = new Sustain("medkit", 50, 0);
        vaccination = new Sustain("vaccination", 0, 50);

        hospital.placeItem(medKit);

        policestation.placeItem(policegun);

        firestation.placeItem(fireaxe);

        grocerystore.placeItem(energybar);
        grocerystore.placeItem(energydrink);
        grocerystore.placeItem(cannedtuna);

        pub.placeItem(shotgun);
        pub.placeItem(rum);

        drugstore.placeItem(vaccination);

    }

    public void play() {
        printWelcome();

        boolean finished = false;

        while (!finished) { //TODO: Add wincondition.
            Command command = parser.getCommand();
            finished = processCommand(command);
            if (player.schrodinger()) {
                System.out.println("You are dead.");
                finished = true;
            }
        }

        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if (commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (null != commandWord) {
            switch (commandWord) {
                case HELP:
                    printHelp();
                    break;
                case GO:
                    goRoom(command);
                    break;
                case STATUS:
                    player.getStatus();
                    break;
                case GRAB:
                    takeItem(command);
                    break;
                case DROP:
                    dropItem(command);
                    break;
                case SEARCH:
                    currentRoom.searchRoom();
                    break;
                case INVENTORY:
                    player.showInventory();
                    break;
                case SUICIDE:
                    player.updateHealth(-100);
                    break;
                case QUIT:
                    wantToQuit = quit(command);
                    break;
                default:
                    break;
            }
        }
        return wantToQuit;
    }

    private void printHelp() { //TODO Change help message to be suitable for our game.
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }
        String direction = command.getSecondWord();
        if (null != command.getCommandWord());
        switch (command.getSecondWord()) { //Allows abbreviations for directions.
            case "ne":
                direction = "northeast";
                break;
            case "nw":
                direction = "northwest";
                break;
            case "se":
                direction = "southeast";
                break;
            case "sw":
                direction = "southwest";
                break;
            case "n":
                direction = "north";
                break;
            case "s":
                direction = "south";
                break;
            case "e":
                direction = "east";
                break;
            case "w":
                direction = "west";
                break;

            default:
                break;
        }

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            player.degenHungerAndThirst(); //update hunger and thirst gauges on roomchange.
            //player.updateHealth(-50); //testing of dying player.
        }
    }

    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;
        }
    }
//pick up an item in the room you are in. Command: Grab "item"

    private void takeItem(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("What item?");
            return;
        }
        Items item = currentRoom.getItem(command.getSecondWord());

        if (null == item) {
            System.out.println("Can't find that item");
        } else {

            System.out.println("You picked up the " + item.getName());
            Player.inventory.put(item.getName(), item);

            currentRoom.removeItem(item.getName());
        }
    }

    //drop an item in your inventory and leave it in current room. Command: Drop "item"
    private void dropItem(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("What item?");
            return;
        }
        Items item = player.getInventory(command.getSecondWord());

        if (null == item) {
            System.out.println("That is not an item in your inventory.");
        } else {

            System.out.println("You dropped the " + item.getName());
            currentRoom.placeItem(item);

            Player.inventory.remove(item.getName());
        }
    }
} // Class Game
