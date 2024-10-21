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
