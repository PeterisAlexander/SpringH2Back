package com.projet.training.utils;

import static java.time.LocalDate.ofEpochDay;
import static org.apache.commons.lang3.RandomStringUtils.random;

import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomInformation {
	
	public static String randomString() {
		Random rd = new Random();
		String str = random(rd.nextInt(10), true, false);
		
		if(str == "" || str == " " || str.length() < 3) {
			str = randomString();
		}
		return str;
	}
	
	public static LocalDate randomDate() {
		long start = LocalDate.of(1970, 1, 1).toEpochDay();
	    long end = LocalDate.of(2000, 1, 1).toEpochDay();
	    long randomDay = ThreadLocalRandom.current().nextLong(start, end);
	    LocalDate randomDate = ofEpochDay(randomDay);

	    return randomDate;
	}
}
