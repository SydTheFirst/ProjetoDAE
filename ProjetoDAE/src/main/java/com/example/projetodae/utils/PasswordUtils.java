package com.example.projetodae.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
//NAO É USADO DE MOMENTO TALVEZ SEJA USADO NO FUTURO
public class PasswordUtils {

    // Generate a random salt
    public static String getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16]; // 16 bytes for salt
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt); // Encode to String for storage
    }

    // Hash the password with SHA-256 and a salt
    public static String hashPassword(String password, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt.getBytes()); // Add the salt
        byte[] hashedPassword = md.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hashedPassword); // Encode to String for storage
    }

    // Combine password and salt to create a hashed password
    public static String createHashedPassword(String password) throws NoSuchAlgorithmException {
        String salt = getSalt(); // Generate a new salt
        String hashedPassword = hashPassword(password, salt);
        return salt + ":" + hashedPassword; // Store salt and hash together, separated by a colon
    }

    // Method to check if a password matches a stored hash
    public static boolean checkPassword(String password, String storedHash) throws NoSuchAlgorithmException {
        String[] parts = storedHash.split(":");
        String salt = parts[0];
        String hashedPassword = hashPassword(password, salt);
        return hashedPassword.equals(parts[1]); // Compare the hashed passwords
    }
}
