package com.example.banco_hex_yoder.logs_repository.data;



import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
        basePackages = "com.example.banco_hex_yoder.logs_repository.data.repositorios",
        mongoTemplateRef = "logsMongoTemplate"
)
public class LogsMongoRepository {

    @Bean(name = "logsMongoTemplate")
    public MongoTemplate logsMongoTemplate() {
        return new MongoTemplate(
                MongoClients.create("mongodb+srv://mongo:mongo@sofkacluster.p0e1d.mongodb.net/bancologs?ssl=true"),
                "bancologs"
        );
    }
}
