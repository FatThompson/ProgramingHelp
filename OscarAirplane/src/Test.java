//this is a smoke test
//proper testing is still needed! 

public class Test{
    public static void main(String[] args) {
        // General creation
        Airplane airplane = new Airplane();
        airplane.newSeats();

        // test seat things
        Test.seatTesting();

        // test the delta things
        Test.deltaTesting();


    }
    private static void seatTesting(){

        Seat seat = new Seat();
        System.out.println("Seat Taken: "+seat.isSeatTaken());
        seat.seatTaken();
        System.out.println("Seat Taken: "+seat.isSeatTaken());
    }

    private static void deltaTesting() {

        // Create new plane and show
        Airplane delta = new Delta();
        delta.displaySeats();

        //set a seat and show aagin
        delta.getSeat(0,1).seatTaken(true);
        delta.displaySeats();

        delta.getSeat("b 1").seatTaken(true);
        delta.displaySeats();

        delta.getSeat("b 1").seatTaken(true);
        delta.getSeat("e 0").seatTaken();
        delta.getSeat("c 0").seatTaken();
        delta.getSeat("a 0").seatTaken();
        delta.getSeat("a 0").seatTaken();
        delta.displaySeats();
        
        Seat seat_e1 = delta.getSeat("a 1");
        System.out.println("Seat Taken: " +delta.checkSeat(seat_e1));
        seat_e1.seatTaken();
        delta.checkSeat(seat_e1);
        System.out.println("Seat Taken: " +delta.checkSeat(seat_e1));

        System.out.println("Seat Taken: " +delta.checkSeat(delta.getSeat("a 1")));


        //intentional error statments
        try{
            delta.getSeat("! 2");
        } catch (IndexOutOfBoundsException iobe) {
            System.out.println("Catch Successfull: \n"+iobe.toString());
        } catch (Exception e){
            System.out.println("Other Error Occured");
        }

        
        try{
            delta.getSeat("a 12123");
        } catch (IndexOutOfBoundsException iobe) {
            System.out.println("Catch Successfull: \n"+iobe.toString());
        } catch (Exception e){
            System.out.println("Other Error Occured");
        }

        
        try{
            delta.getSeat("a12123");
        } catch (IllegalArgumentException iobe) {
            System.out.println("Catch Successfull: \n"+iobe.toString());
        } catch (Exception e){
            System.out.println("Other Error Occured");
        }

        try{
            delta.getSeat("1 1 1");
        } catch (IllegalArgumentException iobe) {
            System.out.println("Catch Successfull: \n"+iobe.toString());
        } catch (Exception e){
            System.out.println("Other Error Occured");
        }


        

    }
}