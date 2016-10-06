package worldofzuul;

class Objects
{
  // Objects name
  public String name;

  // The Objects "health", how much it can withstand
  public int Durability = 0;

  public void Found()
  {
    String ActionString = "You found " + this.name;
    System.out.println( ActionString );
  }

  public void Destroy()
  {
    String ActionString = "You destroyed " + this.name;
    System.out.println( ActionString );
  }

}
