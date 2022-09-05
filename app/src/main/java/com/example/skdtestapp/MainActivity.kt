package com.example.tagapplication

//import android.R

//import android.R
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.lokalise.sdk.Lokalise
import com.lokalise.sdk.LokaliseContextWrapper
import java.util.*
import com.example.tagapplication.databinding.ActivityMainBinding as ActivityMainBinding1


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding1


    private var editTextDate: EditText? = null
    private var buttonDate: Button? = null
    private var checkBoxIsSpinnerMode: CheckBox? = null

    private var lastSelectedYear = 0
    private var lastSelectedMonth = 0
    private var lastSelectedDayOfMonth = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding1.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.defaultLocale.text = "App Locale: ${Locale.getDefault().toLanguageTag()}\n" +
                "Device locale: ${
            Resources.getSystem().getConfiguration().getLocales().get(0)
        }"



        editTextDate = findViewById<View>(R.id.editText_date) as EditText
        buttonDate = findViewById<View>(R.id.button_date) as Button
        checkBoxIsSpinnerMode = findViewById(R.id.checkBox_isSpinnerMode)

        buttonDate!!.setOnClickListener { buttonSelectDate() }

        // Get Current Date

        // Get Current Date
        val c = Calendar.getInstance()
        lastSelectedYear = c[Calendar.YEAR]
        lastSelectedMonth = c[Calendar.MONTH]
        lastSelectedDayOfMonth = c[Calendar.DAY_OF_MONTH]






         //plural string
        val res = resources
        val pluralViewOne = findViewById<TextView>(R.id.plural_view_one)
        val quantityStringFor1 = res.getQuantityString(R.plurals.my_cats, 1, 1)
        pluralViewOne.text = quantityStringFor1

        val pluralViewFew = findViewById<TextView>(R.id.plural_view_few)
        val quantityStringFor2 = res.getQuantityString(R.plurals.my_cats, 2, 2)
        pluralViewFew.text = quantityStringFor2

        val pluralViewMany = findViewById<TextView>(R.id.plural_view_many)
        val quantityStringFor5 = res.getQuantityString(R.plurals.my_cats, 5, 5)
        pluralViewMany.text = quantityStringFor5



     //   println("Printing list of locales");
      //  val locales = Locale.getAvailableLocales()
      //  val localcountries = ArrayList<String>()
       // for (l in locales) {
        //    localcountries.add(l.displayLanguage.toString())
        //    println("Locale code " + l.language.toString() + ", locale name " + l.displayLanguage.toString());
       // }
        println("Lokalise Bundle " + Lokalise.currentBundleId.toString());
     //   Lokalise.


    }


    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LokaliseContextWrapper.wrap(newBase))
    }


    // User click on 'Select Date' button.
    private fun buttonSelectDate() {
        val isSpinnerMode: Boolean = this.checkBoxIsSpinnerMode!!.isChecked()

        // Date Select Listener.
        val dateSetListener =
            OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                editTextDate?.setText(dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                lastSelectedYear = year
                lastSelectedMonth = monthOfYear
                lastSelectedDayOfMonth = dayOfMonth
            }
        var datePickerDialog: DatePickerDialog? = null
        datePickerDialog = if (isSpinnerMode) {
            // Create DatePickerDialog:
            DatePickerDialog(
                this,
                android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth
            )
        } else {
            DatePickerDialog(
                this,
                dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth
            )
        }

        // Show
        datePickerDialog.show()
    }
}