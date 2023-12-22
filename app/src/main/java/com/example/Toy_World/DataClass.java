package com.example.Toy_World;


public class DataClass {
    private String dataAddress;
    private String dataAddress2;
    private String dataTitle;
    private String dataDesc;
    private String dataLang;
    private String dataImage;
    private String key;
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getDataAddress() { return dataAddress; }
    public String getDataTitle() {
        return dataTitle;
    }
    public String getDataAddress2() {
        return dataAddress2;
    }
    public String getDataDesc() {
        return dataDesc;
    }
    public String getDataLang() { return dataLang; }
    public String getDataImage() {
        return dataImage;
    }
    public DataClass(String dataTitle, String dataAddress, String dataAddress2,String dataDesc, String dataLang, String dataImage) {
        this.dataAddress = dataAddress;
        this.dataTitle = dataTitle;
        this.dataDesc = dataDesc;
        this.dataAddress2 =dataAddress2;
        this.dataLang = dataLang;
        this.dataImage = dataImage;
    }
    public DataClass(){
    }
}