/**
 * <h1> Defines Plants</h1>
 * @see LivingThings
 *
 * @author Justin Chin
 * @author Emmanuil Simkhayev
 * @version 211 Software Design Project
 * @since Dec. 26, 2016
 */

public class Plant extends LivingThings {

    private static final String look= "* ";     //what the plant look like
    private static final int growthTime = 5;    //how often plant populates
    private static final int deadEnergy = 100;  //energy till plant dies

    /**
     * @param locX The X Coordinate of This Plant.
     * @param locY The Y Coordinate of This Plant.
     */
    public Plant(int locX, int locY){
        super(1,locX,locY,look);
    }

    /**
     * Checks if Plant is Ready to Grow.
     * @return True if Plant is Ready to Grow, else False.
     */
    public boolean isGrow(){
        if( getAge() % growthTime == 0) return true;
        else return false;
    }


    /**
     * Checks if Plant is Dead.
     * @return True if Plant is Dead, else False.
     */
    @Override
    public boolean isDead(){
        if(getEnergy() <= deadEnergy) return true;
        else return false;
    }


    /**
     * Gets Look of Plant.
     * @return This Plant's Look.
     */
    public static String getLook(){
        return look;
    }

}
