package school.student.service;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import school.student.entity.Student;

public class BinaryTreeLogic {

	
	public BinaryTreeLogic() {
		
	}
	
	// to create a new empty tree
	private BinaryTree root = new BinaryTree(null,null,null);
	
	// to store on the disk
	DataReadWrite disk = new DataReadWrite();
	
	// update a student's information
	public void updateStudent(String lastname, String firstname, 
			String department, String phone, String year) {
		// TO DO	
	}
	
	// global search method 1
	public ArrayList<BinaryTree> globalSearch(String lastname, BinaryTree tree){
		if(tree == null) 
			tree = disk.readNode(0);
		ArrayList<BinaryTree> result = searchInTree(lastname, tree);
		return result;
	}
	
	// global search method 2
	public Student globalSearch2(String lastname, BinaryTree tree) {
		if(tree == null) 
			tree = disk.readNode(0);
		BinaryTree res = searchInTree2(lastname, tree);
		tree.getStudent().setIndex(res.getIndex());
		return res.getStudent();

	}
	

	
	public BinaryTree getRoot() {
		return root;
	}

	public void setRoot(BinaryTree root) {
		this.root = root;
	}

	
	/* compareToIgnoreCase() compares lexicographically (dictionary ordering).
	 * return 0, means they are equal - "apple".compareTo("cherry") );  // return -2 :apple comes before cherry
	 * "cherry".compareTo("banana")); //1  - cherry comes after banana
	 * trim() method eliminates the empty space at the beginning and at the end of a String
	 */
	// method to search a student by name
	public ArrayList<BinaryTree> searchInTree(String studentName, BinaryTree tree){
		ArrayList<BinaryTree> result = new ArrayList<>(); 
		BinaryTree currentNode = tree;
		
		// condition: tree is not null AND studentName entered matches the name of the current object
		// AND (the studentName searched comes before the current object name AND IndexNodeLeft not -1) 
		// OR (the studentName searched comes after the current object name AND IndexNodeRight not -1) 
		while (tree != null && tree.getStudent().getLastName().trim().equalsIgnoreCase(studentName) 
				&& (
					(studentName.compareToIgnoreCase(tree.getStudent().getLastName().trim()) < 0) && (tree.getIndexNodeLeft() != -1)
				|| (studentName.compareToIgnoreCase(tree.getStudent().getLastName().trim()) > 0) && (tree.getIndexNodeRight() != -1))

				){
			// the studentName searched comes before the current object name : closer to A
				if(studentName.compareToIgnoreCase(tree.getStudent().getLastName().trim()) < 0) 
					if(tree.getIndexNodeLeft() != -1)
						tree = disk.readNode(tree.getIndexNodeLeft());
						currentNode = tree;
			// the studentName searched comes after the current object name : closer to Z
				if(studentName.compareToIgnoreCase(tree.getStudent().getLastName().trim()) > 0)
					if(tree.getIndexNodeRight()!= -1)
						tree = disk.readNode(tree.getIndexNodeRight());
						currentNode = tree;
		}
			if(currentNode.getStudent().getLastName().compareToIgnoreCase(studentName) == 0) {
				result.add(currentNode);
				while(currentNode.getIndexNodeDuplicate() != -1) {
					currentNode = disk.readNode(currentNode.getIndexNodeDuplicate());
					result.add(currentNode);
				}
			}
		return result;
	}

	
	
	// method to add a student
	public void addStudentToTree(Student student) {
		
		try(RandomAccessFile raf = 
				new RandomAccessFile("C:/Users/David.NAVRUZ/Documents/workspace/Graduate Student Phonebook/binarytree.txt", "rw")) {
			
			int nbNode = (int) (raf.length() / DataReadWrite.NODE_SIZE);
			System.out.println("\tSize of the file in bytes : " + raf.length());
			System.out.println("\tSize of a node : " + DataReadWrite.NODE_SIZE);
			System.out.println("\tNumber of nodes : " + nbNode);
			int index = nbNode;
			BinaryTree addTree = new BinaryTree(null, student, null, null, null);
			addTree.setIndex(0);
			
			// if the tree is empty : CREATING THE ROOT:
			if(raf.length() == 0) {
				disk.writeNode(addTree);
				System.out.println("Add the element as root : " + student.getLastName());
			}
			// if the tree is full, we go through:
			else {
				// we compare the root with the object student stud
				BinaryTree currentNode = disk.readNode(0); // we get the root
				int compare = student.getLastName().compareTo(currentNode.getStudent().getLastName());
				
				// this is the level right before the leaf, 
				// we check if we should add the element to left or right
				// Student name given is not equal to the current student object's name
				// AND it comes before the current student object's name AND Left Node is empty
				// OR the name comes after the current student object's name AND Right Node is empty
				while((compare !=0) && ((compare < 0) && (currentNode.getIndexNodeLeft() != -1)
					|| (compare > 0) && (currentNode.getIndexNodeRight() != -1))){
					
					// student name entered comes before the current student object's name
					// NODE LEFT
					if(compare < 0) {
						System.out.println(" In the Node " + currentNode.getStudent().getLastName());
						System.out.println(student.getLastName() + " comes before / closer to A ");
						System.out.println("We check its left node index " + currentNode.getIndexNodeLeft());
						currentNode = disk.readNode(currentNode.getIndexNodeLeft());
					}
					// NODE RIGHT
					if(compare > 0) {
						System.out.println(" In the Node " + currentNode.getStudent().getLastName());
						System.out.println(student.getLastName() + " comes after / closer to Z ");
						currentNode = disk.readNode(currentNode.getIndexNodeRight());
					}
					compare = student.getLastName().compareTo(currentNode.getStudent().getLastName());
				}
					// the same name : duplicate   
					if(compare == 0) {
						addDuplicate(student, currentNode, addTree, index);
					}
					else {
						System.out.println("\tcompareLeaf is equal " + compare);
					}
					if(compare < 0) {
						// NODE LEFT
						raf.seek(currentNode.getIndex() * DataReadWrite.NODE_SIZE + DataReadWrite.I_NODELEFT );
						System.out.println("\tWriting the index of Node Left");
						raf.writeInt(index);
						// Writing the Node at the end of the file
						raf.seek(raf.length());
						addTree.setIndexNodeParent(currentNode.getIndex());
						System.out.println("\tWriting the tree at the end of the file - reading head: " + raf.getFilePointer());
						disk.writeNode(addTree);
					}
					if(compare < 0) {
						// NODE RIGHT
						raf.seek(currentNode.getIndex() * DataReadWrite.NODE_SIZE + DataReadWrite.I_NODERIGHT);
						System.out.println("\tWriting the index of Node Right");
						raf.writeInt(index);
						// Writing the Node at the end of the file
						raf.seek(raf.length());
						addTree.setIndexNodeParent(currentNode.getIndex());
						System.out.println("\tWriting the tree at the end of the file - reading head: " + raf.getFilePointer());
						disk.writeNode(addTree);
					}
					if(compare == 0) {
						addDuplicate(student, currentNode, addTree, index);
					}
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}	
	}
	
	
	// method to add a duplicate name into the tree
	public void addDuplicate(Student s, BinaryTree currentNode, BinaryTree addTree, int index) {
		
		try(RandomAccessFile raf = 
				new RandomAccessFile("C:/Users/David.NAVRUZ/Documents/workspace/Graduate Student Phonebook/binarytree.txt", "rw"))
		{
			while (currentNode.getIndexNodeDuplicate() != -1) {
				System.out.println("\n\tNew Node Duplicate : " + currentNode.getStudent().getLastName());
				currentNode = disk.readNode(currentNode.getIndexNodeDuplicate());
			}
			// Duplicate
			System.out.println("\n\tIn the last Node Duplicate : " + currentNode.getStudent().getLastName());
			// current node's index x (node size + index of duplicate)
			// Writing the REF into the Parent Node
			raf.seek(currentNode.getIndex() * DataReadWrite.NODE_SIZE + DataReadWrite.I_DUPLICATE);
			System.out.println("\tWriting the index of duplicate element");
			raf.writeInt(index);
			currentNode.setIndexNodeDuplicate(index);
			currentNode.setDuplicate(addTree);
			
			// Writing the Node at the end of the file:
			raf.seek(raf.length());
			addTree.setIndexNodeParent(currentNode.getIndex());
			addTree.setParent(currentNode);
			addTree.setIndex(index);
			System.out.println("\tWriting the tree at the end of the file - reading head points to : " 
									+ raf.getFilePointer());
			disk.writeNode(addTree);
			
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	
	// method to add a list of students to the tree
	public void addListStudentToTree(BinaryTree tree, List<Student> liste) {
		for(Student s : liste) {
			addStudentToTree(s);
		}
		
	}
	
	// method 1 to search a student in the tree
	public ArrayList<BinaryTree> searchInTree1(String studentName, BinaryTree tree){
		ArrayList<BinaryTree> result = new ArrayList<>(); 
		BinaryTree currentNode = tree;
		
		while (tree != null && !tree.getStudent().getLastName().trim().equalsIgnoreCase(studentName) 
				&& (
					((studentName.compareToIgnoreCase(tree.getStudent().getLastName().trim()) < 0)
					&& (tree.getIndexNodeLeft() != -1)
					|| (studentName.compareToIgnoreCase(tree.getStudent().getLastName().trim()) > 0) && (tree.getIndexNodeRight()!= -1)
						))){
			// name close to the A
			if(studentName.compareToIgnoreCase(tree.getStudent().getLastName().trim()) < 0) {
				if(tree.getIndexNodeLeft() != -1) {
					tree = disk.readNode(tree.getIndexNodeLeft());
					currentNode = tree;
				}
			}
			// name close to the Z
			if(studentName.compareToIgnoreCase(tree.getStudent().getLastName().trim()) > 0) {
				if(tree.getIndexNodeRight() != -1) {
					tree = disk.readNode(tree.getIndexNodeRight());
					currentNode = tree;
				}
			}
		}
		// if it's a duplicate name
		if(currentNode.getStudent().getLastName().compareToIgnoreCase(studentName) == 0) {
			result.add(currentNode);
			while(currentNode.getIndexNodeDuplicate() != -1) {
				currentNode = disk.readNode(tree.getIndexNodeDuplicate());
				currentNode = tree;
			}
		}
		return result;
	}
	
	
		// method 12to search a student in the tree
		public BinaryTree searchInTree2(String studentName, BinaryTree tree){
			
			while(tree != null && !tree.getStudent().getLastName().trim().equalsIgnoreCase(studentName)
					&& (
							((studentName.compareToIgnoreCase(tree.getStudent().getLastName().trim()) < 0)
							&& (tree.getIndexNodeLeft() != -1)
							|| (studentName.compareToIgnoreCase(tree.getStudent().getLastName().trim()) > 0) && (tree.getIndexNodeRight()!= -1)
								))){
				// closer to A
				if(studentName.compareToIgnoreCase(tree.getStudent().getLastName().trim()) < 0) {
					if(tree.getIndexNodeLeft() != -1) {
						tree = disk.readNode(tree.getIndexNodeLeft());
					}
				}
				// closer to Z
				if(studentName.compareToIgnoreCase(tree.getStudent().getLastName().trim()) > 0) {
					if(tree.getIndexNodeRight() != -1) {
						tree = disk.readNode(tree.getIndexNodeRight());
					}
				}	
			}
			return tree;
		}
	
	

		// method to display the tree
		public void displayTree(BinaryTree tree, List<Student> liste) {
			if(tree == null) tree = disk.readNode(0);
			if(tree.getIndexNodeLeft() != -1)
					displayTree(disk.readNode(tree.getIndexNodeLeft()), liste);
			System.out.println("We add the student " + tree.getStudent().getLastName());
			// We add the Student object to the list
			liste.add(tree.getStudent());
				if(tree.getIndexNodeDuplicate() != -1) {
					BinaryTree bintree = disk.readNode(tree.getIndexNodeDuplicate());
					System.out.println("We add a duplicate student " + bintree.getStudent().getLastName());
					liste.add(bintree.getStudent());
						while(bintree.getIndexNodeDuplicate() != -1) {
							System.out.println("We add a duplicate student " + bintree.getStudent().getLastName());
							liste.add(bintree.getStudent());
							bintree = disk.readNode(bintree.getIndexNodeDuplicate());
						}
				}
				if(tree.getIndexNodeRight() != -1) {
					displayTree(disk.readNode(tree.getIndexNodeRight()), liste);
				}	
	}
	
	
		
	// method to delete a student from the tree
	public BinaryTree delete(BinaryTree tree, Student s) {
		// If the tree is null, we return null
		if(tree == null) {
			return tree;
		}
		// If the name entered matches the current student's name, we delete the node where student is located
		if(s.getLastName().equals(tree.getStudent().getLastName())) {
			return deleteRoot(tree);
		}
		// After deleting the node, we rearrange the tree
		int compare = s.getLastName().compareTo(tree.getStudent().getLastName());
			if(compare < 0) {
				tree.setIndexNodeLeft(tree.getIndexNodeLeft());
			}
			else {
				tree.setIndexNodeRight(tree.getIndexNodeRight());
			}
		return tree;
	}
	
	
	// method to delete the root
	public BinaryTree deleteRoot(BinaryTree tree) {
		if(tree.getNodeLeft() == null) {
			return tree.getNodeRight();
		}
		if(tree.getNodeRight() == null) {
			return tree.getNodeLeft();
		}
		BinaryTree replacement = lastDecendant(tree.getNodeLeft());
		tree.setStudent(replacement.getStudent());
		tree.setNodeLeft(delete(tree.getNodeLeft(), replacement.getStudent()));
		return tree;
	}
	
	// returns the last node : the rightmost element
	public BinaryTree lastDecendant(BinaryTree tree) {
		if(tree.getNodeRight() == null) {
			return tree;
		}
		return lastDecendant(tree.getNodeRight());
	}
	
	
	
}
