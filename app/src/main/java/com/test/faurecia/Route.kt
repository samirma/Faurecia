package com.test.faurecia

object Route {

    // Define the route for the list screen.
    const val LIST = "appList"

    // Define the template for the detail screen route.
    private const val DETAIL_TEMPLATE = "appDetail/%s"

    // Define the argument key for the app ID.
    const val APPID_ARG = "appId"

    // Define the route for the detail screen.
    val DETAIL = String.format(DETAIL_TEMPLATE, "{$APPID_ARG}")

    // Function to get the detail screen URL by app ID.
    fun getDetailUrlById(appID: String) = String.format(DETAIL_TEMPLATE, appID)
}