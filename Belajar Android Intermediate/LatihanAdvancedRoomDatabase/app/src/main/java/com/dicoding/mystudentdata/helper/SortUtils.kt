package com.dicoding.mystudentdata.helper

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    fun getShortedQuery(shortType: SortType) : SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM student ")
        when (shortType) {
            SortType.ASCENDING -> {
                simpleQuery.append("ORDER BY name ASC")
            }
            SortType.DESCENDING -> {
                simpleQuery.append("ORDER BY name DESC")
            }
            SortType.RANDOM -> {
                simpleQuery.append("ORDER BY RANDOM()")
            }
        }

        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}