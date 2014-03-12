/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SECA2.File;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author KH
 */
@ManagedBean(name="fileList")
public class FileListBean {

    private List<FileEntity> files;
    private List<FileEntity> largeFiles;

    public FileListBean() {

        //Players  
        files = new ArrayList<FileEntity>();
        FileEntity file1 = new FileEntity();
        FileEntity file2 = new FileEntity();
        FileEntity file3 = new FileEntity();
        FileEntity file4 = new FileEntity();
        FileEntity file5 = new FileEntity();
        FileEntity file6 = new FileEntity();
        FileEntity file7 = new FileEntity();
        file1.randInit();file1.setFILENAME("7 liner 1");file1.setCREATED_BY("Alex");
        file2.randInit();file2.setFILENAME("6 liner 1");file2.setCREATED_BY("Alex");
        file3.randInit();file3.setFILENAME("6 liner 2");file3.setCREATED_BY("Alex");
        file4.randInit();file4.setFILENAME("6 liner 3");file4.setCREATED_BY("Alex");
        file5.randInit();file5.setFILENAME("6 liner 4");file5.setCREATED_BY("Alex");
        file6.randInit();file6.setFILENAME("8 liner 1");file6.setCREATED_BY("Alex");
        file7.randInit();file7.setFILENAME("Special Characters");file7.setCREATED_BY("Alex");
        
        files.add(file1);
        files.add(file2);
        files.add(file3);
        files.add(file4);
        files.add(file5);
        files.add(file6);
        files.add(file7);
        
        largeFiles = new ArrayList<FileEntity>();
        for(int i=0;i<100;i++){
            FileEntity fileTemp = new FileEntity();
            fileTemp.randInit();fileTemp.setFILENAME("X liner "+i);//fileTemp.setCREATED_BY("Alex");
            largeFiles.add(fileTemp);
        }
    }

    public List<FileEntity> getFiles() {
        return files;
    }

    public void setPlayers(List<FileEntity> files) {
        this.files = files;
    }

    public List<FileEntity> getLargeFiles() {
        return largeFiles;
    }

    public void setLargeFiles(List<FileEntity> largeFiles) {
        this.largeFiles = largeFiles;
    }
    
    
}
