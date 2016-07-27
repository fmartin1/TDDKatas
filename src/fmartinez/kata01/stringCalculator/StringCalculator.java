package fmartinez.kata01.stringCalculator;

import java.util.regex.Pattern;

public class StringCalculator {

	public int Add(String input) {
		int result = 0;
		
		String delimeter = ",|\n";
		String[] numberArray;
		if( input.startsWith("//") ) {
			delimeter = getCustomDelimeterFrom(input.split("\n")[0].substring(2));
			numberArray = input.split("\n")[1].split(delimeter);
		}
		else {
			numberArray = input.split(delimeter);
		}
		
		for(int i=0; i<numberArray.length; i++){
			try {
				int number = Integer.parseInt(numberArray[i]);
				if ( number < 0 )
					throwNegativeNumberException(numberArray);
				else if ( number > 1000 )
					continue;
				else
					result += number;
			} catch (NumberFormatException nfe) { continue; }
		}
		
		return result;
	}

	private String getCustomDelimeterFrom(String string) {
		if ( string.startsWith("[") && string.endsWith("]") ) {
			StringBuilder delimeterGroup = new StringBuilder();
			int i = 0;
			while( i < string.length() ){
				char c = string.charAt(i);
				if( c == '['){
					StringBuilder delimeter = new StringBuilder();
					i++;
					c = string.charAt(i);
					while( c != ']' ){
						delimeter.append(c);
						i++;
						c = string.charAt(i);
					}
					delimeterGroup.append(Pattern.quote(delimeter.toString()));
					delimeterGroup.append("|");
				}
				i++;
			}
			delimeterGroup.deleteCharAt(delimeterGroup.length()-1);
			return delimeterGroup.toString();
		}
		return Pattern.quote(string);
	}

	private void throwNegativeNumberException(String[] numberArray) {
		StringBuilder message = new StringBuilder();
		message.append("negatives not allowed (");
		boolean first = true;
		for ( String number : numberArray ) {
			try{
				if ( Integer.parseInt(number) < 0 ) {
					message.append( (first) ? number:("," + number) ); 
					first=false;
				}
			} catch (NumberFormatException nfe) { continue; }
		}
		message.append(")");
		throw new IllegalArgumentException(message.toString());
	}
	
}
