package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DaoBook {

	// Save a book to the DB
	public static int save(String callno,String name,String author,String publisher,int quantity) {
		int status = 0;
		try {
			// Creating the Database connection and mapping the objects to the database
			Connection con = DB.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"insert into books(callno,name,author,publisher,quantity) values(?,?,?,?,?)");
			ps.setString(1, callno);
			ps.setString(2, name);
			ps.setString(3, author);
			ps.setString(4, publisher);
			ps.setInt(5, quantity);
			status = ps.executeUpdate();
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);

		}
		
		return status;
		
		
		
	}
	
	
	
}
