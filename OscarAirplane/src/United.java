/**
 * yay inheridence
 * (instead of a config file or something similar)
 */
public class United extends Airplane{
    public United(){
        defaults("Unnamed United Flight");
    }    
    public United(String flightName){
        defaults(flightName);
    }

    private void defaults(String flightName){
        super.rows = 10;
        super.columns = 4;
        super.seatClasses = new String[]{"Economy","Buisness"};
        super.company = "United";
        super.flight = flightName;
        super.hasMeals(false);
        super.newSeats();
    }
}