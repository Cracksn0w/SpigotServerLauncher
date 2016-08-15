package de.moritzklein.spigotserverlauncher.viewcontroller;

import java.io.BufferedWriter;
import java.io.IOException;

import de.moritzklein.spigotserverlauncher.ServerLauncher;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
	
	public void updateTaServerOutput(String pText) {
		Platform.runLater(new Runnable() {
			
			public void run() {
				taServerOutput.setText(taServerOutput.getText() + pText + "\n");
			}
			
		});
	}
	
	public void initComponent() {
		taServerOutput.setEditable(false);
		
		btnSend.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(tfCommand.getText().equalsIgnoreCase("")) return;
				
				try {
					BufferedWriter bWriter = serverLauncher.getBufferedWriter();
					
					bWriter.write(tfCommand.getText() + "\n");
					bWriter.flush();
					
					tfCommand.setText("");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		});
		
		btnStartStop.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				if(serverLauncher.isRunning() == false) {
					taServerOutput.setText("");
					serverLauncher.startProcess();
					btnStartStop.setText("Stop");
				}else {
					serverLauncher.stopProcess();
					btnStartStop.setText("Start");
				}
				
			}
			
		});
		
		serverLauncher = new ServerLauncher(this);
	}
	
}