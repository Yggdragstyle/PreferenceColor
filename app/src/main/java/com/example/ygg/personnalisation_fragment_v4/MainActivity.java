package com.example.ygg.personnalisation_fragment_v4;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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

                getSupportActionBar().setBackgroundDrawable( new ColorDrawable( getContrastColor(color) ));
                View bg = findViewById(R.id.mainBG);

                bg.setBackgroundColor( color );


                findViewById(R.id.s1).setBackgroundColor(lighten(color, 0.8f));
                findViewById(R.id.s2).setBackgroundColor(lighten(color, 0.8f));
                findViewById(R.id.s3).setBackgroundColor(lighten(color, 0.8f));



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

    public static int lighten(int color, float percent) {

        float[] hsv = { 0f, 0f, 0f };
        Color.colorToHSV(color, hsv);

        Log.d("Mes_test", Float.toString( hsv[2] ));
        Log.d("Mes_test", Integer.toString(Color.HSVToColor(hsv)));

        hsv[2] *= percent;

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
