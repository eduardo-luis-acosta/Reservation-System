import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Thread;

public class Reservation {

    //admin variables
    static boolean TERMINATION = false;
    static final String TERMcode = "ShutDown404";
    static final String ADMINpass = "teddie.Admin.bypass";

    //hotel variables
    private final static ArrayList<Reservation> guest = new ArrayList<>(10);

    //guest variables
    public static Scanner user = new Scanner(System.in);
    private String lastN;
    private String firstN;
    private int numPeople;
    private boolean VIP = false;
    

    //constructors
    public Reservation(String firstN, String lastN, int numPeople, boolean VIP) {
        this.firstN = firstN;
        this.lastN = lastN;
        this.numPeople = numPeople;
        this.VIP = VIP;
    }
    public Reservation() {
        this.firstN = "OPEN";
        this.lastN = "SLOT";
    }

    //retriever methods
    public String getFirst() {
        return firstN;
    }
    public String getLast() {
        return lastN;
    }
    public int NumberOfPeople() {
        return numPeople;
    }
    public int getRoomNum(Reservation info) {
        return guest.indexOf(info);
    }
    public boolean getVIPstatus() {
        return VIP;
    }

    //modifier methods
    public void setFirst(Reservation info, String newFirst) {
        info.firstN = newFirst;
        System.out.println("\nCurrent First Name: " + info.toString());

        System.out.println("\n-------------------------------");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        requestPathway();
    }
    public void setLast(Reservation info, String newLast) {
        info.lastN = newLast;
        System.out.println("\nCurrent Last Name: " + info.toString());

        System.out.println("\n-------------------------------");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        requestPathway();
    }
    public void changeVIP (Reservation info) {

        if(info.VIP) {
            info.VIP = false;
        } else {
            info.VIP = true;
        }

        String statusToString = "";
        if(!info.VIP) {
            statusToString = "regular";
        } else {
            statusToString = "\u001B[33m" + "VIP" + "\u001B[37m";
        }

        System.out.println("\nYou're now listed as " + statusToString + " member status!");

        System.out.println("\n-------------------------------");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        requestPathway();

    }
    public void numGuestsChange(Reservation info, boolean arithmetic) {
        
        //number of new guests that the user requested
        int newAmount = 0;

        //if addsub = true, add new guests to current amount
        //if addsub = false, subtract new guests from the current amount
        if(arithmetic){
            System.out.print("\nPlease State How Many More Guests You Would Like To Add: ");
            int moreGuests = user.nextInt();
            newAmount = info.numPeople + moreGuests;
        } else {
            System.out.print("\nPlease State How Many Guests You Would Like To Remove From Your Reservation: ");
            int lessGuests = user.nextInt();
            newAmount = info.numPeople - lessGuests;
        }

        //set number of people to new amount 
        info.numPeople = newAmount;
        
        //if number of people is less than 0, set to 1
        if(info.numPeople < 0) {
            info.numPeople = 1;
        }

        System.out.println("\nYour reservation is currently holding " + info.numPeople + " guests!");
        requestPathway();
    }


    //main
    public static void main (String args[]) {

        //fill arraylist of blank reservation objects
        for(int l = 0; l < 10; l++){
            Reservation info = new Reservation();
            guest.add(info);
        }

        //creates an object to call methods, not for any other use
        Reservation caller = new Reservation();

        //welcome to prompt
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\nWelcome To The Teddie Hotel!");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("Please Type The # Of Reservations You Want To Register (MAX: 10): ");



        //calls newGuest method
        //cast test case variable in bounds (1-10)
                //the amount of reservations user wants
        double input = user.nextDouble();
        int a = (int) Math.round(input);
        if(a < 1){
            a = 1;
        }
        if(a > 10){
            a = 10;
        }    

        caller.newGuest(a);

        System.out.print("\n-------------------------------");

        //calls requestPathway method
        caller.requestPathway();
    }
    
    //creates and appends new reservations
    public void newGuest(int a) {

        System.out.println("\n-------------------------------");

        /*  
            creates new guest object
            creates user-defined object
            prints guest information
        */
        for(int i = 0; i < a; i++) {
            user.nextLine();
            
            //asking user for object parameters
            //first name
            System.out.print("\nFirst Name: ");
            String f = user.nextLine();

            //last name
            System.out.print("Last Name: ");
            String l = user.nextLine();

            //people/reservation
            System.out.print("# Of Guests: ");
            int np = user.nextInt();

            //asks for user requests VIP room status
            System.out.print("Are You A VIP (Yes/No)? ");
            boolean vip = false;   
            user.nextLine();
            if(user.nextLine().equalsIgnoreCase("Yes")) {
                vip = true;
            }
            
            
            System.out.println("\n-------------------------------");


            //creates user-define object
            Reservation info = new Reservation(f, l, np, vip);
            //searches for the next available spot (available spots have the name "Jimmy Dean")
            int c = -1;
            int index = 0;
            for(c = -1; c < index; c++){
                if(!(guest.get(index).getLast().equals("SLOT"))){
                    index++;
                }
            }
            guest.set(c, info);

            //prints information given
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("\nReservation Name: " + "\u001b[35m" + info.firstN +  ", " + info.lastN + "\u001b[0m");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("\u001b[0m" + "Number Of Guests: " + "\u001b[35m" + info.numPeople);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("\u001b[0m" + "Status: " + "\u001b[35m" + info.VIP);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("\u001b[0m" + "Room #: " + "\u001b[35m" + info.getRoomNum(info));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("\u001b[0m" + "\n-------------------------------");
            
        }

        System.out.println("\nCurrent Hotel Residents: ");
        //prints guest arraylist after all reservations
        for(int i = 0; i < guest.size(); i++) {    
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(guest.get(i).toString());
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.print("\n-------------------------------");

        requestPathway();
    }
    
    //print out guest array list
    //every guest's first and last name and their VIP status
    public String toString() {

        //prints regular if VIProom = false
        //prints VIP if VIProom = true
        String VIPTT;
        if(!VIP) {
            VIPTT = "Regular" ;
        } else {
            VIPTT = "\u001b[33m" + "VIP" + "\u001b[0m";
        }

        //return text
        return firstN + " " + lastN +  " (" + VIPTT + ")";
    }

    //pathway to alter a reservation guest list
    public void requestPathway() {

        Reservation caller = new Reservation();

        System.out.println("\n\nList Of Possible Reservation Requests: ");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" - Create");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" - Change");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" - Remove");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("\nRequest: ");
        
        String userRequest = user.nextLine();

        //add reservation to the list
        if(userRequest.equalsIgnoreCase("Create")) {

            System.out.println("\n-------------------------------");

            //cast test case variable in bounds (1-10)
            //the amount of reservations user wants
            System.out.print("\nPlease Type The # Of Reservations You Want To Register : ");
            double input = user.nextDouble();
            int a = (int) Math.round(input);
            if(a < 1){
                a = 1;
            }
            if(a > 10){
                a = 10;
            }    

            caller.newGuest(a);

        }

        //remove object at user designated index
        if(userRequest.equalsIgnoreCase("Remove")) {

            System.out.println("\n-------------------------------");

            System.out.println("\nPlease State Which Reservation (LAST NAME) You Would Like To Remove.");
            
            //prints arraylist as column
            System.out.print("\n");
            for(int i = 0; i < guest.size(); i++){
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(guest.get(i).toString());
            }
            
            System.out.print("\nLast Name: ");
            
            String userRequestName = user.nextLine();
            caller.removal(userRequestName);
        }
        
        //change existing reservation
        if(userRequest.equalsIgnoreCase("Change")) {

            System.out.println("\n-------------------------------");
            
            System.out.print("\n");
            for(int i = 0; i < guest.size(); i++){
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(guest.get(i).toString());
            }

            System.out.print("\nPlease State Your Reservation (LAST NAME ONLY): ");
            String username = user.nextLine();
            
            //loops to find user inputed reservation 
            for(int i = 0; i < guest.size(); i++){
                boolean found = false;
                if(username.equalsIgnoreCase(guest.get(i).getLast())) {
                    found = true;
                }
                //requests what changes they would like to make to specified reservation
                if(found) {
                    for(int j = 0; j < 1; j++) {
                        
                        System.out.println("\n-------------------------------");

                        System.out.println("\nGuest Accessed: " + guest.get(i).toString());

                        System.out.println("\nWhat Would You Like To Change?");
                            System.out.println(" - First Name");
                            System.out.println(" - Last Name");
                            System.out.println(" - Status");
                            System.out.println(" - # Of Guests");

                        System.out.print("\nResponse: ");
                        String userRequestV3 = user.nextLine();
                        
                        System.out.println("\n-------------------------------");

                        if(userRequestV3.equalsIgnoreCase("First Name")){

                            System.out.print("\nPlease State Your New First Name: ");
                            String newFirst = user.nextLine();

                            caller.setFirst(guest.get(i), newFirst);
                        }
                    
                        if(userRequestV3.equalsIgnoreCase("Last Name")){

                            System.out.print("\nPlease State Your New Last Name: ");
                            String newLast = user.nextLine();

                            caller.setLast(guest.get(i), newLast);
                        }
                    
                        if(userRequestV3.equalsIgnoreCase("Status")) {

                            String statusToString = "regular";
                            if(guest.get(j).VIP) {
                                statusToString = "\u001B[33m" + "VIP" + "\u001B[37m";
                            }

                            System.out.print("\nYou were previously listed as " + statusToString + " member status." );
                            caller.changeVIP(guest.get(i));

                        }

                        if(userRequestV3.equalsIgnoreCase("# Of Guests")){

                            System.out.println("\nCurrent Number Of Guests: " + guest.get(i).NumberOfPeople());

                            System.out.println("\nIncrease Or Decrease?");
                            System.out.print("Response: ");
                            String math = user.nextLine();
                            
                            boolean arithmetic = false;
                            if(math.equalsIgnoreCase("Increase")){
                                arithmetic = true;
                            } else {
                                arithmetic = false;
                            }

                            guest.get(j).numGuestsChange(guest.get(i), arithmetic);

                            requestPathway();
                        }
                    }    
                }
            }   
        }

        //not listed in a request list
        //allows admintrative access in program if correct password is inputed
        if(userRequest.equals("ADMIN")) {
            
            System.out.println("\n-------------------------------");
            
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("\nAdminstration Terminal : Pending...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("Administrative Password: ");
            
            if(user.nextLine().equals(ADMINpass)){
                termination();
            }
        } 
    }

    //removes reservations
    public void removal(String userRequestName) {
        
        System.out.println("\n-------------------------------");

        //loops to find a reservation object using the last name
        //remove a found object
        for(int i = 0; i < guest.size(); i++){
            int j = 0;
            for(j = 0; j < guest.size(); j++) {
                if(guest.get(j).getLast().equalsIgnoreCase(userRequestName)) {
                    guest.remove(j);
                    j = 0;
                }
            }
        }
        
        //prints modified arraylist
        System.out.println("\nReservation " + "\u001b[31m" + "REMOVED" + "\u001b[37m");

         //prints arraylist as column
        if(guest.size() < 10){
            Reservation info = new Reservation();
            guest.add(info);
        }
        
        System.out.println("\nUpdated Guest List: ");
        for(int i = 0; i < guest.size(); i++){
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(guest.get(i).toString());
        }
        
        System.out.println("\n-------------------------------");
        
        requestPathway();
    }

    //termination command (ADMIN ONLY)
    public static void termination() {

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\nAdministrative Terminal: " + "\u001b[32m" + "ACTIVE" + "\u001b[0m");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("--> ADMINSTRATIVE CONSOLE COMMAND: " );

        //checks if admin request termination of program
        while(!TERMINATION) {
            String AdminInput = user.nextLine();
            if(AdminInput.equals(TERMcode)){
                TERMINATION = true; 
            }
            if(!(AdminInput.equals(TERMcode))){
                TERMINATION = false;

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("\nSystem Shut Down: " + "\u001b[31m" + "UNSUCCESSFUL" + "\u001b[0m");

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print("--> ADMINISTRATIVE CONSOLE COMMAND: ");
            }
        }
        
        //terminates if TRUE
        if(TERMINATION){

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("\nSystem Shut Down : " + "\u001b[32m" + "SUCCESSFUL" + "\u001b[0m");
            System.exit(0);
        }

    }
}