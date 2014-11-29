package MainPCG;

import java.util.ArrayList;
import java.util.List;

public class Automat {

	private List<String> input, stack;
	private List<Integer> rules;

	public Automat(List<String> input) {
		this.input = input;
		this.input.add("$");
		
		this.stack = new ArrayList<String>();
		this.stack.add("#");
		
		this.rules = new ArrayList<Integer>();
	}

	public void PushToStack(String value) {
		stack.add(0, value);
	}

	public String PopFromStack() {
		
		String value = stack.get(0);
		
		stack.remove(0);
		
		return value;
	}
	
	public String PopFromInput() {
		
		String value = input.get(0);
		
		input.remove(0);
		
		return value;
	}

	public void addNumebrOfUsedRule(Integer rule) {
		this.rules.add(rule);
	}

	// Get methods
	public List<String> GetActualInput() {
		return input;
	}

	public List<String> GetActualStack() {
		return stack;
	}

	public List<Integer> GetActualRules() {
		return rules;
	}
}
