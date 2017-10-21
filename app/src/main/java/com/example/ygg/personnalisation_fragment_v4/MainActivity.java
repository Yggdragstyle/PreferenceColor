package com.example.ygg.personnalisation_fragment_v4;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.madrapps.pikolo.HSLColorPicker;
import com.madrapps.pikolo.listeners.SimpleColorSelectionListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final HSLColorPicker colorPicker = (HSLColorPicker) findViewById(R.id.colorPickerPikolo);
        colorPicker.setColorSelectionListener(new SimpleColorSelectionListener() {
            @Override
            public void onColorSelected(int color) {


                SeekBar sb = (SeekBar) findViewById(R.id.seekbar);
                Toolbar actionBarToolbar = (Toolbar)findViewById(R.id.action_bar);
                View bg = findViewById(R.id.mainBG);
                SwitchCompat switchCompat = (SwitchCompat) findViewById(R.id.switchControl);


                int[][] states = new int[][] {
                        new int[] {-android.R.attr.state_checked},
                        new int[] {android.R.attr.state_checked},
                };

                int oposite = getContrastColor(color);



                // ******************   T H E M E S   ****************** //

                // ***** DARK *****

                    int[] thumbColors = new int[] {
                            Color.WHITE,
                            lighten(oposite, 0.6f)
                    };

                    int[] trackColors = new int[] {
                            Color.parseColor("#C0C0C0"),
                            oposite
                    };

                    int extrude = lighten(color, 0.6f);
                    int exO = lighten(oposite, 0.4f);

                    int txt = Color.WHITE;
                    int title = darken(color, 0.4f);


                // ***** LIGHT *****
                if( Color.luminance(color) > 0.2f ) {

                    thumbColors = new int[] {
                            Color.BLACK,
                            darken(color, 0.6f)
                    };

                    trackColors = new int[] {
                            Color.GRAY,
                            lighten(color, 0.6f)
                    };

                    extrude = darken(color, 0.8f);
                    exO = darken(oposite, 0.6f);

                    txt = darken(color, 0.4f);
                    title = Color.WHITE;
                }
                // ****************** //

                // ***************************************************** //



                // BACKGROUND
                getSupportActionBar().setBackgroundDrawable( new ColorDrawable( oposite ));
                bg.setBackgroundColor( color );

                // SEPARATE
                findViewById(R.id.s1).setBackgroundColor(extrude);
                findViewById(R.id.s2).setBackgroundColor(extrude);
                findViewById(R.id.s3).setBackgroundColor(extrude);

                // SEEKBAR
                sb.getProgressDrawable().setColorFilter( exO, PorterDuff.Mode.MULTIPLY);
                sb.getThumb().setColorFilter( oposite, PorterDuff.Mode.SRC_ATOP);

                // SWITCH
                DrawableCompat.setTintList(DrawableCompat.wrap(switchCompat.getThumbDrawable()), new ColorStateList(states, thumbColors));
                DrawableCompat.setTintList(DrawableCompat.wrap(switchCompat.getTrackDrawable()), new ColorStateList(states, trackColors));

                // EDIT TEXT
                ((EditText)findViewById(R.id.editText)).setTextColor(color);

                // TEXT COLOR
                ((TextView)findViewById(R.id.label1)).setTextColor(txt);
                ((TextView)findViewById(R.id.label2)).setTextColor(txt);
                ((TextView)findViewById(R.id.label3)).setTextColor(txt);

                if (actionBarToolbar != null) { actionBarToolbar.setTitleTextColor(title); }







//                // TEST ****
//                int test = lighten(color, 0.8f);

//                View test = findViewById(R.id.mainBG).findViewWithTag("mon_tag");
//
//                if(test != null) { test.setBackgroundColor(lighten(color, 0.8f)); }



//                ArrayList<View> result = getViewsByTag( (ViewGroup) findViewById(R.id.mainBG), "mon_tag" );
//
//                if(!result.isEmpty()) {
//                    View separate = result.get(0);
//
//                    if (separate != null) {
//                        separate.setBackgroundColor(lighten(color, 0.8f));
//                    }
//                }
            }
        });

    }

    public static int getContrastColor(int color) {

        float r = 128 + ( 128 - Color.red(color) ),
                g = 128 + ( 128 - Color.green(color) ),
                b = 128 + ( 128 - Color.blue(color) );

        return Color.rgb( (int)r, (int)g, (int)b );
    }

    public static int darken(int color, float percent) {

        float[] hsv = { 0f, 0f, 0f };
        Color.colorToHSV(color, hsv);

        hsv[2] *= percent;

        return Color.HSVToColor(hsv);
    }

    public static int lighten(int color, float percent) {

        float[] hsv = { 0f, 0f, 0f };
        Color.colorToHSV(color, hsv);

        hsv[2] /= percent;

        return Color.HSVToColor(hsv);
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
