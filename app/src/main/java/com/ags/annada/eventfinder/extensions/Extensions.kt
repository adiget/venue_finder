package com.ags.annada.eventfinder.extensions

import android.support.v7.graphics.Palette
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.ags.annada.eventfinder.R
import com.ags.annada.eventfinder.views.PaletteTransformation
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

/**
 * Created by : annada
 * Date : 29/01/2018.
 */

//Extension function of ViewGroup to inflate the view
fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

//Extension function of the ImageView to load the url using Picasso
fun ImageView.loadUrl(url: String) {
    Picasso.with(context)
            .load(url)
            .transform(PaletteTransformation.instance())
            //attempt to load the image from cache
            .networkPolicy(NetworkPolicy.OFFLINE)
            .into(this)
}