
/**
 * yay inheridence
 */
public class Delta extends Airplane{

    public Delta(){
        defaults("Unnamed Delta Flight");
    }
    
    public Delta(String flightName){
        defaults(flightName);
    }

    private void defaults(String flightName){
        super.rows = 2;
        super.columns = 5;
        super.seatClasses = new String[]{"Economy","Buisness","First"};
        super.company = "Delta";
        super.flight = flightName;
        super.hasMeals(true);
        super.newSeats();
    }

}