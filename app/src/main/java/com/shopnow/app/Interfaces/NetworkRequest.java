package com.shopnow.app.Interfaces;


import com.android.volley.VolleyError;

import org.json.JSONObject;

public interface NetworkRequest {
    void onSuccess(JSONObject response);
    void onError(VolleyError errorMsg);
}
