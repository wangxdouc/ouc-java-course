package sample.swing;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class JTableUpdateSample extends JPanel {

	private static final long serialVersionUID = 1L;

	TableModel dataModel;
	JScrollPane scrollpane;
	JTable table;
	Timer timer;

	public JTableUpdateSample() {
		

		dataModel = getTableModel();
		table = new JTable(dataModel);
		scrollpane = new JScrollPane(table);

		timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				table.validate();
				table.updateUI();
			}
		});

		timer.start();
		this.add(scrollpane);
	}

	// 读取外部文件，每一行当做一条字符串存入List中
	public List<String> getData() {
		FileReader fr;
		File file = new File("data.txt");
		int b;
		StringBuffer sb = new StringBuffer();
		List<String> s = new ArrayList<String>();
		try {
			fr = new FileReader(file);
			while ((b = fr.read()) != -1) {
				if (b != '\r') {
					sb.append((char) b);
				}

				if (b == '\n') {
					s.add(sb.toString());
					sb = new StringBuffer();
				}
			}
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	// 使用public List<String> getData() 方法得到的List构建数据模型
	// 此处使用的外部文件中，每一行的字符串用空格分成四个部分
	// 例如，其中一行为：2013-03-18 11:50:55 传感器1 报警，对应表格的一行
	public AbstractTableModel getTableModel() {
		return new AbstractTableModel() {
			private static final long serialVersionUID = 1L;
			public String getColumnName(int columnIndex) {
				String columnNames[] = { "编号", "UUID", "名称", "创建者ID", "创建者", "创建时间" };
				if (columnIndex == 1)
					return columnNames[0];
				if (columnIndex == 2)
					return columnNames[1];
				if (columnIndex == 3)
					return columnNames[2];
				if (columnIndex == 4)
					return columnNames[3];
				if (columnIndex == 5)
					return columnNames[4];
				if (columnIndex == 6)
					return columnNames[5];
				return null;
				
			}

			public int getColumnCount() {
				return 4;
			}

			public int getRowCount() {
				return getData().size();
			}

			public Object getValueAt(int row, int col) {
				switch (col) {
				case (0): {
					return row + 1;
				}
				case (1): {
					return getData().get(row).split(" ", 0)[0];
				}
				case (2): {
					return getData().get(row).split(" ", 0)[1];
				}
				default:
					return getData().get(row).split(" ", 0)[2];
				}
			}
		};
	}

	public static void main(String[] g) {
		JFrame frame = new JFrame();
		JTableUpdateSample t = new JTableUpdateSample();
		frame.add(t);
		frame.setSize(new Dimension(400, 400));
		frame.setVisible(true);
		t.timer.start();
	}
}
