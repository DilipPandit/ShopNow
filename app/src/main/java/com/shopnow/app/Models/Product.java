package com.shopnow.app.Models;


import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;

public class Product extends SugarRecord implements Parcelable {


    String productname;
    int price;
    String vendorname;
    String vendoraddress;
    String productImg;
    String[] productGallery;
    String phoneNumber;
    public Product()
    {

    }
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


    protected Product(Parcel in) {
        productname = in.readString();
        price = in.readInt();
        vendorname = in.readString();
        vendoraddress = in.readString();
        productImg = in.readString();
        phoneNumber = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productname);
        dest.writeInt(price);
        dest.writeString(vendorname);
        dest.writeString(vendoraddress);
        dest.writeString(productImg);
        dest.writeString(phoneNumber);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}