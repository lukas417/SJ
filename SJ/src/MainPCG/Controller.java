package MainPCG;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

	Screen screen;
	
	Solver solver;
	
	public Controller(Solver solver, Screen screen) {
		this.screen = screen;
		this.solver = solver;
		
		screen.getAnalyzeBT().addActionListener(new AnalyzeBTActionListener());
	}
	

	private class AnalyzeBTActionListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			solver.solve(screen.getInputTA().getText());
		}	
	}
}
