package by.it_academy.jd2.Mk_JD2_98_23.controllers.coonections;

import java.util.Properties;

public class DataConnections {
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:postgresql://localhost:5432/voting";
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "root");
    }
}
