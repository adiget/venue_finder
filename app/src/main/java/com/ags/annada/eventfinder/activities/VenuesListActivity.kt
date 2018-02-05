package com.ags.annada.eventfinder.activities

/**
 * Created by : annada
 * Date : 21/10/2017.
 */

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast

import com.ags.annada.eventfinder.R
import com.ags.annada.eventfinder.adapters.CategoryAdapter
import com.ags.annada.eventfinder.adapters.CategoryItemsAdapter
import com.ags.annada.eventfinder.globals.Constants
import com.ags.annada.eventfinder.globals.Constants.CATEGORY_MODEL_KEY
import com.ags.annada.eventfinder.globals.Constants.MAPS_BUNDLE_KEY
import com.ags.annada.eventfinder.model.User
import com.ags.annada.eventfinder.model.categoryapi.CategoryModel
import com.ags.annada.eventfinder.model.venueapi.*
import com.ags.annada.eventfinder.restapis.FourSquareService
import kotlinx.android.synthetic.main.activity_place_picker.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class VenuesListActivity : AppCompatActivity() {

    private var categoryName: String? = null
    private var categoryId: String? = null
    var mCategoryItemsList = ArrayList<FourSquareResults>()

    lateinit private var placePickerAdapter: CategoryItemsAdapter
    lateinit private var staggeredLayoutManager: StaggeredGridLayoutManager
    lateinit private var menu: Menu
    private var isListView: Boolean = false

    private val onItemClickListener = object : CategoryItemsAdapter.OnItemClickListener {
        override fun onItemClick(view: View, position: Int) {
            val mapIntent = Intent(this@VenuesListActivity, MapsActivity::class.java)

            val fourSquareResults: FourSquareResults = mCategoryItemsList[position]

            val mBundle = Bundle()
            mBundle.putParcelable(MAPS_BUNDLE_KEY, fourSquareResults)
            mapIntent.putExtras(mBundle)

            startActivity(mapIntent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_picker)

        setSupportActionBar(toolbar)
        val ab = getSupportActionBar()
        if (ab != null) {
            ab!!.setDisplayHomeAsUpEnabled(true)
        }

        if (savedInstanceState == null) {
            val intent = this.intent
            val bundle = intent.extras

            val categoryModel: CategoryModel = bundle.getParcelable(CATEGORY_MODEL_KEY)
            categoryName = categoryModel.categoryName
            categoryId = categoryModel.categoryId
        } else {
            categoryName = savedInstanceState.getString("mCategoryName")
            categoryId = savedInstanceState.getString("mCategoryId")
        }

        title = categoryName!! + " Near Me"

        staggeredLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        eventList.layoutManager = staggeredLayoutManager

        placePickerAdapter = CategoryItemsAdapter(applicationContext, mCategoryItemsList)
        eventList.adapter = placePickerAdapter

        placePickerAdapter.setOnItemClickListener(onItemClickListener)

        getVenuesNearMeByCategories()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        this.menu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_toggle) {
            toggle()
            return true
        }else if(id == android.R.id.home){
            NavUtils.navigateUpFromSameTask(this)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun toggle() {
        if (isListView) {
            showGridView()
        } else {
            showListView()
        }
    }

    private fun showListView() {
        staggeredLayoutManager.spanCount = 1
        val item = menu.findItem(R.id.action_toggle)
        item.setIcon(R.drawable.ic_action_grid)
        item.title = getString(R.string.show_as_grid)
        isListView = true
    }

    private fun showGridView() {
        staggeredLayoutManager.spanCount = 2
        val item = menu.findItem(R.id.action_toggle)
        item.setIcon(R.drawable.ic_action_list)
        item.title = getString(R.string.show_as_list)
        isListView = false
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

    private fun getVenuesNearMeByCategories() {
        val userLL = CategoryModel.latitude.toString() + "," + CategoryModel.longitude

        val eventCall = FourSquareService.api.searchVenuesByCategoryID(userLL, categoryId.toString())

        eventCall.enqueue(object : Callback<FourSquareJSON> {
            override fun onResponse(call: Call<FourSquareJSON>, response: Response<FourSquareJSON>) {
                val fjson = response.body()
                val fr = fjson.response
                val fg = fr?.group
                val frs: List<FourSquareResults>? = fg?.results

                mCategoryItemsList.clear()

                for (i in frs!!.indices) {
                    mCategoryItemsList.add(FourSquareResults(frs?.get(i).venue, frs.get(i)?.photo))
                }

                placePickerAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<FourSquareJSON>, t: Throwable) {
                Toast.makeText(applicationContext, "This App can't connect to Foursquare's servers!", Toast.LENGTH_LONG).show()
                finish()
            }
        })
    }
}
