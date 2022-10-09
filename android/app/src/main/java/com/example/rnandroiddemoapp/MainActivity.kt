package com.example.rnandroiddemoapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData


class PaymentSheet2(intent123: Intent, startActivity: (intent: Intent) -> Unit) {
//    val e_SomeFn: (x: String) -> Unit
    val e_intent123: Intent
    val e_startActivity: (intent: Intent) -> Unit

    init {
//        e_SomeFn = someFn
      e_intent123 = intent123
        e_startActivity = startActivity
    }

    fun presentWithPaymentIntent(clientSecret: String, configuration: Any) {
        //   paymentSheetLauncher.presentWithPaymentIntent(paymentIntentClientSecret, configuration)
//    var intent123 = Intent( ComponentActivity@MainActivity, MyReactActivity::class.java)
        e_intent123.putExtra("clientSecret", "clientSssecret")
    e_startActivity(e_intent123)
//        e_SomeFn("hello")

    }

}


class PaymentSheet {
    data class CustomerConfiguration(
        val id: String,
        val ephemeralKeySecret: String
    )
    constructor(
        activity: ComponentActivity,
        someFn: (x: String) -> Unit,
        callback: MainActivity.PaymentSheetResultCallback
    )
    //                : this(
//            DefaultPaymentSheetLauncher(activity, callback)
//        )
    fun presentWithPaymentIntent(clientSecret: String, configuration: Any) {
        //   paymentSheetLauncher.presentWithPaymentIntent(paymentIntentClientSecret, configuration)
//    var intent123 = Intent( ComponentActivity@MainActivity, MyReactActivity::class.java)
//    intent123.putExtra("clientSecret", "clientSssecret")
//    startActivity(intent123)
//        this.someFn("hello")

    }
    class Configuration constructor(
        merchantDisplayName : String,
        customer : CustomerConfiguration,
        allowsDelayedPaymentMethods:Boolean
    )
}

class MainActivity : AppCompatActivity() {
    val x =this@MainActivity


    fun someFn(clientSssecret: String)  {
                var intent123 = Intent( this@MainActivity, MyReactActivity::class.java)
        intent123.putExtra("clientSecret", "clientSssecret")
        startActivity(intent123)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//        val paymentSheet = PaymentSheet(this,  ::onPaymentSheetResult)


        var intent123 = Intent( this@MainActivity, MyReactActivity::class.java)
        val paymentSheet = PaymentSheet2(intent123, ::startActivity)
        setContentView(R.layout.activity_main)
        val btnGoRNScreen = findViewById<Button>(R.id.btnGoRNScreen)

        btnGoRNScreen.setOnClickListener {
//            val intent = Intent(this@MainActivity, MyReactActivity::class.java)
//            intent.putExtra("message_from_native", "edt.text")
//            startActivity(intent)
            Log.d("onclick", "1")
            prepareCheckout { customerConfig, clientSecret ->
                Log.d("onclick", "2")
                paymentSheet.presentWithPaymentIntent(
                    clientSecret,
                    PaymentSheet.Configuration(
                        merchantDisplayName = "merchantName",
                        customer = customerConfig,
                        allowsDelayedPaymentMethods = true
                    )
                )
                Log.d("onclick", "5")

            }
        }
    }
    fun interface PaymentSheetResultCallback {
        fun onPaymentSheetResult(paymentSheetResult: PaymentSheetResult)
    }

     private fun onPaymentSheetResult(
        paymentResult: PaymentSheetResult
    ) {
         var a=paymentResult
    }

    val inProgress = MutableLiveData<Boolean>()
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

    companion object {
        const val merchantName = "Example, Inc."
        const val backendUrl = "https://stripe-mobile-payment-sheet.glitch.me/checkout"
    }
}

class PaymentConfiguration(temp:Any,publishableKey:String) {
companion object {
    fun init(
    context: Context,
    publishableKey: String,
    stripeAccountId: String? = null
) {
}
}

}

class PaymentSheetResult {

}
