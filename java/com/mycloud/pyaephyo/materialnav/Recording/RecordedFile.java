package com.mycloud.pyaephyo.materialnav.Recording;

import java.io.File;

/**
 * Created by Lullaby on 25-Oct-16.
 */

public class RecordedFile {

    private File file;
    private String fileName;

    private RecordedFileList fileList;

    public String getFileName() {
        return fileName;
    }

    public  RecordedFile(File f) {

        File[] fileArr = f.listFiles();

        fileList = RecordedFileList.getInstance();
        fileList.clear();
        for(int i=0; i<fileArr.length; i++) {

            if(!fileArr[i].isDirectory()) {
                fileList.add(fileArr[i]);
                fileName = fileArr[i].getName();
            }

        }



    }

}
