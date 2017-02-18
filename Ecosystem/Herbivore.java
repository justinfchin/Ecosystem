/**
 * <h1> Defines Herbivores</h1>
 * @see LivingThings
 *
 * @author Justin Chin
 * @author Emmanuil Simkhayev
 * @version 211 Software Design Project
 * @since Dec. 19, 2016
 */

public class Herbivore extends Animal{

    private static final String look = "& ";            //what the Herbivores look like
    private static final int moveSpeed = 2;             //how often it moves
    private static final int birthEnergy = 18;
    private static final String food = Plant.getLook();

    /**
     * @param size The Size of the Environment
     * @param locX The X Coordinate of This Herbivore.
     * @param locY The Y Coordinate of This Herbivore.
     */
    public Herbivore(int size, int locX, int locY) {
        super(size, locX, locY,moveSpeed,look,food,birthEnergy);

    }

    /**
     * Gets Look of Herbivore.
     * @return This Herbivore's Look.
     */
    public static String getLook(){
        return look;
    }

    /**
     * @return returns the birthEnergy
     */
    public int getBirthEnergy()
    {
    	return birthEnergy;
    }
    

}
