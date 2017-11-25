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
    byte[] images = null;

    /**
     * The printed text index.
     */
    int index = 0;

    /**
     * The image height.
     */
    public int height;

    public byte[] getImages() {
        return images;
    }

    public void setImages(byte[] images) {
        this.images = images;
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

    public Text2ImageOutput(int index, int height, byte[] images) {
        this.images = images;
        this.index = index;
        this.height = height;
    }
}
