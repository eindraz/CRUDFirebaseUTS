package com.wahyuindra.crudfirebaseuts

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter (private var listHandphone: ArrayList<data_handphone>, context: Context) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private var context: Context

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val Nama: TextView
        val Spek: TextView
        val Harga: TextView
        val ListItem: LinearLayout

        init {
            Nama = itemView.findViewById(R.id.lsNama)
            Spek = itemView.findViewById(R.id.lsSpek)
            Harga = itemView.findViewById(R.id.lsHarga)
            ListItem = itemView.findViewById(R.id.list_item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val V: View = LayoutInflater.from(parent.getContext()).inflate( R.layout.view_design, parent, false)
        return ViewHolder(V)
    }

    @SuppressLint("SetTextI18n")

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val Nama: String? = listHandphone.get(position).nama
        val Merk: String? = listHandphone.get(position).merk
        val Chipset: String? = listHandphone.get(position).chipset
        val RAM: String? = listHandphone.get(position).RAM
        val Harga: String? = listHandphone.get(position).harga

        holder.Nama.text = "$Merk $Nama"
        holder.Spek.text = "$Chipset $RAM"
        holder.Harga.text = "Harga: $Harga"

        holder.ListItem.setOnLongClickListener(object : View.OnLongClickListener {

            override fun onLongClick(v: View?): Boolean {

                holder.ListItem.setOnLongClickListener(object : View.OnLongClickListener {
                    override fun onLongClick(v: View?): Boolean {

                        holder.ListItem.setOnLongClickListener { view ->
                            val action = arrayOf("Update", "Delete")
                            val alert: AlertDialog.Builder = AlertDialog.Builder(view.context)
                            alert.setItems(action, DialogInterface.OnClickListener { dialog, i ->
                                when (i) {
                                    0 -> {
                                        val bundle = Bundle()
                                        bundle.putString("dataNama", listHandphone[position].nama)
                                        bundle.putString("dataMerk", listHandphone[position].merk)
                                        bundle.putString("dataChipset", listHandphone[position].chipset)
                                        bundle.putString("dataRAM", listHandphone[position].RAM)
                                        bundle.putString("dataHarga", listHandphone[position].harga)
                                        bundle.putString("getPrimaryKey", listHandphone[position].key)
                                        val intent = Intent(view.context, UpdateData::class.java)
                                        intent.putExtras(bundle)
                                        context.startActivity(intent)
                                    }
                                    1 -> {
                                        listener?.onDeleteData(listHandphone.get(position), position)
                                    }
                                }
                            })
                            alert.create()
                            alert.show()
                            true
                        }
                        return true;
                    }
                })


                return true
            }
        })
    }

    override fun getItemCount(): Int {
        return listHandphone.size
    }

    init {
        this.context = context
    }

    interface dataListener {
        fun onDeleteData(data: data_handphone?, position: Int)
    }

    var listener: dataListener? = null

    fun RecyclerViewAdapter(listHandphone: ArrayList<data_handphone>, context: Context?) {
        this.listHandphone = listHandphone!!
        this.context = context!!
        listener = context as MyListData?
    }


}