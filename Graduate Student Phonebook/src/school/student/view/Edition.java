package school.student.view;
import school.student.entity.*;
import school.student.service.*;
import school.student.service.DataReadWrite;

import java.io.RandomAccessFile;

import javax.swing.JOptionPane;

import javafx.application.HostServices;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Edition extends VBox {

	private ObservableList<Student> listStudent;
	private BorderPane root;
	private Program program;
	private Stage stage;
	private BinaryTreeLogic treelogic = new BinaryTreeLogic();
	private BinaryTree tree;
	private String login;
	private String password;
	
	GridPane grid = new GridPane();
	
	private Label labelLastName = new Label("Last Name : ");
	private TextField textfieldLastName = new TextField();
	private Label labelFirstName = new Label("First Name : ");
	private TextField textfieldFirstName = new TextField();
	private Label labelYear = new Label("Graduation Year : ");
	private TextField textfieldYear = new TextField();
	private Label labelDepartment = new Label("Department : ");
	private TextField textfieldDepartment = new TextField();
	private Label labelPhone = new Label("Phone : ");
	private TextField textfieldPhone = new TextField();
	
	private Button buttonSave = new Button("Save");
	private Button buttonReturn = new Button("Return");
	
	private ImageView ima = new ImageView();
	private Button buttonIma = new Button(
			"file:///C:/Users/David.NAVRUZ/Documents/workspace/Graduate Student Phonebook/logo.png");
	
	
	public Edition(Program program) {
		this.program = program;
		this.listStudent = program.getlistStudent();
		this.root = program.getRoot();
		Edition ed = this;
		MainPage main = new MainPage(program);
		DataReadWrite data = new DataReadWrite();
		
		// Return Button Action Event
		buttonReturn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				MainPage main = new MainPage(program);
					if(password.equals("admin") && login.equals("admin")) {
						// displays the new login bar
						main.getLabelConnection().setVisible(false);
						main.getTextfieldLogin().setVisible(false);
						main.getPasswordField().setVisible(false);
						main.getButtonSignIn().setVisible(false);
						main.getButtonCreateStudent().setVisible(false);
						main.getButtonDeleteStudent().setVisible(false);
						main.getButtonModifyStudent().setVisible(false);
						main.gethBoxConnection().getChildren().add(main.getLabelConnectedAdmin());
						main.gethBoxConnection().getChildren().add(main.getButtonSignOut());
					}
					root.setCenter(main);
			}
		});
			// Save Button Action Event
			buttonSave.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					try(RandomAccessFile raf = new RandomAccessFile(
						"C:/Users/David.NAVRUZ/Documents/workspace/Graduate Student Phonebook/binarytree.txt", "rw")){
						raf.seek(0);
						System.out.println("Initialization pointer" + raf.getFilePointer());
						String lastName = ed.textfieldLastName.getText();
						Student selectedStudent = new Student();
						selectedStudent = treelogic.globalSearch2(lastName.trim(), null);
						System.out.println(selectedStudent);
						
						int sIndex = selectedStudent.getIndex();
						System.out.println(sIndex*data.NODE_SIZE);
						raf.seek(sIndex*data.NODE_SIZE + data.FIRSTNAME);
						System.out.println("Pointer node index researched :" + raf.getFilePointer());
						
						String firstName = data.completeSpace(ed.textfieldFirstName.getText(), data.FIRSTNAME);
						String department = data.completeSpace(ed.textfieldDepartment.getText(), data.DPT);
						String phone = data.completeSpace(ed.textfieldPhone.getText(), data.PHONE);
						String year = data.completeSpace(ed.textfieldYear.getText(), data.YEAR);
						
						raf.writeChars(firstName);
						System.out.println("pos after "+ firstName +" " + raf.getFilePointer());
						raf.writeChars(department);
						System.out.println("pos after "+ department +" " + raf.getFilePointer());
						raf.writeChars(phone);
						System.out.println("pos after "+ phone +" " + raf.getFilePointer());
						raf.writeChars(year);
						System.out.println("pos after "+ year +" " + raf.getFilePointer());
						
						JOptionPane.showMessageDialog(null, "** You have modified the Student **");

					} catch (Exception e) {
						e.printStackTrace();
					}

					if(password.equals("admin") && login.equals("admin")) {
						// displays the new login bar
						main.getLabelConnection().setVisible(false);
						main.getTextfieldLogin().setVisible(false);
						main.getPasswordField().setVisible(false);
						main.getButtonSignIn().setVisible(false);
						main.getButtonCreateStudent().setVisible(true);
						main.getButtonDeleteStudent().setVisible(true);
						main.getButtonModifyStudent().setVisible(true);
						main.gethBoxConnection().getChildren().add(main.getLabelConnectedAdmin());
						main.gethBoxConnection().getChildren().add(main.getButtonSignOut());
					}
						root.setCenter(main);
				}
			});
			
			grid.setAlignment(Pos.CENTER);
			grid.setPrefSize(500, 500);
			grid.setHgap(20);
			grid.setVgap(20);
			grid.add(labelLastName, 0, 2);
			grid.add(textfieldLastName, 1, 2);
			grid.add(labelFirstName, 0, 3);
			grid.add(textfieldFirstName, 1, 3);
			grid.add(labelDepartment, 0, 4);
			grid.add(textfieldDepartment, 1, 4);
			grid.add(labelPhone, 0, 5);
			grid.add(textfieldPhone, 1, 5);
			grid.add(labelYear, 0, 6);
			grid.add(textfieldYear, 1, 6);
			grid.add(buttonSave, 0, 8);
			grid.add(buttonReturn, 1, 8);
			grid.add(buttonIma, 2, 8);
		
			buttonIma.setGraphic(ima);
			ima.setFitHeight(30);
			ima.setFitWidth(30);
			buttonIma.setPrefSize(50, 40);
			buttonIma.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					System.out.println("je suis ds l'événement LinkedIn");
					HostServices services = program.getHostServices();
					String url = new String("https://www.google.com/search?q=");
					url+= textfieldFirstName.getText();
					url+= "+";
					url+= textfieldLastName.getText();
					url+= "+site%3Alinkedin.com";
					services.showDocument(url);

				}
			});
			this.getChildren().add(grid);
		
			
		}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public TextField getTextfieldLastName() {
		return textfieldLastName;
	}


	public void setTextfieldLastName(TextField textfieldLastName) {
		this.textfieldLastName = textfieldLastName;
	}


	public TextField getTextfieldFirstName() {
		return textfieldFirstName;
	}


	public void setTextfieldFirstName(TextField textfieldFirstName) {
		this.textfieldFirstName = textfieldFirstName;
	}


	public TextField getTextfieldYear() {
		return textfieldYear;
	}


	public void setTextfieldYear(TextField textfieldYear) {
		this.textfieldYear = textfieldYear;
	}


	public TextField getTextfieldDepartment() {
		return textfieldDepartment;
	}


	public void setTextfieldDepartment(TextField textfieldDepartment) {
		this.textfieldDepartment = textfieldDepartment;
	}


	public TextField getTextfieldPhone() {
		return textfieldPhone;
	}


	public void setTextfieldPhone(TextField textfieldPhone) {
		this.textfieldPhone = textfieldPhone;
	}
		

	
}
