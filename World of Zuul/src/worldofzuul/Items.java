package worldofzuul;

// Potentiel objects a user can keep on himself.
public class Items {

    public String name;

    public void pickUp(Items item) {
        Player.inventory.add(item);
        
    }

    // Throw the object away
    public void throwAway(int index) {
        Player.inventory.remove(index);//TODO add search to find correct index of item.

    }

    // Broken, etc.
    private int item_Status() {
        return 0;
    }

    public void found() {
        String actionString = "You found " + this.name;
        System.out.println(actionString);
    }

    public void destroy() {
        String actionString = "You destroyed " + this.name;
        System.out.println(actionString);
    }

}
