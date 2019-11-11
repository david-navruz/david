package school.student.service;

import java.util.ArrayList;

public class BinaryTreeLogic {

	
	
	public BinaryTreeLogic() {
		
	}

	private BinaryTree root = new BinaryTree(null,null,null);
	DataReadWrite disk = new DataReadWrite();
	
	
	
	public void updateStudent(String lastname, String firstname, 
			String department, String phone, String year) {
		
	}
	
	
	public ArrayList<BinaryTree> globalSearch(String lastname, BinaryTree tree){
		
		if(tree == null) 
			tree = disk.readNode(0);
		ArrayList<BinaryTree> result = searchInTree(lastname, tree);
		return result;
	}
	
	
	
	
	
	
}
