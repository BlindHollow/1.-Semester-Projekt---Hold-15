package worldofzuul;

class Objects
{
  
  // Objects name
  private String name;

  // The Objects "health", how much it can withstand
  private int Durability = 0;

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
