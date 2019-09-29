package myprofile.example.com.daftarmenu.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MenuMinumanModel (
    @PrimaryKey(autoGenerate = true)
    var idMenuModel: Int,
    var namaMenu:String,
    var hargaMenu:String,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var gambarMenu:ByteArray
)

