package com.lezend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LaunchListener implements ActionListener {
	
	private Launcher parentFrame;
	
	public LaunchListener(Launcher launcher) {
		parentFrame = launcher;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		try {
			
			String command = parentFrame.homeDir + 
			   				 parentFrame.slash + "Orbit" + 
			   				 parentFrame.slash + "Orbit.jar";
			
			ProcessBuilder builder = new ProcessBuilder("java", "-jar", command);
			
		    Process process = builder.start();
		    
		    InputStreamReader 	isr = new InputStreamReader(process.getInputStream());
		    BufferedReader 	 	br = new BufferedReader(isr);
		    String line;
		    
		    while ((line = br.readLine()) != null) {
		      System.out.println(line);
		    }
		    System.out.println(builder.command());
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
}