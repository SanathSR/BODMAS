package questionNo12;

import java.util.Scanner;

public class BodmasRule {

	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("Enter the expression");
		String expression = scanner.next();

		eval(expression);
	}

	private static void eval(String expression) {

		String temporary = expression;
		boolean brackets = true;
		do {
			int closeBracketIndex = isCloseBracket(temporary);
			int openBracketIndex = isOpenBracket(temporary, closeBracketIndex);

			if (closeBracketIndex == -1 && openBracketIndex == -1)
				brackets = false;
			else {
				String partOfExpression = "";
				for (int i = openBracketIndex + 1; i < closeBracketIndex; i++) {
					partOfExpression += temporary.charAt(i);
				}
				partOfExpression = evaluation(partOfExpression);

				String simplifiedExpression = "";

				if (openBracketIndex != 0) {
					for (int j = 0; j < openBracketIndex; j++)
						simplifiedExpression += temporary.charAt(j);
				}

				simplifiedExpression += partOfExpression;

				for (int k = closeBracketIndex + 1; k < temporary.length(); k++) {
					simplifiedExpression += temporary.charAt(k);
				}
				temporary = simplifiedExpression;
			}
		} while (brackets);

		temporary = evaluation(temporary);
		System.out.println(temporary);
	}

	private static int isOpenBracket(String temporary, int closeBracketIndex) {

		boolean check = true;
		int bracketIndex = -1;
		int index = closeBracketIndex;
		if (closeBracketIndex >= 0) {
			do {
				index--;
				if (temporary.charAt(index) == '(') {
					bracketIndex = index;
					check = false;
				}
			} while (check && index >= 0);
		}
		return bracketIndex;
	}

	private static int isCloseBracket(String temporary) {

		boolean check = true;
		int bracketIndex = -1;
		int index = -1;
		do {
			index++;
			if (temporary.charAt(index) == ')') {
				bracketIndex = index;
				check = false;
			}
		} while (check && index < temporary.length() - 1);
		return bracketIndex;
	}

	private static String evaluation(String expression) {

		String tempExpression = expression;

		boolean sqFlag = true;
		do {
		int squareIndex = tempExpression.length()-1;
		while (squareIndex >=0 ) {
			if (tempExpression.charAt(squareIndex) == '^') {
				tempExpression = square(tempExpression, squareIndex);
			}
			squareIndex--;
		}
		sqFlag = checkPresence(tempExpression, '^');
		} while (sqFlag);
		
		boolean divFlag = true;
		do {
			int divideIndex = 0;
			while (divideIndex < tempExpression.length()) {
				if (tempExpression.charAt(divideIndex) == '/') {
					tempExpression = divide(tempExpression, divideIndex);
				}
				divideIndex++;
			}
			divFlag = checkPresence(tempExpression, '/');
		} while (divFlag);

		boolean mulFlag = true;
		do {
			int multiplyIndex = 0;
			while (multiplyIndex < tempExpression.length()) {
				if (tempExpression.charAt(multiplyIndex) == '*') {
					tempExpression = multiply(tempExpression, multiplyIndex);
				}
				multiplyIndex++;
			}
			mulFlag = checkPresence(tempExpression, '*');
		} while (mulFlag);

		boolean addFlag = true;
		do {
			int additionIndex = 0;
			while (additionIndex < tempExpression.length()) {
				if (tempExpression.charAt(additionIndex) == '+') {
					tempExpression = addition(tempExpression, additionIndex);
				}
				additionIndex++;
			}
			addFlag = checkPresence(tempExpression, '+');
		} while (addFlag);

		boolean subFlag = true;
		do {
			int subtractIndex = 0;
			while (subtractIndex < tempExpression.length()) {
				if (tempExpression.charAt(subtractIndex) == '-') {
					tempExpression = subtract(tempExpression, subtractIndex);
				}
				subtractIndex++;
			}
			subFlag = checkPresence(tempExpression, '-');
		} while (subFlag);
		return tempExpression;
	}

	private static boolean checkPresence(String tempExpression, char c) {

		boolean flag = false;
		for (int i = 0; i < tempExpression.length(); i++) {
			if (tempExpression.charAt(i) == c)
				flag = true;
		}
		return flag;
	}

	private static String subtract(String tempExpression, int subtractIndex) {

		String temp = "";

		int previousIndex = findPreviousIndex(subtractIndex, tempExpression);
		int nextIndex = findNextIndex(subtractIndex, tempExpression);

		if (previousIndex != 0) {
			for (int i = 0; i <= previousIndex; i++)
				temp += tempExpression.charAt(i);
		}

		int previousNumber = stringToInt(tempExpression, previousIndex, subtractIndex);
		int nextNumber = stringToInt(tempExpression, subtractIndex, nextIndex);

		int result = previousNumber - nextNumber;

		temp += result;

		for (int j = nextIndex; j < tempExpression.length(); j++)
			temp += tempExpression.charAt(j);
		return temp;
	}

	private static String addition(String tempExpression, int additionIndex) {

		String temp = "";

		int previousIndex = findPreviousIndex(additionIndex, tempExpression);
		int nextIndex = findNextIndex(additionIndex, tempExpression);

		if (previousIndex != 0) {
			for (int i = 0; i <= previousIndex; i++)
				temp += tempExpression.charAt(i);
		}

		int previousNumber = stringToInt(tempExpression, previousIndex, additionIndex);
		int nextNumber = stringToInt(tempExpression, additionIndex, nextIndex);

		int result = previousNumber + nextNumber;

		temp += result;

		for (int j = nextIndex; j < tempExpression.length(); j++)
			temp += tempExpression.charAt(j);
		return temp;
	}

	private static String divide(String tempExpression, int divideIndex) {

		String temp = "";

		int previousIndex = findPreviousIndex(divideIndex, tempExpression);
		int nextIndex = findNextIndex(divideIndex, tempExpression);

		if (previousIndex != 0) {
			for (int i = 0; i <= previousIndex; i++)
				temp += tempExpression.charAt(i);
		}

		int previousNumber = stringToInt(tempExpression, previousIndex, divideIndex);
		int nextNumber = stringToInt(tempExpression, divideIndex, nextIndex);

		int result = (((int) previousNumber) / ((int) nextNumber));

		temp += result;

		for (int j = nextIndex; j < tempExpression.length(); j++)
			temp += tempExpression.charAt(j);
		return temp;
	}

	private static String multiply(String tempExpression, int multiplyIndex) {

		String temp = "";

		int previousIndex = findPreviousIndex(multiplyIndex, tempExpression);
		int nextIndex = findNextIndex(multiplyIndex, tempExpression);

		if (previousIndex != 0) {
			for (int i = 0; i <= previousIndex; i++)
				temp += tempExpression.charAt(i);
		}

		int previousNumber = stringToInt(tempExpression, previousIndex, multiplyIndex);
		int nextNumber = stringToInt(tempExpression, multiplyIndex, nextIndex);

		int result = previousNumber * nextNumber;

		temp += result;

		for (int j = nextIndex; j < tempExpression.length(); j++)
			temp += tempExpression.charAt(j);
		return temp;
	}

	private static String square(String tempExpression, int squareIndex) {

		String temp = "";

		int previousIndex = findPreviousIndex(squareIndex, tempExpression);
		int nextIndex = findNextIndex(squareIndex, tempExpression);

		if (previousIndex != 0) {
			for (int i = 0; i <= previousIndex; i++)
				temp += tempExpression.charAt(i);
		}

		int previousNumber = stringToInt(tempExpression, previousIndex, squareIndex);
		int nextNumber = stringToInt(tempExpression, squareIndex, nextIndex);

		int result = calculatePower(previousNumber ,nextNumber);

		temp += result;

		for (int j = nextIndex; j < tempExpression.length(); j++)
			temp += tempExpression.charAt(j);
		return temp;
	}

	private static int calculatePower(int previousNumber, int nextNumber) {
		
		int size = nextNumber;
		int baseNumber =previousNumber;
		while (size > 1) {
			baseNumber = baseNumber * previousNumber;
			size--;
		}
		return baseNumber;
	}

	private static int findNextIndex(int currentIndex, String tempExpression) {

		int index = currentIndex + 1;
		boolean check = true;
		while (index < tempExpression.length() && check) {
			if ('*' == tempExpression.charAt(index) || '/' == tempExpression.charAt(index)
					|| '+' == tempExpression.charAt(index) || '-' == tempExpression.charAt(index)
					|| '^' == tempExpression.charAt(index))
				check = false;
			if (check)
				index++;
		}
		return index;
	}

	private static int findPreviousIndex(int currentIndex, String tempExpression) {

		int index = currentIndex - 1;
		boolean check = true;
		while (index > 0 && check) {
			if ('*' == tempExpression.charAt(index) || '/' == tempExpression.charAt(index)
					|| '+' == tempExpression.charAt(index) || '-' == tempExpression.charAt(index)
					|| '^' == tempExpression.charAt(index))
				check = false;
			if (check)
				index--;
		}
		return index;
	}

	private static int stringToInt(String temp, int first, int last) {

		int finalNumber = 0;
		if (first != 0)
			first += 1;
		for (int i = first; i < last; i++) {
			int temporary = (int) temp.charAt(i);
			finalNumber = (finalNumber * 10) + (temporary - 48);
		}
		return finalNumber;
	}
}

//ascii . =46