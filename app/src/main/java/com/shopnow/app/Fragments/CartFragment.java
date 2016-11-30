package com.shopnow.app.Fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shopnow.R;
import com.shopnow.app.Activities.BaseActivity;
import com.shopnow.app.Adapters.CartAdapter;
import com.shopnow.app.Adapters.ProductAdapter;
import com.shopnow.app.Interfaces.CartClickListener;
import com.shopnow.app.Interfaces.ParserListener;
import com.shopnow.app.Models.Product;
import com.shopnow.app.Parser.ParserImp;
import com.shopnow.app.utilities.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class CartFragment extends Fragment implements CartClickListener {
    ParserImp parserImp;
    ArrayList<Product> cartArrayList;
    CartAdapter cartAdapter;
    RecyclerView rvCart;
    GridLayoutManager gridLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        if (cartArrayList == null)
            cartArrayList = new ArrayList<>();
        if (Product.listAll(Product.class) != null)
            cartArrayList.addAll(Product.listAll(Product.class));
        rvCart = (RecyclerView) view.findViewById(R.id.rvCart);
        gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        cartAdapter = new CartAdapter(cartArrayList);
        cartAdapter.setCartListener(this);
        rvCart.setAdapter(cartAdapter);
        rvCart.setLayoutManager(gridLayoutManager);
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
    public void onVendorCallClick(int position) {

    }

    @Override
    public void onProductRemoveClick(int position) {
        Product product = Product.findById(Product.class, Product.listAll(Product.class).get(position).getId());
        product.delete();
        setAdapterData();
    }
}
