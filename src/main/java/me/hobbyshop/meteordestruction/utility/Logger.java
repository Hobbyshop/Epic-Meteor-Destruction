package me.hobbyshop.meteordestruction.utility;

public class Logger {

    public static void info(String msg) {
        System.out.println("[INFO] " + msg);
    }

    public static void warning(String msg) {
        System.out.println("[WARNING] " + msg);
    }

    public static void error(String msg) {
        System.out.println("[ERROR] " + msg);
    }

    public static void fatal(String msg) {
        System.out.println("[FATAL] " + msg);
    }

}
