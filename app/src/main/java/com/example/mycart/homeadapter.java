package com.example.mycart;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mycart.utils.Users;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class homeadapter extends RecyclerView.Adapter {
    List<Users> mProducts;
    Context mContext;
    public static final int LOADING_ITEM = 0;
    public static final int PRODUCT_ITEM = 1;
    int LoadingItemPos;
    public boolean loading = false;

    public homeadapter(Context mContext, List<Users> homemodelArrayList) {
        mProducts = new ArrayList<>();
        this.mContext = mContext;
        mProducts=homemodelArrayList;
    }



    @Override
    public int getItemViewType(int position) {
        Users currentProduct = mProducts.get(position);

            return PRODUCT_ITEM;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //Check which view has to be populated

            View row = inflater.inflate(R.layout.homeshoeitem, parent, false);
            return new ProductHolder(row);


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //get current product
        final Users mHomemodell = mProducts.get(position);
        if (holder instanceof ProductHolder) {
            ProductHolder productHolder = (ProductHolder) holder;
            //bind products information with view
            Picasso.with(mContext).load(mHomemodell.getState()).into(productHolder.imageViewProductThumb);
            productHolder.textViewProductName.setText(mHomemodell.getName());
            productHolder.textViewProductPrice.setText(mHomemodell.getCity());
            productHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // user selected product now you can show details of that product
                    //Toast.makeText(mContext, "Selected "+currentProduct.getProductName(), Toast.LENGTH_SHORT).show();
                   callClass(mHomemodell);
                }
            });
        }

    }

    private void callClass(Users mHomemodell) {
        Intent mIntent=new Intent(mContext, shoedetailpage.class);
        mIntent.putExtra("ShoeModel",  mHomemodell);
        mContext.startActivity(mIntent);
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

