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

import com.madrapps.pikolo.HSLColorPicker
import com.madrapps.pikolo.listeners.SimpleColorSelectionListener

import java.lang.reflect.Array
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val colorPicker = findViewById(R.id.colorPickerPikolo) as HSLColorPicker
        colorPicker.setColorSelectionListener(object : SimpleColorSelectionListener() {
            override fun onColorSelected(color: Int) {


                val sb = findViewById(R.id.seekbar) as SeekBar
                val actionBarToolbar = findViewById(R.id.action_bar) as Toolbar
                val bg = findViewById(R.id.mainBG)
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
                (findViewById(R.id.editText) as EditText).setTextColor(color)

                // TEXT COLOR
                (findViewById(R.id.label1) as TextView).setTextColor(txt)
                (findViewById(R.id.label2) as TextView).setTextColor(txt)
                (findViewById(R.id.label3) as TextView).setTextColor(txt)

                actionBarToolbar.setTitleTextColor(title)

            }
        })

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


    //    public static ArrayList<View> getViewsByTag(ViewGroup root, String tag){
    //        ArrayList<View> views = new ArrayList<View>();
    //        final int childCount = root.getChildCount();
    //        for (int i = 0; i < childCount; i++) {
    //            final View child = root.getChildAt(i);
    //            if (child instanceof ViewGroup) {
    //                views.addAll(getViewsByTag((ViewGroup) child, tag));
    //            }
    //
    //            final Object tagObj = child.getTag();
    //            if (tagObj != null && tagObj.equals(tag)) {
    //                views.add(child);
    //            }
    //
    //        }
    //        return views;
    //    }
}
