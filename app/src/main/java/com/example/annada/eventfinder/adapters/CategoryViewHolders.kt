package com.example.annada.eventfinder.adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.annada.eventfinder.activities.VenuesListActivity
import kotlinx.android.synthetic.main.row_view.view.*

/**
 * Created by : annada
 * Date : 22/10/2017.
 */

class CategoryViewHolders(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    var mCategoryId: String? = null

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val context = v.context
        val i = Intent(context, VenuesListActivity::class.java)

        i.putExtra("name", v.venue_name!!.text)
        i.putExtra("categoryID", mCategoryId)
        context.startActivity(i)
    }

    companion object {
        private val TAG = CategoryViewHolders::class.java.simpleName
    }
}
