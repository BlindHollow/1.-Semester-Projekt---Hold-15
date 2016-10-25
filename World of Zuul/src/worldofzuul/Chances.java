package worldofzuul;

import java.util.ArrayList;
import java.util.Random;

// TODO: Write Documentation
public class Chances
{
    // Intern Objects
    private class Elements
    {
        public Elements( double Procentage,
                         int External_Identifier,
                         int Internal_Identifier )
        {
            Value       = Procentage;

            Id_Ext      = External_Identifier;
            Id_Internal = Internal_Identifier;
        }

        // Center on a given Length, along a axis
        private double Center;

        // Finder Centrum af en given Længde
        public void SetCenter( double iLength )
        {
          Center = iLength/2;
        }

        // Returns Center
        public double GetCenter()
        {
          return Center;
        }

        // Thresholds on a Axis
        private double Threshold;

        // Calculates the threshold
        public void SetThreshold( double iLength )
        {
          double HalfProcentage = Value / 2;

          double Result = ( iLength/100 ) * HalfProcentage;

          Threshold = Result;
        }

        // Returns Threshold
        public double GetThreshold()
        {
          return Threshold;
        }

        // Given procentage for the given Element
        public double Value;

        // Identifiers
          // External, something the user define
        public int Id_Ext;

          // Used by the class
        private int Id_Internal;

        public int GetInternal()
        {
          return Id_Internal;
        }

        public void SetInternal( int Id )
        {
          Id_Internal = Id;
        }

    }

    // Variabler
        // In case of error
    private boolean Error;

      // Maximum length
    private double Maximum;
    private int current_Identifier;

    //
    private ArrayList<Elements> ElementList;

    // -Initialisation Process ------------------------------------------ //
    public final void Initialise()
    {
       Error = false;
       Maximum = 4096.0;

       current_Identifier = 0;

       ElementList = new ArrayList();
    }

    // -Startup Methods ------------------------------------------------- //
    public Chances( double[] NumberOfElements,
                    int[] Identifiers )
    {
      int NOE_Length = NumberOfElements.length;
      int Ids_Length = Identifiers.length;

      if( NOE_Length != Ids_Length )
      {
          System.out.print("Error: 1");
          // Error
          return;
      }

      // Initialise the class
      Initialise();

      // Get Summerized Size of Elements
      double SummerizedElements = Summerize( NumberOfElements );

      // Checks if the summerized Value, of Elements is above 100 Procent.
      // If it is, throw an error and exit
      if( ( SummerizedElements >= 100.0f ) != true )
      {
        // Add Objects to Elements
        AccumulateObjects( NumberOfElements,
                           Identifiers );
      }
      else
      {
          
          System.out.print("Error: 2");
          // Error
          return;
      }

      SortElements();
      
      for(int x = 0; x <= ElementList.size()-1; x++)
      {
          Elements a = ElementList.get(x);
          
          System.out.println("Procentage:" + a.Value );
      }

    }

    // -Accumulate Processes -------------------------------------------- //
        // Tilføjer Elementær ud fra Arrays
    private void AccumulateObjects( double[] NumberOfElements,
                                    int[] ext_Id )
    {

        // Tilføjer elementet til listen med dets associeret ID
        for( int x = 0;
                 x <= NumberOfElements.length - 1;
                 x ++ )
        {
            current_Identifier++;

            // Laver et nyt
            Elements element = new Elements( NumberOfElements[x],
                                             ext_Id[x],
                                             current_Identifier );

            ElementList.add( element );
        }

    }

        // Tilføjer Element ud fra et Input
    private void AccumulateObjects( double ElementValue,
                                    int Id )
    {
      current_Identifier++;
      
      Elements element = new Elements( ElementValue,
                                       Id, 
                                       current_Identifier );
      ElementList.add( element );
    }

    // -Add Elements ---------------------------------------------------- //
    public void AddElement( float Procentage )
    {

    }

    public void AddElements( float[] Procentages )
    {

    }

    // -Functions ------------------------------------------------------- //
    public int Calculate()
    {
        double Midpoint = Maximum/2;



        return 0;
    }

    // -Sorting ---------------------------------------------------------- //
      // Sortere Elementer ud fra mindst -> Størst, ud fra procent sats
    private void SortElements()
    {

      for( int x = ElementList.size() - 1;
               x >= 0;
               x -- )
      {

        int y = getHighestElement( 0,
                                   x );

        if( y == -1 )
        {
          System.out.print("Error: 3\r\n");
          break;
        }
          SwitchPosition( x,
                          y );

      }

    }

    // Finder det højeste element i en array,
    // inde for et specific område
    private int getHighestElement( int start,
                                   int end )
    {
      // given that a mistake is made
      if( ( end - start ) < 0 )
        return -1;

      int index = 0;

      for( int x = start;
               x <= end;
               x ++ )
      {
        // Current Element in Array
        Elements currentIndex = ElementList.get( x );

        // Get Last
        Elements currentHighestElement = ElementList.get( index );

        // Hvis det er det, samme skip
        if( currentIndex.Value == currentHighestElement.Value )
          continue;

        if( currentIndex.Value <= currentHighestElement.Value )
        {
          index = x;
        }

      }

      return index;
    }

    // Switch 2 positions in an array
    private void SwitchPosition( int p1, int p2 )
    {
      Elements tempBufferPos1,
               tempBufferPos2;

      // Temperary Buffer
      tempBufferPos1 = ElementList.get( p1 );
      tempBufferPos2 = ElementList.get( p2 );

      // Needs to be changed later, but it works as intended
      ElementList.set( p2, 
                       tempBufferPos1);

      ElementList.set( p1,
                       tempBufferPos2 );

    }

    // -Summerizing ------------------------------------------------------ //
    // Takes an array as input and adds them together
    private double Summerize( double[] ArrayInput )
    {
        double ReturnValue = 0;

        for( int x = 0;
                 x <= ArrayInput.length - 1;
                 x ++ )
        {
            ReturnValue = ReturnValue + ArrayInput[x];
        }

        return ReturnValue;
    }

}
