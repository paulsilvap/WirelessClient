package com.android.wireless.client

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import com.android.hostcardemulator.R
import kotlinx.android.synthetic.main.confirmation.*

class Confirmation:AppCompatActivity(){

    companion object {
        var status:String? = null
        var amount:String? = null
        var establishment_name:String? = null
        var transaction_id:String? = null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.confirmation)

        Log.d("Confirmation Activity", "I'm here")

        establishment_name = intent.getStringExtra("establishment_name")
        transaction_id = intent.getStringExtra("transaction_id")
        amount = intent.getStringExtra("amount")
        text_amount.append("Ammount to pay: " + amount)
        text_establishment_name.append("Payment request from: " + establishment_name)
        var buttonAccept: Button = findViewById(R.id.accept)
        buttonAccept.setOnClickListener {
            status = "accept"
            consumeService()
        }
        var buttonCancel: Button = findViewById(R.id.cancel)
        buttonCancel.setOnClickListener{
            status = "cancel"
            consumeService()
        }

    }

    fun consumeService() {
        val serviceTask = ServiceTask(this, "http://172.23.204.203:8000/confirmation", status, transaction_id, amount, establishment_name)
        serviceTask.execute()
    }

}