package MainPCG;

import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;


public class Screen extends JFrame {
	
	private static final long serialVersionUID = 376264707339898936L;

	public static final String APP_NAME = "Project SMALL";

	
	private JTextArea inputTA;

	private JTextArea analysisTA;
	
	private JTextArea tableTA;
	
	private JScrollPane inputSP;
	
	private JScrollPane analysisSP;
	
	private JScrollPane tableSP;
	
	private JButton analyzeBT;
	
	
	public Screen() {		
		initialize();
	}
	
	private void initialize() {
		setSize(1200, 1034);
		setTitle(APP_NAME);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(getCustomLayout());
	}
	
	private GroupLayout getCustomLayout() {
	
		GroupLayout layout = new GroupLayout(getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(getTableSP(), Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1164, Short.MAX_VALUE)
						.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addComponent(getAnalyzeBT(), GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
								.addComponent(getInputSP(), GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(getAnalysisSP(), GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)))
					.addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
							.addComponent(getInputSP(), GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(getAnalyzeBT(), GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addComponent(getAnalysisSP(), GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(getTableSP(), GroupLayout.PREFERRED_SIZE, 336, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);

		
		return layout;
	}
	
	public JTextArea getInputTA() {
		if(inputTA == null) {
			inputTA = new JTextArea();
			inputTA.setFont(new Font("Monospaced", Font.PLAIN, 12));
		}
		
		return inputTA;
	}

	public JTextArea getAnalysisTA() {
		if(analysisTA == null) {
			analysisTA = new JTextArea();
			analysisTA.setEditable(false);
			analysisTA.setFont(new Font("Monospaced", Font.PLAIN, 12));
		}

		return analysisTA;
	}
	
	public JTextArea getTableTA() {
		if(tableTA == null) {
			tableTA = new JTextArea();
			tableTA.setEditable(false);
		}
		
		return tableTA;
	}

	public JScrollPane getInputSP() {
		if(inputSP == null) {
			inputSP = new JScrollPane();
			inputSP.setViewportView(getInputTA());
		}

		return inputSP;
	}

	public JScrollPane getAnalysisSP() {
		if(analysisSP == null) {
			analysisSP = new JScrollPane();
			analysisSP.setViewportView(getAnalysisTA());
		}

		return analysisSP;
	}

	public JScrollPane getTableSP() {
		if(tableSP == null) {
			tableSP = new JScrollPane();
			tableSP.setViewportView(getTableTA());
		}

		return tableSP;
	}
	
	public JButton getAnalyzeBT() {
		if(analyzeBT == null) {
			analyzeBT = new JButton("Analyze");
		}

		return analyzeBT;
	}
}
