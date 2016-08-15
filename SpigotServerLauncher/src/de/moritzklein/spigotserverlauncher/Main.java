package de.moritzklein.spigotserverlauncher;

import de.moritzklein.spigotserverlauncher.viewcontroller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	private MainController mainController;
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage mainStage) throws Exception {
		mainStage.setTitle("Spigot Server Launcher");
		mainStage.setWidth(599);
		mainStage.setHeight(400);	
		mainStage.setResizable(false);	
		
		FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/de/moritzklein/spigotserverlauncher/viewcontroller/Main.fxml"));
		Scene scene = new Scene(mainLoader.load());
		mainController = mainLoader.getController();
		mainController.initComponent();
		
		mainStage.setScene(scene);
		
		mainStage.show();
	}

	public MainController getMainController() {
		return mainController;
	}
	
}