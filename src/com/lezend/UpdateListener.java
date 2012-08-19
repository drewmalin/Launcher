package com.lezend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class UpdateListener implements ActionListener {
	
	private Launcher parentFrame;
	
	public UpdateListener(Launcher launcher) {
		parentFrame = launcher;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		parentFrame.jProgressBar.setIndeterminate(true);

		//----- Check version -----//
		try {
			File newFile = new File(parentFrame.homeDir + parentFrame.slash + "Orbit");
			if (!newFile.exists()) newFile.mkdir();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		//----- Download -----//
		
		//  if necessary
		Downloader dl = new Downloader(parentFrame);
		dl.execute();
	}
}
