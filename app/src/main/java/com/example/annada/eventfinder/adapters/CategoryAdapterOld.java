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
import com.example.annada.eventfinder.model.VenueModel;
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

public class CategoryAdapterOld extends RecyclerView.Adapter<VenueRecyclerAdapter.ViewHolder> {
    private List<CategoryModel> mCategories;
    private Context mContext;

    public CategoryAdapterOld(Context context, List<CategoryModel> categoryList) {
        mCategories = categoryList;
        mContext = context;
    }

    @Override
    public VenueRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view, parent, false);
        return new VenueRecyclerAdapter.ViewHolder(rowView);
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    @Override
    public void onBindViewHolder(VenueRecyclerAdapter.ViewHolder holder, int position) {

        final CategoryModel selectedCategory = mCategories.get(position);
        final VenueRecyclerAdapter.ViewHolder finalHolder = holder;

        holder.VenueName.setText(selectedCategory.getCategoryName());
        Picasso.with(mContext)
                .load(selectedCategory.getCategoryIcon())
                .transform(PaletteTransformation.instance())
                //attempt to load the image from cache
                .networkPolicy(NetworkPolicy.OFFLINE)
                .fit()
                .into(finalHolder.VenueIcon, new PaletteTransformation.PaletteCallback(finalHolder.VenueIcon) {
                    @Override
                    public void onSuccess(Palette palette) {
                        int color = palette.getLightVibrantColor(
                                mContext.getResources().getColor(R.color.light_gray));
                        finalHolder.VenueIcon.setBackgroundColor(color);
                        selectedCategory.color = color;
                    }

                    @Override
                    public void onError() {
                        //Try again online if cache failed
                        Picasso.with(mContext)
                                .load(selectedCategory.getCategoryIcon())
                                .into(finalHolder.VenueIcon, new Callback() {
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryIcon;
        TextView categoryName;

        public ViewHolder(View itemView) {
            super(itemView);

            categoryName = (TextView) itemView.findViewById(R.id.venue_name);
            categoryIcon = (ImageView) itemView.findViewById(R.id.venue_icon);
        }
    }
}