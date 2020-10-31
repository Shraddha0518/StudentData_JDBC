package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentApp {

	private Scanner sc = null;
	
	public StudentApp() {
		sc = new Scanner(System.in);
	}
	
	public void getStudentData() {
		System.out.println("Please enter sid, name, mobile and email:");
		Student s = new Student();
		s.setSid(Integer.parseInt(sc.nextLine()));
		s.setName(sc.nextLine());
		s.setMobile(sc.nextLine());
		s.setEmail(sc.nextLine());
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db", "root","root@123");
			
			PreparedStatement ps = con.prepareStatement("insert into stud_data(sid,name,mobile,emailid) values (?,?,?,?)");
			
			ps.setInt(1, s.getSid());
			ps.setString(2, s.getName());
			ps.setString(3,s.getMobile());
			ps.setString(4, s.getEmail());
			
			int status = ps.executeUpdate();
			
			ps.close();
			con.close();
			
			if(status > 0 ) {
				System.out.println("Student Inserted...");
			}else {
				System.out.println("Student operation failed...");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		StudentApp sap = new StudentApp();
		sap.getStudentData();
	}
}
