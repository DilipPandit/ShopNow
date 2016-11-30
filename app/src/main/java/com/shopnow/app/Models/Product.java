package com.shopnow.app.Models;


import com.orm.SugarRecord;

public class Product extends SugarRecord {


    String productname;
    int price;
    String vendorname;
    String vendoraddress;
    String productImg;
    String[] productGallery;
    String phoneNumber;

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getVendorname() {
        return vendorname;
    }

    public void setVendorname(String vendorname) {
        this.vendorname = vendorname;
    }

    public String getVendoraddress() {
        return vendoraddress;
    }

    public void setVendoraddress(String vendoraddress) {
        this.vendoraddress = vendoraddress;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String[] getProductGallery() {
        return productGallery;
    }

    public void setProductGallery(String[] productGallery) {
        this.productGallery = productGallery;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}
