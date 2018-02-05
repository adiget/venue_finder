package com.ags.annada.eventfinder.activities

/**
 * Author: annada
 * Created: 21/10/2017
 */

import android.Manifest
import android.app.Service
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.content.pm.PackageManager
import android.provider.Settings
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast

import com.ags.annada.eventfinder.R
import com.ags.annada.eventfinder.adapters.CategoryAdapter
import com.ags.annada.eventfinder.adapters.VenueRecyclerAdapter
import com.ags.annada.eventfinder.globals.Constants
import com.ags.annada.eventfinder.globals.Constants.CATEGORY_MODEL_KEY
import com.ags.annada.eventfinder.model.FourSquareResponse
import com.ags.annada.eventfinder.model.VenueModel
import com.ags.annada.eventfinder.model.categoryapi.CategoryListResponse
import com.ags.annada.eventfinder.model.categoryapi.CategoryModel
import com.ags.annada.eventfinder.restapis.FourSquareService
import com.ags.annada.eventfinder.views.SpaceItemDecoration

import java.util.ArrayList

import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.Double


class MainActivity : AppCompatActivity(), LocationListener {

    private var locationManager: LocationManager? = null
    private var location: Location? = null
    private val REQUEST_LOCATION = 200

    lateinit private var staggeredLayoutManager: StaggeredGridLayoutManager
    lateinit private var mAdapterNw: CategoryAdapter
    lateinit private var mAdapter: VenueRecyclerAdapter
    lateinit private var menu: Menu
    private var isListView: Boolean = false

    companion object {
        //The App specific constant when requesting the Location Permission
        private val PERMISSION_ACCESS_FINE_LOCATION = 1
        var mVenueList = ArrayList<VenueModel>()
        var mCategoriesList = ArrayList<String>()
        var mCategoriesListNw = ArrayList<CategoryModel>()
        private var COLUMN_COUNT: Int = 0
    }

    private val onItemClickListener = object : CategoryAdapter.OnItemClickListener {
        override fun onItemClick(view: View, position: Int) {
            val catListIntent = Intent(this@MainActivity, VenuesListActivity::class.java)

            val categoryModel: CategoryModel =   mCategoriesListNw[position]

            val mBundle = Bundle()
            mBundle.putParcelable(CATEGORY_MODEL_KEY, categoryModel)
            catListIntent.putExtras(mBundle)

            startActivity(catListIntent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialiseUi()

        locationManager = getSystemService(Service.LOCATION_SERVICE) as LocationManager

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION)
        } else {
            // make API call with longitude and latitude
            locationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 2f, this)

            if (locationManager != null) {
                location = locationManager!!.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

                CategoryModel.latitude = location!!.latitude
                CategoryModel.longitude = location!!.longitude

                getCategories()
            } else {

                //TODO move this to Initialise function
                mVenueList = ArrayList()
                mAdapter = VenueRecyclerAdapter(this, mVenueList)
                items_list!!.adapter = mAdapter

                getVenuesAtLocation(Constants.DEFAULT_LOCATION)
            }
        }
    }

    private fun initialiseUi() {
        setSupportActionBar(toolbar)

        Handler(mainLooper).post { progressBar.visibility = View.VISIBLE }

//        COLUMN_COUNT = resources.getInteger(R.integer.grid_cols_count)
//        items_list!!.layoutManager = GridLayoutManager(this, COLUMN_COUNT)
//        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing)
//        items_list!!.addItemDecoration(SpaceItemDecoration(COLUMN_COUNT, spacingInPixels, true))

        staggeredLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        items_list.layoutManager = staggeredLayoutManager

        mCategoriesListNw = ArrayList()
        mAdapterNw = CategoryAdapter(this, mCategoriesListNw)
        items_list.adapter = mAdapterNw

        mAdapterNw.setOnItemClickListener(onItemClickListener)
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


    private fun getCategories() {
        val userLL = location!!.latitude.toString() + "," + location!!.longitude.toString()

        val categoryCall = FourSquareService.api.venuesCategories(userLL)

        categoryCall.enqueue(object : Callback<CategoryListResponse> {

            override fun onResponse(call: Call<CategoryListResponse>, response: Response<CategoryListResponse>) {
                progressBar.visibility = View.INVISIBLE

                makeCategoriesListNw(response.body())

                mAdapterNw!!.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<CategoryListResponse>, t: Throwable) {
                progressBar.visibility = View.INVISIBLE

                mCategoriesList.clear()

                mAdapterNw!!.notifyDataSetChanged()
                Toast.makeText(applicationContext, "Can't connect to Foursquare's servers!", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun getVenuesAtLocation(location: String) {

        val venueCall = FourSquareService.api.venuesNearLocation(location)

        venueCall.enqueue(object : Callback<FourSquareResponse> {
            override fun onResponse(call: Call<FourSquareResponse>, response: Response<FourSquareResponse>) {
                progressBar.visibility = View.INVISIBLE

                makeVenueModel(response.body())

                mAdapter!!.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<FourSquareResponse>, t: Throwable) {
                progressBar.visibility = View.INVISIBLE
                mVenueList.clear()
                mAdapter!!.notifyDataSetChanged()
                Toast.makeText(applicationContext, "Can't connect to Foursquare's servers!", Toast.LENGTH_LONG).show()
            }
        })
    }

    internal fun makeCategoriesListNw(apiResponse: CategoryListResponse) {
        mCategoriesListNw.clear()

        val categories = apiResponse.response!!.categories!![0].categories

        for (i in categories!!.indices) {
            val category = categories!![i]

            mCategoriesListNw.add(CategoryModel(category.id!!, category.name!!, category.icon!!))
        }
    }

    internal fun makeVenueModel(apiResponse: FourSquareResponse) {
        mVenueList.clear()

        val items = apiResponse.response!!.groups!![0].items

        for (i in items!!.indices) {
            val venue = items[i].venue
            mVenueList.add(VenueModel(venue!!.name!!, venue.rating!!, venue.categories!!))
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == REQUEST_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    //make api call
                    locationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 2f, this)

                    if (locationManager != null) {
                        location = locationManager!!.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

                        CategoryModel.latitude = location!!.latitude
                        CategoryModel.longitude = location!!.longitude

                        getCategories()

                    } else {
                        getVenuesAtLocation(Constants.DEFAULT_LOCATION)
                    }
                }
            } else {
                Toast.makeText(this, getString(R.string.permission_notice), Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onLocationChanged(location: Location) {
        this.location = location
    }

    override fun onStatusChanged(s: String, i: Int, bundle: Bundle) {}

    override fun onProviderEnabled(s: String) {}

    override fun onProviderDisabled(provider: String) {
        if (provider == LocationManager.GPS_PROVIDER) {
            showGPSDisabledAlertToUser()
        }
    }

    private fun showGPSDisabledAlertToUser() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setMessage("GPS is disabled in your device. Would you like to enable it?")
                .setCancelable(false)
                .setPositiveButton("Goto Settings Page To Enable GPS") { dialog, id ->
                    val callGPSSettingIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    startActivity(callGPSSettingIntent)
                }
        alertDialogBuilder.setNegativeButton("Cancel") { dialog, id -> dialog.cancel() }
        val alert = alertDialogBuilder.create()
        alert.show()
    }
}
