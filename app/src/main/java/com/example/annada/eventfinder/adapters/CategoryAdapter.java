package com.example.annada.eventfinder.adapters;

import android.content.Context;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.annada.eventfinder.R;
import com.example.annada.eventfinder.model.categoryapi.CategoryModel;
import com.example.annada.eventfinder.views.PaletteTransformation;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by : annada
 * Date : 22/10/2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolders> {
    private List<CategoryModel> mCategories;
    private Context mContext;

    public CategoryAdapter(Context context, List<CategoryModel> categoryList) {
        mCategories = categoryList;
        mContext = context;
    }

    @Override
    public CategoryViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view, parent, false);
        return new CategoryViewHolders(rowView);
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    @Override
    public void onBindViewHolder(CategoryViewHolders holder, int position) {

        final CategoryModel selectedCategory = mCategories.get(position);
        final CategoryViewHolders finalHolder = holder;

        holder.mCategoryId = selectedCategory.getCategoryId();
        holder.categoryName.setText(selectedCategory.getCategoryName());
        Picasso.with(mContext)
                .load(selectedCategory.getCategoryIcon())
                .transform(PaletteTransformation.instance())
                //attempt to load the image from cache
                .networkPolicy(NetworkPolicy.OFFLINE)
                .fit()
                .into(finalHolder.categoryIcon, new PaletteTransformation.PaletteCallback(finalHolder.categoryIcon) {
                    @Override
                    public void onSuccess(Palette palette) {
                        int color = palette.getLightVibrantColor(
                                mContext.getResources().getColor(R.color.light_gray));
                        finalHolder.categoryIcon.setBackgroundColor(color);
                        selectedCategory.color = color;
                    }

                    @Override
                    public void onError() {
                        //Try again online if cache failed
                        Picasso.with(mContext)
                                .load(selectedCategory.getCategoryIcon())
                                .into(finalHolder.categoryIcon, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                    }

                                    @Override
                                    public void onError() {
                                    }
                                });
                    }
                });

    }
}
