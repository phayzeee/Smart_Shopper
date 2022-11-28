package com.smartshopper.smart_shopper.model

data class Deals(
    var storeName: String? = null,
    var product: ArrayList<ProductDeal>,
)

data class ProductDeal(
    var name: String? = null,
    var deal: String? = null,
    var duration: String? = null
)