package com.fourdsight.service.screeen;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ScreenShoter {
	
	private static final int IMG_WIDTH = 50;
	private static final int IMG_HEIGHT = 50;

	public String execute(String url) throws IOException {
		File srcFiles = null;
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setJavascriptEnabled(true);
		capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
				"C:\\Users\\mkilic\\Desktop\\challenge\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");

		WebDriver driver = new PhantomJSDriver(capabilities);
		driver.manage().window().setSize(new Dimension(300, 300));
		driver.get(url);
		srcFiles = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// loadFile(encodeFileToBase64Binary(srcFiles));
		return encodeFileToBase64Binary(srcFiles);
	}

	private String encodeFileToBase64Binary(File fileImport) throws IOException {
		BufferedImage image = ImageIO.read(fileImport);
		BufferedImage resizeImageHintJpg = resizeImage(image, 1);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write( resizeImageHintJpg, "jpg", baos );
		baos.flush();
		byte[] imageInByte = baos.toByteArray();
		baos.close();
		
		String imageStr = null;
		
		imageStr = Base64.encodeBase64String(imageInByte);
		System.out.println(imageStr);
		return imageStr;
	}

	
	 private static BufferedImage resizeImage(BufferedImage originalImage, int type){
			BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
			Graphics2D g = resizedImage.createGraphics();
			g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
			g.dispose();
				
			return resizedImage;
		    }
	 
	
	
	
	// private static void loadFile(String base64) {
	// String base64String = base64;
	//
	// String extension="jpg";
	//
	//
	// //convert base64 string to binary data
	// byte[] data = DatatypeConverter.parseBase64Binary(base64String);
	// String path = "C:\\Users\\mkilic\\Desktop\\challenge\\test_image." +
	// extension;
	// File file = new File(path);
	// try (OutputStream outputStream = new BufferedOutputStream(new
	// FileOutputStream(file))) {
	// outputStream.write(data);
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	//
	//
	// }

}
