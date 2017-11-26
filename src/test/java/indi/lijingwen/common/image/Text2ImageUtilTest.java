package indi.lijingwen.common.image;

// import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Testing class for Text2ImageUtil.
 *
 * @author lijingwen
 */
public class Text2ImageUtilTest {

    private Text2ImageSettings settings = null;

    @Before
    public void setup() {
        settings = new Text2ImageSettings();
    }

    @Test
    public void shortContent() throws IOException {
        String text = "Hello world!";
        Text2ImageOutput output = Text2ImageUtil.generateImage(settings, text);
        validateOutput(text, output, "text-short-");
    }

    @Test
    public void longContent() throws IOException {
        String text = "testing is testing, not enough to continue testing. testing is testing, not enough to continue testing. testing is testing, not enough to continue testing";
        Text2ImageOutput output = Text2ImageUtil.generateImage(settings, text);
        validateOutput(text, output, "text-long-");
    }

    @Test
    public void multiLinesShortContent() throws IOException {
        String text = "testing is testing, not enough to continue testing ----\n---- testing is testing, not enough to continue testing.----\n----testing is testing, not enough to continue testing";
        Text2ImageOutput output = Text2ImageUtil.generateImage(settings, text);
        validateOutput(text, output, "text-mutil-line-");
    }

    @Test
    public void multiLinesLongContent() throws IOException {
        String text = "testing is testing, not enough to continue testing.testing is testing, not enough to continue testing.testing is testing, not enough to continue testing.\n testing is testing, not enough to continue testing.testing is testing, not enough to continue testing.testing is testing, not enough to continue testing\ntesting is testing, not enough to continue testing";
        Text2ImageOutput output = Text2ImageUtil.generateImage(settings, text);
        validateOutput(text, output, "text-mutil-long-line-");
    }

    @Test
    public void multiImagesContent() throws IOException {
        String text = "testing is testing, not enough to continue testing.testing is testing, not enough to continue testing.testing is testing, not enough to continue testing.\n testing is testing, not enough to continue testing.testing is testing, not enough to continue testing.testing is testing, not enough to continue testing\ntesting is testing, not enough to continue testing";
        int index = 0;
        int files = 0;
        settings.setHeight(70);
        while (index < text.length()) {
            files++;
            Text2ImageOutput output = Text2ImageUtil.generateImage(settings, text.substring(index));
            index += output.getIndex();
            validateMutilOutput(text, output, "text-mutil-images-");
        }
        Assert.assertEquals(files, 2);
    }

    @Test
    public void chineseContent() throws IOException {
        String text = "测试就是测试而已，继续测试不够长。-------\n --------测试就是测试而已，继续测试不够长。测试就是测试而已，继续测试不够长。测试就是测试而已，继续测试不够长。测试就是测试而已，继续测试不够长。测试就是测试而已，继续测试不够长。测试就是测试而已，继续测试不够长。测试就是测试而已，继续测试不够长。测试就是测试而已，继续测试不够长。测试就是测试而已，继续测试不够长。测试就是测试而已，继续测试不够长。";
        int index = 0;
        int files = 0;
        settings.setHeight(55);
        while (index < text.length()) {
            files++;
            Text2ImageOutput output = Text2ImageUtil.generateImage(settings, text.substring(index));
            index += output.getIndex();
            validateMutilOutput(text, output, "text-chinese-");
        }

        Assert.assertEquals(files, 3);
    }

    private void validateOutput(String text, Text2ImageOutput output, String outputFilePrefix) throws IOException {
        Assert.assertNotNull(output.getImage());
        Assert.assertEquals(text.length(), output.index);
        System.out.println("image height: " + output.getHeight());
        System.out.println("process text: " + output.getIndex());
        writeImageToFile(output.getImage());
    }

    private void validateMutilOutput(String text, Text2ImageOutput output, String outputFilePrefix) throws IOException {
        Assert.assertNotNull(output.getImage());
        Assert.assertTrue(text.length() >= output.index);
        System.out.println("image height: " + output.getHeight());
        System.out.println("process text: " + output.getIndex());
        writeImageToFile(output.getImage());
    }

    private void writeImageToFile(byte[] image) {
        System.out.println("TODO: if you want to see the test case output file, please open the commented out code");
        // TODO: if you want to see the test case output file, please open the commented out code.
        // Also should import org.apache.commons.io.FileUtils;
        // File dictionary = new File("./");
        // File imgBytesFile = File.createTempFile(outputFilePrefix, ".bmp", dictionary);
        // FileUtils.writeByteArrayToFile(imgBytesFile, output.getImage());
        // System.out.println(imgBytesFile);
    }


}
