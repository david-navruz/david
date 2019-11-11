package school.student.service;

import school.student.entity.Student;

public class BinaryTree {

	private int index;
	private BinaryTree nodeLeft;
	private BinaryTree nodeRight;
	private Student student;
	private BinaryTree duplicate;
	private BinaryTree parent;
	private int indexNodeLeft;
	private int indexNodeRight;
	private int indexNodeParent;
	private int indexNodeDuplicate;
	
	
	// constructor which creates an empty tree
	// -1 means the node doesn't have any child 
	public BinaryTree() {
		this.student = null;
		this.nodeLeft = null;
		this.nodeRight = null;
		this.parent = null;
		this.duplicate = null;
		this.indexNodeLeft = -1;
		this.indexNodeRight = -1;
		this.indexNodeParent = -1;
		this.indexNodeDuplicate = -1;
		this.index = -1;
	}
	

	public BinaryTree(BinaryTree nodeLeft, Student student, BinaryTree nodeRight) {
		this.student = student;
		this.nodeLeft = nodeLeft;
		this.nodeRight = nodeRight;
		this.parent = null;
		this.duplicate = null;
		this.indexNodeLeft = -1;
		this.indexNodeRight = -1;
		this.indexNodeParent = -1;
		this.indexNodeDuplicate = -1;
		this.index = -1;
	}

	public BinaryTree(BinaryTree nodeLeft, Student student, BinaryTree nodeRight, 
			BinaryTree parent, BinaryTree duplicate) {
		this.student = student;
		this.nodeLeft = nodeLeft;
		this.nodeRight = nodeRight;
		this.parent = parent;
		this.duplicate = duplicate;
		this.indexNodeLeft = -1;
		this.indexNodeRight = -1;
		this.indexNodeParent = -1;
		this.indexNodeDuplicate = -1;
		this.index = -1;
	}

	
	public boolean isLeafNode() {
		/*
		 * return true is the node is a leaf (doesn't have any child)
		 * @param null
		 * @return boolean
		 */
		if(this.getIndexNodeLeft() == -1 && this.getIndexNodeRight() == -1) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	@Override
	public String toString() {
		return "BinaryTree [index=" + index + ", student=" + student
				+ ", indexNodeLeft=" + indexNodeLeft + ", indexNodeRight=" + indexNodeRight 
				+ ", indexNodeParent=" + indexNodeParent
				+ ", indexNodeDuplicate=" + indexNodeDuplicate + "]";
	}
	

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public BinaryTree getNodeLeft() {
		return nodeLeft;
	}

	public void setNodeLeft(BinaryTree nodeLeft) {
		this.nodeLeft = nodeLeft;
	}

	public BinaryTree getNodeRight() {
		return nodeRight;
	}

	public void setNodeRight(BinaryTree nodeRight) {
		this.nodeRight = nodeRight;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public BinaryTree getDuplicate() {
		return duplicate;
	}

	public void setDuplicate(BinaryTree duplicate) {
		this.duplicate = duplicate;
	}

	public BinaryTree getParent() {
		return parent;
	}

	public void setParent(BinaryTree parent) {
		this.parent = parent;
	}

	public int getIndexNodeLeft() {
		return indexNodeLeft;
	}

	public void setIndexNodeLeft(int indexNodeLeft) {
		this.indexNodeLeft = indexNodeLeft;
	}

	public int getIndexNodeRight() {
		return indexNodeRight;
	}

	public void setIndexNodeRight(int indexNodeRight) {
		this.indexNodeRight = indexNodeRight;
	}

	public int getIndexNodeParent() {
		return indexNodeParent;
	}

	public void setIndexNodeParent(int indexNodeParent) {
		this.indexNodeParent = indexNodeParent;
	}

	public int getIndexNodeDuplicate() {
		return indexNodeDuplicate;
	}

	public void setIndexNodeDuplicate(int indexNodeDuplicate) {
		this.indexNodeDuplicate = indexNodeDuplicate;
	}

}
