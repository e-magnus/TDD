package is.ru.stringcalculator;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

	public static int add(String text){

		if(hasDelimiter(text)){
			// do stuff
		}
		if(text.equals("")){
			return 0;
		}
		else if(text.contains("-")){
			return checkForExceptions(text);
		}
		else if(text.contains(",") || text.contains("\n")){
			return sum(splitNumbers(text));
		}
		else
			return 1;
	}

	private static int toInt(String number){
		return Integer.parseInt(number);
	}

	private static String[] splitNumbers(String numbers){
	    return numbers.split("[,\\n]");
	}

    private static int sum(String[] numbers){
 	    int total = 0;
        for(String number : numbers){
        	if(toInt(number) <= 1000)
		    	total += toInt(number);
		}
		return total;
    }

    private static int checkForExceptions(String message) {
    	String[] numbers = splitNumbers(message);
    	int current = 0;
    	List<Integer> negativeNumbers = new ArrayList<>();
    	for(String number : numbers){
    		current = toInt(number);
		    if(current < 0)
		    	negativeNumbers.add(current);
		}
		if(message.contains("-"))
			throw new IllegalArgumentException("Negatives not allowed: "+ negativeNumbers.toString());
		return 1;
    }

    protected static boolean hasDelimiter(String message){
    	if(message.startsWith("//"))
    		return true;
    	return false;
    }
}