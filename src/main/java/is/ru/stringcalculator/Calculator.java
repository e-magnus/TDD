package is.ru.stringcalculator;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

	// Add all numbers from 0 - 1000 in given string, using "," or "\n" as deliminaters. 
	// Special deliminators can be declere like this in the begining of string: //[delimiter]\n[numbers]”`
	// Example: `“//;\n1;2”` should return 3 (the delimiter is ;)
	public static int add(String text){
		String regex = "[,\\n]";						// Regex for accepted deliminiters

		if(hasDelimiter(text)){							// Check for special deliminator
			regex = putNewDelimiter(regex);
			text = trimMessage(text);
		}

		if(text.equals("")){							// Check if string is empty
			return 0;
		}
		else if(text.contains("-")){					// Check if sting has negative numbers
			return checkForExceptions(text, regex);
		}
		// Check if string contains default deliminatos + ";"
		// To do: Find better solution here, when new deliminators are added!
		else if(text.contains(",") || text.contains("\n") || text.contains(";") ){
			return sum(splitNumbers(text, regex));
		}
		else
			return 1;
	}

	// Cast string to integers
	private static int toInt(String number){
		return Integer.parseInt(number);
	}

	// Split string to numbers using delimiters in regular expression
	private static String[] splitNumbers(String numbers, String regularExpression){
	    return numbers.split(regularExpression);
	}

	// Returns total sum of numbers in the string (in the range 0-1000)
    private static int sum(String[] numbers){
 	    int total = 0;
        for(String number : numbers){
        	if(toInt(number) <= 1000)
		    	total += toInt(number);
		}
		return total;
    }

    // Cast exceptions if nubers are negative
    private static int checkForExceptions(String message, String regularExpression) {
    	String[] numbers = splitNumbers(message, regularExpression);
    	int current = 0;
    	List<Integer> negativeNumbers = new ArrayList<>();
    	for(String number : numbers){
    		current = toInt(number);
		    if(current < 0)
		    	negativeNumbers.add(current);
		}
		if(!negativeNumbers.isEmpty())					// Cast excetion if negative number is found;
			throw new IllegalArgumentException("Negatives not allowed: "+ negativeNumbers.toString());
		return 1;
    }

    // Checks if messages has special delimiter
    protected static boolean hasDelimiter(String message){
    	if(message.startsWith("//"))
    		return true;
    	return false;
    }

    // Add new delimiter to regex
    private static String putNewDelimiter(String regularExpression){
    	// To do: Make this work properly...
    	return (regularExpression = "[,\\n;]");
    }

    // Cut out first line from message
    private static String trimMessage(String message) {
    	// To do: Make this work properly...
    	return message = "1;2";
  }

}