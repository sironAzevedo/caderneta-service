package br.com.caderneta.service.service;

import java.io.InputStream;
import java.net.URI;

import org.springframework.web.multipart.MultipartFile;

public interface IS3Service {

	URI uploadFile(MultipartFile multipartFile);

	URI uploadFile(InputStream is, String fileName, String contentType);

	URI uploadFile(InputStream is, String fileName, String contentType, String folderName);

}
