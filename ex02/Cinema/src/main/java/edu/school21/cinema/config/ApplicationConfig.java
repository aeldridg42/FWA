package edu.school21.cinema.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

@Configuration
@ComponentScan("edu.school21.cinema")
@PropertySource("classpath:application.properties")
public class ApplicationConfig {
    private final ApplicationContext applicationContext;
    private final Environment env;

//    @Value("${defaultImage}")
//    private String defaultImage;

    @Autowired
    public ApplicationConfig(ApplicationContext applicationContext, Environment env) {
        this.applicationContext = applicationContext;
        this.env = env;
    }

    @Bean
    public DataSource dataSource() throws SQLException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("db.driverClassName")));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        Connection connection = dataSource.getConnection();
        ScriptUtils.executeSqlScript(connection, new ClassPathResource(Objects.requireNonNull(env.getProperty("schema"))));

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws SQLException {
        return new JdbcTemplate(dataSource());
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public String defaultImage() throws IOException {
        return new ClassPathResource(Objects.requireNonNull(env.getProperty("defaultImage"))).getFile().getAbsolutePath();
    }

}
