/**
 * <h1> Defines Animal </h1>
 * 
 * @author Justin Chin
 * @author Emmanuil Simkhayev
 * @Version 221 Sofware Design Project
 * @since Dec. 26, 2016
 *
 */



public class Animal extends LivingThings {

    private static final int maxEnergy = 40;    //max energy level
    private int birthEnergy;   					//energy req to give birth
    private static final int birthAge = 3;      //age req to give birth
    private boolean isHungry;
    private int moveSpeed;                      //how fast animal moves
    private boolean beenHere[][];               //array of visited location
    private String food;                        //food for animal
    private int walk;
    private int[] xFoodCoord;					//x coordinate of food
    private int[] yFoodCoord;					//y coordinate of food
    private int numbOfFood;						//number of food in land
    private int size;
    private int[] distances;
    private boolean didMove;
    private String look;
    
    /**
     * @param size - n x n size of object matrix
     * @param locX - X coordinate location of animal
     * @param locY - Y coordinate location of animal
     * @param moveSpeed - Cycle at which animal moves
     * @param look - the way the animal looks. i.e & is a herbivore.
     * @param food - the animals' food. carnivore eats & and herbivore eats *
     */
    public Animal(int size, int locX, int locY,int moveSpeed,String look,String food, int birthEnergy) {
        super(20, locX, locY, look);

        this.moveSpeed = moveSpeed;
        this.food = food;
        beenHere = new boolean[size][size];
        this.size = size;
        distances = new int[size*size];
        didMove = false;//checks to see if animal moved already this cycle
        isHungry = false;
        this.look = look;
        this.birthEnergy = birthEnergy;
    }


    /**
     * Checks if Dead
     * @return True if Dead, else False.
     */
    @Override
    public boolean isDead(){
        if (getEnergy() <= 0) return true;
        else return false;
    }

    
    /**METHOD - checks if eligible to birth
     * @return - if false animal cannot give birth
     */
    public boolean canBirth(){
        if(getEnergy() >= birthEnergy && getAge() >= birthAge) return true;
        else return false;
    }
    
    
    /**
     * This method stores the location of the animals food in two separate arrays
     * yFoodCoord for Y coordinate of food 
     * xFoodCoord for X coordinate of food
     * @param land - 2d object of all living things
     */
    public void getFoodLocation(Object[][] land)
	{
		numbOfFood = 0;//initial number of food is zero
		xFoodCoord = new int[size*size];
        yFoodCoord = new int[size*size];
        
		for ( int i = 0; i < size; ++ i)
		{
			//System.out.println("In getFoodLocation" + food);
			for ( int j = 0; j < size; ++j)
			{
				
				
				//System.out.println("numbOfFood = " + numbOfFood);
				if (land[i][j].toString().equals(food))
				{
					//System.out.println("TRUE");
					yFoodCoord[numbOfFood] = i;//store y coordinates of food
					xFoodCoord[numbOfFood] = j;//store x coordinates of food
					++numbOfFood;
					//System.out.println("i = " + i + "j =" +j);
					//System.out.println("numbOfFood = " + numbOfFood);
				}
			}
		}
	}

    /**
     * @param land - 2d object of all living things
     * @param x - oldX, otherwise known as current x coordinate of animal
     * @param y - oldY, AKA current y coordinate of animal
     * @return - an integer value which determines the direction the animal moves
     * @minIndex is the index of the closest food source
     * 
     */
    public int searchFood(Object[][] land, int x, int y)//x and y are oldx, oldy
    {
    	//System.out.println("In SearchFood");
    	getFoodLocation(land);//find the location of food
    	
    	setFoodLocation(x,y);
    	
    	if ( numbOfFood == 0)
    		return 4;
    	
    	//int minIndex = distances.indexOf(Collections.min(distances));
    	int minIndex = getMinIndex();
    	//printArray();
    	//System.out.println(minIndex);
    	//System.out.println(this + " " + y+ " " + x + " Hunting for");
    	//System.out.println(food +" " + yFoodCoord[minIndex] + " " + xFoodCoord[minIndex]);		
    	
    		
		
    	if (yFoodCoord[minIndex] < y)
		{//if y coordinate of food is less than y coordinate of animal
			//System.out.println("Move up");
			return 0;//move up
		}
		if (yFoodCoord[minIndex] > y ){
			//System.out.println("Move down");
			return 1;//move down
		}
		if (xFoodCoord[minIndex] > x){
			//System.out.println("Move right");
			return 2; //move right
		}
		if (xFoodCoord[minIndex] < x){
			//System.out.println("Move left");
			return 3;//move left
		}
    	
    	
    	return 4;//don't move!
    }
    
    /**
     * @param x - Current x coordinate of animal
     * @param y - Current y coordinate of animal
     * this method takes the absolute value of the difference between x and y coordinates of 
     * each food and this animal.
     * It then adds the xAbs and yAbs values and stores them in an ArrayList, where the smallest 
     * value in the ArrayList is the closest food source
     */
    public void setFoodLocation(int x, int y)
    {
    	//System.out.println("in SetFoodLocation");
    	for ( int i = 0; i < numbOfFood; ++i)
    	{//subtract each coordinate of food from animal coordinates
    		int xAbs = Math.abs(xFoodCoord[i] - x);
        	int yAbs = Math.abs(yFoodCoord[i] - y);
        	
        	setDistances(xAbs,yAbs,i);//add the sum of these two values in arraylist
        	//the index with the smallest value is the closest food. 
        }
    }

    /**
     * Sets the Distance
     * @param xAbs Absolute X
     * @param yAbs Absolute Y
     * @param index Index Value
     */
    public void setDistances(int xAbs, int yAbs, int index)
    {
    	distances[index] = xAbs + yAbs;
    }

    /**
     * Prints the Array
     */
    public void printArray()
    {
    	for ( int i = 0; i < numbOfFood; ++i)
    	{
    		System.out.print(distances[i] + " ");
    	}
    	System.out.println();
    }

    /**
     * Gets the Minimum Index
     * @return Minimum Index
     */
    public int getMinIndex()
    {
    	int min = 0;
    	for ( int i = 0; i < numbOfFood; ++i)
    	{
    		if(distances[i]< distances[min])
    			min = i;
    	}
    	return min;
    }
   

    /**
     * Walks
     * @param land - 2d object of all living things
     * This method executes the action of moving this animal.
     * It also checks if this animal has eaten food and increases energy accordingly. 
     */
    public void walk(Object[][] land){
        //save current location into beenHere array
       beenHere[getLocY()][getLocX()] = true;

        //perform a random walk towards food source or free space
            //CHECK IF ANIMAL CAN MOVE IF SO THEN MOVE
                if(canMove()){
                    //Old Locations
                    int oldX = getLocX();
                    int oldY = getLocY();
                    int size = land.length;
                    
                    
                    if ( isHungry())//check if animal is hungry
                    	walk = searchFood(land,oldX, oldY);
                    else//if not hungry, then move randomly
                    {
                    	walk =  randomNum.nextInt(4);
                    	//System.out.println(this + " " + oldY + " " + oldX + " walk = " + walk + " Energy = " + getEnergy());
                    }
                   // System.out.println("Walk = " + walk);

                    switch(walk){
                        case 0: //UP    
                        	//System.out.println(checkNorth(land));
                        	//System.out.println(getLocY());
                        	if(getLocY() - 1 < 0 || checkNorth(land) == "@ " )
                        	{
                        		moveSouth();
                        		break;
                        	}
                        	
                        	moveNorth(); 
                            break;
                        case 1: //DOWN
                        	//System.out.println(getLocY());
                        	if(getLocY() + 1 > size- 1 ||checkSouth(land) == "@ " )
                        	{
                        		moveNorth();
                        		break;
                        	}
                        	
                            moveSouth();
                            break;
                        case 2: //RIGHT
                        	//System.out.println(checkEast(land));
                        	//System.out.println(getLocX());
                        	if(getLocX() + 1 > size - 1 ||checkEast(land) == "@ ")
                        	{
                        		moveWest();
                        		break;
                        	}
                            moveEast();
                            break;
                        case 3: //LEFT
                        	//System.out.println(checkWest(land));
                        	//System.out.println(getLocX());
                        	if(getLocX() - 1 < 0 || checkWest(land) == "@ ")
                        	{
                        		
                        		break;
                        	}
                            moveWest();
                            break;
                        case 4: //DON'T MOVE
                            break;
                    }
                    	
                    //Check for Collision
                    //System.out.println("newLocation = " + getLocY() + " " + getLocX());
                    
                    String collided = land[getLocY()][getLocX()].toString();
                    //If Collided
                    if(!collided.equals(". ")){
                        //If Ran Into a Plant
                        if(collided.equals(Plant.getLook()) && food == Plant.getLook()){
                            //NOM NOM Increase Energy
                         //   System.out.println("**PLANT EATEN**");
                            if(!isMax()) setEnergy(getEnergy()+ 5+ randomNum.nextInt(5));//+randomNum.nextInt(5));///get energy
                            //Delete Old Animal Location
                            land[oldY][oldX] = ". ";
                            //Replace Planet with Herbivore
                            land[getLocY()][getLocX()] = this;
                            //System.out.println("Energy of " + this + "= " + getEnergy());
                            didMove = true;

                        }
                        //If Ran Into a Herbivore
                        if(collided.equals(Herbivore.getLook()) && food == Herbivore.getLook()){
                            //NOM NOM Increase Energy
                         //   System.out.println("**HERBIVORE EATEN**");
                            if(!isMax()) setEnergy(getEnergy() + 4+randomNum.nextInt(4));///get energy
                            //Delete Old Animal Location
                            land[oldY][oldX] = ". ";
                            //Replace Planet with Carnivore
                            land[getLocY()][getLocX()] = this;
                            //System.out.println("Energy of " + this + "= " + getEnergy());
                            didMove = true;

                        }
                    }
                    
                    //If didn't Collide With Anything
                    else {
                        //Delete Old Animal Location
                        land[oldY][oldX] = ". ";
                        //Replace New Location With Animal
                        land[getLocY()][getLocX()] = this; 
                       // System.out.println("Energy of " + this + "= " + getEnergy());
                        didMove = true;
                    }
                   // System.out.println(this + " " + getLocY() + " " + getLocX() + " walk = " + walk + " Energy = " + getEnergy());
                }
            }



    /**
     * checks if animal can move ( herbivore moves every 2 cycles)
     * @return - false means the animal cannot move
     */
    public boolean canMove(){
        if (getAge() % moveSpeed == 0 && didMove() == false) 
        	return true;
        
        else return false; 
    }

    /**
     * Checks if animal moved.
     * @return True if Moved, else False
     */
    public boolean didMove()
    {
    	if (didMove == true)//if animal moved return true
    		return true;
    	
    	return false;
    }
    
    /**
     * This method sets a boolean value depending if the current animal
     * has moved. If an animal moves down or right, the method deathLiving in
     * Planet class will iterate over the same animal over and over until it moves
     * left or up.
     * @param bool
     */
    public void setDidMove(boolean bool)
    {
    	didMove = bool;//bool is going to equal false, so it will set didMove to false
    }
    
    /**
     * This method checks if the current animal is a carnivore or
     * herbivore and checks if its energy level activates its hunger.
     * 
     * @return if true then the animal will hunt for food, if not true, it will
     * move randomly as it is not hungry. 
     */
    public boolean isHungry()
    {	
    	if (look == Herbivore.getLook())//if herbivore 
    	{
    		if (getEnergy() <19)
    			return true;
    	}
    	else
    	{
    		if ( getEnergy() < 15)
    		return true;
    	}
    	return false;
    	
    }
    /**
     * checks if animal is starving
     * @return
     */
    public boolean isStarving(){
        if(getAge() % 3 == 0) return true;
        else return false;
    }

    
    /**
     * checks if animal is at maximum energy
     * @return
     */
    public boolean isMax(){
        if(getEnergy() >= maxEnergy) return true;
        else return false;
    }

    /**
     * Moves North by 1
     */
    public void moveNorth(){ setLocY(getLocY()-1);}

    /**
     * Moves South by 1
     */
    public void moveSouth(){ setLocY(getLocY()+1);}

    /**
     * Moves West by 1
     */
    public void moveWest() { setLocX(getLocX()-1);}

    /**
     * Moves East by 1
     */
    public void moveEast() { setLocX(getLocX()+1);}

    
    /**
     * checks one space above animal
     * @param land-2d object of all living things
     * @return
     */
    public Object checkNorth(Object[][] land){
        return land[getLocY()-1][getLocX()];
    }
    /**
     * checks one space below animal
     * @param land-2d object of all living things
     * @return
     */
    public Object checkSouth(Object[][] land){
        return land[getLocY()+1][getLocX()];
    }
    /**
     * checks one space right of animal
     * @param land-2d object of all living things
     * @return
     */
    public Object checkEast(Object[][] land){
        return land[getLocY()][getLocX()+1];
    }
    /**
     * checks one space left of animal
     * @param land-2d object of all living things
     * @return
     */
    public Object checkWest(Object[][] land){
        return land[getLocY()][getLocX()-1];
    }

    
    /**
     * method checks if animal can give birth, then creates new instance of object if it can.
     * @param land
     */
    public void birth(Object[][] land){
        if (canBirth()) {
          //  System.out.println("** "+getClass().getName()+" HAS BEEN BORN**");
            //BABY to BE BORN RANDOMLY TO PROTECT AGAINST PREDATORS
            int randX = 0;
            int randY = 0;
            //New Random Locations Will Generate If It is Occupied
            do {
                randX = randomNum.nextInt(land.length);
                randY = randomNum.nextInt(land.length);
            } while (land[randY][randX] != ". ");
                if(getClass().getName()=="Herbivore") land[randY][randX] = new Herbivore(land.length, randY,randX);
                if(getClass().getName()=="Carnivore") land[randY][randX] = new Carnivore(land.length,randY,randX);

                //Change Mother Energy to Minus 3
                setEnergy(getEnergy()-3);
        }
    }



}
