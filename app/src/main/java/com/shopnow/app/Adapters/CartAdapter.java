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
import com.shopnow.app.Interfaces.CartClickListener;
import com.shopnow.app.Models.Product;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    ArrayList<Product> cartArrayList;
    ImageLoader imageLoader;
    CartClickListener cartClickListener;

    public CartAdapter(ArrayList<Product> productArrayList) {
        imageLoader = AppController.getInstance().getImageLoader();
        this.cartArrayList = productArrayList;
    }

    public void setCartListener(CartClickListener cartClickListener) {
        this.cartClickListener = cartClickListener;
    }

    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cart_item, parent, false);
        return new CartAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartAdapter.ViewHolder holder, final int position) {
        holder.ivProduct.setImageUrl(cartArrayList.get(position).getProductImg(), imageLoader);
        holder.ivProduct.setDefaultImageResId(R.drawable.images_not_available);
        holder.ivProduct.setErrorImageResId(R.drawable.images_not_available);
        holder.tvProductName.setText(cartArrayList.get(position).getProductname());
        holder.tvProductPrice.setText("Rs." + cartArrayList.get(position).getPrice() + " x " + cartArrayList.get(position).getProductCount());
        holder.tvVendorName.setText(cartArrayList.get(position).getVendorname());
        holder.tvVendorAddress.setText(cartArrayList.get(position).getVendoraddress());
        holder.btnCallVender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartClickListener.onVendorCallClick(position);
            }
        });
        holder.btnRemoveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartClickListener.onProductRemoveClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return cartArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        NetworkImageView ivProduct;
        Button btnRemoveProduct, btnCallVender;
        TextView tvVendorAddress, tvVendorName, tvProductPrice, tvProductName;

        public ViewHolder(View itemView) {
            super(itemView);
            ivProduct = (NetworkImageView) itemView.findViewById(R.id.ivProduct);
            btnRemoveProduct = (Button) itemView.findViewById(R.id.btnRemoveProduct);
            btnCallVender = (Button) itemView.findViewById(R.id.btnCallVender);
            tvVendorAddress = (TextView) itemView.findViewById(R.id.tvVendorAddress);
            tvVendorName = (TextView) itemView.findViewById(R.id.tvVendorName);
            tvProductPrice = (TextView) itemView.findViewById(R.id.tvProductPrice);
            tvProductName = (TextView) itemView.findViewById(R.id.tvProductName);
        }
    }
}
