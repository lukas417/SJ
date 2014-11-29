package MainPCG;

import java.util.Arrays;
import java.util.List;

public class Solver {

	private Screen screen;

	private String[][] matrix;

	private List<String> rules;

	private List<String> terminals;

	public Solver(Screen screen, String[][] matrix, List<String> rules,
			List<String> terminals) {
		this.screen = screen;
		this.matrix = matrix;
		this.rules = rules;
		this.terminals = terminals;
	}

	public void solve(String input) {
		
		Automat automat = new Automat(Arrays.asList(input.split("\\s+")));

		//Main logic to cheek input code
		while(!automat.GetActualInput().isEmpty())
		{
			//Example of use this method
			if (isTerminal(automat.PopFromInput())) {
				// Do something with terminal
			} else {
			}
		}
	}

	private boolean isTerminal(String value) {
		if (terminals.indexOf(value) == -1) {
			return false;
		} else {
			return true;
		}
	}
}
