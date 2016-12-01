package com.shopnow.app.Fragments;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shopnow.R;
import com.shopnow.app.Adapters.CartAdapter;
import com.shopnow.app.Interfaces.CartClickListener;
import com.shopnow.app.Models.Product;
import com.shopnow.app.utilities.Utility;

import java.util.ArrayList;

import static android.webkit.WebSettings.PluginState.ON;


public class CartFragment extends Fragment implements CartClickListener {
    ArrayList<Product> cartArrayList;
    CartAdapter cartAdapter;
    RecyclerView rvCart;
    GridLayoutManager gridLayoutManager;
    TextView tvNoRecord, tvTotal;
    final int CALL_ID = 100;
    int position = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        init(view);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void init(View view) {
        if (cartArrayList == null)
            cartArrayList = new ArrayList<>();
        if (Product.listAll(Product.class) != null)
            cartArrayList.addAll(Product.listAll(Product.class));
        rvCart = (RecyclerView) view.findViewById(R.id.rvCart);
        tvNoRecord = (TextView) view.findViewById(R.id.tvNoRecord);
        tvTotal = (TextView) view.findViewById(R.id.tvTotal);
        gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        cartAdapter = new CartAdapter(cartArrayList);
        cartAdapter.setCartListener(this);
        rvCart.setAdapter(cartAdapter);
        rvCart.setLayoutManager(gridLayoutManager);
        setView();
        calculateTotal();
    }

    private void calculateTotal() {
        if (cartArrayList.size() > 0) {
            int tempCount = 0;
            for (int i = 0; i < cartArrayList.size(); i++) {
                tempCount = tempCount + cartArrayList.get(i).getPrice();
            }
            tvTotal.setText(getString(R.string.total) + tempCount);
        }
    }

    private void setView() {
        if (cartArrayList.size() == 0) {
            rvCart.setVisibility(View.GONE);
            tvNoRecord.setVisibility(View.VISIBLE);
            tvTotal.setVisibility(View.GONE);
        } else {
            rvCart.setVisibility(View.VISIBLE);
            tvNoRecord.setVisibility(View.GONE);
            tvTotal.setVisibility(View.VISIBLE);
        }
    }

    BroadcastReceiver productAddBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase(getActivity().getString(R.string.receiver_filter))) {
                setAdapterData();
            }
        }
    };


    private void setAdapterData() {
        if (Product.listAll(Product.class) != null) {
            if (cartArrayList.size() > 0)
                cartArrayList.clear();
            cartArrayList.addAll(Product.listAll(Product.class));
            cartAdapter.notifyDataSetChanged();
            setView();
            calculateTotal();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(productAddBroadcastReceiver, new IntentFilter(getActivity().getString(R.string.receiver_filter)));
    }

    @Override
    public void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(productAddBroadcastReceiver);
    }

    @Override
    public void onVendorCallClick(int pos) {
        position = pos;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            if (!Utility.checkIfAlreadyhavePermission(getActivity())) {
                Utility.requestForSpecificPermission(getActivity(), CALL_ID);
            } else {
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + cartArrayList.get(position).getPhoneNumber())));
            }
        } else {
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + cartArrayList.get(position).getPhoneNumber())));
        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CALL_ID: {

                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + cartArrayList.get(position).getPhoneNumber())));
                } else {
                    Utility.showToastMsg(getActivity(), getString(R.string.permission));
                }
                break;
            }

        }
    }

    @Override
    public void onProductRemoveClick(int position) {
        Product product = Product.findById(Product.class, Product.listAll(Product.class).get(position).getId());
        product.delete();
        setAdapterData();
        Utility.showToastMsg(getActivity(), getActivity().getString(R.string.pro_remove));
    }
}
