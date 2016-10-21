package worldofzuul;

import java.util.ArrayList;
import java.util.Random;

// Random Object, that can have multiple Elements, where one will be chosen randomly
// Examples
// Dead 0 ( *50% ) or Alive 1 ( 50% )
// Healty 0( n ) poisened ( n )
// Hit poorly 1( n ), hit Average 2 ( n ), hit Excelent 3( n )
// plus. 0 is rest element, so if the given elements, is not enough to fill 100%,
// it will calculate the rest
// And exceeding 100% will naturally cause an error
public class RandomChances {

    public boolean Error = false;

    // Elementer, som definer dets range, det kan afgive. 0 er naturligt rest.
    private ArrayList<RandomElement> nElements;

    private float RestValue = 0.0f;

    public RandomChances(float[] NumberOfElements) {
        System.out.println("RandomChances: adding - number of Elements " + NumberOfElements.length);
        int arraySize = NumberOfElements.length;

        nElements = new ArrayList();

        float t = CalculateSize(NumberOfElements);

        float value = 100 - t;

        RestValue = value;

        System.out.println("RandomChances: Rest Value = " + RestValue);

        if (t != -1) {

            RandomElement Empty = new RandomElement(0, 0.0f);

            Empty.SetThresholds(0, 1000);
            Empty.SetValue(RestValue);

            nElements.add(Empty);

            for (int x = 0;
                    x <= arraySize - 1;
                    x++) {
                RandomElement RE = new RandomElement(x + 1,
                        NumberOfElements[x]);

                System.out.println("RandomChances: Added - " + NumberOfElements[x]);
            }

            for (RandomElement re : nElements) {
                System.out.println("RandomChances: Found " + re.GetId());
            }

        } else {
            System.out.println("RandomChances: Error occured, t == -1");
        }

    }

    private float CalculateSize(float[] NumberOfElements) {
        System.out.println("RandomChances: Calculating Size");
        float v = 0.0f;

        for (float n : NumberOfElements) {
            v = v + n;

            System.out.println("RandomChances: Current Value = " + v);

            if (aboveThreshold(v, 100.0f) == true) {
                System.out.println("RandomChances: Error occured - AboveThreshold");
                Error = true;
                return -1;
            }
        }

        return v;
    }

    private float CalculateSize(RandomElement[] Elements) {
        float v = 0.0f;

        for (RandomElement e : Elements) {
            v = v + e.GetValue();

            if (aboveThreshold(v, 100.0f) == true) {
                Error = true;
                return -1;
            }
        }

        return v;
    }

    // x is above a given threshold y
    private boolean aboveThreshold(float current, float threshold) {

        if (current > threshold) {
            return true;
        } else {
            return false;
        }

    }

    private boolean belowThreshold(float current, float threshold) {
        if (current < threshold) {
            return true;
        } else {
            return false;
        }
    }

    public RandomElement[] ReturnListOfElements() {
        RandomElement[] List = (RandomElement[]) nElements.toArray();

        return List;
    }

    public void AddElement(float Value) {
        float t = CalculateSize(ReturnListOfElements());

        if (t != -1) {
            if (aboveThreshold(t, 100.0f) == true) {
                Error = true;
            }

            if (Error == true) {
                int lastElementIndex = nElements.size() - 1;
                int Element = 0;

                // retrieves last element, skal ændres senere...
                for (RandomElement RE : nElements) {
                    Element = RE.GetId();
                }

                Element = Element + 1;

                nElements.add(new RandomElement(Element,
                        Value));
            }

        }

    }

    private RandomElement[] SortList() {
        System.out.println("RandomChances: Sort list");
        System.out.println("RandomChances: Elements " + nElements.size());

        ArrayList<RandomElement> Returnlist = new ArrayList();

        if (nElements.size() == 1) {
            //
            Returnlist = nElements;
        } else {
            // Start sorting
            System.out.println("RandomChances: More than 1 element, starts sorting");

        }

        return (RandomElement[]) Returnlist.toArray();
    }

    // Returns a random Elements ID
    public int CalculateElement() {
        // Minimum T Value
        int mnT = 0;

        // Maximum T Value
        int mxT = 1000;

        // get center position
        float cT = (float) mxT / 2;

        // Random Value following the X axis (1D)
        Random randomGenerator = new Random();

        // Ændre senere, måske ?
        float n = (float) randomGenerator.nextInt(mxT - mnT) + (float) mnT;

        System.out.println("RandomChances: Random Val: " + n);

        RandomElement[] Elements = SortList();

        System.out.println("RandomChances: Returned Sortlist");

        // Starts in center, -> gets expanded, xA follow <- and xB follows ->
        float xA = cT;
        float xB = cT;

        // Calculate Thresholds, expands xA & xB
        for (int x = 0;
                x <= Elements.length - 1;
                x++) {
            // Current Elements Procentage
            float currentProcentage = Elements[x].GetValue();

            // Delta procentage
            float tProcentage = currentProcentage / 2;

            // Gives dThreshold
            float dThreshold = tProcentage * (1000 / 100);

            xA = xA + dThreshold;
            xB = xB + dThreshold;

        }

        return 0;
    }

}
