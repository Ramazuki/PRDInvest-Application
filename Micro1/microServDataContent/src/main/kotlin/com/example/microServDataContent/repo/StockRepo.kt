package com.example.microServDataContent.repo

import com.example.microServDataContent.Model.Stock
import org.springframework.data.repository.ListCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface StockRepo: ListCrudRepository<Stock, Long> {

    fun deleteByIdAndUserId(id:Int, userId:Int){}
    fun findAllByUserId(userId: Int):List<Stock>
}