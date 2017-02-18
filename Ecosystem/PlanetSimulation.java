/**
 * <h1> To Simulate Classes Planet,PlanetSIMUI, and PlanetSIMController</h1>
 *
 * @author Justin Chin
 * @author Emmanuil Simkhayev
 * @version 211 Software Design Project
 * @since Dec. 26, 2016
 */


public class PlanetSimulation {

    public static void main(String[] args) {
        PlanetSimUI EarthUI = new PlanetSimUI();
        Planet Earth = new Planet(10,10);
        PlanetSimController EarthControl = new PlanetSimController(EarthUI,Earth);

        EarthUI.setVisible(true);

    }


}
