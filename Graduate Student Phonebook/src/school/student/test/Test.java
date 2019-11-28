package school.student.test;

import java.util.ArrayList;

import school.student.entity.Student;
import school.student.service.BinaryTree;
import school.student.service.BinaryTreeLogic;

public class Test {

	public static void main(String[] args) {
		
		Student s1 = new Student("Racine", "AA", "75", "AI01", "2000");
		Student s2 = new Student("Abel", "AA", "75", "AI01", "2000");
		Student s3 = new Student("Dodo", "AA", "75", "AI01", "2000");
		Student s4 = new Student("Barnabe", "AA", "75", "AI01", "2000");
		Student s5 = new Student("Yannick", "AA", "75", "AI01", "2000");
		Student s6 = new Student("Coco", "AA", "75", "AI01", "2000");

		ArrayList<Student> listeS = new ArrayList<>();
		listeS.add(s2);
		listeS.add(s3);
		listeS.add(s4);
		listeS.add(s5);
		listeS.add(s6);
		
		BinaryTree racine = new BinaryTree(null, s1, null);
		BinaryTreeLogic gestionArbre = new BinaryTreeLogic();
		

		for (Student s : listeS){
				gestionArbre.addListStudentToTree(racine, listeS);
		}
		
		

	}
	
		
	
	
}
