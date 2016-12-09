package worldofzuul;

/**
 * @author Bytoft, Mikkel
 * @author Christensen, Martin Steen
 * @author Hansen, Søren Vest
 * @author Johansen, Emil Højgaard
 * @author Madsen, Kent vejrup
 * @author Thy, Mads Heimdal
 */
// Potentiel objects a user can keep on himself.
public class Items {

    public String name;

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

    public String getName() {
        return name;
    }

}
