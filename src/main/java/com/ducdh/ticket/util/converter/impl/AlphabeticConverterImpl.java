package com.ducdh.ticket.util.converter.impl;

import com.ducdh.ticket.util.converter.AlphabeticConverter;

public class AlphabeticConverterImpl implements AlphabeticConverter {

    private static final int DIGIT = (int) 'A';
    private static final int DIGITS = (int) 'A' - 1;

    @Override
    public int toNumeric(String alphabetic) {
        char[] numbers = alphabetic.toCharArray();
        int result = 0;
        int count = 0;
        for (int i = numbers.length - 1; i >= 0; i--) {
            if (i == numbers.length - 1) {
                result = ((int) numbers[i]) - DIGIT;
            } else {
                result += ((int) numbers[i] - DIGITS) * Math.pow(26, count);
            }
            count++;
        }
        return result;
    }

    @Override
    public String toAlphabetic(int numeric) {
        if(numeric < 0) {
            throw new RuntimeException("Negative integer is not supported");
        }

        int quot = numeric / 26;
        int rem = numeric % 26;
        char letter = (char) ((int) 'A' + rem);
        if(quot == 0) {
            return "" + letter;
        } else {
            return toAlphabetic(quot - 1) + letter;
        }
    }
}
