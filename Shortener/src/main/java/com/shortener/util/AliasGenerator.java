package com.shortener.util;

import java.util.Random;

public class AliasGenerator {
	private static final String ALPHABET = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_-";
	private static final int BASE = ALPHABET.length();
	private static Random random = new Random();
	
	public static String generate(int length) {
		StringBuilder alias = new StringBuilder();
		
		for (int i = 0 ; i < length ; i++) {
			int charIndex = random.nextInt(BASE);
			alias.append(ALPHABET.charAt(charIndex));
		}
		
		return alias.toString();
	}
}
