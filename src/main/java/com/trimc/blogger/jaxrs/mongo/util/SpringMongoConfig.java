package com.trimc.blogger.jaxrs.mongo.util;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;

@Configuration
@ComponentScan("com.trimc.blogger.jaxrs")
public class SpringMongoConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	@Qualifier("appConfig")
	public AppConfig getAppConfig() {
		return new AppConfig();
	}

	private MongoClient getMongoClient() throws Exception {
		MongoClient mongoClient = new MongoClient(getAppConfig().getHost(), getAppConfig().getPort());
		return mongoClient;
	}

	private MongoTemplate getMongoTemplate(String databaseName) throws Exception {
		SimpleMongoDbFactory factory = new SimpleMongoDbFactory(getMongoClient(), databaseName);
		MongoTemplate mongoTemplate = new MongoTemplate(factory);

		return mongoTemplate;
	}

	public @Bean MongoTemplate mongoTemplate() throws Exception {
		return getMongoTemplate(getAppConfig().getDatabase());
	}
}
