package MainPCG;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class Main {

	public static void main(String[] args) {
			
		for(LookAndFeelInfo LnF : UIManager.getInstalledLookAndFeels()) {
			if("Nimbus".equals(LnF.getName())) {
				try {
					UIManager.setLookAndFeel(LnF.getClassName());
				} catch (Exception e) {
					System.err.print(e.getMessage());
				}
	        }
	    }
		
		String[][] matrix = Resource.LoadTable();
		List<String> rules = Resource.LoadRules();
		
		Screen screen = new Screen(matrix, rules);
		Solver solver = new Solver(screen, matrix, rules);
		new Controller(solver, screen);
	}
}