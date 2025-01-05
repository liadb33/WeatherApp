package com.example.weatherapp.Activities

import android.animation.LayoutTransition
import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import android.text.Spannable
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.text.style.SuperscriptSpan
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.LayoutAnimationController
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.weatherapp.Adapters.ViewNavAdapter
import com.example.weatherapp.Data.HourForecast
import com.example.weatherapp.Data.LocationSearchResult
import com.example.weatherapp.Data.WeatherResponse
import com.example.weatherapp.Fragments.SearchResultFragment
import com.example.weatherapp.Fragments.TodayFragment
import com.example.weatherapp.Fragments.TomorrowFragment
import com.example.weatherapp.Interfaces.LocationUpdateCallback
import com.example.weatherapp.Interfaces.SearchSuggestionClicked
import com.example.weatherapp.R
import com.example.weatherapp.Utilities.LocationManager
import com.example.weatherapp.Utilities.RetrofitClient
import com.example.weatherapp.Utilities.SharedPreferencesManagerV3
import com.example.weatherapp.Utilities.WeatherIconMapper
import com.example.weatherapp.Utilities.WeatherWorker
import com.example.weatherapp.databinding.ActivityMainBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.textview.MaterialTextView
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    private lateinit var todayFragment : TodayFragment
    private lateinit var tomorrowFragment : TomorrowFragment
    private lateinit var searchView: SearchView
    private lateinit var searchItem: MenuItem
    private lateinit var searchResultFragment: SearchResultFragment

    private lateinit var gson: Gson


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        todayFragment = TodayFragment()
        tomorrowFragment = TomorrowFragment()

        initViews()
        setSupportActionBar(binding.mainToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun initViews() {


        gson = Gson()
        scheduleWeatherWorker()

        binding.mainViewpager2.isUserInputEnabled = false
        supportActionBar?.setDisplayShowTitleEnabled(false)
        // In your Activity's onCreate method
        setTabNavigator()

        // initializes searchResult fragment
        searchResultFragment = SearchResultFragment()
        searchResultFragment.searchSuggestionClicked = object : SearchSuggestionClicked {
            override fun onSearchSuggestionClicked(name: String, country: String) {
                getCurrentWeather("$name,$country")
                getForecastAhead("$name,$country")
                LocationManager.getInstance().stopLocationUpdates()
                searchResultFragment.view?.visibility = View.GONE
                searchView.onActionViewCollapsed()
                searchItem.collapseActionView()
            }
        }
        //attaches the fragment to the activity
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_search_result_FRAME, searchResultFragment)
            .commit()

        binding.mainSearchResultFRAME.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)


        binding.mainBUTTONNext.setOnClickListener {
            if (binding.mainLocationText.text.isNotEmpty()) {
                val intent = Intent(this, NextSevenForecast::class.java)
                intent.putExtra("location", binding.mainLocationText.text.toString())
                startActivity(intent)
            }
        }


    }

    private fun scheduleWeatherWorker() {

        // defines constraints
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        //periodic work request
        val weatherWorkRequest = PeriodicWorkRequest.Builder(
            WeatherWorker::class.java, 15, TimeUnit.MINUTES
        ).setConstraints(constraints).build()

        //enqueues the work request
        WorkManager.getInstance(this).enqueue(weatherWorkRequest)
    }

    private fun testServerConnection() {
        RetrofitClient.instance.getCurrentWeather("london")
            .enqueue(object : Callback<WeatherResponse> {
                override fun onResponse(
                    call: Call<WeatherResponse>,
                    response: Response<WeatherResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.d(
                            "TEST_GET",
                            "Successfully connected to the server and got the data! ${response.body()}"
                        )
                    } else {
                        Log.e(
                            "TEST_GET",
                            "Failed to connect to the server. Response code: ${response.code()}"
                        )
                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    Log.e("TEST_GET", "Error connecting to the server: ${t.message}", t)
                }
            })
    }

    fun hideSearchFragment() {
        if (searchResultFragment.isVisible)
            searchResultFragment.view?.visibility = View.GONE
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        searchItem = menu?.findItem(R.id.main_search)!!
        searchView = searchItem.actionView as SearchView

        searchView.queryHint = "Type here..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    binding.mainShimmerLayout.startShimmer()
                    todayFragment.startShimmering()
                    getCurrentWeather(query)
                    getForecastAhead(query)
                    hideSearchFragment()
                }
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if (newText != null) {
                    if (newText.isEmpty()) {
                        hideSearchFragment()
                    } else {
                        handleSearchQuery(newText)
                    }
                }
                return true
            }

        })

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (!searchView.isIconified) {
                    searchView.isIconified = true
                    searchView.onActionViewCollapsed()
                } else {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                    isEnabled = true
                }
            }

        })

        return true
    }

    fun handleSearchQuery(newText: String) {
        searchResultFragment.view?.visibility = View.VISIBLE
        RetrofitClient.instance.searchAutoComplete(query = newText)
            .enqueue(object : Callback<List<LocationSearchResult>> {
                override fun onResponse(
                    p0: Call<List<LocationSearchResult>>,
                    p1: Response<List<LocationSearchResult>>
                ) {
                    p1.body().let {
                        if (it != null) {
                            searchResultFragment.loadSearchResult(it)
                        }
                    }
                }

                override fun onFailure(p0: Call<List<LocationSearchResult>>, p1: Throwable) {

                }

            })
    }

    private fun getForecastAhead(location: String) {

        RetrofitClient.instance.getForecastWeather(query = location)
            .enqueue(object : Callback<WeatherResponse> {
                override fun onResponse(p0: Call<WeatherResponse>, p1: Response<WeatherResponse>) {
                    p1.body()?.let {
                        updateTabUi(it)
                    }
                }

                override fun onFailure(p0: Call<WeatherResponse>, p1: Throwable) {
                    //pass
                }

            })
    }

    private fun updateTabUi(weatherResponse: WeatherResponse) {

        todayFragment.loadHourForecast(weatherResponse.forecast.forecastday[0].hour)
        tomorrowFragment.loadHourForecast(weatherResponse.forecast.forecastday[1].hour)
    }


    private fun setTabNavigator() {

        val viewNavAdapter = ViewNavAdapter(this)
        viewNavAdapter.addFragment(todayFragment, "Today")
        viewNavAdapter.addFragment(tomorrowFragment, "Tomorrow")

        binding.mainViewpager2.adapter = viewNavAdapter
        TabLayoutMediator(binding.mainTabLayout, binding.mainViewpager2) { tab, position ->
            tab.text = viewNavAdapter.getPageTitle(position)
        }.attach()
    }

    //gets current weather according to location received
    private fun getCurrentWeather(location: String) {

        RetrofitClient.instance.getCurrentWeather(query = location).enqueue(object :
            Callback<WeatherResponse> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: retrofit2.Response<WeatherResponse>
            ) {
                response.body()?.let {
                    updateMainUi(it)
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, response: Throwable) {
                //pass
            }
        })
        binding.mainTABLayoutNext.visibility = View.GONE
        binding.mainLLC.visibility = View.GONE
        binding.mainShimmerLayout.visibility = View.VISIBLE
        binding.mainShimmerLayout.startShimmer()
    }

    private fun formatTemperatureText(temperature: Double): SpannableString {
        val temperatureText = "${temperature}°C"
        val spannableString = SpannableString(temperatureText)
        val superscriptSpan = SuperscriptSpan()
        val relativeSize = RelativeSizeSpan(0.5f)  // Makes the °C half the size

        spannableString.setSpan(
            superscriptSpan,
            temperatureText.indexOf("°"),
            temperatureText.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(
            relativeSize,
            temperatureText.indexOf("°"),
            temperatureText.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        return spannableString
    }


    private fun formatWeatherDate(input: String): String {

        val inputFormat = "yyyy-MM-dd HH:mm"
        val outputFormat = "EEE, MMM dd, yyyy, HH:mm"

        // Create DateTimeFormatter for input and output formats
        val inputFormatter = DateTimeFormatter.ofPattern(inputFormat)
        val outputFormatter = DateTimeFormatter.ofPattern(outputFormat)

        // Parse the input string to LocalDateTime
        val dateTime = LocalDateTime.parse(input, inputFormatter)

        // Format the LocalDateTime to the desired output string
        return dateTime.format(outputFormatter)
    }

    override fun onResume() {
        super.onResume()

        testServerConnection()
        //request permission to location
        LocationManager.getInstance().requestLocationPermission(this)


        //checks if last location data is available in shared pref
        val lastWeather: String =
            SharedPreferencesManagerV3.getInstance().getString("lastWeather", "")
        val hourlyForecast: String = SharedPreferencesManagerV3.getInstance().getString("hourlyForecast", "")

        if (lastWeather.isNotEmpty() && hourlyForecast.isNotEmpty()) {
            updateMainUi(gson.fromJson(lastWeather, WeatherResponse::class.java))
            updateTabUi(gson.fromJson(hourlyForecast, WeatherResponse::class.java))
        } else { // if there isnt last location data in shared pref, gets current location
            //sets a new location update callback to LocationManager
            LocationManager.getInstance().locationUpdateCallback = object : LocationUpdateCallback {
                override fun onLocationUpdated(latitude: Double, longitude: Double) {

                    getCurrentWeather("${latitude},${longitude}")
                    getForecastAhead("${latitude},${longitude}")
                }
            }
            //retrieves location
            LocationManager.getInstance().getDeviceLocation()
        }


    }

    @SuppressLint("SetTextI18n")
    private fun updateMainUi(weatherResponse: WeatherResponse) {
        binding.mainLocationText.text = weatherResponse.location.name + "," + weatherResponse.location.country
        binding.mainWeatherDegreeText.text = formatTemperatureText(weatherResponse.current.temp_c)
        binding.mainRainfallText.text = weatherResponse.current.precip_mm.toString() + "mm"
        binding.mainWindText.text = weatherResponse.current.wind_kph.toString() + "km/h"
        binding.mainHumidityText.text = weatherResponse.current.humidity.toString() + "%"
        binding.mainDateText.text = formatWeatherDate(weatherResponse.location.localtime)
        binding.weatherDetailsIMG.setImageResource(WeatherIconMapper.getWeatherIcon(weatherResponse.current.condition.text))
        binding.mainFeelsLikeText.text = "Feels like " + weatherResponse.current.feelslike_c.toString() + "°"
        binding.mainWeatherDescriptionText.text = weatherResponse.current.condition.text
        binding.mainTABLayoutNext.visibility = View.VISIBLE
        binding.mainShimmerLayout.visibility = View.GONE
        binding.mainLLC.visibility = View.VISIBLE
        binding.mainShimmerLayout.stopShimmer()
    }


    override fun onPause() {
        super.onPause()
        LocationManager.getInstance().stopLocationUpdates()
    }

    override fun onDestroy() {
        super.onDestroy()
        LocationManager.getInstance().stopLocationUpdates()
    }
}