package MainPCG;

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
		
		screen.getAnalysisTA().setText(""); // Clear output screen
		
		while (!workStack.isEmpty()) {
			if (!checkTops(inputStack, workStack)) {
				screen.getAnalysisTA().append("Prazdny vstup, neprazdny pracovny zasobnik\n");
				return;
			}

			String element = workStack.pop();
			if(isTerminal(element)) {
				String stackElem = inputStack.pop();
				if("let".equals(element) && isLetter(stackElem)) {
					screen.getAnalysisTA().append("Akceptujem: " + stackElem + "\n");
				} else if(stackElem.equals(element)) {
					screen.getAnalysisTA().append("Akceptujem: " + stackElem + "\n");
				} else {
					screen.getAnalysisTA().append("**********************\n* Neocakavany symbol *\n**********************\n");
				}
			} else if(isNonTerminal(element)) {
				String inputElement = inputStack.peek();
				inputElement = translate(inputElement);
				if(inputElement != null) {
					String ruleNumberString = findRule(element, inputElement);
					if (!"".equals(ruleNumberString)) {
						Integer ruleNumber = Integer.decode(ruleNumberString);
						String[] rulesToAdd = getRuleByNumber(ruleNumber);
						addRulesToWorkStack(workStack, rulesToAdd);
						rulesStack.push(ruleNumber);
					} else {
						screen.getAnalysisTA().append("***********************\n* Neexistuje pravidlo *\n***********************\n");

						if (isLetter(inputElement) || isNumber(inputElement)) {
							inputStack.pop();
							workStack.push(element);
						}
					}
				} else {
					inputStack.pop(); // IF unknown symbol, remove it from stack
				}
			} else {
				screen.getAnalysisTA().append("******************\n* Neznamy symbol *\n******************\n");
			}
		}

		if(!inputStack.isEmpty()) {
			screen.getAnalysisTA().append("Prazdny pracovny zasobnik, neprazdny vstup\n");
		}
	}
	
	private String translate(String inputElement) {
		if(isTerminal(inputElement)) {
			return inputElement;
		} else if(Character.isLetter(inputElement.charAt(0))) {
			return "let";
		}
		
		screen.getAnalysisTA().append("Nepovolen� symbol: " + inputElement + "\n");
		return null;
	}

	private boolean isLetter(String element) {
		if(element.length() == 1 && Character.isLetter(element.charAt(0))) {
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}
	
	private boolean isNumber(String element) {
		if(element.length() == 1 && Character.isDigit(element.charAt(0))) {
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}

	private String[] getParsedInput(String input) {

		String[] splittedInput = input.split("\\s+");

		ArrayList<String> result = new ArrayList<>();

		for (String item : splittedInput) {

			itemToAnalyze = item;

			if (isTerminalExceptLET(itemToAnalyze)) {
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

		if (itemToAnalyze != null || !itemToAnalyze.isEmpty()) {
			for (String terminal : terminals) {
	
				if (terminal.equals("let"))
					continue;
	
				if (itemToAnalyze.startsWith(terminal)) {
	
					itemToAnalyze = itemToAnalyze.substring(terminal.length());
	
					startTerminal = terminal;
					
					break;
				}
			}
		}
		
		if (itemToAnalyze != null || !itemToAnalyze.isEmpty()) {
			for (String terminal : terminals) {
	
				if (terminal.equals("let"))
					continue;
	
				if (itemToAnalyze.endsWith(terminal)) {
	
					itemToAnalyze = itemToAnalyze.substring(0, itemToAnalyze.length() - terminal.length());
	
					endTerminal = terminal;
					
					break;				
				}
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
			screen.getAnalysisTA().append("Vstupny zasobnik: " + inputStack + "\nPracovny zasobnika: " + workStack + "\n");
		}

		return Boolean.TRUE;
	}

	public String findRule(String nonTerminal, String terminal) {
		screen.getAnalysisTA().append("Vyhladavam pre " + nonTerminal + ", " + terminal + "\n");
		
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

	private void addRulesToWorkStack(Stack<String> workStack, String[] rulesToAdd) {
		screen.getAnalysisTA().append("Pridavam pravidla: ");
		for (int i = rulesToAdd.length - 1; i >= 0; --i) {
			if (!"EPSILON".equals(rulesToAdd[i])) {
				screen.getAnalysisTA().append(rulesToAdd[i] + ", ");
				workStack.push(rulesToAdd[i]);
			}
		}
		screen.getAnalysisTA().append("\n");
	}

	private boolean isTerminal(String value) {
		if (terminals.contains(value)) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}
	
	private boolean isTerminalExceptLET(String value) {
		
		if (value.equals("let"))
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
