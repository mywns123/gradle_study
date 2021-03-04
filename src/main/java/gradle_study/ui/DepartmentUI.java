package gradle_study.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class DepartmentUI extends JFrame {

	private JPanel contentPane;
	private JPanel pDept;
	private JPanel pBtn;
	private JPanel pList;
	private JLabel lblDeptNo;
	private JTextField tfDeptNo;
	private JLabel lbDeptName;
	private JTextField tfDeptName;
	private JLabel lblFloor;
	private JTextField tfFloor;
	private JButton btn_add;
	private JButton btn_cancel;
	private JScrollPane scrollPane;
	private JTable table;
	
	public DepartmentUI() {
		setTitle("부서정보");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		pDept = new JPanel();
		contentPane.add(pDept);
		pDept.setLayout(new GridLayout(0, 2, 10, 0));
		
		lblDeptNo = new JLabel("부서번호");
		lblDeptNo.setHorizontalAlignment(SwingConstants.RIGHT);
		pDept.add(lblDeptNo);
		
		tfDeptNo = new JTextField();
		pDept.add(tfDeptNo);
		tfDeptNo.setColumns(10);
		
		lbDeptName = new JLabel("부서명");
		lbDeptName.setHorizontalAlignment(SwingConstants.RIGHT);
		pDept.add(lbDeptName);
		
		tfDeptName = new JTextField();
		pDept.add(tfDeptName);
		tfDeptName.setColumns(10);
		
		lblFloor = new JLabel("위치");
		lblFloor.setHorizontalAlignment(SwingConstants.RIGHT);
		pDept.add(lblFloor);
		
		tfFloor = new JTextField();
		tfFloor.setColumns(10);
		pDept.add(tfFloor);
		
		pBtn = new JPanel();
		contentPane.add(pBtn);
		
		btn_add = new JButton("추가");
		pBtn.add(btn_add);
		
		btn_cancel = new JButton("취소");
		pBtn.add(btn_cancel);
		
		pList = new JPanel();
		contentPane.add(pList);
		pList.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		pList.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{1, "개발", 8},
			},
			new String[] {
				"부서번호", "부서명", "위치"
			}
		));
		scrollPane.setViewportView(table);
	}

}
