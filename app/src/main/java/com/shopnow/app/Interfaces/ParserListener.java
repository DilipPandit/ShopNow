package com.shopnow.app.Interfaces;

import com.shopnow.app.Models.Product;

import java.util.ArrayList;

public interface ParserListener {
    void onDataParse(ArrayList<Product> productArrayList);
}
