package worldofzuul;

public class Weapons extends Items
{
  public int Damage;
  public int Modifier;



  public int Attack()
  {
    int Total_Damage = Damage * Modifier;

    System.out.println( "You Attacked" + Total_Damage );

    return Total_Damage;
  }

}
