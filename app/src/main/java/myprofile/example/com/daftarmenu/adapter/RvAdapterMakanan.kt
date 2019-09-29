package myprofile.example.com.daftarmenu.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_list_menu.iv_gambar
import kotlinx.android.synthetic.main.item_list_menu.tv_harga
import kotlinx.android.synthetic.main.item_list_menu.tv_mie
import myprofile.example.com.daftarmenu.R.layout
import myprofile.example.com.daftarmenu.adapter.RvAdapterMakanan.MenuViewHolder
import myprofile.example.com.daftarmenu.data.MenuModel

class RvAdapterMakanan(private val data:List<MenuModel>) :
    RecyclerView.Adapter<MenuViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        return MenuViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(layout.item_list_menu, parent, false)
        )
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
            Glide.with(containerView)
                .load(item.gambarMenu)
                .into(iv_gambar)

         itemView.setOnClickListener{
             Toast.makeText(containerView.context,
                 item.namaMenu,Toast.LENGTH_SHORT)
                 .show()
         }
        }
    }
}