package com.gao00078algonquinlive.hsvcolorpicker;

// import all packages

import android.app.Activity;
import android.app.DialogFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Observable;
import java.util.Observer;

import model.HSVModel;

/**
 * MAD9132 Midterm Assignment - HSV Color Picker Android App
 * This app is designed for changing colors by modify the factor of Hue, Saturation and Brightness
 * The default 15 color selection is provided at the bottom of the interface
 *
 * @author Kai Gao (gao00078@algonquinlive.com)
 */

public class MainActivity extends Activity implements Observer, SeekBar.OnSeekBarChangeListener {
    // declare all variables
    private static final String ABOUT_DIALOG_TAG = "About Dialog";
    private TextView mColorSwatch;
    private HSVModel mModel;
    private TextView mHueTextView;
    private TextView mSaturationTextView;
    private TextView mVBTextView;
    private SeekBar mHueScrollBar;
    private SeekBar mSaturationScrollBar;
    private SeekBar mVBScrollBar;

    // about Dialog
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            DialogFragment newFragment = new AboutDialog();
            newFragment.show(getFragmentManager(), ABOUT_DIALOG_TAG);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mModel = new HSVModel();
        mModel.setHSV(HSVModel.MIN_HUE, HSVModel.MIN_SAT, HSVModel.MIN_VB);
        mModel.addObserver(this);
        mColorSwatch = (TextView) findViewById(R.id.color_swatch);
        mHueTextView = (TextView) findViewById(R.id.hue_textView);
        mSaturationTextView = (TextView) findViewById(R.id.saturation_textView);
        mVBTextView = (TextView) findViewById(R.id.VB_textView);
        mHueScrollBar = (SeekBar) findViewById(R.id.hue_scrollBar);
        mSaturationScrollBar = (SeekBar) findViewById(R.id.saturation_scrollBar);
        mVBScrollBar = (SeekBar) findViewById(R.id.VB_scrollBar);
        mHueScrollBar.setMax(HSVModel.MAX_HUE);
        mSaturationScrollBar.setMax(100);
        mVBScrollBar.setMax(100);
        // set event listener
        mHueScrollBar.setOnSeekBarChangeListener(this);
        mSaturationScrollBar.setOnSeekBarChangeListener(this);
        mVBScrollBar.setOnSeekBarChangeListener(this);
        // show current HSV values when long click on color swatch
        mColorSwatch.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int hue = mModel.getHue();
                float saturation = mModel.getSaturation();
                saturation = saturation * 100;
                float brightness = mModel.getVB();
                brightness = brightness * 100;
                String string = "H: " + hue + "\u00B0 S: " + saturation + "% V: " + brightness + "%";
                Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        this.updateView();
    }

    public void changeColor(View view) {
        switch (view.getId()) {
            case R.id.black_button:
                mModel.asBlack();
                break;
            case R.id.red_button:
                mModel.asRed();
                break;
            case R.id.lime_button:
                mModel.asLime();
                break;
            case R.id.blue_button:
                mModel.asBlue();
                break;
            case R.id.yellow_button:
                mModel.asYellow();
                break;
            case R.id.cyan_button:
                mModel.asCyan();
                break;
            case R.id.magenta_button:
                mModel.asMagenta();
                break;
            case R.id.silver_button:
                mModel.asSilver();
                break;
            case R.id.gray_button:
                mModel.asGray();
                break;
            case R.id.maroon_button:
                mModel.asMaroon();
                break;
            case R.id.olive_button:
                mModel.asOlive();
                break;
            case R.id.green_button:
                mModel.asGreen();
                break;
            case R.id.purple_button:
                mModel.asPurple();
                break;
            case R.id.teal_button:
                mModel.asTeal();
                break;
            case R.id.navy_button:
                mModel.asNavy();
                break;
        }
        int hue = mModel.getHue();
        float saturation = mModel.getSaturation();
        saturation = saturation * 100;
        float brightness = mModel.getVB();
        brightness = brightness * 100;
        String string = "H: " + hue + "\u00B0 S: " + saturation + "% V: " + brightness + "%";
        Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser == false) {
            return;
        }
        switch (seekBar.getId()) {
            case R.id.hue_scrollBar:
                mModel.setHue(mHueScrollBar.getProgress());
                mHueTextView.setText(getResources().getString(R.string.VBPercentage, progress).toUpperCase());
                break;
            case R.id.saturation_scrollBar:
                float saturation = mSaturationScrollBar.getProgress();
                saturation = saturation / 100;
                mModel.setSaturation(saturation);
                mSaturationTextView.setText(getResources().getString(R.string.saturationPercentage, progress).toUpperCase() + "%");
                break;
            case R.id.VB_scrollBar:
                float brightness = mVBScrollBar.getProgress();
                brightness = brightness / 100;
                mModel.setVB(brightness);
                mVBTextView.setText(getResources().getString(R.string.VBPercentage, progress).toUpperCase() + "%");
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        switch (seekBar.getId()) {
            case R.id.hue_scrollBar:
                mHueTextView.setText(getResources().getString(R.string.hue));
                break;
            case R.id.saturation_scrollBar:
                mSaturationTextView.setText(getResources().getString(R.string.saturation));
                break;
            case R.id.VB_scrollBar:
                mVBTextView.setText(getResources().getString(R.string.VB));
                break;
        }
    }


    @Override
    public void update(Observable observable, Object data) {
        this.updateView();
    }

    private void updateColorSwatch() {
        float[] hsv = {mModel.getHue(), mModel.getSaturation(), mModel.getVB()};
        mColorSwatch.setBackgroundColor(Color.HSVToColor(hsv));
    }

    private void updateHueSB() {
        mHueScrollBar.setProgress(mModel.getHue());
    }

    private void updateSaturationSB() {
        float saturation = mModel.getSaturation();
        saturation = saturation * 100;
        mSaturationScrollBar.setProgress((int) saturation);
    }

    private void updateBrightnessSB() {
        float brightness = mModel.getVB();
        brightness = brightness * 100;
        mVBScrollBar.setProgress((int) brightness);
    }

    public void updateView() {
        this.updateColorSwatch();
        this.updateHueSB();
        this.updateSaturationSB();
        this.updateBrightnessSB();
    }
}