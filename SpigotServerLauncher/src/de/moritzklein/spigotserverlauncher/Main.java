package de.moritzklein.spigotserverlauncher;

import de.moritzklein.spigotserverlauncher.viewcontroller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

	private MainController mainController;
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage mainStage) throws Exception {
		mainStage.setTitle("Spigot Server Launcher");
		mainStage.setWidth(850);
		mainStage.setHeight(590);	
		mainStage.setResizable(false);	
		
		FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/de/moritzklein/spigotserverlauncher/viewcontroller/Main.fxml"));
		Pane mainPane = mainLoader.load();
		mainController = mainLoader.getController();
		mainController.initComponent();
		
		mainStage.setScene(new Scene(mainPane));
		
		mainStage.show();
	}

	public MainController getMainController() {
		return mainController;
	}
	
}