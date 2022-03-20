package com.wahyuindra.crudfirebaseuts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var auth: FirebaseAuth? = null
    private val RC_SIGN_IN = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogout.setOnClickListener(this)
        btnSimpan.setOnClickListener(this)
        btnShowData.setOnClickListener(this)

        auth = FirebaseAuth.getInstance()

    }

    private fun isEmpty(s: String): Boolean {
        return TextUtils.isEmpty(s)
    }

    override fun onClick(v: View) {
        when (v.getId()) {
            R.id.btnSimpan -> {

                val getUserID = auth!!.currentUser!!.uid
                val database = FirebaseDatabase.getInstance()
                val getNama: String = etNama.getText().toString()
                val getMerk: String = etMerk.getText().toString()
                val getChipset: String = etChipset.getText().toString()
                val getRAM: String = etRAM.getText().toString()
                val getHarga: String = etHarga.getText().toString()

                val getReference: DatabaseReference
                getReference = database.reference

                if (isEmpty(getNama)) {
                    Toast.makeText(this@MainActivity, "Nama harus diisi !!", Toast.LENGTH_SHORT).show()
                } else {
                    getReference.child("Admin").child(getUserID).child("Handphone").push()
                        .setValue(data_handphone(getNama, getMerk, getChipset, getRAM, getHarga))
                        .addOnCompleteListener(this) {
                            etNama.setText("")
                            etMerk.setText("")
                            etChipset.setText("")
                            etRAM.setText("")
                            etHarga.setText("")
                            Toast.makeText(this@MainActivity, "Data Tersimpan", Toast.LENGTH_SHORT).show()
                        }
                }


            } R
            .id.btnLogout ->

            AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(object : OnCompleteListener<Void> {
                    override fun onComplete(p0: Task<Void>) {
                        Toast.makeText(this@MainActivity, "Logout Berhasil", Toast.LENGTH_SHORT).show()
                                intent = Intent(applicationContext,
                            LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                })
            R.id.btnShowData -> {
                startActivity(Intent(this@MainActivity, MyListData::class.java))

            }
        }
    }

}