package com.shopnow.app.Parser;


import android.content.Context;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shopnow.R;
import com.shopnow.app.Interfaces.ParserListener;
import com.shopnow.app.Models.Product;
import com.shopnow.app.NetworkCall.NetworkRequestImp;
import com.shopnow.app.Interfaces.NetworkRequest;
import com.shopnow.app.utilities.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ParserImp {
    ArrayList<Product> productsList;
    ParserListener parserListener;

    public void setListener(ParserListener parserListener) {
        this.parserListener = parserListener;
    }

    public void getProductData(String url, Context context) {

        if (Utility.isNetworkAvailable(context)) {
            NetworkRequestImp networkRequestImp = new NetworkRequestImp();
            networkRequestImp.NetworkObjectRequest(url, context, new NetworkRequest() {
                @Override
                public void onSuccess(JSONObject response) {
                    Gson gson = new Gson();
                    TypeToken<List<Product>> token = new TypeToken<List<Product>>() {
                    };
                    try {
                        productsList = gson.fromJson(response.getJSONArray("products").toString(), token.getType());
                        parserListener.onDataParse(productsList);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(VolleyError errorMsg) {

                }
            });
        } else {
            Utility.showToastMsg(context, context.getString(R.string.no_internet));
        }
    }
}
