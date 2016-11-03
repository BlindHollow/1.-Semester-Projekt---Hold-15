package worldofzuul; //NETBEANS

import java.util.Scanner;

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
    private boolean wantToQuit;
    private boolean noteFound;
    private Room pilotRoom;
    private boolean pilotFound;

    public Game() {
        createRooms();
        createItems();
        createZombies();
        player = new Player();
        parser = new Parser();

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
        pilotRoom = outside1;
    }

    /**
     * Creates the items and places them in rooms.
     *
     */
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

    /**
     * Creates zombies and places them in rooms.
     */
    private void createZombies() {
        Zombie smallZombie, mediumZombie, largeZombie;

        //Create zombies with a name, hp and a damage modifier
        smallZombie = new Zombie("dave", 10, 10);
        mediumZombie = new Zombie("john", 20, 12);
        largeZombie = new Zombie("tommy", 30, 15);

        hospital.placeZombie(smallZombie);

    }

    public void play() {
        printWelcome();

        boolean finished = false;

        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
            if (player.schroedinger()) {
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
        wantToQuit = false;

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
                case ATTACK:
                    attackZombie(command);
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

    private void printHelp() {
        System.out.println("You wake up from a coma");
        System.out.println("You are in a hospital");
        System.out.println("A note reads:");
        System.out.println("A virus outbreak has turned people to zombies");
        System.out.println("Good luck, friendo");
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
            if (noteFound) {
                movePilot();
            }
            if (currentRoom.equals(helipad) && pilotRoom.equals(helipad)) {
                gameWon();
            } else if (currentRoom.equals(helipad)) {
                noteFound = true;
            }
        }
    }

    private void attackZombie(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("What zombie?");
            return;
        }
        Zombie zombie = currentRoom.getZombie(command.getSecondWord());

        if (null == zombie) {
            System.out.println("Can't find that zombie in the room");
        } else {

            zombie.hit(5);
            if (zombie.schroedinger()) {
                currentRoom.removeZombie(zombie.getName());
                System.out.println(zombie.getName() + " is dead. Hooray...");
            }
        }
    }

    private void movePilot() {
        if (pilotFound) {
            pilotRoom = currentRoom;
        } else if (pilotRoom.equals(currentRoom)) {
            pilotFound = true;
            System.out.println("You found the pilot");
        } else {
            int roomInt = (int) (Math.random() * pilotRoom.getSize());
            Room nextRoom = pilotRoom.getExit(roomInt);
            if (nextRoom == null) {
                System.out.println("No door for pilot.. Fix u moron");
            } else {
                pilotRoom = nextRoom;
                if (pilotRoom.equals(currentRoom)) {
                    pilotFound = true;
                    System.out.println("You found the pilot");
                }
            }
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
        } else if (player.inventory.size() >= 3) {
            System.out.println("Your inventory is full.");
        } else {

            System.out.println("You picked up the " + item.getName());
            player.inventory.put(item.getName(), item);

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

            player.inventory.remove(item.getName());
        }
    }

    private void gameWon() {
        System.out.printf("You won the game.\n Do you want to play again? Y/N\n> ");
        Scanner scan = new Scanner(System.in);
        String playString = "f";
        while (!playString.equals("n") && !playString.equals("y")) {
            playString = scan.next();
            switch (playString.toLowerCase()) {
                case "n":
                    wantToQuit = true;
                    break;
                case "y":
                    Application.newGame();
                    break;
                default:
                    System.out.println(playString + " is not an acceptable answer.\n Do you want to play again? Y/N\n> ");
                    break;
            }
        }
    }

    public Room currentRoom() {
        return currentRoom;
    }

} // Class Game
