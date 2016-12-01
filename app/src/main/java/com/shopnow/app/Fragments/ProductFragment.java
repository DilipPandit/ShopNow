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
import android.widget.TextView;

import com.shopnow.R;
import com.shopnow.app.Adapters.ProductAdapter;
import com.shopnow.app.Interfaces.ParserListener;
import com.shopnow.app.Interfaces.ProductClickListener;
import com.shopnow.app.Models.Product;
import com.shopnow.app.Parser.ParserImp;
import com.shopnow.app.utilities.Constants;
import com.shopnow.app.utilities.Utility;

import java.util.ArrayList;


public class ProductFragment extends Fragment implements ParserListener, ProductClickListener {
    ParserImp parserImp;
    ArrayList<Product> productArrayList;
    ProductAdapter productAdapter;
    RecyclerView rvProduct;
    GridLayoutManager gridLayoutManager;
    TextView tvNoRecord;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_product, container, false);
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("data"))
                productArrayList = savedInstanceState.getParcelableArrayList("data");
        }

        init(view);
        return view;
    }

    private void init(View view) {
        if (parserImp == null)
            parserImp = new ParserImp();
        parserImp.setListener(this);
        if (productArrayList == null)
            productArrayList = new ArrayList<>();
        if (productArrayList.size() == 0)
            parserImp.getProductData(Constants.product_url, getActivity());

        rvProduct = (RecyclerView) view.findViewById(R.id.rvProduct);
        tvNoRecord = (TextView) view.findViewById(R.id.tvNoRecord);
        gridLayoutManager = new GridLayoutManager(getActivity(), 2);

        productAdapter = new ProductAdapter(productArrayList);
        productAdapter.setOnAddToCartClick(this);
        rvProduct.setAdapter(productAdapter);
        rvProduct.setLayoutManager(gridLayoutManager);
        setView();

    }

    private void setView() {
        if (productArrayList.size() == 0) {
            rvProduct.setVisibility(View.GONE);
            tvNoRecord.setVisibility(View.VISIBLE);
        } else {
            rvProduct.setVisibility(View.VISIBLE);
            tvNoRecord.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDataParse(ArrayList<Product> productList) {
        if (productArrayList.size() > 0)
            productArrayList.clear();
        productArrayList.addAll(productList);
        productAdapter.notifyDataSetChanged();
        setView();
    }

    @Override
    public void onProductClick(int position) {
        Product product = new Product();
        product.setPhoneNumber(productArrayList.get(position).getPhoneNumber());
        product.setPrice(productArrayList.get(position).getPrice());
        product.setProductGallery(productArrayList.get(position).getProductGallery());
        product.setProductImg(productArrayList.get(position).getProductImg());
        product.setProductname(productArrayList.get(position).getProductname());
        product.setVendoraddress(productArrayList.get(position).getVendoraddress());
        product.setVendorname(productArrayList.get(position).getVendorname());
        product.save();
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(new Intent(getActivity().getString(R.string.receiver_filter)));
        Utility.showToastMsg(getActivity(), getActivity().getString(R.string.pro_added));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("data", productArrayList);
        super.onSaveInstanceState(outState);
    }
}
