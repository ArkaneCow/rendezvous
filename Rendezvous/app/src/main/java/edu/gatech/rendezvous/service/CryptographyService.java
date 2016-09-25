package edu.gatech.rendezvous.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by jwpilly on 9/24/16.
 */
public class CryptographyService {
    private static CryptographyService ourInstance = null;


    private String hashAlgorithm = "SHA-1";

    public static CryptographyService getInstance() {
        if (ourInstance == null) {
            ourInstance = new CryptographyService();
        }
        return ourInstance;
    }
    private CryptographyService() {
    }


    public String getHashAlgorithm() {
        return hashAlgorithm;
    }

    public void setHashAlgorithm(String hashAlgorithm) {
        this.hashAlgorithm = hashAlgorithm;
    }

    public String hashString(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance(hashAlgorithm);
            md.update(input.getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
