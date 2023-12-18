package com.example.myjetpackfront

import com.google.gson.annotations.SerializedName


data class UserDto (

  @SerializedName("name"   ) var name   : String?           = null,
  @SerializedName("tikers" ) var tikers : ArrayList<String> = arrayListOf()

)