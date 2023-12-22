package com.test.faurecia

object Route {

    const val LIST = "appList"

    private const val DETAIL_TEMPLATE = "appDetail/%s"
    val DETAIL = String.format(DETAIL_TEMPLATE, "{appId}")
    fun getDetailUrlById(appID: String) = String.format(DETAIL_TEMPLATE, appID)

}
