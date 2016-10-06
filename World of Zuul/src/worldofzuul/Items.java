package worldofzuul;

// Potentiel objects a user can keep on himself.
public class Items extends Objects
{
    public void Pickup()
    {

    }

    // Use it, depended on the type of object it is
    public abstract void Use();

    // Throw the object away
    public void ThrowAway()
    {

    }

    // Broken, etc.
    private int Item_Status()
    {

    }

}
