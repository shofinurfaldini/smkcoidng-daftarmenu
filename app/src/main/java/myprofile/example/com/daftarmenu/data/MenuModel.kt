package myprofile.example.com.daftarmenu.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MenuModel (
    @PrimaryKey(autoGenerate = true)
    var idMenuModel: Int?=null,
    var namaMenu:String,
    var hargaMenu:String,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var gambarMenu:ByteArray?
)

