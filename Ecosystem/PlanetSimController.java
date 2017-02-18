/**
 * <h1> Defines Planet Controller </h1>
 *
 * @author Justin Chin
 * @author Emmanuil Simkhayev
 * @Version 221 Sofware Design Project
 * @since Dec. 26, 2016
 *
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlanetSimController {

    private PlanetSimUI theView;
    private Planet theModel;

    public PlanetSimController(PlanetSimUI theView, Planet theModel){
        this.theView = theView;
        this.theModel = theModel;

        this.theView.addListener(new EventHandler());
        writePlanes();

    }

    /**
     * Private Inner Class for Event Handling
     */
    private class EventHandler implements ActionListener {

        /**
         * Process Field Events
         * @param event
         */
        @Override
        public void actionPerformed(ActionEvent event){
                theModel.move(Integer.parseInt(theView.getCycleField()));
                writePlanes();

            }

        }

    /**
     * Updates all the Planes
     */
    public void writePlanes(){
        theView.setPlanetPane("Cycle Number: "+theModel.getClock()+"\n\n"+theModel.draw());
        theView.setTextPane(theModel.printLocations());
        theView.setKeyText("Herbivore: "+Herbivore.getLook()+
                "\nCarnivore: "+Carnivore.getLook()+
                "\nPlant : "+Plant.getLook()+
                "\n"+theModel.printStats());
    }

}
