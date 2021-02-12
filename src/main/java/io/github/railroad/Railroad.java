package io.github.railroad;

import io.github.railroad.config.Configs;
import io.github.railroad.drp.DiscordRichPresenceManager;
import io.github.railroad.objects.ConfirmWindow;
import io.github.railroad.objects.RailroadTopMenu;
import io.github.railroad.packageExplorer.PackageExplorerTab;
import io.github.railroad.utility.UIUtils;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Railroad extends Application {

	private Scene mainScene;
	private Configs config;

	public static void boot(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage mainWindow) {
		this.config = new Configs();
		this.createComponents(new RailroadTopMenu(this.config.lang), mainWindow);
		Image[] icons = new Image[2];
		UIUtils.getIcons(icons);
		Stage window = UIUtils.setupWindow(mainWindow, this.config.lang.get("window.title"), this.mainScene, icons);
		window.setOnCloseRequest(event -> {
			event.consume();
			this.onClose(window);
		});
		DiscordRichPresenceManager richPresenceManager = new DiscordRichPresenceManager();
		richPresenceManager.setDetails("Railroad IDE").setStats("Editing {insert file name here}")
				.setBigImage(DiscordRichPresenceManager.BigImageKeys.NONE, "Railroad IDE").build();
	}

	private void onClose(Stage window) {
		boolean shouldClose = ConfirmWindow.displayWindow(this.config.lang.get("dialog.quit"),
				this.config.lang.get("dialog.quit.prompt"));
		if (shouldClose)
			window.close();
	}

	public SplitPane centerSplitPane;
	public Pane leftPane;

	// TODO: Start filling out some of these other menus.
	public void createComponents(Node topMenu, Stage window) {
		BorderPane borderPane = new BorderPane();
		borderPane.setTop(topMenu);


		//Testing
		TabPane paneToOpenFile = new TabPane();

		centerSplitPane = new SplitPane();
		//leftPane = new PackageExplorerTab(paneToOpenFile);

		centerSplitPane.getItems().addAll(new PackageExplorerTab(paneToOpenFile), paneToOpenFile);

		//borderPane.setLeft(leftPane);
		borderPane.setCenter(centerSplitPane);





		this.mainScene = new Scene(borderPane);
	}

	public Configs getConfig() {
		return this.config;
	}

	public void setMainScene(Scene mainScene) {
		this.mainScene = mainScene;
	}
}
