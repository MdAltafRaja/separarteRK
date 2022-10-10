package io.juspay.orca.paymentsheet.example

import android.content.Intent
import androidx.activity.ComponentActivity
import com.example.rnandroiddemoapp.MyReactActivity

class PaymentSheet(private val activity: ComponentActivity) {

    fun presentWithPaymentIntent(clientSecret: String, configuration: Any) {
        var intent = Intent( activity, MyReactActivity::class.java)
        intent.putExtra("clientSecret", clientSecret)
        activity.startActivity(intent)
    }

    class Configuration constructor(
        merchantDisplayName : String,
        customer : PaymentSheet.CustomerConfiguration,
        allowsDelayedPaymentMethods:Boolean
    )

    data class CustomerConfiguration(
        val id: String,
        val ephemeralKeySecret: String
    )
}