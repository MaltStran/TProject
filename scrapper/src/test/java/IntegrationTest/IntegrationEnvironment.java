package IntegrationTest;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.DirectoryResourceAccessor;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.File;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
public class IntegrationEnvironment {

    static final PostgreSQLContainer CONTAINER;

    static {
        CONTAINER = (PostgreSQLContainer) new PostgreSQLContainer("postgres:15")
                .withDatabaseName("scrapper")
                .withUsername("postgres")
                .withPassword("1234")
                .withExposedPorts(5432);
        CONTAINER.start();
        try(Connection connection = DriverManager.getConnection(CONTAINER.getJdbcUrl(), CONTAINER.getUsername(),
                CONTAINER.getPassword())){
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            Path changeLogFile = new File("").toPath().toAbsolutePath().getParent().resolve("migrations");
            Liquibase liquibase = new Liquibase("master.xml",new DirectoryResourceAccessor(changeLogFile), database);
            liquibase.update(new Contexts(), new LabelExpression());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
