package com.example.converter_demo_app

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.text.InputType
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.converter_demo_app.ui.AppViewModel
import com.google.android.material.snackbar.Snackbar
import org.mariuszgromada.math.mxparser.Expression
import java.util.Locale

object Utils {
    private const val TAG = "ERROR-"
    const val SAVED_VALUE = "savedValue"
    const val SAVED_RESULT = "saveResult"
    const val SYMBOL_CORRECT = "⇽"
    const val SYMBOL_CLEAR = "C"
    const val SYMBOL_BRACKETS = "( )"
    const val SYMBOL_SOLVE = "="
    const val SYMBOL_CHECK = "-/+"

    fun blockInput(resultView: EditText, valueEdit: EditText) {
        resultView.inputType = InputType.TYPE_NULL
        valueEdit.inputType = InputType.TYPE_NULL
    }

    fun clearView(valueEdit: EditText, resultView: TextView) {
        resultView.text = ""
        valueEdit.setText("")
    }

    fun getSpinnerValueString(spinner: Spinner): String {
        return spinner.selectedItem.toString()
    }

    fun textIsEmpty(textview: TextView): Boolean {
        return TextUtils.isEmpty(textview.text.toString())
    }

    fun snackBarFabric(layout: View, snackText: String, duration: Int): Snackbar {
        return Snackbar.make(layout, snackText, duration)
    }

    fun readAndSolve(valueEdit: EditText, resultView: TextView) {
        if (!TextUtils.isEmpty(valueEdit.text.toString())) {
            val getValue = valueEdit.text.toString()
            val value = Expression(getValue)
            val getResult = value.calculate().toString()
            resultView.text = getResult
        } else {
            resultView.text = ""
        }
    }

    fun checkBrackets(valueEdit: EditText) {
        if (valueEdit.text.toString().contains("(")) {
            valueEdit.append(")")
        } else {
            valueEdit.append("(")
        }
    }

    fun postTextOnClick(model: AppViewModel?, button: Button) {
        val buttonText = button.text.toString()
        button.setOnClickListener { model!!.setPostNumber(buttonText) }
    }

    fun returnLocale(): String {
        return Locale.getDefault().displayLanguage
    }

    @SuppressLint("SetTextI18n")
    fun appendMinusPlus(valueEdit: EditText) {
        val x = valueEdit.text.length
        if (x != 15 || valueEdit.text.toString().contains("-")) {
            val sb = StringBuilder()
            if (valueEdit.text.toString().contains("-")) {
                sb.append(valueEdit.text.toString())
                sb.deleteCharAt(0)
                valueEdit.setText(sb.toString())
            } else {
                valueEdit.setText("-" + valueEdit.text)
            }
        }
    }

    fun correctValue(valueEdit: EditText, resultEdit: TextView) {
        val x = valueEdit.text.length
        if (x > 0) {
            valueEdit.setText(removeLastChar(valueEdit.text.toString()))
        } else {
            Log.d(TAG, "INVALID INPUT")
            clearView(valueEdit, resultEdit)
        }
    }

    private fun removeLastChar(str: String): String {
        return removeLastChars(str)
    }

    private fun removeLastChars(str: String): String {
        return str.substring(0, str.length - 1)
    }

    fun measurementUnitsHandler(
        spinnerTextValue: String?,
        measurementUnit: TextView
    ) {
        val resources = measurementUnit.resources
        when (spinnerTextValue) {
            "Milligram", "Міліграм" -> measurementUnit.text = resources.getString(R.string.unit_Mg)
            "Gram", "Грам" -> measurementUnit.text = resources.getString(R.string.unit_G)
            "Kilogram", "Кілограм" -> measurementUnit.text = resources.getString(R.string.unit_Kg)
            "Tonne", "Тонна" -> measurementUnit.text = resources.getString(R.string.unit_T)
            "Grain", "Гран" -> measurementUnit.text = resources.getString(R.string.unit_Gr)
            "Ounce", "Унція" -> measurementUnit.text = resources.getString(R.string.unit_Oz)
            "Pound", "Фунт" -> measurementUnit.text = resources.getString(R.string.unit_Lb)
            "Hundredweight", "Хандредвейт" -> measurementUnit.text =
                resources.getString(R.string.unit_Hw)
            "Ton(long)", "Тонна(довга)" -> measurementUnit.text =
                resources.getString(R.string.unit_Tl)
            "Millimetre", "Міліметр" -> measurementUnit.text = resources.getString(R.string.unit_Mm)
            "Centimetre", "Сантіметр" -> measurementUnit.text =
                resources.getString(R.string.unit_Sm)
            "Metre", "Метр" -> measurementUnit.text = resources.getString(R.string.unit_M)
            "Kilometre", "Кілометр" -> measurementUnit.text = resources.getString(R.string.unit_Km)
            "Inch", "Дюйм" -> measurementUnit.text = resources.getString(R.string.unit_In)
            "Foot", "Фут" -> measurementUnit.text = resources.getString(R.string.unit_Ft)
            "Yard", "Ярд" -> measurementUnit.text = resources.getString(R.string.unit_Yd)
            "Mile", "Міля" -> measurementUnit.text = resources.getString(R.string.unit_Mi)
            "Celsius", "Цельсій" -> measurementUnit.text = resources.getString(R.string.unit_c)
            "Kelvin", "Кельвін" -> measurementUnit.text = resources.getString(R.string.unit_k)
            "Rankine", "Ранкін" -> measurementUnit.text = resources.getString(R.string.unit_r)
            "Fahrenheit", "Фаренгейт" -> measurementUnit.text = resources.getString(R.string.unit_f)
            "Square millimeter", "Міліметр квадратний" -> measurementUnit.text =
                resources.getString(R.string.unit_Mm_Square)
            "Square centimeter", "Сантіметр квадратний" -> measurementUnit.text =
                resources.getString(R.string.unit_Cm_Square)
            "Square meter", "Метр квадратний" -> measurementUnit.text =
                resources.getString(R.string.unit_М_Square)
            "Square kilometer", "Кілометр квадратний" -> measurementUnit.text =
                resources.getString(R.string.unit_Km_Square)
            "Hectare", "Гектар" -> measurementUnit.text = resources.getString(R.string.unit_Ha)
            "Square mile", "Міля квадратна" -> measurementUnit.text =
                resources.getString(R.string.unit_Mi_Square)
            "Square yard", "Ярд квадратний" -> measurementUnit.text =
                resources.getString(R.string.unit_Yd_Square)
            "Square feet", "Фут квадратний" -> measurementUnit.text =
                resources.getString(R.string.unit_Ft_Square)
            "Square inch", "Дюйм квадратний" -> measurementUnit.text =
                resources.getString(R.string.unit_In_Square)
            "Acre", "Акр" -> measurementUnit.text = resources.getString(R.string.unit_Ac)
            "Seconds", "Секунди" -> measurementUnit.text =
                resources.getString(R.string.unit_Seconds)
            "Minutes", "Хвилини" -> measurementUnit.text =
                resources.getString(R.string.unit_Minutes)
            "Hour", "Година" -> measurementUnit.text = resources.getString(R.string.unit_Hour)
            "Day", "День" -> measurementUnit.text = resources.getString(R.string.unit_Day)
            "Week", "Тиждень" -> measurementUnit.text = resources.getString(R.string.unit_Week)
            "Month", "Місяц" -> measurementUnit.text = resources.getString(R.string.unit_Month)
            "Year", "Рік" -> measurementUnit.text = resources.getString(R.string.unit_Year)
            "Cubic millimetre", "Міліметр кубічний" -> measurementUnit.text =
                resources.getString(R.string.unit_Mm_Cubic)
            "Cubic centimetre", "Сантіметр кубічний" -> measurementUnit.text =
                resources.getString(R.string.unit_Cm_Cubic)
            "Cubic metre", "Метр кубічний" ->
                measurementUnit.text = resources.getString(R.string.unit_M_Cubic)
            "Milliliter", "Мілілітр" -> measurementUnit.text = resources.getString(R.string.unit_Ml)
            "Liter", "Літр" -> measurementUnit.text = resources.getString(R.string.unit_L)
            "Fluid ounce", "Унція рідка" ->
                measurementUnit.text = resources.getString(R.string.unit_Fl_oz)
            "Barrel(UK)", "Баррель(UK)" ->
                measurementUnit.text = resources.getString(R.string.unit_Bbl_uk)
            "Gill", "Джил" -> measurementUnit.text = resources.getString(R.string.unit_Gi)
            "Pint", "Пінта" -> measurementUnit.text = resources.getString(R.string.unit_Pt)
            "Quart", "Кварт" -> measurementUnit.text = resources.getString(R.string.unit_Qt)
            "Gallon", "Галлон" -> measurementUnit.text = resources.getString(R.string.unit_Gal)
            "Millinewton", "Міліньютон" -> measurementUnit.text =
                resources.getString(R.string.unit_Mn)
            "Newton", "Ньютон" -> measurementUnit.text = resources.getString(R.string.unit_N)
            "Kilonewton", "Кілоньютон" -> measurementUnit.text =
                resources.getString(R.string.unit_Kn)
            "Ton-force(metric)", "Тонна-сила(метрична)" -> measurementUnit.text =
                resources.getString(R.string.unit_Tf)
            "Gram-force", "Грам-сила" -> measurementUnit.text =
                resources.getString(R.string.unit_Gf)
            "Kilogram-force", "Кілограм-сила" -> measurementUnit.text =
                resources.getString(R.string.unit_Kgf)
            "Pond", "Понд" -> measurementUnit.text = resources.getString(R.string.unit_P)
            "Pound-force", "Фунт-сила" -> measurementUnit.text = resources.getString(R.string.unit_Lbf)
            "Ounce-force", "Унція-сила" -> measurementUnit.text = resources.getString(R.string.unit_Ozf)
            "Ton-force (long)", "Тонна-сила(довга)" -> measurementUnit.text = resources.getString(R.string.unit_Tonf)
            "Poundal", "Паундаль" -> measurementUnit.text = resources.getString(R.string.unit_Pdl)
            "Meter/second", "Метрів/секунда" -> measurementUnit.text = resources.getString(R.string.unit_M_s)
            "Meter/hour", "Метрів/година" -> measurementUnit.text = resources.getString(R.string.unit_M_h)
            "Kilometer/second", "Кілометр/секунда" -> measurementUnit.text = resources.getString(R.string.unit_Km_s)
            "Kilometer/hour", "Кілометр/година" -> measurementUnit.text = resources.getString(R.string.unit_Km_h)
            "Foot/second", "Фут/секунда" -> measurementUnit.text = resources.getString(R.string.unit_F_s)
            "Foot/hour", "Фут/година" -> measurementUnit.text = resources.getString(R.string.unit_F_h)
            "Mile/hour", "Міль/година" -> measurementUnit.text = resources.getString(R.string.unit_Mi)
            "Knot", "Вузол" -> measurementUnit.text = resources.getString(R.string.unit_Kt)
        }
    }

    fun currencyUnitHandler(
        spinnerTextValue: String?,
        measurementUnit: TextView
    ) {
        val resources = measurementUnit.resources
        when (spinnerTextValue) {
            "United States Dollar", "Доллар США" -> measurementUnit.text =
                resources.getString(R.string.unit_Usd)
            "Great Britain Pound", "Великобританський фунт" -> measurementUnit.text =
                resources.getString(R.string.unit_Gbp)
            "Indonesian rupiah", "Індозенійська Рупія" -> measurementUnit.text =
                resources.getString(R.string.unit_Ipr)
            "Polish złoty", "Польский Злотий" -> measurementUnit.text =
                resources.getString(R.string.unit_Pln)
            "New Zealand dollar", "Доллар НЗ" -> measurementUnit.text =
                resources.getString(R.string.unit_Nzd)
            "Russian Ruble", "Рубль" -> measurementUnit.text =
                resources.getString(R.string.unit_Rub)
        }
    }

    fun currencyConverter(value: Double, targetRate: Double?, initRate: Double?): Double {
        return targetRate!! * value / initRate!!
    }

    fun returnDateString(dateTextView: TextView): String {
        return dateTextView.text.toString()
    }

    fun generateViewModel(owner: ViewModelStoreOwner?): AppViewModel {
        return ViewModelProvider(owner!!).get(AppViewModel::class.java)
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)
        return capabilities?.hasCapability(NET_CAPABILITY_INTERNET) == true
    }

    fun textIsLessThanFifteenSymbols(textview: TextView): Boolean {
        return textview.text.toString().trim { it <= ' ' }.length > 15
    }
}
