package fr.ecp.IS1220.MyFoodora.System;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.io.*;

/**
 * This class permit to secure passwords with an MD5 algorithm
 */
public class Password implements Serializable {

	private static final long serialVersionUID = -2139226566254215773L;
	
	private String hashedPassword;
	
	public Password (String passwordToHash){
		try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes 
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            this.hashedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hashedPassword == null) ? 0 : hashedPassword.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Password other = (Password) obj;
		if (hashedPassword == null) {
			if (other.hashedPassword != null)
				return false;
		} else if (!hashedPassword.equals(other.hashedPassword))
			return false;
		return true;
	}
	
	
}
