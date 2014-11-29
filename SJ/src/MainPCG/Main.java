package MainPCG;
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
		
		Screen screen = new Screen();
		Solver solver = new Solver(screen);
		new Controller(solver, screen);
	}
}