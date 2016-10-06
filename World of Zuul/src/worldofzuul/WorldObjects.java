package worldofzuul;

 class WorldObjects
{
  // Objects name
  public String name;

  public void Found()
  {
    String ActionString = "You found " + this.name;
    System.out.println( ActionString );
  }

  public void Destroy()
  {
    String ActionString = "You destroyed" + this.name;
    System.out.println( ActionString );
  }

}
