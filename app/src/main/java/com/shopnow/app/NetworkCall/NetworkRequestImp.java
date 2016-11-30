package com.shopnow.app.NetworkCall;


import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.shopnow.R;
import com.shopnow.app.Application.AppController;
import com.shopnow.app.Interfaces.NetworkRequest;

import org.json.JSONObject;

public class NetworkRequestImp {


    public void NetworkObjectRequest(String url, Context context, final NetworkRequest networkRequest) {
        final ProgressDialog pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        networkRequest.onSuccess(response);
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                networkRequest.onError(error);
                pDialog.hide();
            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjReq, context.getResources().getString(R.string.app_name));
    }
}
