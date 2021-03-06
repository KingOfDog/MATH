package initializers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import resources.lang.Language;

import com.jfoenix.controls.JFXButton;

import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.utils.MaterialIconFactory;
import handlers.SceneHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

public class InitMain {

	private Stage stage;
	
	public InitMain(Stage stage) throws IOException {
		this.stage = stage;
	}
	
	public Scene init() throws IOException, InvocationTargetException {
		SceneHandler sw = new SceneHandler();
		Scene scene = sw.switchScene("main", this.stage);
		StackPane sp = (StackPane) scene.lookup("#container");

		InitToolbar itb = new InitToolbar(scene);
		scene = itb.init(Language.get("home.toolbar"), false, null, stage, "main");

		StackPane informationContainer = (StackPane) scene.lookup("#informationContainer");
		String[] bImageArray = new String[] {"/resources/img/1.gif", "/resources/img/2.gif", "/resources/img/3.gif", "/resources/img/4.gif", "/resources/img/5.gif", "/resources/img/6.gif", "/resources/img/7.gif", "/resources/img/8.gif", "/resources/img/9.gif", "/resources/img/10.gif"};

		BackgroundImage bImage = new BackgroundImage(new Image(bImageArray[new Random().nextInt(bImageArray.length)],-1,-1, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
		informationContainer.setBackground(new Background(bImage));

		StackPane navigationContainer = (StackPane) scene.lookup("#navigationContainer");

		JFXButton btnStart = new JFXButton();
		MaterialIconFactory.get().setIcon(btnStart, MaterialIcon.PLAY_CIRCLE_FILLED, "90px");
		btnStart.setDefaultButton(true);
		btnStart.getStyleClass().add("blue-button");
		btnStart.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					InitTester tester = new InitTester(stage);
					tester.init();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnStart.setTooltip(new Tooltip(Language.get("home.hover.play")));
		navigationContainer.getChildren().add(btnStart);
		
		// Add settings button
		JFXButton btnSettings = new JFXButton();
		MaterialIconFactory.get().setIcon(btnSettings, MaterialIcon.SETTINGS, "40px");
		btnSettings.setCancelButton(true);
		btnSettings.setTranslateY(125);
		btnSettings.setTooltip(new Tooltip(Language.get("home.hover.settings")));
		btnSettings.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					InitSettings settings = new InitSettings(stage);
					settings.init();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});
		navigationContainer.getChildren().add(btnSettings);

		// Add statistics button
		JFXButton btnStats = new JFXButton();
		MaterialIconFactory.get().setIcon(btnStats, MaterialIcon.EQUALIZER, "40px");
		btnStats.setTranslateY(-125);
		btnStats.setTooltip(new Tooltip(Language.get("home.hover.stats")));
		btnStats.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						InitStats stats = new InitStats(stage);
						try {
							stats.init();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
		});
		navigationContainer.getChildren().add(btnStats);

		return scene;
	}
	
}
