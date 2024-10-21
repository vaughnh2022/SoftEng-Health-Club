/*
 * This is the class that organizes the users command string and sends it to the next class.
 * 
 * This class seperates the first word of this command to interpret next action
 * 
 * This class returns a string that gets printed into the Main Terminal Window
 */
public class InputReaderClass {    
    public static String firstInputReader(String command){
        String returnString = "";
        int a=0;
        while(a<command.length()&&command.charAt(a)!=' '){
            a++;
        }
        String firstCommand=command.substring(0, a);
        String commandLeftovers;
        if(a<command.length()){
            commandLeftovers = command.substring(a+1);
        } else {
            commandLeftovers="";
        }
        returnString=secondInputReader(firstCommand,commandLeftovers);
        return returnString;
    }
    public static String secondInputReader(String firstCommand,String commandLeftovers){
        return "first command is "+ firstCommand+" and leftovers is " + commandLeftovers;
    }
}
