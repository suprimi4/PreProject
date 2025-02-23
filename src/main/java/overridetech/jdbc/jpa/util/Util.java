package overridetech.jdbc.jpa.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hibernate.cfg.Configuration;
import overridetech.jdbc.jpa.model.User;

public class Util {
    public static Connection getJDBCConnection() {

        final String url = "jdbc:postgresql://localhost:5432/Auth";
        final String name = "postgres";
        final String password = "qwerty12356";
        try {
            return DriverManager.getConnection(url, name, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;


    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        return configuration.buildSessionFactory(builder.build());
    }

    private static Configuration getPostgresConfiguration() {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(User.class);
        return configuration;

    }

    public static SessionFactory getHibernateConnection() {
        Configuration configuration = getPostgresConfiguration();
        return createSessionFactory(configuration);
    }


}
