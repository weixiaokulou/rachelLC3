import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.accessibility.*;

public class JF extends JFrame {
	
	private DefaultTableModel tableModel;
	
	public JF() throws IOException {
		 
		super("LC3");
		setSize(700,900);
		setLayout(new BorderLayout(5,5));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);   
		
		//Set out put stream
		final PipedInputStream outPipe = new PipedInputStream();
		System.setOut(new PrintStream(new PipedOutputStream(outPipe), true));
		 
		//Define Menu
		JMenuBar jmb = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem openItem = new JMenuItem("Open");
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {
 
			public void actionPerformed(ActionEvent ex) {
			System.exit(0);
			}
			
		});
		
		//Call items & print
		fileMenu.add(openItem);
		fileMenu.add(new JSeparator());
		fileMenu.add(exitItem);
		jmb.add(fileMenu);
		setJMenuBar(jmb);

		JLabel Screen = new JLabel("Screen:");
		getContentPane().add("North",Screen);
		
		//initComponents();
	    
		Simconsle Scr = new Simconsle();
		JScrollPane scroll = new JScrollPane(Scr.console(outPipe));
		scroll.setHorizontalScrollBarPolicy(
		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
		add("Center",scroll);
		
		//Inside view
		JTable MEM = new JTable();
		
		JButton runButton = new JButton("Run");
		getContentPane().add("South",runButton);
		runButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
            
            }
        });
		setVisible(true);
	}

}