package com.smartshopper.smart_shopper.model

data class Store(
    var storeName: String?=null,
    var productName: ArrayList<Product>,
)

data class Product(
    var name: String?=null,
    var price: String?=null,
)
