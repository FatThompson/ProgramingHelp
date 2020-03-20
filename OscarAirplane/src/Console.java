import java.util.ArrayList;
import java.util.Scanner;

public class Console {
    final private static String[] AVALIABLE_COMMANDS = {
        "Buy",
        "Help",
        "List",
        "Quit",
        ""
    };
    final private static String LINE_INDICATER = "> ";
    static ArrayList<Airplane> avaliableFlights;

    static Scanner scan;

    public static void main(String[] args) {
        // add the flights in
        avaliableFlights = new ArrayList<Airplane>();
        avaliableFlights.add(new Delta("D231"));
        avaliableFlights.add(new United("U318"));
        scan = new Scanner(System.in);
        
        boolean quit = false;
        display("");
        //show commands
        displayHelp();

        do {
            quit = processInputs(getInput(""));
        } while (quit); 

        scan.close();
    }

    private static String getInput(String prompt) {
        System.out.print(LINE_INDICATER + prompt);
    
        String returnString = new String();
        returnString = scan.nextLine();
        
        return returnString;
    }

    private static boolean confirmInput(String prompt){
        char trueChar = 'y';
        String confirmationString = getInput(prompt+"["+trueChar+"/N]")+"_";
        char confirmationchar = confirmationString.toLowerCase().trim().charAt(0);

        if(confirmationchar == trueChar){
            return true;
        }
        return false;
    }

    private static boolean processInputs(String nextLine) {
        //switch case was acting super buggy soooooo 
        // (added breaks to fix) A litteral break fix 
        //   that being said, ifs can do args to things
        // you can go with if, or you can go with else
        // you can go with if, or you can Go with esle
        // you can go with if, but else: else is where it's at
        switch (nextLine.trim().toLowerCase()){
            case "buy":         
                buyFlights();
                break;
            case "help":
                displayHelp();
                break;
            case "list":
                listFlights();
                break;
            case ("quit"):
                System.exit(0);
            case "":
                display();
                break;
            default:
                displayHelp();
                break;
        }

        //backup return (quit)
        return true;
    }

    /**
     * for proccessing the ticket buying
     */
    private static void buyFlights() {
        String flight = getInput("Which Flight would you Look at to? ");
        int flightscount = avaliableFlights.size();
        Airplane airplane;
        for(int i=0;i<flightscount;i++){
            //set airplane var
            airplane = avaliableFlights.get(i);
            if(airplane.flight.equalsIgnoreCase(flight.trim())){
                listSeats(airplane);
                //gather the input 
                String input = getInput("Which Seat Would you like? ");
                try{
                    Seat seat = new Seat(airplane.getSeat(input));
                    boolean hasmeal=false;

                    if(airplane.checkSeat(seat)){
                        display("Sorry, but this seat is unavaliable");    
                    } else {
                        if(airplane.hasMeals()){
                            if(confirmInput("Would you like a meal? ")){
                                hasmeal=true;
                                seat.hasMeal(true);
                            }
                        }

                        for (String string : seat.toString().split("\n")) {
                            display("   "+string);
                        }
                        if(confirmInput("Does this look correct? ")){
                            airplane.getSeat(input).seatTaken();
                            airplane.getSeat(input).hasMeal(hasmeal);
                            display("Confirmed order");
                        } else display("Canceled Order.");
                    }
                } catch (IllegalArgumentException ial){
                    display("Invalid Input recieved: "+input);
                } catch (IndexOutOfBoundsException iobe){
                    display("Invalid seat entered: "+input);
                } 
                
                //stop at first flight
                i = flightscount;
            }
        }
    }

    /**
     * List all avaliable flights
     */
    private static void listFlights(){
        display("Tickets Avaliable");
        avaliableFlights.forEach(flight -> display("   "+flight.toString()));
    }

    /**
     * List all Seats on a flight
     * 
     * @param airplane
     */
    private static void listSeats(Airplane airplane) {
        //show seats
        display("Seats Avaliable on "+airplane.flight);
        airplane.displaySeats();
    }


    /**
     * Displays the help menu
     */
    private static void displayHelp() {
        // System.out.println(LINE_INDICATER+"Avaliable Commands: ");
        System.out.println("Avaliable Commands: ");
        for (String command : AVALIABLE_COMMANDS) {
            display("   "+command);
        }
    }

    // Overloaded for future use
    private static void display(){
        display("");
    }

    private static void display(String s){
        // System.out.println(LINE_INDICATER+s);
        System.out.println(s);
    }

}