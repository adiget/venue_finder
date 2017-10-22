package com.example.annada.eventfinder.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.annada.eventfinder.R;
import com.example.annada.eventfinder.activities.MapsActivity;
import com.example.annada.eventfinder.activities.VenuesListActivity;
import com.example.annada.eventfinder.model.categoryapi.CategoryModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by : annada
 * Date : 22/10/2017.
 */

public class CategoryViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{
    private static final String TAG = CategoryViewHolders.class.getSimpleName();

    @BindView(R.id.venue_icon) ImageView categoryIcon;
    @BindView(R.id.venue_name) TextView categoryName;

    public String mCategoryId;

    public CategoryViewHolders(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Context context = v.getContext();
        Intent i = new Intent(context, VenuesListActivity.class);

        i.putExtra("name", categoryName.getText());
        i.putExtra("categoryID", mCategoryId);
        context.startActivity(i);
    }
}
