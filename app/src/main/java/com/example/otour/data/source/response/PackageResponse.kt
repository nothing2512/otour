package com.example.otour.data.source.response

data class PackageResponse(
    val packageId: Int,
    val name: String,
    val date: DateResponse,
    val days: Int,
    val price: PriceResponse,
    val stock: Int,
    var image: String,
    val view: Int,
    val booked: Int,
    val rating: Int,
    val ownRate: Int,
    val hasView: Int,
    val route: List<RouteResponse>,
    val facilities: List<FacilityResponse>
)