package miage.pds.orm;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * Created by user on 16/12/2015.
 */

@Configuration
public class SpringMongoConfig  {
    static final String URL_DB = "localhost";
    static final int PORT_DB = 27017;
    static final String DB_NAME = "CRM";


    public String getDatabaseName() {
        return SpringMongoConfig.DB_NAME;
    }



    public @Bean
    MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(new MongoClient(SpringMongoConfig.URL_DB,SpringMongoConfig.PORT_DB), getDatabaseName());
    }

    public @Bean
    MongoTemplate mongoTemplate() throws Exception {

        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());

        return mongoTemplate;

    }
}
