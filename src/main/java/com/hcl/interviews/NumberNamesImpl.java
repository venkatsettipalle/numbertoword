package com.hcl.interviews;

import com.hcl.interviews.util.NumberConstants;

public class NumberNamesImpl implements NumberNames{
    /**
     * This public method convert the given number into English standard words and logic as described below
     * Segregate the whole numbers in sets with 3-digit numbers each.
	 * Determine the ones, tens and hundreds of each digit in the sets and put the corresponding word for each.
	 * Determine what set should be the thousand, million, billion, until quintillion, then put the corresponding word.
     * If decimal numbers exist, determine also the tenths and hundredths and put the corresponding word. (If greater than tenths, say thousandths (3-digit value) or millionths (4-digit value), the decimal value is rounded off to hundredths only, that's 2-digit decimal value.)
		Combine the evaluated statements and display the result.
	 * @param number
     * @return String
     */
    @Override
    public String int2Text(int number) {

        if (number == 0) {
        	return NumberConstants.ZERO; 
        }
        
 	   String prefix = "";

        if (number < 0) {
            number = -number;
            prefix = NumberConstants.NEGATIVE;
        }
        
        String current = "";
        int place = 0;
        
        do {
            int n = number % 1000;
            if (n != 0){
                String s = int2TextLessThanOneThousand(n);
                current = s + NumberConstants.SPECIAL_NAMES[place] + current;
            }
            place++;
            number /= 1000;
        } while (number > 0);
        
        return (prefix + current).trim();
    }
    /**
     * This private method reusble and calls when number is less than one thousand
     * @param number
     * @return String
     */
    private String int2TextLessThanOneThousand(int number) {
        String current;
        
        if (number % 100 < 20){
            current = NumberConstants.NUM_NAMES[number % 100];
            number /= 100;
        }
        else {
            current = NumberConstants.NUM_NAMES[number % 10];
            number /= 10;
            
            current = NumberConstants.TENS_NAMES[number % 10] + current;
            number /= 10;
        }
        if (number == 0) return current;
        return NumberConstants.NUM_NAMES[number] + NumberConstants.SPACE+NumberConstants.HUNDRED + current;
    }
    
    public static void main(String[] args) {
        NumberNamesImpl numberNamesImpl = new NumberNamesImpl();
        System.out.println("$$$$$$$ " + numberNamesImpl.int2Text(43112609));
    }
}
