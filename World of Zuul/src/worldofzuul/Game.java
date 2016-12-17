package worldofzuul;

import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.io.*;
import static java.lang.Integer.parseInt;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
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

 * @author Bytoft, Mikkel
 * @author Christensen, Martin Steen
 * @author Hansen, Søren Vest
 * @author Johansen, Emil Højgaard
 * @author Madsen, Kent vejrup
 * @author Thy, Mads Heimdal
 */
public class Game {

    private Parser parser;
    private Room currentRoom;
    private Player player;
    private Room outside1, outside2, helipad, hospital, policestation, grocerystore, firestation, house1, house2, drugstore, pub, gasstation;
    private boolean wantToQuit;
    private boolean noteFound;
    private boolean hasBeenInPub;
    private Room pilotRoom, locationOfNote;
    private boolean pilotFound;
    private ArrayList<Room> rooms = new ArrayList<Room>();
    private Weapons fireaxe, policegun, shotgun, ram, crowbar;
    private Food energybar, energydrink, cannedtuna, rum;
    private Sustain medKit, vaccination;
    private int degenFactor;
    
    private HashMap<String, Room> allowedRooms;
    private HashMap<String, Items> allowedItems;

    private worldofzuul.utilities.Dice random;
    
    public Game() {
        parser = new Parser();
        random = new worldofzuul.utilities.Dice( 0, 100 );
    }

    public void newGame(String playerName) {
        degenFactor = 5;
        createRooms();
        addNeighbours();
        createItems();
        placeItems();
        player = new Player(playerName);
        play();
    }
    /**
    * Function saves the current game state to a file named "save.txt" 
    * @throws IOException 
    */
    public void saveGame() throws IOException {
        
        player.SavePlayerscore();
        
        //Save the player state.
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("save.txt"), "utf-8"))) {
            writer.write(player.getName() + "," + player.getHealth() + "," + player.getHunger() + "," + player.getThirst() + "," + player.getIllness() + ","
                    + degenFactor + "," + currentRoom.getName() + "\n");
            if (player.hasPrimaryWeapon()) {
                writer.write(player.getPrimaryWeapon().getName());
            } else {
                writer.write("noWeapon");
            }
            writer.write("\n");
            if (!player.getInventory().isEmpty()) {
                Set<String> keys = player.getInventory().keySet();
                for (String item : keys) {
                    writer.write(item + ",");

                }
                writer.write("\n");
            } else {
                writer.write("no items" + "\n");
            }
            //Save pilot state.
            writer.write(locationOfNote.getName() + "\n");
            writer.write(pilotRoom.getName() + "\n");
            if (noteFound) {
                writer.write("noteFound");
            } else {
                writer.write("noteNotFound");
            }
            writer.write("\n");
            if (pilotFound) {
                writer.write("pilotFound");
            } else {
                writer.write("notFound");
            }
            writer.write("\n");

            //Save room states.
            if (!rooms.isEmpty()) {
                for (Room room : rooms) {
                    writer.write(room.getName() + "\n");
                    HashMap<String, Room> exits = room.getNeighbours();
                    for (String key : exits.keySet()) {
                        writer.write(key + ",");
                        writer.write(exits.get(key).getName() + "," + "\n");
                    }
                    writer.write("endexits" + "\n");
                    //writer.write("\n");
                    HashMap<String, Items> placements = room.getPlacements();
                    if (!placements.isEmpty()) {
                        for (String key : placements.keySet()) {
                            writer.write(key + ",");
                        }
                    } else {
                        writer.write("No items in room");
                    }
                    writer.write("\n");
                    /*HashMap<String, Zombie> zombies = room.getZombies(); //possibly remove, since zombies spawn randomly.
                    for (String key : zombies.keySet()) {
                        writer.write(key + ",");
                    }
                    writer.write("\n");*/
                    if (room.isLocked()) {
                        writer.write("locked");
                    } else {
                        writer.write("notLocked");
                    }
                    writer.write("\n");
                    writer.write("endroom" + "\n");
                }
            }
            writer.write("endfile");
            writer.close();

        } catch (IOException e) {
            System.out.println("File could not be written");
        }
    }
    /**
    * Loads and starts a game from a file, whose format is determined by save(), TODO: Make complete description of fileformat.
    * @param file
    * Take an input file, the format of the file is determined by the save()
    * @throws IOException
    * @throws NullPointerException
    * NullPointerExceptions happen when the inputfile either is in the wrong format or the inputfile specifies items or rooms that are not allowed.
    * @throws ArrayIndexOutOfBoundsException 
    * Exception thrown when the file is not in the specified format.
    */
    public void loadGame( File file ) throws IOException, NullPointerException, ArrayIndexOutOfBoundsException 
    {
        createRooms();
        createItems();
        
        try ( BufferedReader read = new BufferedReader( new FileReader( file ) ) ) 
        {
            while ( read.ready() ) 
            {
                String playerState = read.readLine();
                String[] playerAttributes = playerState.split(",");
                
                player = new Player( playerAttributes[ 0 ], 
                                     Integer.parseInt( playerAttributes[1] ), 
                                     Integer.parseInt( playerAttributes[2] ), 
                                     Integer.parseInt( playerAttributes[3] ),
                                     Integer.parseInt( playerAttributes[4] ) ); //Creates player object based on stats in the savefile.
                
                currentRoom = allowedRooms.get( playerAttributes[6] ); //sets currentRoom.
                
                degenFactor = Integer.parseInt( playerAttributes[5] ); //sets degenFactor, which determines how much hunger and thirst will deteriorate (ie. how much time do you have to complete the game)
                
                String playerWeapon = read.readLine(); //Does player have a primary weapon equipped?
                if ( playerWeapon.equals( "noWeapon" ) ) 
                {
                    System.out.println( "player has no weapon" ); //no
                } 
                else 
                {
                    player.setPrimaryWeapon( (Weapons) allowedItems.get(playerWeapon) ); //if yes, sets the weapon based off allowed items.
                    System.out.println( "Primary weapon set." );
                }
                String inventoryLine = read.readLine(); //Players inventory
                
                if ( inventoryLine.equals( "no items" ) ) 
                {
                    System.out.println("No items in inventory");
                } 
                else 
                {
                    String[] itemsInInventory = inventoryLine.split(",");
                    
                    for ( String itemname : itemsInInventory ) 
                    { 
                        //iterate through itemsInInventory and add items to new players inventory.
                        Items item = allowedItems.get( itemname );
                        player.getInventory().put( item.getName(), item );
                    }
                }
                
                String locationOfNoteRoom = read.readLine();
                
                locationOfNote = allowedRooms.get(locationOfNoteRoom);
                String pilotRoomName = read.readLine();
                
                pilotRoom = allowedRooms.get(pilotRoomName); //If not has been found sets pilots current room, if not sets pilots starting room.
                
                String noteFoundStatus = read.readLine(); //has note been found or not?
                if ( noteFoundStatus.equals( "noteNotFound" ) ) 
                {
                    System.out.println( "Pilot's note has not been found" );
                    noteFound = false;
                } 
                else 
                {
                    noteFound = true;
                    System.out.println( "Pilot note was found" );
                }
                
                String pilotFoundStatus = read.readLine(); //has pilot been found?
                if ( pilotFoundStatus.equals( "notFound" ) ) 
                {
                    System.out.println( "pilot hasn't been found" );
                } 
                else 
                {
                    pilotFound = true;
                    System.out.println( "Pilot was found" );
                }
                boolean moreRoomsToLoad = true;
                while ( moreRoomsToLoad ) 
                {
                    String room = read.readLine(); //Checks if the file has ended and the load is complete.
                    
                    if ( room.equals( "endfile" ) ) 
                    {
                        System.out.println("load complete");
                        break;
                    }
                    
                    while ( !room.equals("endroom" ) ) 
                    {
                        Room temp = allowedRooms.get(room); //Reads the entered room and gets correct room based on allowedRooms.
                        
                        //System.out.println(room);
                        System.out.println(temp.getName());
                        rooms.add(temp); //Add room specified in file to rooms ArrayList, so the player does not end up in a room with no exits on sewer()
                        
                        while (true) 
                        {
                            String exit = read.readLine(); //Reads the exits that are set for a room.
                            //System.out.println(exit);
                            if (exit.equals("endexits")) 
                            {
                                System.out.println("exits done");
                                break;
                            } 
                            else 
                            {
                                String[] exitDirection = exit.split(",");
                                //System.out.println(exitDirection[0] + exitDirection[1]);
                                temp.setExit( exitDirection[0], allowedRooms.get( exitDirection[1] ) );
                            }
                        }
                        
                        String itemString = read.readLine(); //Describes items in a room
                        if ( !itemString.equals( "No items in room" ) ) 
                        {
                            String[] itemsInRoom = itemString.split(",");
                            for (String itemname : itemsInRoom) 
                            {
                                temp.placeItem(allowedItems.get(itemname));
                            }
                            System.out.println("items done");
                        } 
                        else 
                        {
                            System.out.println("items done - no items");
                        }
                        
                        String locked = read.readLine(); //Is the room locked or not?
                        if (locked.equals("locked")) 
                        {
                            temp.setLock(true);
                            System.out.println("locked");
                        } 
                        else 
                        {
                            System.out.println("was not locked");
                        }

                        room = read.readLine();
                        //System.out.println(room + "\n");
                    }

                }
                
                read.close(); //Closes the reader on completion.
                play();
            }
        } 
        catch ( IOException e ) 
        {
            Thread.currentThread().getStackTrace();
        } 
        catch ( NullPointerException e ) 
        {
            System.out.println("Invalid item or room, fix it and try again.");
            Thread.currentThread().getStackTrace();
            newGame("Bob");
        } 
        catch ( ArrayIndexOutOfBoundsException e ) 
        {
            System.out.println("Fileformat is invalid");
            newGame("Bob");
        }
        
        player.LoadHighscore();
        
    }

    /**
     * Creates the rooms the game is set in. Neighbours are set using
     * Room.setExit(direction) Descriptions created on creation of the rooms.
     */
    private void createRooms() {
        allowedRooms = new HashMap<>();

        outside1    = new Room( "outsidewest", 
                                "on westside of the mainstreet" );
        outside2    = new Room( "outsideeast", 
                                "on the eastside of the mainstreet" );
        
        helipad     = new Room( "helipad", 
                                "on a helipad" );
        hospital    = new Room( "hospital", 
                                "in a hospital" );
        
        policestation   = new Room( "policestation", 
                                    "in the policestation" );
        firestation     = new Room( "firestation", 
                                    "in the firestation" );
        
        grocerystore    = new Room( "grocerystore", 
                                    "in the grocerystore" );
        
        house1      = new Room( "redhouse", 
                                "in the red house" );
        house2      = new Room( "bluehouse", 
                                "in the blue house" );
        
        drugstore   = new Room( "drugstore", 
                                "in the drugstore" );
        
        pub         = new Room( "pub", 
                                "in the pub" );
        
        gasstation  = new Room( "gasstation", 
                                "in the gasstation" );

        allowedRooms.put( outside1.getName(), outside1 );
        allowedRooms.put( outside2.getName(), outside2 );
        
        allowedRooms.put( helipad.getName(), helipad );
        allowedRooms.put( hospital.getName(), hospital );
        
        allowedRooms.put( policestation.getName(), 
                          policestation );
        allowedRooms.put( grocerystore.getName(), 
                          grocerystore );
        allowedRooms.put( firestation.getName(), 
                          firestation );
        allowedRooms.put( drugstore.getName(), 
                          drugstore );
        
        allowedRooms.put( house1.getName(), 
                          house1 );
        allowedRooms.put( house2.getName(), 
                          house2 );
        
        allowedRooms.put( pub.getName(), 
                          pub );
        
        allowedRooms.put( gasstation.getName(), 
                          gasstation );
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
        locationOfNote = helipad;

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
        allowedItems = new HashMap<>();

        fireaxe = new Weapons( "fireaxe", 
                               10, 1, 
                               true );
        
        policegun = new Weapons( "policegun", 
                                 30, 4, 
                                 false );
        shotgun = new Weapons( "shotgun", 
                               20, 5, 
                               false );
        
        crowbar = new Weapons( "crowbar", 
                               10, 1, 
                               true );
        ram = new Weapons( "ram", 
                            3, 4, 
                            true );

        energybar   = new Food( "energybar", 
                                30, 0 );
        energydrink = new Food( "energydrink", 
                                0, 30 );
        cannedtuna  = new Food( "cannedtuna", 
                                50, 0 );
        rum         = new Food( "rum", 
                                0, 20 );

        medKit = new Sustain("medkit", 50, 0);
        vaccination = new Sustain("vaccination", 0, 50);

        allowedItems.put( fireaxe.getName(), 
                          fireaxe );
        allowedItems.put( policegun.getName(), 
                          policegun );
        allowedItems.put( shotgun.getName(), 
                          shotgun );
        allowedItems.put( crowbar.getName(), 
                          crowbar );
        
        allowedItems.put( ram.getName(), 
                          ram );
        
        allowedItems.put( energybar.getName(), 
                          energybar );
        allowedItems.put( energydrink.getName(), 
                          energydrink );
        allowedItems.put( cannedtuna.getName(), 
                          cannedtuna );
        allowedItems.put( rum.getName(), 
                          rum );
        
        allowedItems.put( medKit.getName(), 
                          medKit );
        allowedItems.put( vaccination.getName(), 
                          vaccination );
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

    public void play() {
        printWelcome();

        boolean finished = false;

        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
            if ( player.schroedinger() ) 
            {
                System.out.println( "You are dead." );
                System.out.println( "Your total accomulated points is:" + 
                                     Integer.toString( player.RetrieveScore() ) );
                
                finished = true;
            }
        }

        System.out.println( "Thank you for playing.  Good bye." );
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

        if ( null != commandWord ) 
        {
            
            switch ( commandWord ) 
            {
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
                    try 
                    {
                        saveGame();
                        System.out.println("Game saved");
                    } 
                    catch (IOException e) 
                    {
                        System.out.println("Something happened");
                    }
                    break;
                    
                /*case LOAD:
                    try {
                        loadGame();
                        System.out.println("Game loaded");
                    } catch (IOException e) {
                        System.out.println("Something happened");
                    }
                    break;*/
                    
                case QUIT:
                        wantToQuit = quit(command);
                    break;
                
                default:
                    break;
            }
            
        }
        
        return wantToQuit;
    }

    private void printHelp() 
    {
        System.out.println( "You wake up from a coma" );
        System.out.println( "You are in a hospital " );
        System.out.println( "A note reads: " );
        System.out.println( "A virus outbreak has turned people to zombies " );
        System.out.println( "Good luck, friendo \r\n" );
        System.out.println( "Your command words are:" );
        
        parser.showCommands();
    }

    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }
        String direction = command.getSecondWord();
        if (null != command.getCommandWord());
        
        switch (command.getSecondWord()) 
        { 
            //Allows abbreviations for directions.
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

        if ( nextRoom == null ) 
        {
            System.out.println("There is no door!");
        } 
        else if(!currentRoom.getZombies().isEmpty()) 
        {
            System.out.println("You need to deal with the zombies in the room, before moving");
        }
        else if ( nextRoom.isLocked() == true && !player.hasUsableItem() ) 
        {
            System.out.println("Door is Locked, find something to open the door with and try again.");
        } 
        else 
        {
            currentRoom = nextRoom;
            currentRoom.spawnRandomZombie();

            System.out.println(currentRoom.getLongDescription());

            player.degenHungerAndThirst(degenFactor); //update hunger and thirst gauges on roomchange.

            if (currentRoom == pub && !hasBeenInPub) 
            {
                sewer();
            }
            if (noteFound) {
                movePilot();
            }

            if (currentRoom.equals(locationOfNote) && pilotRoom.equals(locationOfNote)) 
            {
                gameWon();
            } 
            else if (currentRoom.equals(locationOfNote)) 
            {
                noteFound = true;
            }
        }
    }

    private void attackZombie(Command command) 
    {
        if (!command.hasSecondWord()) 
        {
            System.out.println("What zombie?");
            return;
        }
        
        Zombie zombie = currentRoom.getZombie(command.getSecondWord());
        Weapons weapon = player.getPrimaryWeapon();

        if (null == zombie) 
        {
            System.out.println("Can't find that zombie in the room");
        } 
        else if ( player.getPrimaryWeapon() == null ) 
        {
            zombie.hit(5);
            
            player.degenHungerAndThirst(degenFactor);
            
            if ( zombie.schroedinger() ) 
            {
                currentRoom.removeZombie(zombie.getName());
                player.increasePlayerScore( 10 );
                System.out.println(zombie.getName() + " is dead. Hooray...");
            } 
            else 
            {
                zombie.attackPlayer( player );
            }
        } 
        else 
        {
            zombie.hit( weapon.getDamage() );
            
            player.degenHungerAndThirst(degenFactor);
            
            if ( zombie.schroedinger() ) 
            {
                currentRoom.removeZombie(zombie.getName());
                System.out.println(zombie.getName() + " is dead. Hooray...");
            } 
            else 
            {
                zombie.attackPlayer( player );
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
        if (currentRoom == firestation) 
        {
            player.increasePlayerScore( 20 );
            
            currentRoom = policestation;
            player.degenHungerAndThirst(degenFactor);
            currentRoom.getLongDescription();
        } 
        else if (currentRoom == policestation) 
        {
            player.increasePlayerScore( 20 );
            
            currentRoom = helipad;
            player.degenHungerAndThirst(degenFactor);
            currentRoom.getLongDescription();
        } 
        else 
        {
            System.out.println("You can not zipline from here.");
        }
    }

    private void movePilot() 
    {
        if (pilotFound) 
        {
            pilotRoom = currentRoom;
        } 
        else if (pilotRoom.equals(currentRoom)) 
        {    
            pilotFound = true;
            player.increasePlayerScore( 200 );
            System.out.println("You found the pilot");
        } 
        else 
        {
            int roomInt = (int) (Math.random() * pilotRoom.getSize() );
            
            Room nextRoom = pilotRoom.getExit(roomInt);
            
            if (nextRoom == null) 
            {
                System.out.println("No door for pilot.. Fix it, u moron");
            } 
            else 
            {
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

    private void takeItem(Command command) 
    {
        if ( !command.hasSecondWord() ) {
            System.out.println("What item?");
            return;
        }
        
        Items item = currentRoom.getItem(command.getSecondWord());

        if (null == item) 
        {
            System.out.println("Can't find that item");
        } 
        else if (player.getInventory().size() >= 4) 
        {
            System.out.println("Your inventory is full.");
        } 
        else 
        {
            System.out.println("You picked up the " + item.getName());
            player.getInventory().put(item.getName(), item);
            if ((item instanceof Weapons) && !(player.hasPrimaryWeapon())) 
            {
                player.setPrimaryWeapon((Weapons) item);
                System.out.println("Primary Weapon set");
            }

            currentRoom.removeItem(item.getName());
        }
        
    }

    //drop an item in your inventory and leave it in current room. Command: Drop "item"
    private void dropItem(Command command) 
    {
        if (!command.hasSecondWord()) 
        {
            System.out.println("What item?");
            return;
        }
        
        Items item = player.getItemInInventory(command.getSecondWord());

        if (null == item) 
        {
            System.out.println("That is not an item in your inventory.");
        } else 
        {
            System.out.println("You dropped the " + item.getName());
            currentRoom.placeItem(item);

            player.getInventory().remove(item.getName());
        }
    }

    private void gameWon() 
    {
        System.out.printf("You won the game.\n Do you want to play again? Y/N\n> ");
        
        player.increasePlayerScore( 550 );
        Scanner scan = new Scanner(System.in);
        String playString = "f";
        
        while (!playString.equals("n") && !playString.equals("y")) 
        {
            playString = scan.next();
            
            switch (playString.toLowerCase()) 
            {
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

    public Room currentRoom() 
    {
        return currentRoom;
    }

    public Room pilotRoom() 
    {
        return pilotRoom;
    }

} // Class Game
