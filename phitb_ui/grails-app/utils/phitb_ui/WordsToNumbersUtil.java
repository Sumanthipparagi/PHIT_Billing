package phitb_ui;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Testing algorithm to convert words in paragraph representing numbers into
 * standard digit representation
 *
 * Note that currently all formatting and occurrences of "and" are getting
 * stripped out. Not needed for current purposes
 *
 * e.g. Input: "There were twenty five coins" Output: "there were 25 coins"
 *
 */
public class WordsToNumbersUtil {

    public static final String[] units = { "", "One", "Two", "Three", "Four",
            "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve",
            "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
            "Eighteen", "Nineteen" };

    public static final String[] tens = {
            "", 		// 0
            "",		// 1
            "Twenty", 	// 2
            "Thirty", 	// 3
            "Forty", 	// 4
            "Fifty", 	// 5
            "Sixty", 	// 6
            "Seventy",	// 7
            "Eighty", 	// 8
            "Ninety" 	// 9
    };

    public static String convert(final int n) {
        if (n < 0) {
            return "Minus " + convert(-n);
        }

        if (n < 20) {
            return units[n];
        }

        if (n < 100) {
            return tens[n / 10] + ((n % 10 != 0) ? " " : "") + units[n % 10];
        }

        if (n < 1000) {
            return units[n / 100] + " Hundred" + ((n % 100 != 0) ? " " : "") + convert(n % 100);
        }

        if (n < 100000) {
            return convert(n / 1000) + " Thousand" + ((n % 10000 != 0) ? " " : "") + convert(n % 1000);
        }

        if (n < 10000000) {
            return convert(n / 100000) + " Lakh" + ((n % 100000 != 0) ? " " : "") + convert(n % 100000);
        }

        return convert(n / 10000000) + " Crore" + ((n % 10000000 != 0) ? " " : "") + convert(n % 10000000);
    }
public static String convertToIndianCurrency(String num) {
    BigDecimal bd = new BigDecimal(num);
    long number = bd.longValue();
    long no = bd.longValue();
    int decimal = (int) (bd.remainder(BigDecimal.ONE).doubleValue() * 100);
    int digits_length = String.valueOf(no).length();
    int i = 0;
    ArrayList<String> str = new ArrayList<>();
    HashMap<Integer, String> words = new HashMap<>();
    words.put(0, "");
    words.put(1, "One");
    words.put(2, "Two");
    words.put(3, "Three");
    words.put(4, "Four");
    words.put(5, "Five");
    words.put(6, "Six");
    words.put(7, "Seven");
    words.put(8, "Eight");
    words.put(9, "Nine");
    words.put(10, "Ten");
    words.put(11, "Eleven");
    words.put(12, "Twelve");
    words.put(13, "Thirteen");
    words.put(14, "Fourteen");
    words.put(15, "Fifteen");
    words.put(16, "Sixteen");
    words.put(17, "Seventeen");
    words.put(18, "Eighteen");
    words.put(19, "Nineteen");
    words.put(20, "Twenty");
    words.put(30, "Thirty");
    words.put(40, "Forty");
    words.put(50, "Fifty");
    words.put(60, "Sixty");
    words.put(70, "Seventy");
    words.put(80, "Eighty");
    words.put(90, "Ninety");
    String digits[] = {"", "Hundred", "Thousand", "Lakh", "Crore"};
    while (i < digits_length) {
        int divider = (i == 2) ? 10 : 100;
        number = no % divider;
        no = no / divider;
        i += divider == 10 ? 1 : 2;
        if (number > 0) {
            int counter = str.size();
            String plural = (counter > 0 && number > 9) ? "s" : "";
            String tmp = (number < 21) ? words.get(Integer.valueOf((int) number)) + " " + digits[counter]  : words.get(Integer.valueOf((int) Math.floor(number / 10) * 10)) + " " + words.get(Integer.valueOf((int) (number % 10))) + " " + digits[counter] ;
            str.add(tmp);
        } else {
            str.add("");
        }
    }

    Collections.reverse(str);
    String Rupees = String.join(" ", str).trim();

//    String paise = (decimal) > 0 ? " And Paise " + words.get( (decimal - decimal % 10)) + " " + words.get( (decimal % 10)) : "";
    String paise = (decimal) > 0 ? " And Paise " + convert(decimal) : "";
    if(!Rupees.equals("") || !paise.equals(""))
    {
        return "Rupees " + Rupees + paise + " Only";
    }
    else
    {
        return "Rupees " + "Zero and " + " Zero " + "paise  Only";
    }
}
}