package com.example.p440;

public class Item {
   // public float num;
    String name;
    String phone;
    int imgId;
    int num;


    public Item(){

    }

    public Item(String name, String phone, int imgId, int num) {
        this.name = name;
        this.phone = phone;
        this.imgId = imgId;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void plus() {
        this.num++;
    }
}
