package io.juspay.orca.paymentsheet.example

import android.content.Intent
import android.os.Parcelable
import androidx.activity.ComponentActivity
import com.example.rnandroiddemoapp.MyReactActivity
import kotlinx.parcelize.Parcelize


//fun interface PaymentSheetResultCallback {
//    fun onPaymentSheetResult(paymentSheetResult: PaymentSheetResult)
//}
class PaymentSheet(private val activity: ComponentActivity,callback: PaymentSheetResultCallback) {

    internal interface PaymentSheetLauncher {
        fun presentWithPaymentIntent(
            paymentIntentClientSecret: String,
            configuration: Configuration? = null
        )

        fun presentWithSetupIntent(
            setupIntentClientSecret: String,
            configuration: Configuration? = null
        )
    }


//    val e_intent123: Intent
//    val e_startActivity: (intent: Intent) -> Unit
//
//    init {
//        e_intent123 = Intent()
//        e_startActivity = startActivity
//    }
//
//    fun presentWithPaymentIntent(clientSecret: String, configuration: Any) {
//        e_intent123.putExtra("clientSecret", "clientSssecret")
//        e_startActivity(e_intent123)
//    }
fun presentWithPaymentIntent(clientSecret: String, configuration: Configuration) {
    var intent = Intent( activity, MyReactActivity::class.java)
    intent.putExtra("clientSecret", clientSecret)
    activity.startActivity(intent)
}

    @Parcelize
    data class Configuration (
        val merchantDisplayName: String,
        val customer : CustomerConfiguration? = null,
        val allowsDelayedPaymentMethods: Boolean
    ): Parcelable

    @Parcelize
    data class GooglePayConfiguration(
        val environment: Environment,
        val countryCode: String,
        val currencyCode: String? = null
    ) : Parcelable {
        constructor(
            environment: Environment,
            countryCode: String
        ) : this(environment, countryCode, null)
            enum class Environment {
            Production,
            Test
        }
    }

    @Parcelize
    data class CustomerConfiguration(
        val id: String,
        val ephemeralKeySecret: String
    ): Parcelable

//    @Parcelize
//    data class Appearance(
//        val colorsLight: com.stripe.android.paymentsheet.PaymentSheet.Colors = com.stripe.android.paymentsheet.PaymentSheet.Colors.defaultLight,
//        val colorsDark: com.stripe.android.paymentsheet.PaymentSheet.Colors = com.stripe.android.paymentsheet.PaymentSheet.Colors.defaultDark,
//        val shapes: com.stripe.android.paymentsheet.PaymentSheet.Shapes = com.stripe.android.paymentsheet.PaymentSheet.Shapes.default,
//        val typography: com.stripe.android.paymentsheet.PaymentSheet.Typography = com.stripe.android.paymentsheet.PaymentSheet.Typography.default,
//        val primaryButton: com.stripe.android.paymentsheet.PaymentSheet.PrimaryButton = com.stripe.android.paymentsheet.PaymentSheet.PrimaryButton()
//    ) : Parcelable {
//        fun getColors(isDark: Boolean): com.stripe.android.paymentsheet.PaymentSheet.Colors {
//            return if (isDark) colorsDark else colorsLight
//        }}
}

fun interface PaymentSheetResultCallback {
    fun onPaymentSheetResult(paymentSheetResult: PaymentSheetResult)
}
