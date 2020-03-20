

/**
 * Class: Airplane
 * Goal: parent for seats
 * 
 * 
 */
public class Airplane{
    /*
    * Globals
    */
    protected int rows;
    protected int columns;
    protected String[] seatClasses;
    protected String company,flight;
    private boolean hasMeals = false; //assumed no
    private Seat[][] seats;
    private final char[] columnNames = { 'A','B','C','D','E','F',
                                        'G','H','I','J','K','L',
                                        'M','N','O','P','Q','R',
                                        'S','T','U','V','W','X',
                                        'Y','Z' };

 
                                        
    /**
     * sets the has meals var
     * @param newHasMeals
     */
    public void hasMeals(final boolean newHasMeals){
        this.hasMeals = newHasMeals;
    } 
    /**
     * returns hassmeals 
     * @return hasmeals
     */
    public boolean hasMeals(){
        return this.hasMeals;
    }

    /**
     * look through and create a seat in each seat spot
     */
    public void newSeats(){
        if(rows>0 && columns > 0 )
        seats = new Seat[rows][columns];
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                // super not effecient, but meh
                if(null == seatClasses){
                    seats[i][j] = new Seat();
                } else { 
                    seats[i][j] = new Seat(seatClasses);
                }
            }
        }
    }
    /**
     * returns the full array of seats
     * @return seats
     */
    public Seat[][] getSeats(){
        return this.seats;
    }

    /**
     * Get a seat based on "letter number"
     * @param letternumber letter space number
     * @return Seat selected if exists
     */
    public Seat getSeat(final String letternumber){
        if(letternumber.contains(" ")){
            // vars 
            final int defaultNumber = -1;
            final char defaultChar = '~';
            int seatNumber = defaultNumber;
            int seatLetterNumber = defaultNumber;
            char seatLetter = defaultChar;

            //split the array by space 
            String[] letternumberarray = letternumber.split(" ");
            for (String string : letternumberarray) {
                try{
                    if(seatNumber == defaultNumber){   
                        seatNumber = Integer.parseInt(string.trim());
                    } 
                } catch (java.lang.NumberFormatException e) {
                    if(defaultChar == seatLetter) {
                        // get the first char and find the seat column
                        char stringchar = string.charAt(0);
                        seatLetter = stringchar;
                        seatLetterNumber = getColumn(seatLetter);
                    }
                }

                if(seatNumber != defaultNumber && defaultChar != seatLetter){
                    // return the seat
                    return getSeat(seatNumber,seatLetterNumber);
                }
            }
            //throw nullvalues here because getSeat was never run
            throw new IllegalArgumentException("Invalid input recieved");

        } else 
            throw new IllegalArgumentException("Requires 'Letter[space]number'");
        
    }

    /**
     * get a seat and if it is taken or not 
     * @param seat
     * @return boolean is taken
     */
    public boolean checkSeat(Seat seat){
        return seat.isSeatTaken();
    }

    /**
     * get a seat and if it is taken or not 
     * @param Seat
     * @return boolean is taken
     */
    public boolean checkSeat(String seat){
        return checkSeat(getSeat(seat));
    }

    /**
     * get a seat and if it is taken or not 
     * @param row
     * @param letter
     * @return boolean is taken
     */
    public boolean checkSeat(int introw, int intletter){
        return checkSeat(getSeat(introw,intletter));
    }

    /**
     * get a single seat. Need row and colum
     * @param row
     * @param column
     * @return seat
     */
    public Seat getSeat(final int row,final int column){
        if( row >= 0 && 
            row < rows && 
            column >=0 && 
            column < columns) {

            final Seat seat = this.seats[row][column];
            return seat;
        } else {
            throw new IndexOutOfBoundsException("Seat Not Found at"+
                                                " Row: "+ row +
                                                " Column: "+column);
        }
    }

    /**
     * get the column letter assosiation
     * @return column
     */
    public int getColumn(char columnByLetter){
        columnByLetter = Character.toUpperCase(columnByLetter);
        for(int i=0; i<columnNames.length;i++){
            if(columnByLetter == columnNames[i]){
                return i;
            }
        }
        throw new IndexOutOfBoundsException("Could not find Column: "+columnByLetter);
    }
    
    /**
     * Displays the seats on the current plane (just the taken value)
     */
    public void displaySeats(){
        System.out.print("  ");
        for(int i=0;i<columns;i++){
            System.out.print(columnNames[i]+" ");
        } System.out.println("");
        
        int i=0;
        for (final Seat[] row : seats) {
            System.out.print(i++ + " ");
            for (final Seat seat : row){
                System.out.print(seat.seatStatusToString()+" ");
            } System.out.println("");
        } System.out.println("");

    }


    @Override
    public String toString(){
        return "Flight: " + flight
            + ", Tickets Avaliable: " + ticketsAvaliable();
    }

    private int ticketsAvaliable() {
        int totalAvaliableSeats=0;
        for (Seat[] seat : seats) {
            for (Seat seat2 : seat) {
                if(!seat2.isSeatTaken()){
                    totalAvaliableSeats++;
                }
            }
        }
        return totalAvaliableSeats;
    }

}