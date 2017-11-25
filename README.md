# text-print-to-images

This project provides utility class to print text to BMP image.

# How to use

	// Using default settings
	Text2ImageSettings settings = new Text2ImageSettings();

	// Print the "Hello world!" to image
	Text2ImageOutput output = Text2ImageUtil.generateImage(settings, "Hello world!");

	// You can get the image
	byte[] image = output.getImage()
	// You can get the image height
	System.out.println("image height: " + output.getHeight());
	// You can get the printed text length
	System.out.println("process text: " + output.getIndex());

More detail see the JUnit test class: Text2ImageUtilTest

# How to build
	mvn clean install

