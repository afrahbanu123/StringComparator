package ExerciseOne;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class CompareStringData {

	public static void main(String[] args) {

		System.out.println("Hey Welcome to Excelencia String Comparator Process");

		// Getting value for Number of Strings to compare
		Scanner scannerObj = new Scanner(System.in);
		int stringCount;

		// Logic to handle the user inputs
		while (true) {
			try {
				System.out.print("Enter number of strings to compare: ");
				stringCount = scannerObj.nextInt();
				if (stringCount < 2) {
					System.out.println("Error: System must compare at least 2 strings.");
					System.out.print("Would like to provide new value (y/n)? ");
					String input = scannerObj.next();
					if (!input.equalsIgnoreCase("y") && input.equalsIgnoreCase("n")) {
						System.out.println("You have given No..Exiting program...");
						return;
					} else if (!input.equalsIgnoreCase("y")) {
						System.out.println("You have wrong value..Exiting program...");
						return;
					}
				} else if (stringCount > 5) {
					System.out.println("Error: System cannot compare more than 5 strings.");
					System.out.print("Would like to provide new value (y/n)? ");
					String input = scannerObj.next();
					if (!input.equalsIgnoreCase("y") && input.equalsIgnoreCase("n")) {
						System.out.println("You have given No..Exiting program...");
						return;
					} else if (!input.equalsIgnoreCase("y")) {
						System.out.println("You have wrong value..Exiting program...");
						return;
					}
				} else {
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Error: Invalid input. Please enter a valid number.");
				scannerObj.nextLine();
			}
		}

		String[] stringValues = getStringValues(scannerObj, stringCount);
		String comparisonType = getComparisonType(scannerObj);
		compareAndDisplay(stringValues, comparisonType);
		scannerObj.close();
	}

	private static String[] getStringValues(Scanner scanner, int stringCount) {
		String[] stringValues = new String[stringCount];
		scanner.nextLine();

		// Logic to get string input values
		for (int i = 0; i < stringCount; i++) {
			System.out.print("Enter string " + (i + 1) + " value: ");
			String input = scanner.nextLine();

			// Logic to check input contains special characters or numbers
			if (!input.matches("^[a-zA-Z]+$")) {
				System.out.println("Error: Only alphabetic characters are allowed. Please enter a valid string.");
				i--;
				continue;
			}

			stringValues[i] = input;
		}

		// Logic to print input values
		System.out.println("---------------------------------------------------------------");
		System.out.println("The provided values are: ");
		for (int i = 0; i < stringCount; i++) {
			System.out.print(stringValues[i]);
			if (i < stringCount - 1) {
				System.out.print(", ");
			}
		}
		System.out.println();
		return stringValues;
	}

	// Method to get Comparison Type
	private static String getComparisonType(Scanner scanner) {
		String comparisonType = "";
		int count = 0;

		System.out.println("---------------------------------------------------------------");
		while (!comparisonType.equalsIgnoreCase("Vowels") && !comparisonType.equalsIgnoreCase("Consonants")) {
			if (count == 0) {
				System.out.print("Want to compare strings by 'Vowels' or 'Consonants'?: ");
				comparisonType = scanner.nextLine();
				count++;
			} else {
				System.out.print("Provided Comparison Type " + comparisonType
						+ " is wrong. Enter proper value either 'Vowels' or 'Consonants'?: ");
				comparisonType = scanner.nextLine();
			}

		}

		return comparisonType;
	}

	// Method to get Compare String values
	private static void compareAndDisplay(String[] stringList, String comparisonType) {
		System.out.println("Comparing strings by " + comparisonType);
		System.out.println("---------------------------------------------------------------");

		for (String stringValue : stringList) {
			List<Integer> positions = new ArrayList<>();

			// Using Integer Stream and Stream Methods
			IntStream.range(0, stringValue.length()).filter(i -> isMatchingCharacter(stringValue.charAt(i), comparisonType))
					.forEach(i -> positions.add(i));

			System.out.println("String value: " + stringValue);
			System.out.println("Count of " + comparisonType + ": " + positions.size());
			System.out.println("Positions of " + comparisonType + ": " + positions);
		}
	}

	private static boolean isMatchingCharacter(char c, String comparisonType) {
		char lowerCaseChar = Character.toLowerCase(c);
		return (comparisonType.equalsIgnoreCase("Vowels") && isVowel(lowerCaseChar))
				|| (comparisonType.equalsIgnoreCase("Consonants") && isConsonant(lowerCaseChar));
	}

	private static boolean isVowel(char c) {
		return "aeiou".indexOf(c) != -1;
	}

	private static boolean isConsonant(char c) {
		return Character.isLetter(c) && !isVowel(c);
	}
}
