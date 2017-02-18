/**
 * <h1> Defines LivingThings</h1>
 *
 *
 * @author Justin Chin
 * @author Emmanuil Simkhayev
 * @version 211 Software Design Project
 * @since Dec. 26, 2016
 */


import java.security.SecureRandom;

public abstract class LivingThings {
    private int age;                                        //how old object is
    private int locX;                                       //x coordinate of object
    private int locY;                                       //y coordinate of object
    private int energy;                                     //energy of object
    public SecureRandom randomNum = new SecureRandom();    //For Generating Random Numbers
    private String look;                                       //how the object looks

    /**
     *
     * @param energy The initial energy of the LivingThing.
     * @param locX The X Coordinate of the LivingThing.
     * @param locY The Y Coordinate of the LivingThing.
     * @param look The Look of the LivingThing.
     */
    public LivingThings(int energy, int locX, int locY, String look){
        this.age = 0;
        this.energy = energy;
        this.locX = locX;
        this.locY = locY;
        this.look = look;
    }

    /**
     * Gets the Look.
     * @return The Look.
     */
    @Override
    public String toString(){return look;}

    /**
     * Get the Age.
     * @return The Age.
     */
    public int getAge(){
        return age;
    }

    /**
     * Sets the Age
     * @param age Age to be Set
     */
    public void setAge(int age){
        this.age = age;
    }

    /**
     * Increases Age by 1.
     */
    public void incAge1(){
        this.age += 1;
    }

    /**
     * Gets Energy.
     * @return This Energy.
     */
    public int getEnergy(){
        return energy;
    }

    /**
     * Sets Energy
     * @param energy Energy to be Set.
     */
    public void setEnergy(int energy){
        this.energy = energy;
    }

    /**
     * Increase Energy by 1.
     */
    public void incEnergy1(){
        this.energy += 1;
    }

    /**
     * Decrease Energy by 1.
     */
    public void decEnergy1(){
        this.energy -= 1;
    }

    /**
     * Gets X Coordinate.
     * @return This X Coordinate.
     */
    public int getLocX(){
        return locX;
    }

    /**
     * Set X Coordinate.
     * @param locX X Coordinate to be Set.
     */
    public void setLocX(int locX){
        this.locX = locX;
    }

    /**
     * Gets Y Coordinate.
     * @return This Y Coordinate.
     */
    public int getLocY(){
        return locY;
    }

    /**
     * Set Y Coordinate.
     * @param locY Y Coordinate to be Set.
     */
    public void setLocY(int locY){
        this.locY = locY;
    }

    /**
     * Set XY Coordinate
     * @param locX X Coordinate to be Set.
     * @param locY Y Coordinate to be Set.
     */
    public void setXY(int locX, int locY){
        this.locX = locX;
        this.locY = locY;
    }

    /**
     * Randomizes XY Coordinate
     * @param num Random Coordinates from 0 to num.
     */
    public void randomizeLocation (int num){
        this.locX = randomNum.nextInt(num);
        this.locY = randomNum.nextInt(num);
    }

    /**
     * Check if dead.
     * @return  To be implemented.
     */
    public abstract boolean isDead();

}

