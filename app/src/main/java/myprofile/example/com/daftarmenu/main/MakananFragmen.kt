package myprofile.example.com.daftarmenu.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.makanan_fragmen.rv_makanan
import myprofile.example.com.daftarmenu.R
import myprofile.example.com.daftarmenu.R.layout
import myprofile.example.com.daftarmenu.data.MenuModel

class MakananFragmen: Fragment () {

    companion object{
        fun getInstance(): MakananFragmen {
            return MakananFragmen()
        }
    }

    val dataMakanan= mutableListOf<MenuModel>()
    val rvAdapter=RvAdapter(dataMakanan)

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

        addDummyData ()
    }

    private fun addDummyData() {
            dataMakanan.add(
                MenuModel(
                    "Jajangmyeon","Rp. 20.000", R.drawable.jajangmyeon))
            dataMakanan.add(
                MenuModel(
                    "Ramen","Rp. 15.000", R.drawable.ramen))
            dataMakanan.add(
                MenuModel(
                    "Ramyeon","Rp. 10.000", R.drawable.ramyeon))

        rvAdapter.notifyDataSetChanged()
    }
}