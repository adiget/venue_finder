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
import com.example.annada.eventfinder.views.PaletteTransformation;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Callback;

import java.util.List;

/**
 * Created by : annada
 * Date : 21/10/2017.
 */

public class VenueRecyclerAdapter extends RecyclerView.Adapter<VenueRecyclerAdapter.ViewHolder> {
    private List<VenueModel> mVenues;
    private Context mContext;

    public VenueRecyclerAdapter(Context context, List<VenueModel> VenueList) {
        mVenues = VenueList;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view, parent, false);
        return new ViewHolder(rowView);
    }

    @Override
    public int getItemCount() {
        return mVenues.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final VenueModel selectedVenue = mVenues.get(position);
        final ViewHolder finalHolder = holder;

        holder.VenueName.setText(selectedVenue.getVenueName());
        //holder.VenueRating.setText(selectedVenue.getVenueRating());
        Picasso.with(mContext)
                .load(selectedVenue.getVenueIcon())
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
                selectedVenue.color = color;
            }

            @Override
            public void onError() {
                //Try again online if cache failed
                Picasso.with(mContext)
                        .load(selectedVenue.getVenueIcon())
                        .into(finalHolder.VenueIcon, new Callback() {
                            @Override
                            public void onSuccess() {}

                            @Override
                            public void onError() {}
                        });
            }
        });

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView VenueIcon;
        TextView VenueName;

        public ViewHolder(View itemView) {
            super(itemView);

            VenueName = (TextView) itemView.findViewById(R.id.venue_name);
            VenueIcon = (ImageView) itemView.findViewById(R.id.venue_icon);
        }
    }
}
