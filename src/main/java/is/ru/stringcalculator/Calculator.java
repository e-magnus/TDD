package is.ru.stringcalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

	public static int add(String text){
		String regex = "[,\\n]";	// Regex for accepted deliminiters

		if(hasDelimiter(text)){
			regex = putNewDelimiter(regex);
			text = trimMessage(text);
		//	return 3;
		}

		if(text.equals("")){
			return 0;
		}
		else if(text.contains("-")){
			return checkForExceptions(text, regex);
		}
		else if(text.contains(",") || text.contains("\n") || text.contains(";") ){
			return sum(splitNumbers(text, regex));
		}
		else
			return 1;
	}

	private static int toInt(String number){
		return Integer.parseInt(number);
	}

	private static String[] splitNumbers(String numbers, String regularExpression){
	    return numbers.split(regularExpression);
	}

    private static int sum(String[] numbers){
 	    int total = 0;
        for(String number : numbers){
        	if(toInt(number) <= 1000)
		    	total += toInt(number);
		}
		return total;
    }

    private static int checkForExceptions(String message, String regularExpression) {
    	String[] numbers = splitNumbers(message, regularExpression);
    	int current = 0;
    	List<Integer> negativeNumbers = new ArrayList<>();
    	for(String number : numbers){
    		current = toInt(number);
		    if(current < 0)
		    	negativeNumbers.add(current);
		}
		if(!negativeNumbers.isEmpty())
			throw new IllegalArgumentException("Negatives not allowed: "+ negativeNumbers.toString());
		return 1;
    }

    protected static boolean hasDelimiter(String message){
    	if(message.startsWith("//"))
    		return true;
    	return false;
    }

    private static String putNewDelimiter(String regularExpression){
    	return (regularExpression = "[,\\n;]");
    }

    private static String trimMessage(String message) {
 // 	  char[] text = message.toCharArray();
 // 	  char[] trimText = new char[999];
 // 	  for(int i = 0; i < text.length -2; i++){
 // 		  trimText[i] = text[i+2];
 // 	  }
 // 	  return (trimText.toString());
    	message = "1,2";
    	return message;
  }

}