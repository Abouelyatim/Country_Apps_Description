package com.example.geomob.db.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.geomob.db.entity.Country

@Dao
interface CountryDAO {
    @Query("SELECT * FROM country")
    fun getCountries(): List<Country>

    @Query("SELECT count(*) FROM country")
    fun getCountriesCount(): Int

    @Query("SELECT * FROM country WHERE id = :id")
    fun getCountry(id: Int): Country

    @Query("DELETE FROM country")
    fun flushCountryData()

    @Insert(onConflict = REPLACE)
    fun insert(country : Country)

    /*
    * Insert a list of countries in country table.
    * */
    @Insert(onConflict = REPLACE)
    fun insertCountryList(list: List<Country>)

    @Update
    fun update(country : Country)

    @Delete
    fun delete(country: Country)
}