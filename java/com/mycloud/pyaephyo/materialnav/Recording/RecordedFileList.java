package com.mycloud.pyaephyo.materialnav.Recording;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Lullaby on 25-Oct-16.
 */
public class RecordedFileList extends ArrayList<File> {
    private static RecordedFileList ourInstance ;

    private int fileLocation;
    private String fileName;

    public static RecordedFileList getInstance()
    {
        if(RecordedFileList.ourInstance == null) {
            RecordedFileList.ourInstance = new RecordedFileList();
        }

        return RecordedFileList.ourInstance;
    }

    public int getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(int fileLocation) {
        this.fileLocation = fileLocation;
    }

    public File[] getFiles() {

                return  RecordedFileList.ourInstance.toArray(new File[ourInstance.size()]);
    }

    public String getAbsolutePathOfFile(int location) {
       return RecordedFileList.ourInstance.get(location).getAbsolutePath();
    }

}
