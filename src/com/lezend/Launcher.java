package com.lezend;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Launcher extends JFrame {
	
	private static final long serialVersionUID = 1L;
	public JPanel contentPanel;
	public JLabel jLabelFilePath;
	public JScrollPane jScrollChangeLog;
	public JTextArea jTextChangeLog;
	public JButton jButtonBrowse, 
					jButtonUpdate, 
					jButtonLaunch;
	
	public JProgressBar jProgressBar;
	
	private int WIDTH = 545;
	private int HEIGHT = 380;
	
	public String homeDir;
	public String slash;
	
	private void addElement(Container c, Component e, int x, int y, int h, int w) {
		e.setBounds(x, y, h, w);
		c.add(e);
	}
	
	public Launcher() {
		systemSetup();
		Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize();
		
		contentPanel = (JPanel) getContentPane();
		contentPanel.setLayout(null);
		
		/* Changelog text area */
		jTextChangeLog = new JTextArea();
		jTextChangeLog.setEditable(false);
		jTextChangeLog.setLineWrap(true);
		jTextChangeLog.setWrapStyleWord(true);
		jScrollChangeLog = new JScrollPane(jTextChangeLog,
										   JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
										   JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		addElement(contentPanel, jScrollChangeLog, 20, 20, 400, 300);
		
		/* Update Button */
		jButtonUpdate = new JButton("Update");
		jButtonUpdate.setEnabled(true);
		addElement(contentPanel, jButtonUpdate, 425, 120, 100, 100);
		
		/* Launch Button */
		jButtonLaunch = new JButton("Launch");
		jButtonLaunch.setEnabled(false);
		addElement(contentPanel, jButtonLaunch, 425, 220, 100, 100);
		
		/* Progress Bar */
		jProgressBar = new JProgressBar(0, 100);
		jProgressBar.setValue(0);
		jProgressBar.setStringPainted(true);
		jProgressBar.setIndeterminate(false);
		addElement(contentPanel, jProgressBar, 20, 320, 505, 40);
		
		jButtonUpdate.addActionListener(new UpdateListener(this));
		jButtonLaunch.addActionListener(new LaunchListener(this));
		
		setTitle("Orbit Launcher");
		setSize(WIDTH, HEIGHT);
		setLocation(new Point(displaySize.width/2 - (WIDTH/2),
							  displaySize.height/2 - (HEIGHT/2)));
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void systemSetup() {
		
	    String OS = System.getProperty("os.name").toUpperCase();
	    slash = "/";
	    
	    if (OS.contains("WIN")) {
	        homeDir = System.getenv("APPDATA");
	        slash = "\\";
	    }
	    else if (OS.contains("MAC"))
	    	homeDir = System.getProperty("user.home") + "/Library/Application Support";
	    else if (OS.contains("NUX"))
	    	homeDir = System.getProperty("user.home");
	    else
	    	homeDir = System.getProperty("user.dir");
	}
	
	
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Launcher();
			}
		});
	}
}