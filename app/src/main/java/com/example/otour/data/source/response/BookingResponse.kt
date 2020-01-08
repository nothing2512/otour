package com.example.otour.data.source.response

data class BookingResponse(
    val bookingId: Int,
    val name: String,
    var image: String,
    val date: DateResponse,
    val days: Int,
    val user: String,
    val amount: Int,
    val price: PriceResponse,
    val status: Int,
    val route: List<RouteResponse>,
    val facilities: List<FacilityResponse>
)