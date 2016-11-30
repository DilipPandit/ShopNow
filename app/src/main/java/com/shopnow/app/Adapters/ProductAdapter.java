package com.shopnow.app.Adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.shopnow.R;
import com.shopnow.app.Application.AppController;
import com.shopnow.app.Interfaces.ProductClickListener;
import com.shopnow.app.Models.Product;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    ArrayList<Product> productArrayList;
    ImageLoader imageLoader;
    ProductClickListener productClickListener;

    public ProductAdapter(ArrayList<Product> productArrayList) {
        imageLoader = AppController.getInstance().getImageLoader();
        this.productArrayList = productArrayList;
    }

    public void setOnAddToCartClick(ProductClickListener productClickListener) {
        this.productClickListener = productClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.ivProduct.setImageUrl(productArrayList.get(position).getProductImg(), imageLoader);
        holder.ivProduct.setDefaultImageResId(R.drawable.images_not_available);
        holder.ivProduct.setErrorImageResId(R.drawable.images_not_available);
        holder.tvProductName.setText(productArrayList.get(position).getProductname());
        holder.tvProductPrice.setText("Rs." + productArrayList.get(position).getPrice());
        holder.tvVendorName.setText(productArrayList.get(position).getVendorname());
        holder.tvVendorAddress.setText(productArrayList.get(position).getVendoraddress());
        holder.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productClickListener.onProductClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        NetworkImageView ivProduct;
        Button btnAddToCart;
        TextView tvVendorAddress, tvVendorName, tvProductPrice, tvProductName;

        public ViewHolder(View itemView) {
            super(itemView);
            ivProduct = (NetworkImageView) itemView.findViewById(R.id.ivProduct);
            btnAddToCart = (Button) itemView.findViewById(R.id.btnAddToCart);
            tvVendorAddress = (TextView) itemView.findViewById(R.id.tvVendorAddress);
            tvVendorName = (TextView) itemView.findViewById(R.id.tvVendorName);
            tvProductPrice = (TextView) itemView.findViewById(R.id.tvProductPrice);
            tvProductName = (TextView) itemView.findViewById(R.id.tvProductName);
        }
    }
}
