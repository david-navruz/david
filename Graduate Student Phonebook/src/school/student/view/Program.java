package school.student.view;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import school.student.entity.Student;
import school.student.service.BinaryTree;
import school.student.service.BinaryTreeLogic;
import school.student.service.DataReadWrite;

public class Program extends Application{

	
	// Creating the Central BorderPane
	public ObservableList<Student> listStudent = FXCollections.observableArrayList();
	private BorderPane root = new BorderPane();
	private Stage stage;
	
	// Getters Setters
	public ObservableList<Student> getlistStudent() {
		return listStudent;
	}

	public void setlistStudent(ObservableList<Student> listStudent) {
		this.listStudent = listStudent;
	}

	public BorderPane getRoot() {
		return root;
	}

	public void setRoot(BorderPane root) {
		this.root = root;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage stage) throws Exception {
		// Creating the research page
		MainPage mainPage = new MainPage(this);
		mainPage.setPrefSize(1600, 800);
		
		Edition ed = new Edition(this);
		BinaryTreeLogic logic = new BinaryTreeLogic();
		BinaryTree tree = new BinaryTree();
		ArrayList<Student> list = new ArrayList<>();
		DataReadWrite disk = new DataReadWrite();
		list = disk.importDataFile();
		logic.addListStudentToTree(tree, list);
		ObservableList<Student> listeAAfficher = FXCollections.observableList(list);
		mainPage.getViewliste().setItems(listeAAfficher);
		
		root.setCenter(mainPage);
		
		// creating the scene
		Scene scene = new Scene(root);
		root.setStyle("-fx-background-color: #ccd1d1;");
		stage.setScene(scene);
		stage.setTitle("Welcome Page");
		stage.setMaximized(false);
		stage.setResizable(true);
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
		
		
	}


	
	
}
