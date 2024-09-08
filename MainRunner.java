import java.util.Scanner;

public class MainRunner{
    //static main scanner
    static Scanner scanner = new Scanner(System.in);
    //Holds value for current day in program
    static Calandar currentDay = new Calandar(1, 1, 2024);
    
    //Information about commands
    final static String commandsList = "changeDate - changes current day\n"
                           +"getCurrentDay - prints current day in terminal\n"
                           +"exit - exits program\n"
                           +"ls - list of commands";
    //main
    public static void main(String[] args) {
        
        String promptString = "commandLine"; //commandLine string
        
        System.out.println("Welcome to SoftEng Health Club,"
                           +" you can see a list of commands by typing 'ls'"
                           +" and you can exit by entering 'exit'."); //starting information
        while(true){ //while loop that keeps program running
            System.out.print(promptString+": "); //prints commandLine(allows for changes in the future)
            String input = scanner.nextLine(); //accepts input from the commandLine
            if (input.equalsIgnoreCase("exit")) { //checks for exit input
                break; // Exit the loop
            } else if(input.equalsIgnoreCase("changeDate")){ //checks for changeDate input
                int month=monthValidator();//gets valid month
                int day=dayValidator(month);//gets valid day
                int year=yearValidator();//gets valid year
                Calandar tempDay = new Calandar(month, day, year); //forms a new calandar with the date inputted
                currentDay=tempDay; //set currentDay calandar equal to new caladar
                System.out.println("updated current day");//tells user that there has been a succsessful update
            } else if(input.equalsIgnoreCase("getCurrentDay")){ //checks for getCurrentDay input
                System.out.println(""+currentDay.month+"/"+currentDay.day+"/"+currentDay.year); //prints current day
            } else if(input.equalsIgnoreCase("ls")){
                System.out.println(commandsList);
             } else { //error statement
                System.out.println("Invalid command. Try again.");
            }
        }
    }

    public static int yearValidator(){ 
        int year=0;
        while(true){ //while loop until valid year input
            System.out.print("What Year do you want: ");
            if(scanner.hasNextInt()){ //checks for int input
                int x = scanner.nextInt();
                if(0<x&&x<3000){ //checks for valid year
                    year=x;
                    scanner.nextLine();
                    break;
                } else {//clears and gives error message
                    System.out.println("Must be between 1-3000");
                    scanner.nextLine();
                }
            } else { //clears scanner and gives error message
                System.out.println("Not a number");
                scanner.nextLine();
            }
        }
        return year;
    }

    public static int dayValidator(int month){
        int day=0;
        int maxDay=0;
        if(month==4||month==6||month==9||month==11){ //searches for maxDay given the month inputted
            maxDay=30;
        } else if(month==2){
            maxDay=28;
        } else {
            maxDay=31;
        }
        while(true){
            System.out.print("What day do you want: ");
            if(scanner.hasNextInt()){
                int x = scanner.nextInt();
                if(0<x&&x<maxDay+1){ //checks for valid month
                    day=x;
                    scanner.nextLine();
                    break;
                } else { //clears scanner and gives error message
                    System.out.println("Must be between 1-"+maxDay);
                    scanner.nextLine();
                }
            } else { //clears scanner and gives error message
                System.out.println("Not a number");
                scanner.nextLine();
            }
        }
        return day;
    }

    public static int monthValidator(){
        int month=0;
        while(true){
            System.out.print("What month do you want: ");
            if(scanner.hasNextInt()){
                int x = scanner.nextInt();
                if(0<x&&x<13){ //checks for valid month
                    month=x;
                    scanner.nextLine();
                    break; 
                } else { //clears scanner and gives error message
                    System.out.println("Must be between 1-12");
                    scanner.nextLine();
                }
            } else { //clears scanner and gives error message
                System.out.println("Not a number");
                scanner.nextLine();
            }
        }
        return month;
    }

}