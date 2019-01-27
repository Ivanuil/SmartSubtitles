package com.ivanuil.smartsubtitles.classes;

import java.io.File;

public class Subtitle {

    private String subName;
    private String subPath;

    public Subtitle(String subPath) {
        this.subPath = subPath;
        setSubName(subPath);
    }

    public Subtitle(File file) {
        this.subPath = file.getPath();
        setSubName(file.getPath());
    }

    public void setSubName(String string) {
        string = string.replace("_", " ");
        string = string.replace(".srt", "");
        if (string.startsWith(" ")) {
            string = string.replaceFirst(" ", "");
        }
        subName = string;
    }

    public String getName() {
        return subName;
    }

    public String getPath() {
        return subPath;
    }
}
