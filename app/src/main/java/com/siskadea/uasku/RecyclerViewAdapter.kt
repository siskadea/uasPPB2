package com.siskadea.uasku

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

//Class Adapter ini Digunakan Untuk Mengatur Bagaimana Data akan Ditampilkan
class RecyclerViewAdapter( private val listMahasiswa: ArrayList<data_member>,
                           context: Context
) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private val context: Context
    //ViewHolder Digunakan Untuk Menyimpan Referensi Dari View-View
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val Nama: TextView
        val Webku: TextView
        val Nohp: TextView
        val Email: TextView
        val Alamat: TextView
        val ListItem: LinearLayout
        init {//Menginisialisasi View yang terpasang pada layout RecyclerView kita
            Nama = itemView.findViewById(R.id.namax)
            Webku = itemView.findViewById(R.id.webkux)
            Nohp = itemView.findViewById(R.id.nohpx)
            Email = itemView.findViewById(R.id.emailx)
            Alamat = itemView.findViewById(R.id.alamatx)
            ListItem = itemView.findViewById(R.id.list_item)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
//Membuat View untuk Menyiapkan & Memasang Layout yang digunakan pada RecyclerView
        val V: View = LayoutInflater.from(parent.getContext()).inflate(
            R.layout.view_design, parent, false)
        return ViewHolder(V)
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//Mengambil Nilai/Value pada RecyclerView berdasarkan Posisi Tertentu
        val Nama: String? = listMahasiswa.get(position).nama
        val Webku: String? = listMahasiswa.get(position).webku
        val Nohp: String? = listMahasiswa.get(position).nohp
        val Email: String? = listMahasiswa.get(position).email
        val Alamat: String? = listMahasiswa.get(position).alamat
////Memasukan Nilai/Value kedalam View (TextView: NIM, Nama, Jurusan)
        holder.Nama.text = "Nama: $Nama"
        holder.Webku.text = "Webku: $Webku"
        holder.Nohp.text = "Nohp: $Nohp"
        holder.Email.text = "Email: $Email"
        holder.Alamat.text = "Alamat: $Alamat"


        holder.ListItem.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {
//Kodingan untuk membuat fungsi Edit dan Delete, yang akan dibahas pada Tutorial Berikutnya.
                holder.ListItem.setOnLongClickListener { view ->
                    val action = arrayOf("Update", "Delete")
                    val alert: AlertDialog.Builder = AlertDialog.Builder(view.context)
                    alert.setItems(action, DialogInterface.OnClickListener { dialog, i ->
                        when (i) {
                            0 -> {
/* Berpindah Activity pada halaman layout updateData dan mengambil data pada
listMahasiswa, berdasarkan posisinya untuk dikirim pada activity selanjutnya */
                                val bundle = Bundle()
                                bundle.putString("dataNama", listMahasiswa[position].nama)
                                bundle.putString("dataWebsite", listMahasiswa[position].webku)
                                bundle.putString("dataNohp", listMahasiswa[position].nohp)
                                bundle.putString("dataEmail", listMahasiswa[position].email)
                                bundle.putString("dataCatatan", listMahasiswa[position].alamat)
                                bundle.putString("getPrimaryKey", listMahasiswa[position].key)
                                val intent = Intent(view.context, UpdateData::class.java)
                                intent.putExtras(bundle)
                                context.startActivity(intent)
                            }
                            1 -> {
                                listener?.onDeleteData(listMahasiswa.get(position), position)
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
    }

    override fun getItemCount(): Int {
//Menghitung Ukuran/Jumlah Data Yang Akan Ditampilkan Pada RecyclerView
        return listMahasiswa.size
    }

    //Deklarasi objek dari Interfece
    var listener: dataListener? = null

    //Membuat Konstruktor, untuk menerima input dari Database
    init {
        this.context = context
        this.listener = context as MyListData
    }
    //Membuat Interfece
    interface dataListener {
        fun onDeleteData(data: data_member?, position: Int)
    }
    //Membuat Konstruktor, untuk menerima input dari Database
//    fun RecyclerViewAdapter(listMahasiswa: ArrayList<data_mahasiswa>?, context:
//    Context?) {
//        this.listMahasiswa = listMahasiswa!!
//        this.context = context!!
//        listener = context as MyListData?
//    }
}