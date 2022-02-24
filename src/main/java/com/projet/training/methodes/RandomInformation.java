package com.projet.training.methodes;

import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomInformation {
	
	public static String randomString() {
		String abcd = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String randomString = "";
		
		Random rand = new Random();
		
		int low = 3;
		int high = 15;
		int size = rand.nextInt(high-low) + low;
		
		char[] text = new char[size];
		
		for(int i = 0; i < size; i++) {
			text[i] = abcd.charAt(rand.nextInt(abcd.length()));
		}
		
		for(int i = 0; i < text.length; i++) {
			randomString += text[i];
		}
		
		return randomString;
	}
	
	public static LocalDate randomDate() {
		long start = LocalDate.of(1970, 1, 1).toEpochDay();
	    long end = LocalDate.of(2000, 1, 1).toEpochDay();
	    long randomDay = ThreadLocalRandom.current().nextLong(start, end);
	    LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
	    
	    return randomDate;
	}
}
