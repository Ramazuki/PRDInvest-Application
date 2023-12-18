package com.example.myjetpackfront

import com.google.gson.annotations.SerializedName


data class Data (

  @SerializedName("name"    ) var name    : String? = null,
  @SerializedName("price"   ) var price   : Double? = null,
  @SerializedName("imgLink" ) var imgLink : String? = null

)