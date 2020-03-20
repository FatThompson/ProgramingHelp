/**
 * Created a seat class to make the 2D array's easier 
 */
public class Seat {

    /**
     * Globals
     */
    private String seatClass;
    private String[] avaliableSeatClasses;
    private int seatCost;
    private boolean seatTaken; // asuming seat is not taken
    private boolean seatMeal; // assumed no

    public Seat(){
        seatClass = new String();
        seatCost = 0;
        seatTaken = false;
        seatMeal = false;
    } 

    public Seat(String[] avaliableSeatClasses){
        this.avaliableSeatClasses = avaliableSeatClasses;
        seatClass = new String();
        seatCost = 0;
        seatTaken = false;
        seatMeal = false;
    }
    
    public Seat(Seat newseat){
        this.avaliableSeatClasses=newseat.avaliableSeatClasses;
        this.seatClass = newseat.seatClass;
        this.seatCost = newseat.seatCost;
        this.seatTaken = newseat.seatTaken;
        this.seatMeal = newseat.seatMeal;
    }
    
    /**
     * get and set hasmeal with overloading
     */
    public boolean hasMeal(){return this.seatMeal;}
    public void hasMeal(boolean hasMeal){this.seatMeal = hasMeal;}

    /**
     * get and set seatTaken with overloading
     * @return boolean seatTaken
     */
    public boolean isSeatTaken(){return this.seatTaken;}  
    /** 
     * @param newSeatTakenStatus
     */
    public void seatTaken(final boolean newSeatTakenStatus) 
    {this.seatTaken = newSeatTakenStatus;}
    //overloaded
    public void seatTaken(){seatTaken(true);}
    
    public String[] getAvaliableClasses(){
        return this.avaliableSeatClasses;
    }
    /**
     * returns the seat's class
     * @return String SeatClass
     */
    public String getSeatClass(){
        return this.seatClass;
    }
    
    /**
     * Returns the seat's cost
     * @return int SeatCost
     */
    public int getSeatCost(){
        return this.seatCost;
    }


    /**
     * Set the seat class
     * @param newSeatClass
     * @throws Exception could not think of a good type
     */
    public void setSeatClass(final String newSeatClass) throws Exception {
        for (String avaliableSeatClass : avaliableSeatClasses) {
            if(newSeatClass.equalsIgnoreCase(avaliableSeatClass)){
                this.seatClass = newSeatClass;
                break;
            }
        }
        throw new Exception("Could not find seat class");
    }

    public void setSeatCost(final int newSeatCost) {
        this.seatCost = newSeatCost;
    }

    public String seatStatusToString(){
        if(this.seatTaken){
            return "X";
        } else {
            return "O";
        }
    }

    @Override
    public String toString(){
        return "Cost: "+seatCost + "\n"+
                "Meal: "+this.seatMeal;
    }
}