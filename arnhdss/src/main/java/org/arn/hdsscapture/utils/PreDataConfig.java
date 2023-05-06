package org.arn.hdsscapture.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;

@Configuration
public class PreDataConfig {
    private final String PRELOADED_DATA = "classpath:hdss_schema.sql";
    private final String SCRIPT_EXECUTED_TABLE = "hdss_script_executed";
    private final String SCRIPT_EXECUTED_RECORD = "hdss_schema.sql";

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate datasource;

    @Autowired
    private ResourceLoader resourceLoader;

    @PostConstruct
    public void loadIfInMemory() throws Exception {
        Resource resource = resourceLoader.getResource(PRELOADED_DATA);
        Connection connection = datasource.getDataSource().getConnection();

        try {
            // Check if the script has already been executed
            if (!checkIfScriptExecuted(connection)) {
                // If the script has not been executed, execute it
                ScriptUtils.executeSqlScript(connection, resource);

                // Mark the script as executed
                markScriptExecuted(connection);
            }
        } finally {
            connection.close();
        }
    }

    private boolean checkIfScriptExecuted(Connection connection) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
                "SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = ?");
        stmt.setString(1, SCRIPT_EXECUTED_TABLE);

        ResultSet rs = stmt.executeQuery();

        if (rs.next() && rs.getInt(1) > 0) {
            stmt = connection.prepareStatement(
                    "SELECT COUNT(*) FROM " + SCRIPT_EXECUTED_TABLE + " WHERE script_name = ?");
            stmt.setString(1, SCRIPT_EXECUTED_RECORD);

            rs = stmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                // Script has already been executed
                return true;
            }
        }

        return false;
    }

    private void markScriptExecuted(Connection connection) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS " + SCRIPT_EXECUTED_TABLE + " (script_name VARCHAR(255) PRIMARY KEY)");

        stmt.executeUpdate();

        stmt = connection.prepareStatement("INSERT INTO " + SCRIPT_EXECUTED_TABLE + " (script_name) VALUES (?)");
        stmt.setString(1, SCRIPT_EXECUTED_RECORD);

        stmt.executeUpdate();
    }
}
