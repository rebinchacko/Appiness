package com.rebin.appies.ui

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.common.base.Predicate
import com.google.common.collect.Collections2
import com.rebin.appies.Adaptor.CustomAdaptor
import com.rebin.appies.Interface.Clickposition
import com.rebin.appies.R
import com.rebin.appies.network.Heirarchy
import com.rebin.appies.viewmodel.MainViewModel
import java.util.*


class MainActivity : AppCompatActivity(),Clickposition {

    lateinit var viewModel: MainViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var ivsearch:ImageView
    lateinit var etsearch:EditText
    lateinit var customAdaptor: CustomAdaptor
    lateinit var clickposition: Clickposition
    lateinit var progressDialog : ProgressDialog
    var datalist: MutableList<Heirarchy> = mutableListOf()
    var datalist111: MutableList<Heirarchy> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clickposition=this
        viewModel=ViewModelProvider(this).get(MainViewModel::class.java)
        recyclerView=findViewById(R.id.rv1)
        ivsearch=findViewById(R.id.ivsearch)
        etsearch=findViewById(R.id.edsearch)

        progressDialog=ProgressDialog(this)
        progressDialog.setMessage("Please wait")
        progressDialog.show()

        viewModel.getdata()

        viewModel.merror.observe(this, Observer {
            showtoast(it)
            progressDialog.dismiss()
        })
        viewModel.mresponse.observe(this, Observer {
            progressDialog.dismiss()
            if(it.dataObject.get(0).myHierarchy.get(0).heirarchyList.size>0) {
                datalist.addAll(it.dataObject.get(0).myHierarchy.get(0).heirarchyList)
                customAdaptor = CustomAdaptor(datalist, clickposition as MainActivity)
                recyclerView.adapter = customAdaptor

            }
            else{
                showtoast("No Data retrived...")
            }
        })

        ivsearch.setOnClickListener {
            datalist111= datalist.filter{ it.contactName.equals(etsearch.text.toString().capitalize())} as MutableList<Heirarchy>
            customAdaptor=CustomAdaptor(datalist111, clickposition as MainActivity)
            recyclerView.adapter=customAdaptor
            if(datalist111.equals(null)){
                showtoast("No Employee Found.")
            }
            if(etsearch.text.toString()==""){
                datalist.clear()
                progressDialog.show()
                viewModel.getdata()
            }
        }
    }

    override fun phonecall(contactNumber: String) {
        if (!contactNumber.equals("")) {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$contactNumber")
            startActivity(intent)
        }
        else{
            showtoast("No number attached")
        }
    }

    private fun showtoast(s: String) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show()

    }

    override fun message(contactNumber: String) {

        if (!contactNumber.equals("")) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("sms:$contactNumber")
            startActivity(intent)
        }
        else{
            showtoast("No number attached")
        }
    }
}