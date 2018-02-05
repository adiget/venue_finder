package com.ags.annada.eventfinder.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.graphics.Palette
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.ags.annada.eventfinder.R
import com.ags.annada.eventfinder.activities.VenuesListActivity
import com.ags.annada.eventfinder.extensions.enumValueOfOrNull
import com.ags.annada.eventfinder.extensions.inflate
import com.ags.annada.eventfinder.extensions.loadUrl
import com.ags.annada.eventfinder.globals.CategoryMedia
import com.ags.annada.eventfinder.model.categoryapi.CategoryModel
import com.ags.annada.eventfinder.views.PaletteTransformation
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import com.squareup.picasso.Callback
import kotlinx.android.synthetic.main.row_category.view.*


/**
 * Created by : annada
 * Date : 22/10/2017.
 */

class CategoryAdapter(private val mContext: Context, private val mCategories: List<CategoryModel>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolders>() {
    lateinit var itemClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolders {
        //Calling the extension functions of ViewGroup
        val rowView = parent.inflate(R.layout.row_category)
        return CategoryViewHolders(rowView)
    }

    override fun getItemCount() = mCategories.size

    override fun onBindViewHolder(holder: CategoryViewHolders, position: Int) {

        val selectedCategory = mCategories[position]

        holder.itemView.cardNameTV.text = selectedCategory.categoryName

        val catName: String = selectedCategory.categoryName.toUpperCase().replace("\\s".toRegex(), "")
        val catEnumName: CategoryMedia? = enumValueOfOrNull<CategoryMedia>(catName)

        val mediaName: String? = catEnumName?.url ?: selectedCategory.categoryIcon

        Picasso.with(mContext)
                //.load(selectedCategory.categoryIcon)

                .load(mediaName)
                .transform(PaletteTransformation.instance())
                //attempt to load the image from cache
                //.networkPolicy(NetworkPolicy.OFFLINE)
                .fit()
                .into(holder.itemView.cardImageView, object : PaletteTransformation.PaletteCallback(holder.itemView.cardImageView!!) {
                    public override fun onSuccess(palette: Palette) {
                        val color = palette.getLightVibrantColor(
                                mContext.resources.getColor(R.color.light_sky_blue))
                        holder.itemView.categoryCard.setBackgroundColor(color)
                        selectedCategory.color = color
                    }

                    override fun onError() {
                        //Try again online if cache failed
                        Picasso.with(mContext)
                                .load(selectedCategory.categoryIcon)
                                //.load(mediaName)
                                .into(holder.itemView.cardImageView, object : Callback {
                                    override fun onSuccess() {}

                                    override fun onError() {}
                                })
                    }
                })
    }

    inner class CategoryViewHolders(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        //var mCategoryId: String? = null

        init {
            itemView.placeHolder.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            itemClickListener.onItemClick(itemView, adapterPosition)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    fun setOnItemClickListener(itemClickListener: OnItemClickListener){
        this.itemClickListener = itemClickListener
    }
}
