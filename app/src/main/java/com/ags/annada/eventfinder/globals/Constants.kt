package com.ags.annada.eventfinder.globals

import kotlin.reflect.KClass

/**
 * Created by : annada
 * Date : 21/10/2017.
 */

object Constants {
    const val BASE_URL = "https://api.foursquare.com/v2/"

    const val VENUE_ENDPOINT = "venues/explore?"

    const val VENUE_CATEGORIES_ENDPOINT = "venues/categories?"

    const val VENUES_BY_CATEGORIES_ENDPOINT = "search/recommendations?"

    const val CLIENT_ID = "YOUR_CLINT_ID"

    const val CLIENT_SECRETE = "YOUR_CLIENT_SECRETE"

    const val DEFAULT_LOCATION = "London"

    val CATEGORY_MODEL_KEY = "CATEGORY_MODEL_DATA"

    val MAPS_BUNDLE_KEY = "MAPS_BUNDLE_KEY"
}

enum class CategoryMedia private constructor(name: String, url: String) {

    AMPHITHEATER("Amphitheater", "url1"),
    AQUARIUM("Aquarium", "url2"),
    ARCADE("Arcade", "url3"),
    ARTGALLERY("Art Gallery", "url4"),
    BOWLINGALLEY("Bowling Alley", "url5"),
    CASINO("Casino", "url6"),
    CIRCUS("Circus", "url7"),
    COMEDYCLUB("Comedy Club", "url8"),
    CONCERTHALL("Concert Hall", "url9"),
    COUNTRYDANCECLUB("Country Dance Club", "url10"),
    DISCGOLF("Disc Golf", "url11"),
    EXHIBIT("Exhibit", "url12"),
    GENERALENTERTAINMENT("General Entertainment", "url13"),
    GOKARTTRACK("Go Kart Track", "url14"),
    HISTORICSITE("Historic Site", "url15"),
    LASERTAG("Laser Tag", "url16"),
    MEMORIALSITE("Memorial Site", "url17"),
    MINIGOLF("Mini Golf", "url18"),
    MOVIETHEATER("Movie Theatre", "url19"),
    MUSEUM("Museum", "url20"),
    MUSICVENUE("Music Venue", "url21"),
    PERFORMINGARTSVENUE("Performing Arts Venue", "url22"),
    POOLHALL("Pool Hall", "url23"),
    PUBLICART("Public Art", "url24"),
    RACECOURSE("Racecourse", "url25"),
    RACETRACK("Racetrack", "url26"),
    ROLLERRINK("Roller Rink", "url27"),
    SALSACLUB("Salsa Club", "url28"),
    STADIUM("Stadium", "url29"),
    THEMEPARK("Theme Park", "url30"),
    TOURPROVIDER("Tour Provider", "url31"),
    WATERPARK("Water Park", "url32"),
    ZOO("zoo", "url33");

    var url: String
        internal set

    init {
        this.url = url
    }

    fun <T : Enum<*>> KClass<T>.contains(value: String): Boolean {
        return this.java.enumConstants.any { it.name == value }
    }

}

