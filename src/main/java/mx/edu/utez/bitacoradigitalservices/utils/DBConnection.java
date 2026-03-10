package mx.edu.utez.bitacoradigitalservices.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DBConnection {
    @Value("${db.host}")
    private String DB_HOST;
    @Value("${db.port}")
    private int DB_PORT;
    @Value("${db.name}")
    private String DB_NAME;
    @Value("${db.user}")
    private String DB_USER;
    @Value("${db.pass}")
    private String DB_PASSWORD;

    @Bean
    public DataSource getConnection() {
        DriverManagerDataSource source = new DriverManagerDataSource();

        source.setDriverClassName("com.mysql.cj.jdbc.Driver");
        source.setUsername(DB_USER);
        source.setPassword(DB_PASSWORD);

        source.setUrl(String.format("jdbc:mysql://%s:%s/%s", DB_HOST, DB_PORT, DB_NAME));

        return source;
    }
}
