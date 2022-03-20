package com.wahyuindra.crudfirebaseuts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_update_data.*

class UpdateData : AppCompatActivity() {

    private var database: DatabaseReference? = null
    private var auth: FirebaseAuth? = null
    private var uNama: String? = null
    private var uMerk: String? = null
    private var uChipset: String? = null
    private var uRAM: String? = null
    private var uHarga: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_data)
        supportActionBar!!.title = "Update Data"

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference
        data

        btnUpdate.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                uNama = etNama.getText().toString()
                uMerk = etMerk.getText().toString()
                uChipset = etChipset.getText().toString()
                uRAM = etRAM.getText().toString()
                uHarga = etHarga.getText().toString()

                if (isEmpty(uNama!!)) {
                    Toast.makeText(this@UpdateData, "Nama harus diisi", Toast.LENGTH_SHORT).show()
                } else {
                    val setHandphone = data_handphone()
                    setHandphone.nama = etNama.getText().toString()
                    setHandphone.merk = etMerk.getText().toString()
                    setHandphone.chipset = etChipset.getText().toString()
                    setHandphone.RAM = etRAM.getText().toString()
                    setHandphone.harga = etHarga.getText().toString()
                    updateHandphone(setHandphone)
                }
            }
        })
    }

    private fun isEmpty(s: String): Boolean {
        return TextUtils.isEmpty(s)
    }

    private val data: Unit
        private get() {

            val getNama = intent.extras!!.getString("dataNama")
            val getMerk = intent.extras!!.getString("dataMerk")
            val getChipset = intent.extras!!.getString("dataChipset")
            val getRAM = intent.extras!!.getString("dataRAM")
            val getHarga = intent.extras!!.getString("dataHarga")
            etNama!!.setText(getNama)
            etMerk!!.setText(getMerk)
            etChipset!!.setText(getChipset)
            etRAM!!.setText(getRAM)
            etHarga!!.setText(getHarga)
        }

    private fun updateHandphone(handphone: data_handphone) {
        val userID = auth!!.uid
        val getKey = intent.extras!!.getString("getPrimaryKey")
        database!!.child("Admin")
            .child(userID!!)
            .child("Handphone")
            .child(getKey!!)
            .setValue(handphone)
            .addOnSuccessListener {
                etNama!!.setText("")
                etMerk!!.setText("")
                etChipset!!.setText("")
                etRAM!!.setText("")
                etHarga!!.setText("")
                Toast.makeText(this@UpdateData, "Data Berhasil diubah",
                    Toast.LENGTH_SHORT).show()
                finish()
            }
    }


}