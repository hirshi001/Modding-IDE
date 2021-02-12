package io.github.railroad.packageExplorer;

import io.github.railroad.Railroad;
import io.github.railroad.config.Configs;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.PopupWindow;
import javafx.util.Callback;

import java.io.File;


public class PackageExplorerTab extends Pane {

    /**
     * The directory this package explorer is in right now.
     */
    public File packageExplorerRootDirectory;

    public TabPane paneToOpenFile;

    private TreeView<File> tree;

    public PackageExplorerTab(TabPane paneToOpenFile){

        super();

        this.paneToOpenFile = paneToOpenFile;
        packageExplorerRootDirectory = new File("/Users/hrishikesh/Desktop/MemeFolder");

        FolderTreeItem rootItem = new FolderTreeItem(packageExplorerRootDirectory);
        rootItem.setExpanded(true);

        TreeView<File> tree = new TreeView<>(rootItem);
        tree.setEditable(true);


        tree.setCellFactory(p -> new TextFieldTreeCellImpl());
        tree.addEventHandler(MouseEvent.MOUSE_CLICKED, this::handleMouseClicked);


        VBox root = new VBox();

        root.prefWidthProperty().bind(this.widthProperty());
        root.getChildren().addAll(new Label(Configs.INSTANCE.lang.get("package_explorer.name")), tree);

        this.tree = tree;
        this.getChildren().add(root);


       // this.addEventHandler(MouseEvent.);

    }

    private void handleMouseClicked(MouseEvent event) {
        if(event.getClickCount()==2){
            TreeItem<File> treeItem =  tree.getSelectionModel().getSelectedItem();
            if(treeItem == null) return;
            File fileToOpen = treeItem.getValue();
            System.out.println(fileToOpen.getName());
            //TODO: open the File in a new tab
            paneToOpenFile.getTabs().add(new Tab(Double.toString(Math.random())));
            }
    }

    /*
    private void handleMouseClicked(MouseEvent event){
        if(event.getTarget() instanceof TextFieldTreeCellImpl) {
            //System.out.println("InstanceOF");
            TreeItem<File> item =  ((TextFieldTreeCellImpl) event.getTarget()).getTreeItem();
            if(item instanceof FileTreeItem){
                System.out.println(item.getValue().getName());
            }
        }
    }

     */


    private static final class TextFieldTreeCellImpl extends TreeCell<File> {

        private TextField textField;

        public TextFieldTreeCellImpl() {
            addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    //System.out.println("event being handled");
                    if(event.getTarget() instanceof TextFieldTreeCellImpl) {
                        //System.out.println("InstanceOF");
                        TreeItem<File> item =  ((TextFieldTreeCellImpl) event.getTarget()).getTreeItem();
                        if(item instanceof FileTreeItem){
                            System.out.println(item.getValue().getName());
                        }
                    }
                }
            });
        }

        /*
        @Override
        public void startEdit() {
            super.startEdit();

            if (textField == null) {
                createTextField();
            }
            setText(null);
            setGraphic(textField);
            textField.selectAll();
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
            setText((String) getItem());
            setGraphic(getTreeItem().getGraphic());
        }

        */
        @Override
        public void updateItem(File item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(getTreeItem().getGraphic());
                }
            }
        }

        /*
        private void createTextField() {
            textField = new TextField(getString());
            textField.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent t) {
                    if (t.getCode() == KeyCode.ENTER) {
                        commitEdit(textField.getText());
                    } else if (t.getCode() == KeyCode.ESCAPE) {
                        cancelEdit();
                    }
                }
            });
        }
         */

        private String getString() {
            return getItem() == null ? "" : getItem().getName();
        }




    }


}
