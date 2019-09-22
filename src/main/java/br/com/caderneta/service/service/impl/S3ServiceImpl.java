package br.com.caderneta.service.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

import br.com.caderneta.service.common.exceptions.FileException;
import br.com.caderneta.service.service.IS3Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class S3ServiceImpl implements IS3Service {

	@Autowired
	private AmazonS3 s3client;

	@Value("${s3.bucket}")
	private String bucketName;

	@Override
	public URI uploadFile(MultipartFile file) {

		try {
			String fileName = file.getOriginalFilename();
			InputStream is = file.getInputStream();
			String contentType = file.getContentType();
			return uploadFile(is, fileName, contentType);
		} catch (IOException e) {
			throw new FileException("Erro de IO: " + e.getMessage());
		}
	}

	@Override
	public URI uploadFile(InputStream is, String fileName, String contentType) {

		try {
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(contentType);
			log.info("Iniciando upload");
			s3client.putObject(bucketName, fileName, is, meta);
			log.info("Upload finalizado");
			return s3client.getUrl(bucketName, fileName).toURI();
		} catch (URISyntaxException e) {
			throw new FileException("Erro ao converter URL para URI");
		}
	} 
	
	@Override
	public URI uploadFile(InputStream is, String fileName, String contentType, String folderName) {

		try {
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(contentType);
			log.info("Iniciando upload");
			s3client.putObject(bucketName + "/" + folderName, fileName, is, meta);
			log.info("Upload finalizado");
			return s3client.getUrl(bucketName + "/" + folderName, fileName).toURI();
		} catch (URISyntaxException e) {
			throw new FileException("Erro ao converter URL para URI");
		}
	} 
}
