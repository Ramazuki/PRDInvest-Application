package com.example.microServDataContent.Model

import jakarta.persistence.*

/**
 * DTO Model
 */
@Entity
@Table(name = "StockTable")
data class Stock(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id:Int = -1,
    val userId:Int = -1,
    val companyName:String = "",
    val latestPrice:Int = 0,
    val totalValue:Int = 0,
)

