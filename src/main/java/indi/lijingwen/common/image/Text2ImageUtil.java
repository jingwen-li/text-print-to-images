package indi.lijingwen.common.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.Hashtable;

/**
 * This utility class using java awt to print the text to a image.
 *
 * @author lijingwen
 */
public class Text2ImageUtil {

    /**
     * This method will create image by the configurated settings.
     * <p>
     * Notes:
     * 1. the image height maybe be no more than 4 px, because the bottom padding.
     * 2. the image height will remove the blank which space can't contain one line.
     *
     * @param settings the input settings
     * @param content  the string content to be printed
     * @return the output
     */
    public static Text2ImageOutput generateImage(Text2ImageSettings settings, String content) {
        float posY = Text2ImageSettings.TOP_PADDING;
        int printedIndex = 0;
        boolean broken = false;
        float lineBreakWidth = settings.getWidth() - 2 * Text2ImageSettings.LEFT_PADDING;

        // init image buffer just larger than expected to make sure the output image is the sub-image.
        BufferedImage bufferedImage = new BufferedImage(settings.getWidth() + 1,
                settings.getHeight() + Text2ImageSettings.TOP_PADDING * 2 + 1,
                BufferedImage.TYPE_INT_RGB);
        // init 2D graphics
        Graphics2D graphics2D = bufferedImage.createGraphics();
        initGraphics2D(graphics2D, settings);

        FontRenderContext fontRenderContext = graphics2D.getFontRenderContext();

        Hashtable<TextAttribute, Object> textAttributes = new Hashtable<TextAttribute, Object>();
        textAttributes.put(TextAttribute.FAMILY, settings.getFontFamily());
        textAttributes.put(TextAttribute.SIZE, Float.valueOf(settings.getFont()));

        // draw each line
        String[] textArray = content.split(settings.getLineSeparator());
        for (String text : textArray) {
            // add separator length
            if (printedIndex != 0) {
                printedIndex += settings.getLineSeparator().length();
            }
            // remove empty line
            if (text.length() == 0) {
                continue;
            }

            AttributedString attributedText = new AttributedString(text, textAttributes);
            AttributedCharacterIterator attributedCharacterIterator = attributedText.getIterator();
            LineBreakMeasurer lineBreakMeasurer = new LineBreakMeasurer(attributedCharacterIterator, fontRenderContext);

            while (lineBreakMeasurer.getPosition() < attributedCharacterIterator.getEndIndex()) {
                // Get next text layout.
                TextLayout textLayout = lineBreakMeasurer.nextLayout(lineBreakWidth);
                // x position always starts at left padding.
                float posX = Text2ImageSettings.LEFT_PADDING;
                // Move y to the ascent of this layout.
                posY += textLayout.getAscent();
                // Draw the TextLayout at (x, y).
                textLayout.draw(graphics2D, posX, posY);

                // if there is not enough space to draw a new line, then break
                if (posY + textLayout.getAscent() + textLayout.getDescent() + textLayout.getLeading() >= settings.getHeight()) {
                    broken = true;
                    printedIndex += lineBreakMeasurer.getPosition();
                    // if there is still some remain, add the leading
                    if (posY + textLayout.getDescent() < settings.getHeight()) {
                        posY += textLayout.getDescent();
                    }
                    break;
                }

                // Move y to the bottom of this layout.
                posY += textLayout.getDescent() + textLayout.getLeading();
            }
            if (!broken) {
                printedIndex += text.length();
            } else {
                break;
            }
        }
        posY += Text2ImageSettings.TOP_PADDING;
        graphics2D.dispose();
        // Write to byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage.getSubimage(0, 0, settings.getWidth(), (int) Math.ceil(posY)), settings.getImgFormatName(), baos);
            return new Text2ImageOutput(printedIndex, (int) Math.ceil(posY), baos.toByteArray());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                baos.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * Initialise the 2D graphics by the settings.
     *
     * @param graphics2D the instance of Graphics2D to be initialised
     * @param settings   the configuration settings.
     */
    private static void initGraphics2D(Graphics2D graphics2D, Text2ImageSettings settings) {
        graphics2D.setColor(settings.getBackground());
        graphics2D.fillRect(0, 0, settings.getWidth(), settings.getHeight() + Text2ImageSettings.TOP_PADDING * 2);
        graphics2D.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        graphics2D.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        graphics2D.setColor(settings.getColor());
    }

}
