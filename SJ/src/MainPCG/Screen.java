package MainPCG;

import java.awt.Font;
import java.util.List;

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
	
	private TablePanel tableTA;
	
	private JScrollPane inputSP;
	
	private JScrollPane analysisSP;
	
	private JScrollPane rulesSP;
	
	private JTextArea rulesTA;
	
	private JButton analyzeBT;
	
	private String[][] matrix;
	
	private List<String> rules;
	
	
	public Screen(String[][] matrix, List<String> rules) {
		this.matrix = matrix;
		this.rules = rules;
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
						.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
							.addComponent(getTablePA(), GroupLayout.DEFAULT_SIZE, 735, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(getRulesSP(), GroupLayout.PREFERRED_SIZE, 423, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addComponent(getInputSP(), GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
								.addGroup(layout.createSequentialGroup()
									.addComponent(getAnalyzeBT(), GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
									.addGap(326)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(getAnalysisSP(), GroupLayout.DEFAULT_SIZE, 735, Short.MAX_VALUE)))
					.addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addComponent(getInputSP(), GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(getAnalyzeBT(), GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addComponent(getAnalysisSP(), GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
						.addGroup(layout.createSequentialGroup()
							.addComponent(getTablePA(), GroupLayout.PREFERRED_SIZE, 356, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(Alignment.LEADING, layout.createSequentialGroup()
							.addComponent(getRulesSP(), GroupLayout.PREFERRED_SIZE, 356, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
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
	
	public TablePanel getTablePA() {
		if(tableTA == null) {
			tableTA = new TablePanel(matrix);
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
	
	public JButton getAnalyzeBT() {
		if(analyzeBT == null) {
			analyzeBT = new JButton("Analyze");
		}

		return analyzeBT;
	}
	
	public JScrollPane getRulesSP() {
		if(rulesSP == null) {
			rulesSP = new JScrollPane();
			rulesSP.setViewportView(getRulesTA());
		}

		return rulesSP;
	}
	
	public JTextArea getRulesTA() {
		if(rulesTA == null) {
			rulesTA = new JTextArea();
			rulesTA.setEditable(false);
			rulesTA.setFont(new Font("Monospaced", Font.PLAIN, 12));
			
			int i = 1;
			for(String rule : rules) {
				rulesTA.append(i++ + ":     " + rule + "\n");
			}
		}

		return rulesTA;
	}
}
