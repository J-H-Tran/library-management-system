package com.library.util;

import com.library.dao.DatabaseHelper;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class FileIOHelper {
    private final DatabaseHelper dbHelper;

    public FileIOHelper() {
        dbHelper = new DatabaseHelper();
    }
    public void backupDatabase(String filePath) {
        String sql = "BACKUP TO '" + filePath + "'";
        try (Connection conn = dbHelper.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Database backup completed successfully.");
        } catch (SQLException e) {
            Logger.log("An error occurred while backing up the database.");
        }
    }

    public void restoreDatabase(String filePath) {
        String sql = "RESTORE FROM '" + filePath + "'";
        try (Connection conn = dbHelper.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Database restore completed successfully.");
        } catch (SQLException e) {
            Logger.log("An error occurred while restoring the database.");
        }
    }
}
