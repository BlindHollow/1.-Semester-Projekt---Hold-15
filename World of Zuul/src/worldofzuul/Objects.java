package worldofzuul;

class Objects
{
  // Objects name
  public string name;

  public Objects( string Name )
  {
    this.name = Name;

  }

  public void Found()
  {
    String ActionString = "You found " + this.name;
    System.out.in.println( ActionString );
  }

}
