/**
 * <h1> Defines Carnivore</h1>
 * @see LivingThings
 *
 * @author Justin Chin
 * @author Emmanuil Simkhayev
 * @version 211 Software Design Project
 * @since Dec. 26, 2016
 */


import java.util.Objects;

public class Carnivore extends Animal {


    private static final String look= "@ ";      //what the Herbivores look like
    private static final int moveSpeed = 1;     //how often it moves
    private static final int birthEnergy = 17;
    private static final String food = Herbivore.getLook();

    /**
     * @param size The Size of the Environment.
     * @param locX The X Coordinate of This Carnivore.
     * @param locY The Y Coordinate of This Carnivore.
     */
    public Carnivore(int size, int locX, int locY){

        super(size, locX,locY,moveSpeed,look,food, birthEnergy);
    }

    /**
     * Gets Look of Carnivore.
     * @return This Carnivore's Look.
     */
    public static String getLook(){
        return look;
    }

    /**
     * Reduces Energy By 2
     */
    @Override
    public void decEnergy1(){
        setEnergy(getEnergy()-2);
    }
    
    /**
     * @return returns the birthEnergy
     */
    public int getBirthEnergy()
    {
    	return birthEnergy;
    }
    
}