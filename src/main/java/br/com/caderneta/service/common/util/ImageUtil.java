package br.com.caderneta.service.common.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.web.multipart.MultipartFile;

import br.com.caderneta.service.common.exceptions.FileException;

public final class ImageUtil {

	public static final BufferedImage getJpgImageFromFile(MultipartFile file) {
		String ext = FilenameUtils.getExtension(file.getOriginalFilename().toLowerCase());
		if (!"png".equals(ext) && !"jpg".equals(ext)) {
			throw new FileException("Somente imagens PNG e JPG são permitidas");
		}
		
		try {
			BufferedImage img = ImageIO.read(file.getInputStream());
			if ("png".equals(ext)) {
				img = pngToJpg(img);
			}
			return img;
		} catch (IOException e) {
			throw new FileException("Erro ao ler arquivo");
		}
	}

	public static final  BufferedImage pngToJpg(BufferedImage img) {
		BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		jpgImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
		return jpgImage;
	}

	public static final  BufferedImage cropSquare(BufferedImage sourceImg) {
		int min = (sourceImg.getHeight() <= sourceImg.getWidth()) ? sourceImg.getHeight() : sourceImg.getWidth();
		return Scalr.crop(
			sourceImg, 
			(sourceImg.getWidth()/2) - (min/2), 
			(sourceImg.getHeight()/2) - (min/2), 
			min, 
			min);	
	}

	public static final  BufferedImage resize(BufferedImage sourceImg, int size) {
		return Scalr.resize(sourceImg, Scalr.Method.ULTRA_QUALITY, size);
	}

	public static final  InputStream getInputStream(BufferedImage img, String extension) {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(img, extension, os);
			return new ByteArrayInputStream(os.toByteArray());
		} catch (IOException e) {
			throw new FileException("Erro ao ler arquivo");
		}
	} 
	
	private ImageUtil() {
		super();
	}
}
