package MainPCG;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solver {

	private Screen screen;

	private String[][] matrix;

	private List<String> rules;

	private List<String> terminals;

	private List<String> nonTerminals;

	private String itemToAnalyze;

	public Solver(Screen screen, String[][] matrix, List<String> rules) {
		this.screen = screen;
		this.matrix = matrix;
		this.rules = rules;

		findTerminals();
		findNonTerminals();
	}

	public void solve(String input) {
		Stack<String> inputStack = fillInputStack(getParsedInput(input));
		Stack<String> workStack = fillWorkStack();
		Stack<Integer> rulesStack = new Stack<Integer>();

		while (!workStack.isEmpty()) {
			if (!checkTops(inputStack, workStack)) {
				// TODO prazdny vstupny stack a neprazdny pracovny
			}

			String element = workStack.pop();
			if (isTerminal(element)) {
				if (inputStack.pop().equals(element)) {
					System.out.println();
					// INFO - akceptujem terminal
				} else {
					// TODO Error - neocakavany symbol
				}
			} else if (isNonTerminal(element)) {
				String inputElement = inputStack.peek();
				String ruleNumberString = findRule(element, inputElement);
				if (!"".equals(ruleNumberString)) {
					Integer ruleNumber = Integer.decode(ruleNumberString);
					String[] rulesToAdd = getRuleByNumber(ruleNumber);
					addRulesToWorkStack(workStack, rulesToAdd);
					rulesStack.push(ruleNumber);
				} else {
					// TODO pravidlo neexistuje
				}
			} else {
				// TODO neznamy symbol
			}
		}

		if (!inputStack.isEmpty()) {
			// TODO prazdny pracovny stack a neprazdny vstupny
		}
	}

	private String[] getParsedInput(String input) {

		String[] splittedInput = input.split("\\s+");

		ArrayList<String> result = new ArrayList<>();

		for (String item : splittedInput) {

			itemToAnalyze = item;

			if (isTerminalWithout_LET_0_1(itemToAnalyze)) {
				result.add(itemToAnalyze); // ak to je hned terminal
			} else {

				ArrayList<String> foundTerminals;

				while (!(foundTerminals = findSomeTerminals()).isEmpty()) {
					result.addAll(foundTerminals);
				}
			}
		}

		return result.toArray(new String[result.size()]);
	}

	private ArrayList<String> findSomeTerminals() {

		ArrayList<String> result = new ArrayList<String>();
		String startTerminal = null;
		String endTerminal = null;
		
		if (itemToAnalyze == null || itemToAnalyze.isEmpty())
			return result;

		for (String terminal : terminals) {

			if (terminal.equals("0") || terminal.equals("1") || terminal.equals("let"))
				continue;

			if (itemToAnalyze.startsWith(terminal)) {

				itemToAnalyze = itemToAnalyze.replace(terminal, "");

				startTerminal = terminal;
			}
		}
		
		for (String terminal : terminals) {

			if (terminal.equals("0") || terminal.equals("1") || terminal.equals("let"))
				continue;

			if (itemToAnalyze.endsWith(terminal)) {

				itemToAnalyze = itemToAnalyze.replace(terminal, "");

				endTerminal = terminal;
			}
		}

		if (startTerminal != null) {
			result.add(startTerminal);
		}

		if (!itemToAnalyze.isEmpty()) { // parsuj terminaly: 0, 1, let, plus ostatny bordel inych znakov, napriklad: @!%^&*.....
			for (char charItem : itemToAnalyze.toCharArray()) {
				result.add(Character.toString(charItem));
			}
			
			itemToAnalyze = "";
		}

		if (endTerminal != null) {
			result.add(endTerminal);
		}
		
		return result;
	}

	private String[] getRuleByNumber(int ruleNumber) {
		String ruleToParse = rules.get(ruleNumber - 1);
		ruleToParse = ruleToParse.substring(ruleToParse.indexOf("=") + 2);
		ruleToParse = ruleToParse.replace("'", "");

		return ruleToParse.split("\\s+");
	}

	private boolean checkTops(Stack<String> inputStack, Stack<String> workStack) {
		if (inputStack.isEmpty()) {
			return Boolean.FALSE;
		} else {
			screen.getAnalysisTA().append(
					"Vrch vstupn�ho z�sobn�ka: " + inputStack.peek() + ", "
							+ "Vrch pracovn�ho z�sobn�ka: " + workStack.peek()
							+ "\n");
		}

		return Boolean.TRUE;

	}

	public String findRule(String nonTerminal, String terminal) {
		int i;
		for (i = 1; i < matrix[0].length; ++i) {
			if (terminal.equals(matrix[0][i])) {
				break;
			}
		}

		int j;
		for (j = 1; j < matrix.length; ++j) {
			if (nonTerminal.equals(matrix[j][0])) {
				break;
			}
		}

		return matrix[j][i];
	}

	public Stack<String> fillInputStack(String[] input) {
		Stack<String> inputStack = new Stack<String>();

		for (int i = input.length - 1; i >= 0; --i) {
			inputStack.push(input[i]);
		}

		return inputStack;
	}

	public Stack<String> fillWorkStack() {
		Stack<String> workStack = new Stack<String>();
		workStack.push(matrix[1][0]);

		return workStack;
	}

	private void addRulesToWorkStack(Stack<String> workStack,
			String[] rulesToAdd) {
		for (int i = rulesToAdd.length - 1; i >= 0; --i) {
			if (!"EPSILON".equals(rulesToAdd[i])) {
				workStack.push(rulesToAdd[i]);
			}
		}
	}

	private boolean isTerminal(String value) {
		if (terminals.contains(value)) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}
	
	private boolean isTerminalWithout_LET_0_1(String value) {
		
		if (value.equals("0") || value.equals("1")
				|| value.equals("let"))
			return Boolean.FALSE;
		
		if (terminals.contains(value)) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	private boolean isNonTerminal(String value) {
		if (nonTerminals.contains(value)) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	public void findTerminals() {
		terminals = new ArrayList<String>();

		for (int i = 1; i < matrix[0].length - 1; ++i) {
			terminals.add(matrix[0][i]);
		}
	}

	public void findNonTerminals() {
		nonTerminals = new ArrayList<String>();

		for (int i = 1; i < matrix.length; ++i) {
			nonTerminals.add(matrix[i][0]);
		}
	}
}
