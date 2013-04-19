package ml.filelist.controller;

/**
 * Decompiled from walrus-jar...
 * 
 * @author ml
 * 
 */
public class FileListCommand {
    private String type;

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isImage() {
        return "image".equals(this.type);
    }
}