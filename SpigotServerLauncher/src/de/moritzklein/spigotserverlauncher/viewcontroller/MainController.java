package de.moritzklein.spigotserverlauncher.viewcontroller;

import de.moritzklein.spigotserverlauncher.ServerLauncher;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainController {

	@FXML
	private Button btnStartStop;
	
	@FXML
	private Button btnSend;
	
	@FXML
	private TextArea taServerOutput;
	
	@FXML
	private TextField tfCommand;
	
	private ServerLauncher serverLauncher;
	
	private Alert infoAlert;
	
	public void setTextLater(String pText) {
		Platform.runLater(new Runnable() {
			
			public void run() {
				taServerOutput.appendText(pText + "\n");
			}
			
		});
	}
	
	public void initComponent() {
		taServerOutput.setEditable(false);
		
		serverLauncher = new ServerLauncher(this);
	}
	
	@FXML
	public void clearConsoleMenuItem() {
		taServerOutput.clear();
	}
	
	@FXML
	public void sendCommandButton() {
		if(tfCommand.getText().equalsIgnoreCase("") || serverLauncher.getBufferedWriter() == null) return;
		
		serverLauncher.sendCommand(tfCommand.getText());
		tfCommand.clear();
	}
	
	
	@FXML
	public void startServerStopServerButton() {
		if(serverLauncher.isRunning() == false) {
			taServerOutput.clear();
			serverLauncher.startServer();
			btnStartStop.setText("Stop");
		}else {
			serverLauncher.stopServer();
			btnStartStop.setText("Start");
		}
	}
	
	@FXML
	public void startServerMenuItem() {
		if(serverLauncher.isRunning() == false) {
			taServerOutput.clear();
			serverLauncher.startServer();
			btnStartStop.setText("Stop");
		}else {
			
		}
	}
}