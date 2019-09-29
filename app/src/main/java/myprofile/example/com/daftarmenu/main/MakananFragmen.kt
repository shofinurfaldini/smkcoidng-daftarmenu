package myprofile.example.com.daftarmenu.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.makanan_fragmen.rv_makanan
import myprofile.example.com.daftarmenu.R.layout
import myprofile.example.com.daftarmenu.adapter.RvAdapterMakanan
import myprofile.example.com.daftarmenu.data.MenuDB
import myprofile.example.com.daftarmenu.data.MenuModel

class MakananFragmen: Fragment () {

    companion object{
        fun getInstance(): MakananFragmen {
            return MakananFragmen()
        }
    }

    val dataMakanan= mutableListOf<MenuModel>()

    var db:MenuDB?=null
    val rvAdapter= RvAdapterMakanan(dataMakanan)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(
            layout.makanan_fragmen,
            container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            rv_makanan.adapter=rvAdapter
            rv_makanan.layoutManager=
                LinearLayoutManager(context)

        db= MenuDB.getInstance(context!!)
        getMenuMakanan()
    }
    private fun getMenuMakanan(){
        db?.menuDao()?.ambilMenuMakanan()
            ?.observe(this, Observer { hasil->
                when(hasil.size==0) {
                    true -> {
                        Toast.makeText(
                            context,
                            "Data Makanan Masih Kosong",
                            Toast.LENGTH_SHORT
                        ).show()}
                        false->{
                        dataMakanan.clear()
                        dataMakanan.addAll(hasil)
                        rvAdapter
                            .notifyDataSetChanged()

                        }
                    }

            })
}
    }







