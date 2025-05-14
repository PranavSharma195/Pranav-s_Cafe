package com.cafeapp.util;

import java.util.regex.Pattern;

/**
 * @author Pranav_Sharma LMU ID: 23048577 Utility class for validating user
 *         input fields such as strings, emails, phone numbers, and passwords.
 */

public class ValidationUtil {

	/**
	 * Checks if the given string is null or empty (after trimming).
	 *
	 * @param value The string to check.
	 * @return {@code true} if the string is null or empty, otherwise {@code false}.
	 */
	public static boolean isNullOrEmpty(String value) {
		return value == null || value.trim().isEmpty();
	}

	/**
	 * Checks if the given string contains only alphabetic characters (a–z, A–Z).
	 *
	 * @param value The string to check.
	 * @return {@code true} if the string contains only letters, otherwise
	 *         {@code false}.
	 */
	public static boolean isAlphabetic(String value) {
		return value != null && value.matches("^[a-zA-Z]+$");
	}

	/**
	 * Validates if the string starts with a letter and is followed by letters or
	 * digits only.
	 *
	 * @param value The string to validate.
	 * @return {@code true} if the string matches the pattern, otherwise
	 *         {@code false}.
	 */
	public static boolean isAlphanumericStartingWithLetter(String value) {
		return value != null && value.matches("^[a-zA-Z][a-zA-Z0-9]*$");
	}

	/**
	 * Validates if the given string is a valid email address.
	 *
	 * @param email The email string to validate.
	 * @return {@code true} if the string is a valid email format, otherwise
	 *         {@code false}.
	 */
	public static boolean isValidEmail(String email) {
		String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
		return email != null && Pattern.matches(emailRegex, email);
	}

	/**
	 * Validates if the phone number starts with "98" and is exactly 10 digits long.
	 *
	 * @param number The phone number string to validate.
	 * @return {@code true} if the number is valid, otherwise {@code false}.
	 */
	public static boolean isValidPhoneNumber(String number) {
		return number != null && number.matches("^98\\d{8}$");
	}

	/**
	 * Validates if the password contains at least one uppercase letter, one digit,
	 * one special character, and is at least 8 characters long.
	 *
	 * @param password The password string to validate.
	 * @return {@code true} if the password meets all criteria, otherwise
	 *         {@code false}.
	 */
	public static boolean isValidPassword(String password) {
		String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
		return password != null && password.matches(passwordRegex);
	}

	/**
	 * Checks if the password and retyped password match exactly.
	 *
	 * @param password       The original password.
	 * @param retypePassword The retyped password to compare.
	 * @return {@code true} if both strings match, otherwise {@code false}.
	 */
	public static boolean doPasswordsMatch(String password, String retypePassword) {
		return password != null && password.equals(retypePassword);
	}

}