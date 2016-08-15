package de.moritzklein.spigotserverlauncher;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import de.moritzklein.spigotserverlauncher.viewcontroller.MainController;

public class ServerLauncher {

	private MainController mainController;
	
	private Thread processThread;
	private ProcessBuilder processBuilder;
	private Process serverProcess;
	
	private BufferedReader brInput;
	private BufferedWriter bwOutput;
	
	private boolean running;
	
	public ServerLauncher(MainController pMainController) {
		mainController = pMainController;
		running = false;
		
		processBuilder = new ProcessBuilder("java", "-jar", "spigot.jar");
		processBuilder.directory(new File(getClass().getResource("/").getFile()));
	}
	
	public void startProcess() {
		running = true;
		
		processThread = new Thread(new Runnable() {
			
			public void run() {
				
				try {
					
					serverProcess = processBuilder.start();
					
					brInput = new BufferedReader(new InputStreamReader(serverProcess.getInputStream()));
					bwOutput = new BufferedWriter(new OutputStreamWriter(serverProcess.getOutputStream()));
					
					String line;
					
					while((line = brInput.readLine()) != null) {
						mainController.updateTaServerOutput(line);
					}
					
					serverProcess.waitFor();
					
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			}
			
		});
		
		processThread.start();
	}
	
	public void stopProcess() {
		running = false;
		
		try {
			
			bwOutput.write("stop\n");
			bwOutput.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized BufferedWriter getBufferedWriter() {
		return bwOutput;
	}
	
	public boolean isRunning() {
		return running;
	}
	
}