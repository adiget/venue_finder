package com.example.annada.eventfinder.activities

/**
 * Created by : annada
 * Date : 21/10/2017.
 */

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.view.View
import android.widget.Toast

import com.example.annada.eventfinder.model.venueapi.FourSquareGroup
import com.example.annada.eventfinder.model.venueapi.FourSquareJSON
import com.example.annada.eventfinder.model.venueapi.FourSquareResponseOrig
import com.example.annada.eventfinder.model.venueapi.FourSquareResults
import com.example.annada.eventfinder.R
import com.example.annada.eventfinder.adapters.RecyclerViewAdapter
import com.example.annada.eventfinder.model.categoryapi.CategoryModel
import com.example.annada.eventfinder.restapis.FourSquareService
import kotlinx.android.synthetic.main.activity_place_picker.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VenuesListActivity : Activity() {

    private var categoryName: String? = null
    private var categoryId: String? = null

    private var placePickerManager: LinearLayoutManager? = null
    private var placePickerAdapter: RecyclerView.Adapter<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_picker)
        actionBar!!.setHomeButtonEnabled(true)
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            val category = intent.extras
            categoryName = category!!.getString("name")
            categoryId = category.getString("categoryID")

        } else {
            categoryName = savedInstanceState.getString("mCategoryName")
            categoryId = savedInstanceState.getString("mCategoryId")
        }

        title = categoryName!! + " Near Me"

        eventList!!.setHasFixedSize(true)
        placePickerManager = LinearLayoutManager(this)
        eventList!!.layoutManager = placePickerManager
        eventList!!.addItemDecoration(DividerItemDecoration(eventList!!.context, placePickerManager!!.orientation))

        getVenuesNearMeByCategories()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // TODO save your instance to outState
        outState.putString("mCategoryName", categoryName)
        outState.putString("mCategoryId", categoryId)
    }

    override fun onResume() {
        super.onResume()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getVenuesNearMeByCategories() {
        val userLL = CategoryModel.latitude.toString() + "," + CategoryModel.longitude

        val eventCall = FourSquareService.api.searchVenuesByCategoryID(userLL, categoryId.toString())

        eventCall.enqueue(object : Callback<FourSquareJSON> {
            override fun onResponse(call: Call<FourSquareJSON>, response: Response<FourSquareJSON>) {
                val fjson = response.body()
                val fr = fjson.response
                val fg = fr!!.group
                val frs = fg!!.results

                placePickerAdapter = RecyclerViewAdapter(applicationContext, frs)
                eventList!!.adapter = placePickerAdapter
            }

            override fun onFailure(call: Call<FourSquareJSON>, t: Throwable) {
                Toast.makeText(applicationContext, "This App can't connect to Foursquare's servers!", Toast.LENGTH_LONG).show()
                finish()
            }
        })
    }
}
