package com.shopnow.app.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shopnow.R;
import com.shopnow.app.Adapters.ProductAdapter;
import com.shopnow.app.Interfaces.ParserListener;
import com.shopnow.app.Interfaces.ProductClickListener;
import com.shopnow.app.Models.Product;
import com.shopnow.app.Parser.ParserImp;
import com.shopnow.app.utilities.Constants;

import java.util.ArrayList;


public class ProductFragment extends Fragment implements ParserListener, ProductClickListener {
    ParserImp parserImp;
    ArrayList<Product> productArrayList;
    ProductAdapter productAdapter;
    RecyclerView rvProduct;
    GridLayoutManager gridLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        if (parserImp == null)
            parserImp = new ParserImp();
        parserImp.setListener(this);
        parserImp.getProductData(Constants.product_url, getActivity());
        if (productArrayList == null)
            productArrayList = new ArrayList<>();
        rvProduct = (RecyclerView) view.findViewById(R.id.rvProduct);
        gridLayoutManager = new GridLayoutManager(getActivity(), 2);

        productAdapter = new ProductAdapter(productArrayList);
        productAdapter.setOnAddToCartClick(this);
        rvProduct.setAdapter(productAdapter);
        rvProduct.setLayoutManager(gridLayoutManager);

    }

    @Override
    public void onDataParse(ArrayList<Product> productList) {
        if (productArrayList.size() > 0)
            productArrayList.clear();
        productArrayList.addAll(productList);
        productAdapter.notifyDataSetChanged();
    }

    @Override
    public void onProductClick(int position) {
        productArrayList.get(position).save();
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(new Intent(getActivity().getString(R.string.receiver_filter)));
    }
}
