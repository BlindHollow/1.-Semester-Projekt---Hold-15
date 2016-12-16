package worldofzuul;

// Potentiel objects a user can keep on himself.
public class Items {

    public String name;

    public void found() {
        String actionString = "You found " + this.name;
        System.out.println(actionString);
    }

    public void destroy() {
        String actionString = "You destroyed " + this.name;
        System.out.println(actionString);
    }

    public String getName() {
        return name;
    }
    
    // Broken, etc.
    private int item_Status() {
        return 0;
    }
}
