package com.shopnow.app.Interfaces;

import com.shopnow.app.Models.Product;

import java.util.ArrayList;

/**
 * Created by anand on 30/11/16.
 */

public interface ParserListener {
    void onDataParse(ArrayList<Product> productArrayList);
}
