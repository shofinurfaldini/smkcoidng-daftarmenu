package myprofile.example.com.daftarmenu.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.minuman_fragmen.rv_minuman
import myprofile.example.com.daftarmenu.R.layout
import myprofile.example.com.daftarmenu.adapter.RvAdapterMakanan
import myprofile.example.com.daftarmenu.data.MenuModel

class MinumanFragmen :Fragment () {

    companion object {
        fun getInstance(): MinumanFragmen {
            return MinumanFragmen()
        }
    }

    val dataMinuman = mutableListOf<MenuModel>()
    val rvAdapter = RvAdapterMakanan(dataMinuman)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(
            layout.minuman_fragmen,
            container, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_minuman.adapter = rvAdapter
        rv_minuman.layoutManager =
            LinearLayoutManager(context)
    }
}

