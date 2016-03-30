import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.accessibility.*;

public class JF extends JFrame {
	
	JPanel rP = new JPanel();
	JPanel rP2 = new JPanel();
	JPanel Bsl = new JPanel();
	JLabel Screen = new JLabel("Screen:                                       "
			+ "                                                               "
			+ "                                     Registers/Memory:");
	Simconsle Scr = new Simconsle();
	
	final String[] regnum = {"R1","R2","R3","R4","R5","R6","R7","R8"};
	String[][] regcont = {
			{"0","0","0","0","0","0","0","0"}
	};
	
	public JF() throws IOException {
		 
		super("LC3");
		setSize(1200,700);
		setLayout(new BorderLayout(5,5));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);   
		
		
		//Set out put stream
		final PipedInputStream outPipe = new PipedInputStream();
		System.setOut(new PrintStream(new PipedOutputStream(outPipe), true));

		getContentPane().add("North",Screen);
		
		//initComponents();
		JScrollPane scroll = new JScrollPane(Scr.console(outPipe));
		scroll.setHorizontalScrollBarPolicy(
		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 

		//Inside view
		rP.setLayout(new BorderLayout(5,5));
		JSplitPane splitP = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,scroll,rP);
		splitP.setDividerLocation(450);
		add(splitP);
		
		JTable REG = new JTable(regcont,regnum);
		JScrollPane regJSP= new JScrollPane(REG);
		
		rP2.setLayout(new BorderLayout(5,5));
		
		JSplitPane splitP2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,regJSP,rP2);
		splitP2.setDividerLocation(42);
		rP.add(splitP2);
		//REG Table

		JTable MEM = new JTable(32,8);
		
		int z = 0;
		for(int i=0;i<8;i=i+2){
			for(int j=0;j<32;j++){
				MEM.setValueAt("Memory["+z+"]", j, i);
				z++;
			}
		}
		
		//int z = 0;
		for(int i=1;i<8;i=i+2){
			for(int j=1;j<32;j++){
				MEM.setValueAt("", j, i);     //give the memory context here
				//z++;
			}
		}
		rP2.add(MEM);
		//MEM Table
		
		Bsl.setLayout(new FlowLayout(0));
		JSplitPane splitP3 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,MEM,Bsl);
		splitP2.setDividerLocation(42);
		rP2.add(splitP3);
		
		JLabel PC = new JLabel("PC:");
		JTextField pctxt = new JTextField();
		pctxt.setPreferredSize(new Dimension(60,30));
		
		pctxt.setText(""); //Give the PC value here
		
		JButton Next = new JButton("Next");
		JButton	Step = new JButton("Step");
		JButton	Continue = new JButton("Continue");
		JButton	Stop = new JButton("Stop");
		
		Bsl.add(PC);
		Bsl.add(pctxt);
		
		Bsl.add(Next);
		Bsl.add(Step);
		Bsl.add(Continue);
		Bsl.add(Stop);
		setVisible(true);
	}

}