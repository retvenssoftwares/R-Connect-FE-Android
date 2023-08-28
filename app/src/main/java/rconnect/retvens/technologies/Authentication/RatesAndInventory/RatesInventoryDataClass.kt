package rconnect.retvens.technologies.Authentication.RatesAndInventory

data class RatesInventoryDataClass(

    val otaName:String,
    val otaCode:String,
    val rateType:String,
    val currency:String,
    val roomDetails:List<RoomDetailsData>
)

data class RoomDetailsData(
    val roomType: String,
    val roomPlan: String,
    val day1Inventory: String,
    val day1Price: String,
    val day2Inventory: String,
    val day2Price: String,
    val day3Inventory: String,
    val day3Price: String,
    val day4Inventory: String,
    val day4Price: String,
    val day5Inventory: String,
    val day5Price: String,
    val day6Inventory: String,
    val day6Price: String,
    val day7Inventory: String,
    val day7Price: String
)
