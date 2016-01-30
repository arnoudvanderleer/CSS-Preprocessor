package com.tempestasludi.java.p14_cssp.pcss.general;

/**
 * Parser provides general parsing functions.
 *
 * @author Tempestas Ludi
 */
public final class Parser {

	public static int searchBracket(int i, String input) {
		int n = 1;
		char start = input.charAt(i);
		char stop = (char) (start + 1);
		switch (start) {
		case '(':
			stop = ')';
			break;
		case '[':
			stop = ']';
			break;
		case '{':
			stop = '}';
			break;
		default:
			return i;
		}
		i++;
		while (i < input.length() && n > 0) {
			if (input.charAt(i) == start) {
				n++;
			} else if (input.charAt(i) == stop) {
				n--;
			} else if (input.charAt(i) == '"' || input.charAt(i) == '\'') {
				i = searchStringEnd(i, input);
			}
			i++;
		}
		return i - 1;
	}

	public static int searchStringEnd(int i, String input) {
		char start = input.charAt(i);
		while (i < input.length()) {
			i++;
			if (input.charAt(i) == start) {
				return i;
			} else if (input.charAt(i) == '\\') {
				i++;
			}
		}
		return input.length() - 1;
	}

}
