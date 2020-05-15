package com.vllenin.database.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/**
 * Created by Vllenin on 5/15/20.
 */
@Dao
interface UserDAO {

    @Query("SELECT * FROM table_users")
    fun getAllUser(): Array<User>

    @Query("SELECT * FROM table_users WHERE id IN (:ids)")
    fun findUserByIds(vararg ids: Long): Array<User>

    @Query("SELECT * FROM table_users WHERE first_name LIKE (:firstName) AND last_name LIKE (:lastName) LIMIT 5")
    fun findUserByName(firstName: String, lastName: String): Array<User>

    @Insert
    fun insert(vararg users: User)

    @Delete
    fun delete(vararg users: User)

    @Query("DELETE FROM table_users")
    fun deleteAllUser()

}