package fr.ensicaen.dome6.api.wall;

import java.io.Serializable;

public class Color implements Serializable {

    /**
     * has to be between 0 and 1
     */
    public float red;

    /**
     * see {@link Color#red}
     */
    public float green;
    /**
     * see {@link Color#red}
     */
    public float blue;
    /**
     * see {@link Color#red}
     */
    public float alpha;

    /**
     * the values should be between 0 and 1, for example new Color(1,0,0,1)
     * would be red
     *
     * @param red
     * @param green
     * @param blue
     * @param alpha
     */
    public Color(float red, float green, float blue, float alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public float getRed() {
        return red;
    }

    public void setRed(float red) {
        this.red = red;
    }

    public float getGreen() {
        return green;
    }

    public void setGreen(float green) {
        this.green = green;
    }

    public float getBlue() {
        return blue;
    }

    public void setBlue(float blue) {
        this.blue = blue;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }
}