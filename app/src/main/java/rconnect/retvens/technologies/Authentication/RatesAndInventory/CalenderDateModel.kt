package rconnect.retvens.technologies.Authentication.RatesAndInventory

import java.util.Date

data class CalenderDateModel(
    var data: Date,
    var dayAbbreviation: String, // Add this property
    var date: String,
    var month: String
)