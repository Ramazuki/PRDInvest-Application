package com.example.myjetpackfront

import com.google.gson.annotations.SerializedName


data class Stocks (

  @SerializedName("data" ) var data : ArrayList<Data> = arrayListOf()

)