package worldofzuul;

// Element til klassen Random

import java.util.ArrayList;

public class RandomElement
{
  private int id;
  private float value;
  
  private float thresholdA;
  private float thresholdB;

  public RandomElement( int Id, float Value )
  {
    id = Id;
    value = Value;
  }

  // metoder til at få eller retunere attributes
  public int GetId()
  {
    return id;
  }

  public float GetValue()
  {
    return value;
  }
  
  // Note: It's only RandomChances, that is supposed to use this method
    public float ThresholdA()
    {
        return thresholdA;
    }

    public float ThresholdB()
    {
        return thresholdB;
    }

  // metoder til at ændre attributes
  public void SetId( int Id )
  {
    id = Id;
  }

  public void SetValue( float Value )
  {
    value = Value;
  }

  // Note: It's only RandomChances, that is supposed to use this method
  public void SetThresholds( float ThresholdA, float ThresholdB )
  {
      thresholdA = ThresholdA;
      thresholdB = ThresholdB;
  }
  
}
