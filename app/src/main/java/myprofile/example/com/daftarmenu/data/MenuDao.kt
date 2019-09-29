package myprofile.example.com.daftarmenu.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MenuDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun tambahMakanan(makananModel: MenuModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun tambahMinuman(minumanModel: MenuMinumanModel)

    @Query("select * from MenuModel")
    fun ambilMenuMakanan():LiveData<List<MenuModel>>

    @Query("select * from MenuMinumanModel")
    fun ambilMenuMinuman():LiveData<List<MenuMinumanModel>>
}