package indi.lijingwen.common.image;

/**
 * This class is the output from Text2ImageUtil, including the image byte array, the height and the printed index of the
 * text.
 *
 * @author lijingwen
 */
public class Text2ImageOutput {

    /**
     * The image content.
     */
    byte[] image = null;

    /**
     * The printed text index.
     */
    int index = 0;

    /**
     * The image height.
     */
    public int height;

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] images) {
        this.image = image;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Text2ImageOutput(int index, int height, byte[] image) {
        this.image = image;
        this.index = index;
        this.height = height;
    }
}
