package com.library.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {
    private static final String LOG_FILE = "logs/library.log";

    public static void log(String message) {
        try (var fw = new FileWriter(LOG_FILE, true);
             var bw = new BufferedWriter(fw);
             var out = new PrintWriter(bw)) {
            out.println(message);

        } catch (IOException e) {
            System.out.println("An error occurred while logging.");
        }
    }
}
