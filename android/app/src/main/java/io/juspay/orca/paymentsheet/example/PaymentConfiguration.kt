package io.juspay.orca.paymentsheet.example

import android.content.Context

class PaymentConfiguration(context:Context,publishableKey:String) {
    companion object {
        fun init(
            context: Context,
            publishableKey: String,
            stripeAccountId: String? = null
        ) {
        }
    }

}
