package com.example.rnandroiddemoapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import io.juspay.mylibrary.PaymentConfiguration
import io.juspay.mylibrary.PaymentSheet


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val paymentSheet = PaymentSheet(this)

        setContentView(R.layout.activity_main)
        val btnGoRNScreen = findViewById<Button>(R.id.btnGoRNScreen)

        btnGoRNScreen.setOnClickListener {
            Log.d("onclick", "1")
            prepareCheckout { customerConfig, clientSecret ->
                Log.d("onclick", "2")
                paymentSheet.presentWithPaymentIntent(
                    clientSecret,
                    PaymentSheet.Configuration(
                        merchantDisplayName = "merchantName",
                        customer = customerConfig,
                        allowsDelayedPaymentMethods = true
                    ),
                    MyReactActivity::class.java
                )
                Log.d("onclick", "5")

            }
        }
    }


    protected fun prepareCheckout(
        onSuccess: (PaymentSheet.CustomerConfiguration, String) -> Unit
    ) {
        Log.d("onclick", "3")

        var customerConfiguration=PaymentSheet.CustomerConfiguration(
            id = "customer",
            ephemeralKeySecret = "ephemeralKey"
        )
        PaymentConfiguration.init(this, "publishableKey")
        onSuccess(
            customerConfiguration,
            "paymentIntent"
        )
        Log.d("onclick", "4")

    }

}



