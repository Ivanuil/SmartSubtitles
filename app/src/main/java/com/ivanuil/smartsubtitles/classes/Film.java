package com.ivanuil.smartsubtitles.classes;

import java.io.File;
import java.text.DecimalFormat;

public class Film {

    private String filmName;
    private String fileName;
    private String imageURL;
    private String fileSize;
    private String path;

    public Film(File file) {
        setFilmName(file.getName());
        this.fileSize = size(file.length());
        this.fileName = file.getName();
        this.imageURL = null;
        this.path = file.getPath();
    }


    private void setFilmName(String string) {
        string = string.replaceFirst("VID","");
        string = string.replaceAll(".mp4","");
        string = string.replaceAll("_"," ");
        if (string.startsWith(" ")) {
            string = string.replaceFirst(" ", "");
        }
        filmName = string;
    }

    public String size(long size){
        String hrSize = "";
        long k = size;
        double m = size/1024;
        double g = size/1048576;
        double t = size/1073741824;
        DecimalFormat dec = new DecimalFormat("0.00");
        if (k>0) {
            hrSize = dec.format(k).concat("B");
        }
        if (m>0) {
            hrSize = dec.format(m).concat("KB");
        }
        if (g>0) {
            hrSize = dec.format(g).concat("MB");
        }
        if (t>0) {
            hrSize = dec.format(t).concat("GB");
        }
        return hrSize;
    }

    public String getFilmName(){
        return filmName;
    }

    public String getFileName(){
        return fileName;
    }

    public String getImageURL(){
        return imageURL;
    }

    public String getFileSize() {
        return fileSize;
    }

    public String getPath() {
        return path;
    }
}
