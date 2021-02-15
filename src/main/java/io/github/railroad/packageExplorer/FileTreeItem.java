package io.github.railroad.packageExplorer;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;

import java.io.File;

public class FileTreeItem extends TreeItem<File> {


    //public boolean editing = false;
    public BooleanProperty editing = new SimpleBooleanProperty(false);

    public FileTreeItem(File file){
        super(file);
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                //if(event.getClickCount()==2){
                    System.out.println(file.getName());
                //}
            }
        });
    }



    @Override
    public String toString() {
        return super.toString();
    }
}
