package com.example.ygg.personnalisation_fragment_v4;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.madrapps.pikolo.HSLColorPicker;
import com.madrapps.pikolo.listeners.SimpleColorSelectionListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final HSLColorPicker colorPicker = (HSLColorPicker) findViewById(R.id.colorPickerPikolo);
        colorPicker.setColorSelectionListener(new SimpleColorSelectionListener() {
            @Override
            public void onColorSelected(int color) {
                // Do whatever you want with the color
//                ((View)findViewById(R.id.bgMain)).getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
//                View bg = (View) findViewById(R.id.bgMain);

//                TextView txt = (TextView) findViewById(R.id.txt);
//                txt.setTextColor(color);

//                bg.setBackgroundColor(color);
//                txt.setTextColor(  );
//                Color c = getContrastColor( Color.valueOf( color ) );
////                Color c = getContrastColor( Color( color ) );
//                Color c = Color.valueOf( color );
//                txt.setText( c.toString() );
            }
        });

    }

//    public static Color getContrastColor(Color color) {
//
//        float r = 128 + (128 - color.red()),
//                g = 128 + (128 - color.green()),
//                b = 128 + (128 - color.blue());
//
//        return Color([r,g,b]);
////        return Color([(int)r,(int)g,(int)b]);
////        return Color.rgb( r, g, b );
//    }
}
