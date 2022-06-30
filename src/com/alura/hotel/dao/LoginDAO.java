package com.alura.hotel.dao;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.JPasswordField;

import com.alura.hotel.factory.ConnectionFactory;

public class LoginDAO {
	private Connection con;

	public LoginDAO(Connection con) {
		this.con = new ConnectionFactory().recuperaConexion();
	}

	public Boolean logearse(String usuario, JPasswordField txtContrasena) {
		boolean autenticado = false;
		try{
			final PreparedStatement statement = con.prepareStatement(
					"SELECT PASSWORD FROM USUARIOS WHERE USUARIO = '" + usuario + "'",
					Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				statement.execute();
				ResultSet resultSet = statement.getResultSet();		
				
				while (resultSet.next()) {
					try {
						if(validatePassword(new String(txtContrasena.getPassword()), resultSet.getString("PASSWORD"))) {
							autenticado = true;
						}
					} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);			
		}		
		
		return autenticado;
	}

	private String generateStorngPasswordHash(JPasswordField pass) 
		    throws NoSuchAlgorithmException, InvalidKeySpecException
		{
			String password = new String(pass.getPassword());
		    int iterations = 1000;
		    char[] chars = password.toCharArray();
		    byte[] salt = getSalt();

		    PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
		    SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

		    byte[] hash = skf.generateSecret(spec).getEncoded();
		    return iterations + ":" + toHex(salt) + ":" + toHex(hash);
		}
	private byte[] getSalt() throws NoSuchAlgorithmException
	{
	    SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
	    byte[] salt = new byte[16];
	    sr.nextBytes(salt);
	    return salt;
	}

	private String toHex(byte[] array) throws NoSuchAlgorithmException
	{
	    BigInteger bi = new BigInteger(1, array);
	    String hex = bi.toString(16);
	    
	    int paddingLength = (array.length * 2) - hex.length();
	    if(paddingLength > 0)
	    {
	        return String.format("%0"  +paddingLength + "d", 0) + hex;
	    }else{
	        return hex;
	    }
	}
	
	private static boolean validatePassword(String originalPassword, String storedPassword) 
		    throws NoSuchAlgorithmException, InvalidKeySpecException
		{
		    String[] parts = storedPassword.split(":");
		    int iterations = Integer.parseInt(parts[0]);

		    byte[] salt = fromHex(parts[1]);
		    byte[] hash = fromHex(parts[2]);

		    PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), 
		        salt, iterations, hash.length * 8);
		    SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		    byte[] testHash = skf.generateSecret(spec).getEncoded();

		    int diff = hash.length ^ testHash.length;
		    for(int i = 0; i < hash.length && i < testHash.length; i++)
		    {
		        diff |= hash[i] ^ testHash[i];
		    }
		    return diff == 0;
		}
	
	private static byte[] fromHex(String hex) throws NoSuchAlgorithmException
	{
	    byte[] bytes = new byte[hex.length() / 2];
	    for(int i = 0; i < bytes.length ;i++)
	    {
	        bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
	    }
	    return bytes;
	}
}
