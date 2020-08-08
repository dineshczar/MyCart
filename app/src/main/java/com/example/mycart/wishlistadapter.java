package com.example.mycart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class wishlistadapter extends RecyclerView.Adapter {
    List<WishlistProduct> mProducts;
    Context mContext;
    public static final int LOADING_ITEM = 0;
    public static final int PRODUCT_ITEM = 1;
    int LoadingItemPos;
    public boolean loading = false;

    public wishlistadapter(Context mContext, List<WishlistProduct> products) {
        mProducts = products;
        this.mContext = mContext;
    }




    @Override
    public int getItemViewType(int position) {
        WishlistProduct currentProduct = mProducts.get(position);

            return PRODUCT_ITEM;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //Check which view has to be populated

            View row = inflater.inflate(R.layout.wishlistitem, parent, false);
            return new wishlistadapter.ProductHolder(row);


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //get current product
        final WishlistProduct currentProduct = mProducts.get(position);
        if (holder instanceof wishlistadapter.ProductHolder) {
            wishlistadapter.ProductHolder productHolder = (wishlistadapter.ProductHolder) holder;
            //bind products information with view
            Picasso.with(mContext).load(currentProduct.getImgname()).into(productHolder.imageViewProductThumb);
            productHolder.textViewProductName.setText(currentProduct.getShoeName());
            productHolder.textViewProductPrice.setText(currentProduct.getShoePrice());


        }
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    //Holds view of product with information
    private class ProductHolder extends RecyclerView.ViewHolder {
        ImageView imageViewProductThumb;
        TextView textViewProductName, textViewProductPrice, textViewNew;


        public ProductHolder(View itemView) {
            super(itemView);
            imageViewProductThumb = itemView.findViewById(R.id.imageViewProductThumb);
            textViewProductName = itemView.findViewById(R.id.textViewProductName);
            textViewProductPrice = itemView.findViewById(R.id.textViewProductPrice);

        }
    }

    //holds view of loading item
    private class LoadingHolder extends RecyclerView.ViewHolder {
        public LoadingHolder(View itemView) {
            super(itemView);
        }
    }






}

