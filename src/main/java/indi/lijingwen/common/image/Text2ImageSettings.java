package indi.lijingwen.common.image;

import java.awt.*;

/**
 * This class define the image output settings, including font/height/width/color/background.
 *
 * @author lijingwen
 */
public class Text2ImageSettings {

    public static final int DEFAULT_FONT = 16;
    public static final int DEFAULT_HEIGHT = 400;
    public static final int DEFAULT_WIDTH = 800;
    public static final String DEFAULT_FONT_FAMILY = "arial";
    public static final String LINUX_LINE_SEPARATOR = "\n";
    public static final String MAC_LINE_SEPARATOR = "\r";
    public static final String WINDOWS_LINE_SEPARATOR = "\r\n";
    public static final String DEFAULT_LINE_SEPARATOR = LINUX_LINE_SEPARATOR;
    public static final String DEFAULT_IMAGE_FORMAT_NAME = "BMP";
    public static final int LEFT_PADDING = 4;
    public static final int TOP_PADDING = 4;


    /**
     * Base one Windows 96dpi pt = px * 72/96 = px * 3 / 4
     * Notes: not used.
     *
     * @return
     */
    // TODO: refactor other utility class
    public float getWindowsOneCharWidth() {
        return (float) font / 4f * 3f;
    }


    /**
     * The text color.
     */
    private Color color = Color.BLACK;

    /**
     * The back ground color.
     */
    private Color background = Color.white;

    /**
     * The image height(px).
     */
    private int height = DEFAULT_HEIGHT;

    /**
     * The image width(px).
     */
    private int width = DEFAULT_WIDTH;

    /**
     * The font, default is 16.
     */
    private int font = DEFAULT_FONT;

    /**
     * The font family.
     */
    private String fontFamily = DEFAULT_FONT_FAMILY;

    /**
     * The line separator, default is '\n'.
     */
    private String lineSeparator = DEFAULT_LINE_SEPARATOR;

    /**
     * The image output file format name.
     */
    private String imgFormatName = DEFAULT_IMAGE_FORMAT_NAME;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getFont() {
        return font;
    }

    public void setFont(int font) {
        this.font = font;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public String getLineSeparator() {
        return lineSeparator;
    }

    public void setLineSeparator(String lineSeparator) {
        this.lineSeparator = lineSeparator;
    }

    public String getImgFormatName() {
        return imgFormatName;
    }

    public void setImgFormatName(String imgFormatName) {
        this.imgFormatName = imgFormatName;
    }

    public static void main(String[] args) {
        Text2ImageSettings a = new Text2ImageSettings();
        a.setFont(18);
        System.out.println(a.getWindowsOneCharWidth());
    }


}
