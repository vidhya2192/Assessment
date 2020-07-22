/**
* Java program which takes in a string as an input and returns true if 
* all the parentheses in the string are properly closed and nested.
*
* @author  Vidhya Vikraman Nair
*
*/
package com.assignments.availity;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class ParenthesisChecker {
	/**
     * Method to check if the parenthesis in input string is closed and nested properly
     * param expression Input string
     * @return true,if the parenthesis is balanced,else false
     */
	public boolean isParenthesisClosed(String expression) {
		Map<Character, Character> parenthesisMap = new HashMap<>();
		parenthesisMap.put('{', '}');
		parenthesisMap.put('[', ']');
		parenthesisMap.put('(', ')');
		Stack<Character> stack = new Stack<>();
		for (char c : expression.toCharArray()) {
			if (parenthesisMap.containsKey(c)) {
				stack.push(c);
			} else if (!stack.empty() && parenthesisMap.get(stack.peek()) == c) {
				stack.pop();
			} else {
				return false;
			}
		}
		return stack.empty();
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the input string: ");
		String inputString = scanner.nextLine();
		System.out.println("Checking if all the parenthesis are closed.....");
		ParenthesisChecker checker = new ParenthesisChecker();
		if (checker.isParenthesisClosed(inputString)) {
			System.out.println("The parenthesis are closed and nested correctly....");
		} else {
			System.out.println("Oops! The parenthesis are  not closed and nested properly!!!!");
		}
	}
}
