package util;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.ValidationErrorList;
import org.owasp.esapi.Validator;

public class Validation {

	public static String validatePersonName(String input) {

		Validator validator = ESAPI.validator();
		try {
			String clean = validator.getValidInput("Name", input.trim(), "PersonName", 32, false);
			return clean;
		} catch (Exception e) {
			return null;
		}
	}

	public static String validateCCNumber(String input) {

		Validator validator = ESAPI.validator();
		try {
			String clean = validator.getValidCreditCard("CreditCard", input.trim(), false);
			return clean;
		} catch (Exception e) {
			return null;
		}
	}

	public static String validateCCNumber(String input, ValidationErrorList errors) {

		Validator validator = ESAPI.validator();
		String clean = validator.getValidCreditCard("CreditCard", input.trim(), false, errors);
		if (errors.isEmpty()) {
			return clean;
		} else {
			return null;
		}
	}
	
	public static String validateProductCode(String input) {

		// Implement...
    	return null;
	}

	public static String validateProductDescription(String input) {

		// Implement...
    	return null;
	}

	public static String validateProductPrice(String input) {

		// Implement...
    	return null;
	}
}
