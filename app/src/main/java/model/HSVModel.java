package model;

import java.util.Observable;

/**
 * HSVModel File
 * @author Kai Gao (gao00078@algonquinlive.com)
 */

public class HSVModel extends Observable {
    //declare variables
    public static final Integer MAX_HUE = 359;
    public static final Integer MIN_HUE = 0;
    public static final float MAX_SAT = 1;
    public static final float MIN_SAT = 0;
    public static final float MAX_VB = 1;
    public static final float MIN_VB = 0;

    private Integer hue;
    private float saturation;
    private float VB;

    /**
     * No argument constructor.
     * Instantiate a new instance of this class, and
     * initialize hue, saturation and brightness to max values.
     */

    public HSVModel() {
        this(MAX_HUE, MAX_SAT, MAX_VB);
    }

    /**
     * Convenience constructor.
     * @param hue-        starting hue value
     * @param saturation- starting saturation value
     * @param VB- starting value/brightness value
     */

    public HSVModel(Integer hue, float saturation, float VB) {
        super();
        this.hue = hue;
        this.saturation = saturation;
        this.VB = VB;
    }

    //set HSV values for each default color
    public void asBlack() {
        this.setHSV(0, 0,0);
    }

    public void asRed() {
        this.setHSV(0, 1, 1);
    }

    public void asLime() {
        this.setHSV(120, 1, 1);
    }

    public void asBlue() {
        this.setHSV(240, 1, 1);
    }

    public void asYellow() {
        this.setHSV(60, 1, 1);
    }

    public void asCyan() {
        this.setHSV(180, 1, 1);
    }

    public void asMagenta() {
        this.setHSV(300, 1, 1);
    }

    public void asSilver() {
        this.setHSV(0, 0, 0.75f);
    }

    public void asGray() {
        this.setHSV(0, 0, 0.5f);
    }

    public void asMaroon() {
        this.setHSV(0, 100, 0.5f);
    }

    public void asOlive() {
        this.setHSV(60, 1, 0.5f);
    }

    public void asGreen() {
        this.setHSV(120, 1, 0.5f);
    }

    public void asPurple() {
        this.setHSV(300, 1, 0.5f);
    }

    public void asTeal() {
        this.setHSV(180, 1, 0.5f);
    }

    public void asNavy() {
        this.setHSV(240, 1, 0.5f);
    }

    // getter
    public Integer getHue() {
        return hue;
    }

    public float getSaturation() {
        return saturation;
    }

    public float getVB() {
        return VB;
    }

    // setter
    public void setHue(Integer hue) {
        this.hue = hue;
        this.updateObservers();
    }

    public void setSaturation(float saturation) {
        this.saturation = saturation;
        this.updateObservers();
    }

    public void setVB(float VB) {
        this.VB = VB;
        this.updateObservers();
    }

    public void setHSV(Integer hue, float saturation, float VB) {
        this.hue = hue;
        this.saturation = saturation;
        this.VB = VB;
        this.updateObservers();
    }

    private void updateObservers() {
        this.setChanged();
        this.notifyObservers();
    }
}
