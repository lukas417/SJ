package MainPCG;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

public class TablePanel extends JScrollPane {
	
	private static final long serialVersionUID = 1953573797875048417L;
	
	private JTable transitionTB;
	
	public TablePanel(String[][] matrix) {
		initialize(matrix);
	}
	
	private void initialize(String[][] matrix) {
		setViewportView(getTransitionTB(matrix));
	}

	public JTable getTransitionTB(String[][] matrix) {
		if(transitionTB == null) {
			transitionTB = new JTable(new MyTableModel(matrix));
			transitionTB.setPreferredScrollableViewportSize(new Dimension(300, 200));
			transitionTB.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			transitionTB.setTableHeader(null);
			
			TableColumn column;
			for(int i = 0; i < matrix[0].length; ++i) {
				column = transitionTB.getColumnModel().getColumn(i);
			    column.setCellEditor(null);
			}
		}
		
		return transitionTB;
	}

	class MyTableModel extends AbstractTableModel {

		private static final long serialVersionUID = -7673985950140487207L;
		
		private Object[][] data; 

		public MyTableModel(String[][] matrix) {
			data = matrix;
		}

		@Override
		public int getRowCount() {
			return data.length;
		}

		@Override
		public int getColumnCount() {
			return data[0].length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return data[rowIndex][columnIndex];
		}
	}
}