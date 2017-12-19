package com.example.ygg.personnalisation_fragment_v4

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.support.constraint.ConstraintLayout
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.AppCompatCheckBox
import android.support.v7.widget.SwitchCompat
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView

// antonioleiva PACKAGES ->
import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.text.Editable
import android.text.TextWatcher
import android.widget.LinearLayout
import kotlin.reflect.KProperty


import com.madrapps.pikolo.HSLColorPicker
import com.madrapps.pikolo.listeners.SimpleColorSelectionListener

import java.lang.reflect.Array
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    // PREFERENCES
    private var textValue: String by DelegatesExt.preference(this, "textValue", "Pascal Ladale \uD83D\uDC35")
    private var switchValue: Boolean by DelegatesExt.preference(this, "switchValue", true)
    private var seekValue: Int by DelegatesExt.preference(this, "seekValue", 280)
    private var colorValue: Int by DelegatesExt.preference(this, "colorValue", Color.WHITE)




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val et = findViewById(R.id.editText) as TextView
        val sb = findViewById(R.id.seekbar) as SeekBar
        val actionBarToolbar = findViewById(R.id.action_bar) as Toolbar
        val bg = findViewById(R.id.mainBG) as LinearLayout
        val switchCompat = findViewById(R.id.switchControl) as SwitchCompat

        Log.d("mes_logs", colorValue.toString())

        ChangeColor(colorValue)
        et.setText(textValue)
        switchCompat.setChecked(switchValue)
        sb.setProgress(seekValue, true)



        et.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) { }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) { }
            override fun afterTextChanged(s: Editable) { textValue = s.toString() }
        })



        switchCompat.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if(switchValue) { switchValue = false }
                else { switchValue = true }
            }
        })


        sb.setOnSeekBarChangeListener(SeekBarListener());



         // C O L O R   P I C K E R
        val colorPicker = findViewById(R.id.colorPickerPikolo) as HSLColorPicker
        colorPicker.setColorSelectionListener(object : SimpleColorSelectionListener() {
            override fun onColorSelected(color: Int) {

                colorValue = color

                ChangeColor(color)

            }
        })

    }



        fun ChangeColor(color: Int) {

        val et = findViewById(R.id.editText) as TextView
        val sb = findViewById(R.id.seekbar) as SeekBar
        val actionBarToolbar = findViewById(R.id.action_bar) as Toolbar
        val bg = findViewById(R.id.mainBG) as LinearLayout
        val switchCompat = findViewById(R.id.switchControl) as SwitchCompat


        val states = arrayOf(intArrayOf(-android.R.attr.state_checked), intArrayOf(android.R.attr.state_checked))

        val oposite = getContrastColor(color)


        // ******************   T H E M E S   ****************** //

        // ***** DARK *****

        var thumbColors = intArrayOf(Color.WHITE, lighten(oposite, 0.6f))

        var trackColors = intArrayOf(Color.parseColor("#C0C0C0"), oposite)

        var extrude = lighten(color, 0.6f)
        var exO = lighten(oposite, 0.4f)

        var txt = Color.WHITE
        var title = darken(color, 0.4f)


        // ***** LIGHT *****
        if (Color.luminance(color) > 0.2f) {

            thumbColors = intArrayOf(Color.BLACK, darken(color, 0.6f))

            trackColors = intArrayOf(Color.GRAY, lighten(color, 0.6f))

            extrude = darken(color, 0.8f)
            exO = darken(oposite, 0.6f)

            txt = darken(color, 0.4f)
            title = Color.WHITE
        }
        // ****************** //

        // ***************************************************** //


        // BACKGROUND
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(oposite))
        bg.setBackgroundColor(color)

        // SEPARATE
        findViewById(R.id.s1).setBackgroundColor(extrude)
        findViewById(R.id.s2).setBackgroundColor(extrude)
        findViewById(R.id.s3).setBackgroundColor(extrude)

        // SEEKBAR
        sb.progressDrawable.setColorFilter(exO, PorterDuff.Mode.MULTIPLY)
        sb.thumb.setColorFilter(oposite, PorterDuff.Mode.SRC_ATOP)

        // SWITCH
        DrawableCompat.setTintList(DrawableCompat.wrap(switchCompat.thumbDrawable), ColorStateList(states, thumbColors))
        DrawableCompat.setTintList(DrawableCompat.wrap(switchCompat.trackDrawable), ColorStateList(states, trackColors))

        // EDIT TEXT
        et.setTextColor(color)

        // TEXT COLOR
        (findViewById(R.id.label1) as TextView).setTextColor(txt)
        (findViewById(R.id.label2) as TextView).setTextColor(txt)
        (findViewById(R.id.label3) as TextView).setTextColor(txt)

        actionBarToolbar.setTitleTextColor(title)

    }



    companion object {

        fun getContrastColor(color: Int): Int {

            val r = (128 + (128 - Color.red(color))).toFloat()
            val g = (128 + (128 - Color.green(color))).toFloat()
            val b = (128 + (128 - Color.blue(color))).toFloat()

            return Color.rgb(r.toInt(), g.toInt(), b.toInt())
        }

        fun darken(color: Int, percent: Float): Int {

            val hsv = floatArrayOf(0f, 0f, 0f)
            Color.colorToHSV(color, hsv)

            hsv[2] *= percent

            return Color.HSVToColor(hsv)
        }

        fun lighten(color: Int, percent: Float): Int {

            val hsv = floatArrayOf(0f, 0f, 0f)
            Color.colorToHSV(color, hsv)

            hsv[2] /= percent

            return Color.HSVToColor(hsv)
        }
    }



    inner class SeekBarListener : SeekBar.OnSeekBarChangeListener {

        override fun onProgressChanged(seekBar: SeekBar, progress: Int,
                                       fromUser: Boolean) {
            seekValue = progress
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {}

        override fun onStopTrackingTouch(seekBar: SeekBar) {}

    }
}




// antonioleiva ->

object DelegatesExt {
    fun <T> notNullSingleValue() = NotNullSingleValueVar<T>()
    fun <T> preference(context: Context, name: String,
                       default: T) = Preference(context, name, default)
}

class NotNullSingleValueVar<T> {

    private var value: T? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T =
            value ?: throw IllegalStateException("${property.name} not initialized")

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = if (this.value == null) value
        else throw IllegalStateException("${property.name} already initialized")
    }
}

class Preference<T>(private val context: Context, private val name: String,
                    private val default: T) {

    private val prefs: SharedPreferences by lazy {
        context.getSharedPreferences("default", Context.MODE_PRIVATE)
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T = findPreference(name, default)

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putPreference(name, value)
    }

    @Suppress("UNCHECKED_CAST")
    private fun findPreference(name: String, default: T): T = with(prefs) {
        val res: Any = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException("This type can be saved into Preferences")
        }

        res as T
    }

    @SuppressLint("CommitPrefEdits")
    private fun putPreference(name: String, value: T) = with(prefs.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("This type can't be saved into Preferences")
        }.apply()
    }
}
