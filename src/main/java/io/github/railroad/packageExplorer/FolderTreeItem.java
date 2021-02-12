package io.github.railroad.packageExplorer;

import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import javax.swing.tree.TreeNode;
import java.io.File;
import java.util.Enumeration;
import java.util.List;

public class FolderTreeItem extends FileTreeItem {

    int children;

    public FolderTreeItem(File directory){
        super(directory);

        System.out.println(directory.getName() + " " + directory.exists());

        //probably going to remove later
        if(directory.isFile()) throw new IllegalArgumentException();

        assert directory.listFiles()!=null;
        children = directory.listFiles().length;
        File[] subFiles = directory.listFiles();

        List<TreeItem<File>> subFileChildren = this.getChildren();

        for(File file:subFiles){
            if(file.isDirectory()){
                subFileChildren.add(new FolderTreeItem(file));
            }
            else if(file.isFile()){
                subFileChildren.add(new FileTreeItem(file));
            }
        }

    }



}
