package Controller;

import Model.Rocket;
import Model.U0;
import Model.U2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class SimulationTest {

        private Simulation simulation;

        @Before
        public void setUp(){
                ApplicationController appController = new ApplicationController();
                simulation = new Simulation("resources/Phase1.txt", "resources/Phase2.txt");
        }

        @After
        public void tearDown(){
                simulation = null;
        }

        @Test
        public void loadRockets() throws CloneNotSupportedException {
                ArrayList<Rocket> collection = simulation.loadRockets(2, new U2());
                assertEquals(collection.get(1333).getCargoCarried(), 8);
        }

        @Test
        public void getRocketsRequired() throws CloneNotSupportedException {
                ArrayList<Rocket> collection = simulation.loadRockets(1, new U0());
                assertEquals(simulation.getRocketsRequired(collection), 3_930.0);
        }
}