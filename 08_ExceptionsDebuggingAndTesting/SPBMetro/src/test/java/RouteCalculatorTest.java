


import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RouteCalculatorTest extends TestCase {
    StationIndex stationIndex;
    RouteCalculator routeCalculator;
    List<Station> routeWithOneConnectionExpected;
    List<Station> routeWithTwoConnectionExpected;
    List<Station> routeOnTheLine;

    @Override
    protected void setUp() throws Exception {
        stationIndex = new StationIndex();


        Line firstLine = new Line(1, "First");
        Line secondLine = new Line(2, "Second");
        Line thirdLine = new Line(3, "Third");

        stationIndex.addLine(firstLine);
        stationIndex.addLine(secondLine);
        stationIndex.addLine(thirdLine);

        Station s1l1 = new Station("S1_L1", firstLine);
        Station s2l1 = new Station("S2_L1", firstLine);
        Station s3l1 = new Station("S3_L1", firstLine);

        firstLine.addStation(s1l1);
        firstLine.addStation(s2l1);
        firstLine.addStation(s3l1);

        Station s1l2 = new Station("S1_l2", secondLine);
        Station s2l2 = new Station("S2_l2", secondLine);
        Station s3l2 = new Station("S3_l2", secondLine);

        secondLine.addStation(s1l2);
        secondLine.addStation(s2l2);
        secondLine.addStation(s3l2);

        Station s1l3 = new Station("S1_L3", thirdLine);
        Station s2l3 = new Station("S2_L3", thirdLine);
        Station s3l3 = new Station("S3_L3", thirdLine);

        thirdLine.addStation(s1l3);
        thirdLine.addStation(s2l3);
        thirdLine.addStation(s3l3);

        stationIndex.addStation(s1l1);
        stationIndex.addStation(s2l1);
        stationIndex.addStation(s3l1);
        stationIndex.addStation(s1l2);
        stationIndex.addStation(s2l2);
        stationIndex.addStation(s3l2);
        stationIndex.addStation(s1l3);
        stationIndex.addStation(s2l3);
        stationIndex.addStation(s3l3);

        List<Station> connection1to2 = new ArrayList<>();
        connection1to2.add(s2l1);
        connection1to2.add(s2l2);
        stationIndex.addConnection(connection1to2);


        List<Station> connection2to3 = new ArrayList<>();
        connection2to3.add(s3l2);
        connection2to3.add(s1l3);
        stationIndex.addConnection(connection2to3);


        routeWithOneConnectionExpected = new ArrayList<>();

        routeWithOneConnectionExpected.add(s1l1);
        routeWithOneConnectionExpected.add(s2l1);
        routeWithOneConnectionExpected.add(s2l2);
        routeWithOneConnectionExpected.add(s3l2);

        routeWithTwoConnectionExpected = new ArrayList<>();

        routeWithTwoConnectionExpected.add(s1l1);
        routeWithTwoConnectionExpected.add(s2l1);
        routeWithTwoConnectionExpected.add(s2l2);
        routeWithTwoConnectionExpected.add(s3l2);
        routeWithTwoConnectionExpected.add(s1l3);
        routeWithTwoConnectionExpected.add(s2l3);

        routeOnTheLine = new ArrayList<>();
        routeOnTheLine.add(s1l1);
        routeOnTheLine.add(s2l1);
        routeOnTheLine.add(s3l1);


        routeCalculator = new RouteCalculator(stationIndex);


    }

    public void testCalculateDurationWithOneConnection() {
        assertEquals(8.5, RouteCalculator.calculateDuration(routeWithOneConnectionExpected));
    }

    public void testRouteWithOneConnection() {
        List<Station> actual = routeCalculator.getShortestRoute(routeWithOneConnectionExpected.get(0), routeWithOneConnectionExpected.get(routeWithOneConnectionExpected.size() - 1));
        assertEquals(routeWithOneConnectionExpected, actual);

    }

    public void testCalculateDurationWithTwoConnection() {
        assertEquals(14.5, RouteCalculator.calculateDuration(routeWithTwoConnectionExpected));
    }

    public void testRouteWithTwoConnection() {
        List<Station> actual = routeCalculator.getShortestRoute(routeWithTwoConnectionExpected.get(0), routeWithTwoConnectionExpected.get(routeWithTwoConnectionExpected.size() - 1));
        assertEquals(routeWithTwoConnectionExpected, actual);
    }

    public void testCalculateDurationOnThwLine() {
        assertEquals(5.0, RouteCalculator.calculateDuration(routeOnTheLine));
    }

    public void testRouteOnTheLine() {
        List<Station> actaul = routeCalculator.getShortestRoute(routeOnTheLine.get(0), routeOnTheLine.get(routeOnTheLine.size() - 1));
        assertEquals(routeOnTheLine, actaul);
    }


}