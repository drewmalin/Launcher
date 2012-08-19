package com.lezend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.SwingWorker;

public class Downloader extends SwingWorker<Void, Void> {
	
	private Launcher parentFrame;
	private String downloadURL;
	private int chunksize = 262144; // 256kb/chunk
	
	public Downloader(Launcher launcher) {
		parentFrame = launcher;
	}
	
	@Override
	public Void doInBackground() {
		try {
			downloadURL = "https://raw.github.com/phunx/Orbit/master/build/CHANGELOG.txt";
			processTxt();
			downloadURL = "https://github.com/phunx/Orbit/blob/master/build/Orbit.jar?raw=true";
			processJar();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public void done() {
		parentFrame.jProgressBar.setIndeterminate(false);
		parentFrame.jProgressBar.setValue(100);
		parentFrame.jButtonLaunch.setEnabled(true);
	}
	
	private void processJar() throws MalformedURLException, IOException {
		URL url = new URL(downloadURL);
		url.openConnection();
		InputStream reader = url.openStream();
		
		File newFile = new File(parentFrame.homeDir + 
				  			    parentFrame.slash + 
				  			    "Orbit" + 
				  			    parentFrame.slash + 
				  			    "Orbit.jar");
		
		if (!newFile.exists()) newFile.createNewFile();
		
		FileOutputStream writer = new FileOutputStream(newFile, false);
		byte[] buffer = new byte[chunksize];
		int totalBytesRead = 0;
		int bytesRead = 0;
		
		while ((bytesRead = reader.read(buffer)) > 0) {
			writer.write(buffer, 0, bytesRead);
			buffer = new byte[chunksize];
			totalBytesRead += bytesRead;
		}
		
		writer.close();
		reader.close();
	}
	
	private void processTxt() throws MalformedURLException, IOException {
		URL url = new URL(downloadURL);
		url.openConnection();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
		StringBuilder sb = new StringBuilder();
		String log = null;
		while ((log = reader.readLine()) != null) {
			sb.append(log + "\n");
		}
		
		reader.close();
		parentFrame.jTextChangeLog.append(sb.toString());
		
	}
}