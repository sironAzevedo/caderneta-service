package br.com.caderneta.service.configuracao.aws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AwsS3Config {
	
	@Value("${aws.access_key_id}")
	private String awsId;

	@Value("${aws.secret_access_key}")
	private String awsKey; 

	@Bean
	public AmazonS3 s3client() {
		BasicAWSCredentials awsCred = new BasicAWSCredentials(awsId, awsKey);
		AmazonS3 s3client = AmazonS3ClientBuilder.standard().withRegion(Regions.SA_EAST_1)
				.withCredentials(new AWSStaticCredentialsProvider(awsCred)).build();
		return s3client;
	}

}
