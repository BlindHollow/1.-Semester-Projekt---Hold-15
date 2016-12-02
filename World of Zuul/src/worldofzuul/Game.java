package worldofzuul;

import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * This class holds information about the game state. Upon creating a Game
 * object, a parser, a player and an amount of Rooms are created. The play()
 * method contains the main loop of the game, repeatedly checking for
 * commandwords from the user. As long as the command is not quit and the player
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
    private boolean hasBeenInPub;
    private Room pilotRoom;
    private boolean pilotFound;
    private ArrayList<Room> rooms = new ArrayList<Room>();
    private Weapons fireaxe, policegun, shotgun, ram, crowbar;
    private Food energybar, energydrink, cannedtuna, rum;
    private Sustain medKit, vaccination;
    private int degenFactor;

    public Game() {
        parser = new Parser();

    }
    public void newGame() {
        degenFactor = 5;
        createRooms();
        addNeighbours();
        createItems();
        placeItems();
        player = new Player("Bob");
    }

    public void saveGame() throws IOException {
        //Save the player state.
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("save.txt"), "utf-8"))) {
            writer.write(player.getName() + "," + player.getHealth() + "," + player.getHunger() + "," + player.getThirst() + "," + player.getIllness() + ","
                    + degenFactor + "," + currentRoom.getName() + "\n");
            if (!player.getInventory().isEmpty()) {
                Set<String> keys = player.getInventory().keySet();
                for (String item : keys) {
                    writer.write(item + ",");
                    writer.write("\n");
                }
            }
            //Save pilot state.
            if (pilotFound) {
                writer.write("pilotFound," + pilotRoom.getName() + "\n");
            } else {
                writer.write("notFound");
                writer.write("\n");
            }

            //Save room states.
            if (!rooms.isEmpty()) {
                for (Room room : rooms) {
                    writer.write("'" + room.getName() + "'" + "\n");
                    HashMap<String, Room> exits = room.getNeighbours();
                    for (String key : exits.keySet()) {
                        writer.write(key + ",");
                    }
                    writer.write("\n");
                    HashMap<String, Items> placements = room.getPlacements();
                    for (String key : placements.keySet()) {
                        writer.write(key + ",");
                    }
                    writer.write("\n");
                    HashMap<String, Zombie> zombies = room.getZombies();
                    for (String key : zombies.keySet()) {
                        writer.write(key + ",");
                    }
                    writer.write("\n");
                    if (room.isLocked()) {
                        writer.write("locked");
                    }
                    writer.write("\n");
                }
            }
        } catch (IOException e) {
            System.out.println("File could not be written");
        }
    }

    /**
     * Creates the rooms the game is set in. Neighbours are set using
     * Room.setExit(direction) Descriptions created on creation of the rooms.
     */
    private void createRooms() {

        outside1 = new Room("outsidewest", "on westside of the mainstreet");
        outside2 = new Room("outsideeast", "on the eastside of the mainstreet");
        helipad = new Room("helipad", "on a helipad");
        hospital = new Room("hospital", "in a hospital");
        policestation = new Room("policestation", "in the policestation");
        grocerystore = new Room("grocerystore", "in the grocerystore");
        firestation = new Room("firestation", "in the firestation");
        house1 = new Room("redhouse", "in the red house");
        house2 = new Room("bluehouse", "in the blue house");
        drugstore = new Room("drugstore", "in the drugstore");
        pub = new Room("pub", "in the pub");
        gasstation = new Room("gasstation", "in the gasstation");
        
    }
    private void addNeighbours() {

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

        house2.setLock(true);

        currentRoom = hospital; //Sets the games starting Room
        pilotRoom = outside1;
        
        rooms.add(outside1);
        rooms.add(outside2);
        rooms.add(helipad);
        rooms.add(hospital);
        rooms.add(policestation);
        rooms.add(grocerystore);
        rooms.add(firestation);
        rooms.add(house1);
        rooms.add(house2);
        rooms.add(drugstore);
        rooms.add(pub);
        rooms.add(gasstation);
    }

    /**
     * Creates the items and places them in rooms.
     *
     */
    private void createItems() {

        fireaxe = new Weapons("fireaxe", 10, true);
        policegun = new Weapons("policegun", 30, false);
        shotgun = new Weapons("shotgun", 20, false);
        crowbar = new Weapons("crowbar", 10, true);
        ram = new Weapons("ram", 3, true);

        energybar = new Food("energybar", 30, 0);
        energydrink = new Food("energydrink", 0, 30);
        cannedtuna = new Food("cannedtuna", 50, 0);
        rum = new Food("rum", 0, 20);

        medKit = new Sustain("medkit", 50, 0);
        vaccination = new Sustain("vaccination", 0, 50);
        
    }
    private void placeItems() {

        gasstation.placeItem(crowbar);

        hospital.placeItem(medKit);

        policestation.placeItem(policegun);
        policestation.placeItem(ram);

        firestation.placeItem(fireaxe);

        grocerystore.placeItem(energybar);
        grocerystore.placeItem(energydrink);
        grocerystore.placeItem(cannedtuna);

        pub.placeItem(shotgun);
        pub.placeItem(rum);

        drugstore.placeItem(vaccination);

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
                case ZIPLINE:
                    zipline();
                    break;
                case SAVE:
                    try {
                        saveGame();
                        System.out.println("Game saved");
                    } catch (IOException e) {
                        System.out.println("Something happened");
                    }
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
        System.out.println("You wake up from a coma \r\nYou are in a hospital \r\nA note reads: \r\nA virus outbreak has turned people to zombies \r\nGood luck, friendo \r\n\r\n");
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
        } else if (nextRoom.isLocked() == true && !player.hasUsableItem()) {
            System.out.println("Door is Locked, find something to open the door with and try again.");
        } else {
            currentRoom = nextRoom;
            currentRoom.spawnRandomZombie();

            System.out.println(currentRoom.getLongDescription());

            player.degenHungerAndThirst(degenFactor); //update hunger and thirst gauges on roomchange.

            if (currentRoom == pub && !hasBeenInPub) {
                sewer();
            }
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
        Weapons weapon = player.getPrimaryWeapon();

        if (null == zombie) {
            System.out.println("Can't find that zombie in the room");
        } else if (player.getPrimaryWeapon() == null) {
            zombie.hit(5);
            player.degenHungerAndThirst(degenFactor);
            if (zombie.schroedinger()) {
                currentRoom.removeZombie(zombie.getName());
                System.out.println(zombie.getName() + " is dead. Hooray...");
            } else {
                zombie.attackPlayer(player);
            }
        } else {
            zombie.hit(weapon.getDamage());
            player.degenHungerAndThirst(degenFactor);
            if (zombie.schroedinger()) {
                currentRoom.removeZombie(zombie.getName());
                System.out.println(zombie.getName() + " is dead. Hooray...");
            } else {
                zombie.attackPlayer(player);
            }

        }
    }

    private void sewer() {
        Room randomRoom = (rooms.get(new Random().nextInt(rooms.size())));
        currentRoom = randomRoom;
        hasBeenInPub = true;
        player.degenHungerAndThirst(degenFactor);
        System.out.println("You fall into a sewer, you decide to explore it");
        System.out.println(currentRoom.getLongDescription());
    }

    private void zipline() {
        if (currentRoom == firestation) {
            currentRoom = policestation;
            player.degenHungerAndThirst(degenFactor);
            currentRoom.getLongDescription();
        } else if (currentRoom == policestation) {
            currentRoom = helipad;
            player.degenHungerAndThirst(degenFactor);
            currentRoom.getLongDescription();
        } else {
            System.out.println("You can not zipline from here.");
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
        } else if (player.getInventory().size() >= 4) {
            System.out.println("Your inventory is full.");
        } else {

            System.out.println("You picked up the " + item.getName());
            player.getInventory().put(item.getName(), item);
            if (item instanceof Weapons) {
                player.setPrimaryWeapon((Weapons) item);
                System.out.println("Primary Weapon set");
            }

            currentRoom.removeItem(item.getName());
        }
    }

    //drop an item in your inventory and leave it in current room. Command: Drop "item"
    private void dropItem(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("What item?");
            return;
        }
        Items item = player.getItemInInventory(command.getSecondWord());

        if (null == item) {
            System.out.println("That is not an item in your inventory.");
        } else {

            System.out.println("You dropped the " + item.getName());
            currentRoom.placeItem(item);

            player.getInventory().remove(item.getName());
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
                    NewFXMain.startNewGame();
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

    public Room pilotRoom() {
        return pilotRoom;
    }

} // Class Game
