package Tests;

import com.company.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class MainTest {
    @Test
    public void calculateDistanceInRadians() {
        Main main = new Main();
        Main.Coordinates c1 = new Main.Coordinates(0, 51.5);
        Main.Coordinates c2 = new Main.Coordinates(-77.1, 38.8);
        double estimatedDistance = main.calculateDistance(c1, c2, false);
        Assertions.assertEquals(estimatedDistance, 3858.3349720657598);
    }

    @Test
    public void calculateDistanceInDegree() {
        Main main = new Main();
        Main.Coordinates c1 = new Main.Coordinates(0, 51.5);
        Main.Coordinates c2 = new Main.Coordinates(-77.1, 38.8);
        double estimatedDistance = main.calculateDistance(c1, c2, true);
        Assertions.assertEquals(estimatedDistance, 5918.185064088764);
    }


}
