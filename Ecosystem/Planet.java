/**
 * <h1> Planet </h1>
 * <p>
 * This is the environment for the LivingThings Class.
 *
 * @author Justin Chin
 * @author Emmanuil Simkhayev
 * @version 211 Software Design Project
 * @since Dec. 26, 2016
 */

import java.util.ArrayList;
import java.security.SecureRandom;


public class Planet {



    private SecureRandom randomNum = new SecureRandom();    //random# for populating the land


    private int size;                   //Size of the Planet.Planet
    private final int NUM_ITEMS = 4;    //# of ITEMS (plant,free space, herbivore, carnivore)
    private int numSimulations;         //# of Simulations
    private int numCarnivores;          //# of Carnivores
    private int numHerbivores;          //# of Herbivores
    private int numPlants;              //# of Plants
    private Object[][] land;            //2D Array Object
    private int clock = 0;  //Clock for Tracking Iteration
    private final String freeSpace = ". ";  //look of freeSpace


    /**
     *
     * @param size The Size of the Environment.
     * @param numSimulations The Number of Simulations.
     */
    public Planet(int size, int numSimulations){
        this.size = size;
        this.numSimulations = numSimulations+1;//number of times we run the simulation
        this.numCarnivores = size/2;
        this.numHerbivores = size/2;
        this.numPlants = size;
        land = new Object[size][size];//our world/
        layLand(); //initializes planet
    }


    /**
     * Provides Number of Carnivores, Herbivores, and Plants on the Planet
     * @return String with the Statistics.
     */
    public String printStats(){
        numCarnivores = 0;
        numHerbivores = 0;
        numPlants = 0;
        for(Object[] l : land) {
            for (Object x : l) {
                if(x instanceof Carnivore) numCarnivores += 1;
                if(x instanceof Herbivore) numHerbivores += 1;
                if(x instanceof Plant) numPlants +=1;
            }
        }
        return("# of Carnivores: "+numCarnivores+
                "\n# of Herbivores: "+numHerbivores+
                "\n# of Plants: "+numPlants);
    }

    /**
     * Initializes the Land with Carnivores, Herbivores, Plants
     */    public void layLand() {

        //Generates Random Number from 0 to size.
        int randX = randomNum.nextInt(size);
        int randY = randomNum.nextInt(size);

        //Create Initial # of Herbivores Based on User Input
        for (int i = 0; i < numHerbivores; i++) {
            //New Random Locations Will Generate If It is Occupied
            do {

                randX = randomNum.nextInt(size);
                randY = randomNum.nextInt(size);
            } while (land[randY][randX] != null);
            //Create Herbivore Instance in Array and Set Land To Show Herbivore
            land[randY][randX] = new Herbivore(size, randX, randY);
            
        }


        //Create Initial # of Carnivores Based on User Input
        for (int i = 0; i < numCarnivores; i++) {
            //New Random Locations Will Generate If It is Occupied
            do {
                randX = randomNum.nextInt(size);
                randY = randomNum.nextInt(size);
            } while (land[randY][randX] != null);
            //Create Carnivore Instance in Array and Set Land to Show Carnivore
            land[randY][randX] = new Carnivore(size, randX, randY);

        }

        //Create Initial # of Plants Based on User Input
        for (int i = 0; i < numPlants; i++) {
            //New Random Locations Will Generate If It is Occupied
            do {
                randX = randomNum.nextInt(size);
                randY = randomNum.nextInt(size);
            } while (land[randY][randX] != null);
            //Create Plant Instance in Array and Set Land to Show Plant
            land[randY][randX] = new Plant(randX, randY);

        }


    }

    /**
     * Draws the Planet
     */
    public String draw()
    {
        String landOutput = "";
            //Creates the Free Space
            for (int i = 0; i < land.length; i++) {
                for (int j = 0; j < land.length; j++) {
                    if (land[i][j] == null) land[i][j] = freeSpace; //fill with free space
                    landOutput +=land[i][j]; //print the object - note this calls the toString method
                }
                landOutput +='\n';
            }
        return landOutput;

    }



    /**
     * Increases LivingThings Age and Checks for Death
     */    public void deathLiving(){
        //loops through every single item
        for (int i = 0; i < land.length; i++) 
        {
            for (int j = 0; j < land.length; j++) 
            {
                //checks if it is a LivingThing
                if(land[i][j] instanceof LivingThings) 
                {
                    //increase age by 1
                    LivingThings x = (LivingThings)land[i][j];
                    x.incAge1();

                    //checks if it is Animal
                    if(x instanceof Animal)
                    {
                        //check if Animal is dead
                        if(x.isDead()){
                           // System.out.println("** "+x.getClass().getName() + " DIED**");
                            land[i][j] = ". ";
                        }

                    }

                    //this is to test walking
                    if(x instanceof Herbivore)
                    {
                        ((Herbivore) x).decEnergy1();
                        if(((Herbivore) x).isDead()) 
                        {
                           // System.out.println("** "+x.getClass().getName() + " DIED**");
                            land[i][j] = freeSpace;
                            break;
                        }
                        ((Herbivore) x).walk(land);
                    }
                    if(x instanceof Carnivore)
                    {
                        ((Carnivore) x).decEnergy1();
                        if(((Carnivore) x).isDead()) 
                        {
                           // System.out.println("** "+x.getClass().getName() + " DIED**");
                            land[i][j] = freeSpace;
                            break;
                        }
                        ((Carnivore) x).walk(land);
                    }

                }
            }
        }
       
        
    }

    /**
     * This sets each animals didMove method in the Animal class to false
     * so they can move again next cycle. 
     */
    public void resetDidMove()
    {
    	for (int i = 0; i < land.length; ++i)
    	{
    		for ( int j = 0; j<land.length; ++j)
    		{
    			if (land[i][j] instanceof LivingThings)
    			{
    				LivingThings x = (LivingThings)land[i][j];
    				
    				if (x instanceof Animal)
    				{
    					if (x instanceof Herbivore)

    						((Herbivore) x).setDidMove(false);  
    					if(x instanceof Carnivore)
    						((Carnivore) x).setDidMove(false);
    				}
    			}
    			
    		}
    	}
    }

    /**
     * Prints out the location and type of object in each.
     */
    public String printLocations(){
        String locationOutput="";
        for(Object[] l : land) {
            for (Object x : l) {
                if(x instanceof LivingThings){
                    locationOutput += ("["+((LivingThings) x).getLocY()+","+((LivingThings) x).getLocX()+
                        "] - " + x.getClass().getName()  + '\n');
                }
            }
        }
        return locationOutput;
    }

    /**
     * Moves the LivingThings
     */
    public void move(int numSimulations){
            for (int i = 0; i < numSimulations; i++){
                clock += 1;
                deathLiving();
                resetDidMove();
                livingGrowth();

            }

        }

    /**
     * Get Clock
     */
    public int getClock(){

        return clock;
    }

    /**
     * Checks if LivingThings will Grow and Updates Accordingly
     */
    public void livingGrowth() {
    	int count = 0;
    	int iteration = 0;//if plant does not spawn within size*size tries, then break out to avoid infinite loop
        for (Object[] l : land) {
            for (Object x : l) {
                //check if plant is spreading
                //if (x instanceof Plant && count <size ){
                	iteration = 0;//reset iteration count for each plant
                	//Plant y = (Plant) x;
                    if (count % 2 == 0) {
                        //System.out.println("**PLANT HAS GROWN**");
                        int randX = 0;
                        int randY = 0;
                        //New Random Locations Will Generate If It is Occupied
                        do {
                            randX = randomNum.nextInt(size);
                            randY = randomNum.nextInt(size);
                        } while (land[randY][randX] != freeSpace && iteration < size*size);//second condition avoids infinite
                        land[randY][randX] = new Plant(randX, randY);
                        ++count;//number of plants allowed to spawn.
                    }


                if (x instanceof Animal) {
                   ((Animal) x).birth(land);
                }

            }
        }
    }
}

