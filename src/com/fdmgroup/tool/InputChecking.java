package com.fdmgroup.tool;

public class InputChecking {

    public static final String numbersRegex = "^[0-9]+$";
	/**
	 * Return True if the string is empty.
	 * @param stringName Any string
	 * @return Boolean 
	*/
	static public boolean emptyCheck(String stringName) {
		if (stringName == null || stringName.isEmpty()) { 
			return true;
		}
		return false;
	}
	
	
	/**
	 * 
	 * @param string Any string 
	 * @param min The minimum number of characters in string
	 * @param max The maximum number of characters in string
	 * @return Boolean Return True if the length of string is between min and max (inclusive).
	*/
	static public boolean checkLength(String string, int min, int max) {
		if (string.length() < min || string.length() > max) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param pw Any string 
	 * @return Boolean Return True if the password is Valid.
	*/
	static public boolean checkPassword(String pw) {
		if (emptyCheck(pw) || !checkLength(pw, 6, 40) || !(pw.matches("[A-Za-z0-9!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~]]+"))) {
			return false;
		}
		return true;
	}
	
	
	
	
}
