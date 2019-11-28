package com.library.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LibrarianForm extends JFrame {

	static LibrarianForm frame;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JPasswordField passwordField;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try {
					frame = new LibrarianForm();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
	
			}
		});

		
	}
	
	/**
	 * Create the frame.
	 */
	public LibrarianForm() {
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
}
