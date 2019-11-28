package school.student.view;

import java.awt.Desktop;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import school.student.entity.Student;
import school.student.service.BinaryTree;
import school.student.service.BinaryTreeLogic;
import school.student.service.DataReadWrite;

public class MainPage extends VBox{

	
	// initialisation
	private ObservableList<Student> listStudent;
	private BorderPane root;
	private Program program;
	private Stage stage;
	private BinaryTreeLogic treelogic = new BinaryTreeLogic();
	private BinaryTree tree;
	
	// Creating the textfields and buttons for Admin sign in
	private Button buttonLocateFile = new Button ("📂 Browse");
	private Label labelConnection = new Label("Admin Sign in: ");
	private TextField textfieldLogin = new TextField("");
	private PasswordField passwordField = new PasswordField();
	private Button buttonSignIn = new Button("Sign in");
	private Button buttonSignOut = new Button("Sign out");
	private Label labelConnectedAdmin = new Label();
	
	
	// Creating the textfields and buttons for research
	private Label labelLastName = new Label("LastName : ");
	private TextField textFieldLastName = new TextField();
	private Label labelFirstName = new Label("FirstName : ");
	private TextField textFieldFirstName = new TextField();
	private Label labelYear = new Label("Graduation Year : ");
	private TextField textFieldYear = new TextField();
	private Label labelDepartment= new Label("Department : ");
	private ChoiceBox<String> choiceBoxDepartment = new ChoiceBox<String>();
	private Label labelPhone = new Label("Phone : ");
	private TextField textFieldPhone = new TextField();
	private Button buttonResearch = new Button("🔍  Research");
	
	// Creating the alerts
	Alert a1 = new Alert(AlertType.INFORMATION);
	
	// Creating the print button
	private Button buttonPrint = new Button("🖶  Print out the list");
	private Label labelEmpty = new Label();
	private Button buttonReturn = new Button("Return");
	
	
	// Creating the TableView
	private TableView<Student> viewliste = new TableView<Student>();
	private TableColumn<Student , String> columnLastName = new TableColumn<>("LastName");
	private TableColumn columnFirstName = new TableColumn<>("FirstName");
	private TableColumn columnDepartment = new TableColumn<>("Department");
	private TableColumn columnPhone = new TableColumn<>("Phone");
	private TableColumn columnYear = new TableColumn<>("Graduation Year");
	
	// Images
	private ImageView logo = new ImageView("file:///D:/ANNUAIRE/annuaire_projet1/Projet1/logo_lotus.jpg");
	private ImageView banner = new ImageView("file:///D:/ANNUAIRE/annuaire_projet1/Projet1/youpibanner.jpg");
	
	
	// Welcome message
	private Label labelWelcome = new Label(" Welcome ");

	// Creating the button "Create a Student"
	private Button buttonCreateStudent = new Button("🆕  Create a Student");

	// Creating the button "Delete a Student"
	private Button buttonDeleteStudent = new Button("🗑  Delete a Student");

	// Creating the button "Modify a Student"
	private Button buttonModifyStudent = new Button("🖉  Modify a Student");

	private GridPane hboxHelp = new GridPane();
	private Button buttonHelp = new Button("❓");
	

	// GETTERS AND SETTERS ////////////////////////////////////////////

	public ObservableList<Student> getListeStagiaire() {
		return listStudent;
	}
	public void setListeStagiaire(ObservableList<Student> listStudent) {
		this.listStudent = listStudent;
	}
	public BorderPane getRoot() {
		return root;
	}
	public void setRoot(BorderPane root) {
		this.root = root;
	}
	public Program getProgramme() {
		return program;
	}
	public void setProgramme(Program program) {
		this.program = program;
	}
	public Stage getStage() {
		return stage;
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	public BinaryTreeLogic getTreelogic() {
		return treelogic;
	}
	public void setTreelogic(BinaryTreeLogic treelogic) {
		this.treelogic = treelogic;
	}
	public BinaryTree getTree() {
		return tree;
	}
	public void setTree(BinaryTree tree) {
		this.tree = tree;
	}
	public Button getButtonLocateFile() {
		return buttonLocateFile;
	}
	public void setButtonLocateFile(Button buttonLocateFile) {
		this.buttonLocateFile = buttonLocateFile;
	}
	public Label getLabelConnection() {
		return labelConnection;
	}
	public void setLabelConnection(Label labelConnection) {
		this.labelConnection = labelConnection;
	}
	public TextField getTextfieldLogin() {
		return textfieldLogin;
	}
	public void setTextfieldLogin(TextField textfieldLogin) {
		this.textfieldLogin = textfieldLogin;
	}
	public PasswordField getPasswordField() {
		return passwordField;
	}
	public void setPasswordField(PasswordField passwordField) {
		this.passwordField = passwordField;
	}
	public Button getButtonSignIn() {
		return buttonSignIn;
	}
	public void setButtonSignIn(Button buttonSignIn) {
		this.buttonSignIn = buttonSignIn;
	}
	public Button getButtonSignOut() {
		return buttonSignOut;
	}
	public void setButtonSignOut(Button buttonSignOut) {
		this.buttonSignOut = buttonSignOut;
	}
	public Label getLabelConnectedAdmin() {
		return labelConnectedAdmin;
	}
	public void setLabelConnectedAdmin(Label labelConnectedAdmin) {
		this.labelConnectedAdmin = labelConnectedAdmin;
	}
	public Label getLabelLastName() {
		return labelLastName;
	}
	public void setLabelLastName(Label labelLastName) {
		this.labelLastName = labelLastName;
	}
	public TextField getTextFieldLastName() {
		return textFieldLastName;
	}
	public void setTextFieldLastName(TextField textFieldLastName) {
		this.textFieldLastName = textFieldLastName;
	}
	public Label getLabelFirstName() {
		return labelFirstName;
	}
	public void setLabelFirstName(Label labelFirstName) {
		this.labelFirstName = labelFirstName;
	}
	public TextField getTextFieldFirstName() {
		return textFieldFirstName;
	}
	public void setTextFieldFirstName(TextField textFieldFirstName) {
		this.textFieldFirstName = textFieldFirstName;
	}
	public Label getLabelYear() {
		return labelYear;
	}
	public void setLabelYear(Label labelYear) {
		this.labelYear = labelYear;
	}
	public TextField getTextFieldYear() {
		return textFieldYear;
	}
	public void setTextFieldYear(TextField textFieldYear) {
		this.textFieldYear = textFieldYear;
	}
	public Label getLabelDepartment() {
		return labelDepartment;
	}
	public void setLabelDepartment(Label labelDepartment) {
		this.labelDepartment = labelDepartment;
	}
	public ChoiceBox<String> getChoiceBoxDepartment() {
		return choiceBoxDepartment;
	}
	public void setChoiceBoxDepartment(ChoiceBox<String> choiceBoxDepartment) {
		this.choiceBoxDepartment = choiceBoxDepartment;
	}
	public Label getLabelPhone() {
		return labelPhone;
	}
	public void setLabelPhone(Label labelPhone) {
		this.labelPhone = labelPhone;
	}
	public TextField getTextFieldPhone() {
		return textFieldPhone;
	}
	public void setTextFieldPhone(TextField textFieldPhone) {
		this.textFieldPhone = textFieldPhone;
	}
	public Button getButtonResearch() {
		return buttonResearch;
	}
	public void setButtonResearch(Button buttonResearch) {
		this.buttonResearch = buttonResearch;
	}
	public Alert getA1() {
		return a1;
	}
	public void setA1(Alert a1) {
		this.a1 = a1;
	}
	public Button getButtonPrint() {
		return buttonPrint;
	}
	public void setButtonPrint(Button buttonPrint) {
		this.buttonPrint = buttonPrint;
	}
	public Label getLabelEmpty() {
		return labelEmpty;
	}
	public void setLabelEmpty(Label labelEmpty) {
		this.labelEmpty = labelEmpty;
	}
	public Button getButtonReturn() {
		return buttonReturn;
	}
	public void setButtonReturn(Button buttonReturn) {
		this.buttonReturn = buttonReturn;
	}
	public TableView<Student> getViewliste() {
		return viewliste;
	}
	public void setViewliste(TableView<Student> viewliste) {
		this.viewliste = viewliste;
	}
	public TableColumn<Student, String> getColumnLastName() {
		return columnLastName;
	}
	public void setColumnLastName(TableColumn<Student, String> columnLastName) {
		this.columnLastName = columnLastName;
	}
	public TableColumn getColumnFirstName() {
		return columnFirstName;
	}
	public void setColumnFirstName(TableColumn columnFirstName) {
		this.columnFirstName = columnFirstName;
	}
	public TableColumn getColumnDepartment() {
		return columnDepartment;
	}
	public void setColumnDepartment(TableColumn columnDepartment) {
		this.columnDepartment = columnDepartment;
	}
	public TableColumn getColumnPhone() {
		return columnPhone;
	}
	public void setColumnPhone(TableColumn columnPhone) {
		this.columnPhone = columnPhone;
	}
	public TableColumn getColumnYear() {
		return columnYear;
	}
	public void setColumnYear(TableColumn columnYear) {
		this.columnYear = columnYear;
	}
	public ImageView getLogo() {
		return logo;
	}
	public void setLogo(ImageView logo) {
		this.logo = logo;
	}
	public ImageView getBanner() {
		return banner;
	}
	public void setBanner(ImageView banner) {
		this.banner = banner;
	}
	public Label getLabelWelcome() {
		return labelWelcome;
	}
	public void setLabelWelcome(Label labelWelcome) {
		this.labelWelcome = labelWelcome;
	}
	public Button getButtonCreateStudent() {
		return buttonCreateStudent;
	}
	public void setButtonCreateStudent(Button buttonCreateStudent) {
		this.buttonCreateStudent = buttonCreateStudent;
	}
	public Button getButtonDeleteStudent() {
		return buttonDeleteStudent;
	}
	public void setButtonDeleteStudent(Button buttonDeleteStudent) {
		this.buttonDeleteStudent = buttonDeleteStudent;
	}
	public Button getButtonModifyStudent() {
		return buttonModifyStudent;
	}
	public void setButtonModifyStudent(Button buttonModifyStudent) {
		this.buttonModifyStudent = buttonModifyStudent;
	}
	public GridPane getHboxHelp() {
		return hboxHelp;
	}
	public void setHboxHelp(GridPane hboxHelp) {
		this.hboxHelp = hboxHelp;
	}
	public Button getButtonHelp() {
		return buttonHelp;
	}
	public void setButtonHelp(Button buttonHelp) {
		this.buttonHelp = buttonHelp;
	}
	

	///////////////////////////////////////////////////////////////////
	
	public ObservableList<Student> getListStudent() {
		return listStudent;
	}
	public void setListStudent(ObservableList<Student> listStudent) {
		this.listStudent = listStudent;
	}
	public Program getProgram() {
		return program;
	}
	public void setProgram(Program program) {
		this.program = program;
	}
	public HBox gethBoxTop() {
		return hBoxTop;
	}
	public void sethBoxTop(HBox hBoxTop) {
		this.hBoxTop = hBoxTop;
	}
	public HBox gethBoxPhoto() {
		return hBoxPhoto;
	}
	public void sethBoxPhoto(HBox hBoxPhoto) {
		this.hBoxPhoto = hBoxPhoto;
	}
	public HBox gethBoxConnection() {
		return hBoxConnection;
	}
	public void sethBoxConnection(HBox hBoxConnection) {
		this.hBoxConnection = hBoxConnection;
	}
	public HBox gethBoxWelcome() {
		return hBoxWelcome;
	}
	public void sethBoxWelcome(HBox hBoxWelcome) {
		this.hBoxWelcome = hBoxWelcome;
	}
	public GridPane getGridCenter() {
		return gridCenter;
	}
	public void setGridCenter(GridPane gridCenter) {
		this.gridCenter = gridCenter;
	}
	public GridPane getGridReturn() {
		return gridReturn;
	}
	public void setGridReturn(GridPane gridReturn) {
		this.gridReturn = gridReturn;
	}


	// Creating the HBox Top
	private HBox hBoxTop = new HBox();
	private HBox hBoxPhoto = new HBox();
	private HBox hBoxConnection = new HBox();
	private HBox hBoxWelcome = new HBox();

	// Creating the Central GridPane
	private GridPane gridCenter = new GridPane();
	
	// Creating the Return GridPane
	private GridPane gridReturn = new GridPane();
	
	///////////////////////////////////////////////////////////////////
	
	public MainPage(Program program) {
		this.program = program;
		this.listStudent = program.getlistStudent();
		this.root = program.getRoot();
		MainPage main = this;
		
		// Adding the departments to the choicebox
				choiceBoxDepartment.setValue("-- Choose the Department --");
				choiceBoxDepartment.getItems().addAll("-- Choose the Department --", 
						"Economy","Computer Science", "Law", "History",
						"Biology", "Engineering");

		// Setting the size of the connection arguments
				logo.setFitHeight(150);
				logo.setFitWidth(200);
				banner.setFitHeight(150);
				banner.setFitWidth(1000);
				labelConnection.setPrefWidth(150);
				textfieldLogin.setPrefSize(200, 30);
				textfieldLogin.setPromptText("Login");
				passwordField.setPrefSize(200, 30);
				passwordField.setPromptText("Password");
				buttonLocateFile.setPrefSize(200, 30);
		
		//Design : Fonts
		labelWelcome.setFont(Font.font("century schoolbook", 30));
				labelConnectedAdmin.setText("Admin connecté");
				labelConnectedAdmin.setStyle("-fx-font-size: 13px");
				labelConnectedAdmin.setStyle("-fx-font-style: italic");
				labelConnection.setStyle("-fx-font-size: 13px");
				labelLastName.setStyle("-fx-font-size: 14px");
				labelFirstName.setStyle("-fx-font-size: 13px");
				labelDepartment.setStyle("-fx-font-size: 13px");
				labelPhone.setStyle("-fx-font-size: 13px");
				labelYear.setStyle("-fx-font-size: 13px");
				buttonSignIn.setStyle("-fx-font-size: 13px");
				buttonSignOut.setStyle("-fx-font-size: 13px");
				buttonResearch.setStyle("-fx-font-size: 13px");
				buttonLocateFile.setStyle("-fx-font-size: 13px");
				buttonCreateStudent.setStyle("-fx-font-size: 13px");
				buttonModifyStudent.setStyle("-fx-font-size: 13px");
				buttonDeleteStudent.setStyle("-fx-font-size: 13px");
				buttonPrint.setStyle("-fx-font-size: 13px");
				buttonHelp.setStyle("-fx-font-size: 13px");
		buttonResearch.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				BinaryTree tree = new BinaryTree();
				BinaryTreeLogic treeLogic = new BinaryTreeLogic();
				List<Student> listr = new ArrayList<>();
					if(!textFieldLastName.getText().equalsIgnoreCase("") 
							&& textFieldFirstName.getText().equalsIgnoreCase("")
							&& textFieldYear.getText().equalsIgnoreCase("")) {
						String nameEntered = textFieldLastName.getText();
						System.out.println(nameEntered);
						ArrayList<BinaryTree> resTree = treeLogic.globalSearch(nameEntered, null);
							for(BinaryTree tre : resTree) {
								listr.add(tre.getStudent());
							}
						ObservableList<Student> result = FXCollections.observableList(listr);
						System.out.println(result);
						main.viewliste.setItems(result);
						main.viewliste.refresh();

					}
					else {
					treeLogic.displayTree(null, listr);
					if(!textFieldFirstName.getText().equals("")) {
						listr = listr.stream().filter(tre -> tre.getFirstName().trim().equalsIgnoreCase(textFieldFirstName.getText()))
								.collect(Collectors.toList());
						}
					if(!textFieldYear.getText().equals("")) {
						listr = listr.stream().filter(tre -> tre.getGraduationYear().trim().equalsIgnoreCase(textFieldYear.getText()))
								.collect(Collectors.toList());
						}
					if(!choiceBoxDepartment.getValue().equals("-- Choose a Department --")) {
						listr = listr.stream().filter(tre -> tre.getDepartment().trim().equalsIgnoreCase(choiceBoxDepartment.getValue()))
								.collect(Collectors.toList());
						}
					if(!textFieldPhone.getText().equals("")) {
						listr = listr.stream().filter(tre -> tre.getPhone().trim().equalsIgnoreCase(textFieldPhone.getText()))
								.collect(Collectors.toList());
						}
					ObservableList<Student> result = FXCollections.observableList(listr);
					System.out.println(result);
					main.viewliste.setItems(result);
					main.viewliste.refresh();

					textFieldLastName.clear();
					textFieldFirstName.clear();
					textFieldYear.clear();
					textFieldPhone.clear();
					choiceBoxDepartment.setValue("-- Choose a Department --");
				}
			}	
		});			
		
		// Sign In Button Event
		buttonSignIn.setPrefWidth(200);
		buttonSignIn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(textfieldLogin.getText().equals("admin") && passwordField.getText().equals("admin")){
				// displays the login bar
				labelConnection.setVisible(false);
				textfieldLogin.setVisible(false);
				passwordField.setVisible(false);
				buttonSignIn.setVisible(false);
				buttonCreateStudent.setVisible(true);
				buttonDeleteStudent.setVisible(true);
				buttonModifyStudent.setVisible(true);
				hBoxConnection.getChildren().add(labelConnectedAdmin);
				hBoxConnection.getChildren().add(buttonSignOut);
				// displays the new buttons
				viewliste.setEditable(true);
			}
			else {
				labelConnection.setText(" Login Error ");
				labelConnection.setStyle("-fx-text-fill: #c22200;");
			}	
			}
		});
		
		// Sign Out Button Event
		buttonSignOut.setPrefWidth(200);
		buttonSignOut.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
			hBoxConnection.getChildren().remove(buttonSignOut);
			labelConnection.setVisible(true);
			textfieldLogin.setVisible(true);
			textfieldLogin.clear();
			passwordField.setVisible(true);
			passwordField.clear();
			buttonSignIn.setVisible(true);
			buttonCreateStudent.setVisible(false);
			buttonModifyStudent.setVisible(false);
			buttonDeleteStudent.setVisible(false);
			hBoxConnection.getChildren().remove(labelConnectedAdmin);
			viewliste.setEditable(false);

			}
		});
		
			buttonLocateFile.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					FileChooser fileChooser = new FileChooser();
					File file = fileChooser.showOpenDialog(stage);
					if(file != null) {
						String path = file.getAbsolutePath();
						System.out.println("new path : " + path);
						DataReadWrite.pathFile = path;
						System.out.println(DataReadWrite.pathFile);
						DataReadWrite data = new DataReadWrite();
						data.pathFile = path;
						ArrayList<Student> liste = data.importDataFile();
						System.out.println(liste);
						BinaryTreeLogic logic = new BinaryTreeLogic();
						logic.addListStudentToTree(null, liste);
						viewliste.setItems(FXCollections.observableArrayList(data.importDataFile()));
				}
			}
			});
		
			buttonCreateStudent.setPrefWidth(200);
			buttonCreateStudent.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					String lastName = textFieldLastName.getText();
					String firstName = textFieldFirstName.getText();
					String department = choiceBoxDepartment.getValue();
					String phone = textFieldPhone.getText();
					String GraduationYear = textFieldYear.getText();
				
					Student st = new Student();
					st.setLastName(lastName);
					st.setFirstName(firstName);
					st.setDepartment(department);
					st.setPhone(phone);
					st.setGraduationYear(GraduationYear);
					treelogic.addStudentToTree(st);
				
					// Clear the fields
					textFieldLastName.clear();
					textFieldLastName.clear();
					textFieldYear.clear();
					choiceBoxDepartment.setValue("-- Choose a Department --");
					textFieldPhone.clear();
					viewliste.refresh();
					JOptionPane.showMessageDialog(null, "** You have created a student **");
				}
				
			});
		
			buttonCreateStudent.setVisible(false);
			buttonDeleteStudent.setPrefWidth(200);
			// Delete Student Button Event
			buttonDeleteStudent.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// lets you delete a line selected on the viewlist
					Student selectedStudent = viewliste.getSelectionModel().getSelectedItem();
					viewliste.getItems().remove(selectedStudent);
					//	deleteStudent(student);
					JOptionPane.showMessageDialog(null, "** You have deleted a Student **");
				}
			});

			
			buttonDeleteStudent.setVisible(false);
			buttonModifyStudent.setPrefWidth(200);
			buttonModifyStudent.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					Edition ed = new Edition(program);
				
					Student selectedStudent = viewliste.getSelectionModel().getSelectedItem();
					root.setCenter(ed);
					selectedStudent = treelogic.globalSearch2(selectedStudent.getLastName(), null);
					ed.getTextfieldLastName().setText(selectedStudent.getLastName().trim());
					ed.getTextfieldLastName().setDisable(true);
					
					ed.getTextfieldFirstName().setText(selectedStudent.getFirstName().trim());
					ed.getTextfieldDepartment().setText(selectedStudent.getDepartment().trim());
					ed.getTextfieldPhone().setText(selectedStudent.getPhone().trim());
					ed.getTextfieldYear().setText(selectedStudent.getGraduationYear().trim());
					ed.setLogin(textfieldLogin.getText());
				
				}
				
			});
			buttonModifyStudent.setVisible(false);
			
			////////////////////////////////////
			// Defining the size of the search fields
			labelLastName.setPrefWidth(100);
			labelFirstName.setPrefWidth(100);
			labelDepartment.setPrefWidth(100);
			labelPhone.setPrefWidth(100);
			labelYear.setPrefWidth(100);
			textFieldLastName.setPrefSize(200, 30);
			textFieldFirstName.setPrefSize(200, 30);
			textFieldYear.setPrefSize(200, 30);
			choiceBoxDepartment.setPrefSize(200, 30);
			buttonResearch.setPrefSize(200, 30);
			buttonHelp.setPrefSize(40,30);
			// size of the print button
			buttonPrint.setPrefWidth(200);
			buttonPrint.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					main.getChildren().remove(hBoxTop);
					main.getChildren().remove(gridCenter);
					main.getChildren().remove(hBoxWelcome);
					main.getChildren().add(gridReturn);
					gridReturn.setAlignment(Pos.BOTTOM_RIGHT);
				
					Printer printer = Printer.getDefaultPrinter();
					PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, 20,20,20,20);
					double scaleX = pageLayout.getPrintableWidth() / viewliste.getWidth();
					double scaleY = pageLayout.getPrintableHeight() / viewliste.getHeight();
					Scale scale=new Scale(scaleX, scaleY);
					viewliste.getTransforms().add(scale);
					PrinterJob printerJob = PrinterJob.createPrinterJob();

					if(printerJob.showPrintDialog(main.getScene().getWindow()) && printerJob.printPage(viewliste)) {
						viewliste.getTransforms().remove(scale);
						printerJob.endJob();
					} 
					viewliste.getTransforms().remove(scale); 
				}
			});
			
			// size of the view list
			viewliste.setPrefWidth(1200);
			viewliste.setPrefHeight(1200);
			
			// declaring the columns
			columnLastName.setCellValueFactory(new PropertyValueFactory("Last Name"));
			columnFirstName.setCellValueFactory(new PropertyValueFactory("First Name"));
			columnDepartment.setCellValueFactory(new PropertyValueFactory("Department"));
			columnPhone.setCellValueFactory(new PropertyValueFactory("Phone"));
			columnYear.setCellValueFactory(new PropertyValueFactory("Graduation Year"));
			columnLastName.setCellFactory(TextFieldTableCell.forTableColumn());
			columnFirstName.setCellFactory(TextFieldTableCell.forTableColumn());
			columnDepartment.setCellFactory(TextFieldTableCell.forTableColumn());
			columnPhone.setCellFactory(TextFieldTableCell.forTableColumn());
			columnYear.setCellFactory(TextFieldTableCell.forTableColumn());
			viewliste.setEditable(false);
			columnLastName.setEditable(true);
			columnFirstName.setEditable(true);
			columnDepartment.setEditable(true);
			columnPhone.setEditable(true);
			columnYear.setEditable(true);
			
			// size of the return grid
			gridReturn.setPrefSize(1200, 200);
			gridReturn.setAlignment(Pos.BOTTOM_RIGHT);
			//		buttonRetour.setVisible(false);
			buttonReturn.setPrefWidth(200);
			buttonReturn.setOnAction(new EventHandler() {
				@Override
				public void handle(Event event) {
					main.getChildren().remove(viewliste);
					main.getChildren().add(hBoxTop);
					main.getChildren().add(gridCenter);
					main.getChildren().remove(gridReturn);
					main.getChildren().add(viewliste);
					gridReturn.setAlignment(Pos.BOTTOM_RIGHT);
				}
			});
			
			/// GridPane Photo
			hBoxPhoto.getChildren().add(logo);
			
			// Connection
			hBoxConnection.getChildren().addAll(labelConnection, textfieldLogin, passwordField, buttonSignIn);
			hBoxConnection.setSpacing(20);
			hBoxConnection.setAlignment(Pos.BOTTOM_RIGHT);
			hBoxConnection.setStyle("-fx-background-color: #fdbd84 ;");
			// Welcome message
			hBoxWelcome.getChildren().add(labelWelcome);
			hBoxWelcome.setAlignment(Pos.CENTER);
			hBoxWelcome.setMinHeight(100);
			hBoxWelcome.setStyle("-fx-background-color: #fd9a42 ;");
			
			hboxHelp.setStyle("-fx-background-color: #fd9a42 ;");
			hboxHelp.add(buttonHelp,1,50);
			hboxHelp.setAlignment(Pos.BASELINE_RIGHT);
			buttonHelp.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					Desktop d = Desktop.getDesktop();
					try{
						d.open(new File("C:/Users/Stagiaire/Desktop/Projet1_Notice_ utilisateur_finale.pdf"));
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}

			});
				
			//  TOP
			hBoxTop.getChildren().addAll(hBoxPhoto, hBoxConnection);
			hBoxTop.setHgrow(hBoxConnection, Priority.ALWAYS);


			// GRID PANE CENTER
			gridCenter.setStyle("-fx-background-color: #fd9a42 ;");
			gridCenter.setAlignment(Pos.BASELINE_CENTER);	
			gridCenter.setHgap(20);
			gridCenter.setVgap(20);
			
			// grid research
			gridCenter.setAlignment(Pos.BASELINE_LEFT);
			gridCenter.add(labelLastName,6,4);
			gridCenter.add(textFieldLastName,7,4);
			gridCenter.add(labelFirstName,6,5);
			gridCenter.add(textFieldFirstName,7,5);
			gridCenter.add(labelDepartment,9,4);
			gridCenter.add(choiceBoxDepartment,10,4);
			gridCenter.add(labelPhone,9,5);
			gridCenter.add(labelYear,12,4);
			gridCenter.add(textFieldYear,13,4);
			gridCenter.add(buttonResearch,13,5);
			gridCenter.add(buttonLocateFile, 14, 5);
			
			gridCenter.add(buttonCreateStudent, 15, 2);
			gridCenter.add(buttonModifyStudent, 15, 3);
			gridCenter.add(buttonDeleteStudent, 15, 4);
			gridCenter.add(buttonPrint, 15, 5);
			gridCenter.add(labelEmpty, 15, 6);
			// End of the Grid Center
			
			// column of the viewlist
			viewliste.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			viewliste.setStyle("-fx-background-color: #e7e9f9;");
			
			//ajout colonnes
			viewliste.getColumns().addAll(columnLastName,columnFirstName,
					columnDepartment,columnPhone,columnYear);

			//  GRID PANE RETOUR
			gridReturn.add(buttonReturn, 0, 0);

			// Adding to the Borderpane
			this.getChildren().add(hBoxTop);
			this.getChildren().add(hBoxWelcome);
			this.getChildren().add(hboxHelp);
			this.getChildren().add(gridCenter);
			this.getChildren().add(viewliste);

			}

	}
