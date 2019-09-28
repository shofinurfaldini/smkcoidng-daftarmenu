package myprofile.example.com.daftarmenu.main

import android.text.Layout
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_list_menu.iv_gambar
import kotlinx.android.synthetic.main.item_list_menu.tv_harga
import kotlinx.android.synthetic.main.item_list_menu.tv_mie
import myprofile.example.com.daftarmenu.R
import myprofile.example.com.daftarmenu.data.MenuModel

class RvAdapter(private val data:List<MenuModel>) :
    RecyclerView.Adapter<RvAdapter.MenuViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        return MenuViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_menu,parent,false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    class MenuViewHolder (
        override val containerView: View)
        :RecyclerView.ViewHolder(containerView)
        ,LayoutContainer
    {
        fun bindData(item: MenuModel){
          tv_mie.text=item.namaMenu
          tv_harga.text=item.hargaMenu
          iv_gambar.setImageResource(item.gambarMenu)
        }
    }
}