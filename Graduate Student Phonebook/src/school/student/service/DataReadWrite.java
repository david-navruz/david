package school.student.service;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.RandomAccess;

import school.student.entity.Student;

public class DataReadWrite {

	
	public static File dataFile = 
			new File("C:/Users/David.NAVRUZ/Documents/workspace/Graduate Student Phonebook/students.DON");
	public static String pathFile = dataFile.getAbsolutePath().replace("\\", "/");
	File fileSave;
	File fileRead;
	
		// Byte size: int = 4 bytes, char=2 bytes, double=8 bytes, boolean=1 bytes, 
		public static int INDEX = 4; // 1 int
		public static int LASTNAME = 50; // 25 chars
		public static int FIRSTNAME = 50;
		public static int DPT = 10;
		public static int PHONE = 20;
		public static int YEAR = 8;
		public static int NODELEFT = 4;
		public static int NODERIGHT = 4;
		public static int PARENT = 4;
		public static int DUPLICATE = 4;
		
		public static int I_NODE = 0;
		public static int I_LASTNAME = INDEX ;
		public static int I_FIRSTNAME = I_LASTNAME + LASTNAME;
		public static int I_DPT = I_FIRSTNAME + FIRSTNAME;
		public static int I_PHONE = I_DPT + DPT;
		public static int I_YEAR = I_PHONE + PHONE;
		public static int I_NODELEFT = I_YEAR + YEAR;
		public static int I_NODERIGHT = I_NODELEFT + NODELEFT;
		public static int I_PARENT = I_NODERIGHT + NODERIGHT;
		public static int I_DUPLICATE = I_PARENT + PARENT;
		
		// Byte size of a node
		public static int NODE_SIZE = ( INDEX + LASTNAME + FIRSTNAME + DPT + PHONE + YEAR + NODELEFT + 
							NODERIGHT + PARENT + DUPLICATE);

		public DataReadWrite() {
			
		}	
	
		public void searchFile() {
			
		}
	
		// method to import data from the file
		public static ArrayList<Student> importDataFile(){
			System.out.println(pathFile);
			int nbOfStudentsImported = 0;
			ArrayList<Student> importList = new ArrayList<Student>();
		
			try {
				FileReader fread = new FileReader(pathFile);
				BufferedReader bread = new BufferedReader(fread);
				String lastname = "";
				String firstname = "";
				String dept = "";
				String phone = "";
				String year = "";
				int count = 0;
				String line = "";
				
				while((line = bread.readLine()) != null) {
					if(count == 0) {
						lastname = line;
					}
					if (count == 1) {
						firstname = line;
					}
					if (count == 2) {
						dept = line;
					}
					if (count == 3) {
						year = line;
					}
					if (count == 4) {
						Student s = new Student(lastname,firstname,dept,year,phone);
						importList.add(s);
						nbOfStudentsImported++;
						count=0;
					}
					else {
						count++;
					}
				}
				bread.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println("Verify the source file");
			}
			catch(EOFException e) {
				System.out.println("Number of students imported : " + nbOfStudentsImported);
			}
			catch(IOException e) {
				System.out.println("Problem related to reading or writing of the file");
			}
			return importList;
		}
		
		
		public void writeNode(BinaryTree tree) {
			System.out.println("\n*******\n in writeNode");
			
			try (RandomAccessFile raf = new RandomAccessFile
					("C:/Users/David.NAVRUZ/Documents/workspace/Graduate Student Phonebook/binarytree.txt", "rw")){
			raf.seek(raf.length());
			int nbTree = (int) raf.length() / NODE_SIZE;
			int index = nbTree;
			
			System.out.println("File size in byte : " + raf.length());
			System.out.println("Node size in byte : " + NODE_SIZE);
			System.out.println(index);
			
			String lastname = completeSpace(tree.getStudent().getLastName(), LASTNAME);
			String firstname = completeSpace(tree.getStudent().getFirstName(), FIRSTNAME);
			String dpt = completeSpace(tree.getStudent().getDepartment(), DPT);
			String phone = completeSpace(tree.getStudent().getPhone(), PHONE);
			String year = completeSpace(tree.getStudent().getGraduationYear(), YEAR);
			
			raf.writeInt(index);
			tree.setIndex(index);
			
			raf.writeChars(lastname);
			raf.writeChars(firstname);
			raf.writeChars(dpt);
			raf.writeChars(phone);
			raf.writeChars(year);
			
			raf.writeInt(tree.getIndexNodeLeft());
			raf.writeInt(tree.getIndexNodeRight());
			raf.writeInt(tree.getIndexNodeParent());
			raf.writeInt(tree.getIndexNodeDuplicate());
			}
			catch (Exception e) {
				System.out.println(e);
				e.printStackTrace();
			}
			
		}
		
		public BinaryTree readNode(int noIndexTree) {
			
			BinaryTree node = new BinaryTree();
			Student st = new Student();
			try (RandomAccessFile raf = new RandomAccessFile
						("C:/Users/David.NAVRUZ/Documents/workspace/Graduate Student Phonebook/binarytree.txt", "rw")){
			raf.seek(noIndexTree * NODE_SIZE + DataReadWrite.INDEX);
			String lastname = "";
			String firstname = "";
			String dpt = "";
			String phone = "";
			String year = "";
			
			for (int i=0; i<(LASTNAME/2); i++){
				LASTNAME += raf.readChar();
			}
			
			for (int i=0;i<FIRSTNAME/2;i++){
				FIRSTNAME += raf.readChar();
			}
			
			for (int i=0;i<DPT/2;i++){
				dpt += raf.readChar();
			}
			
			for (int i=0;i<PHONE/2;i++){
				PHONE += raf.readChar();
			}
			
			for (int i=0;i<YEAR/2;i++){
				YEAR += raf.readChar();
			}
			
			int indexNodeLeft = raf.readInt();
			int indexNodeRight = raf.readInt();
			int indexParent = raf.readInt();
			int indexDuplicate = raf.readInt();
			
			st.setGraduationYear(year);
			st.setDepartment(dpt);
			st.setLastName(lastname);
			st.setFirstName(firstname);
			st.setPhone(phone);
			st.setIndex(noIndexTree);
			
			node.setIndex(noIndexTree);
			node.setStudent(st);
			node.setIndexNodeLeft(indexNodeRight);
			node.setIndexNodeRight(indexNodeRight);
			node.setIndexNodeParent(indexParent);
			node.setIndexNodeDuplicate(indexDuplicate);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return node;
		}
		
		
		public String completeSpace(String s, int size){
			for(int i=s.length();i<size/2;i++){
				s += " ";
			}
			return s;
		}

		@Override
		public String toString() {
			return "DataReadWrite [fileSave=" + fileSave + ", fileRead=" + fileRead + "]";
		}

		public File getFileSave() {
			return fileSave;
		}

		public void setFileSave(File fileSave) {
			this.fileSave = fileSave;
		}

		public File getFileRead() {
			return fileRead;
		}

		public void setFileRead(File fileRead) {
			this.fileRead = fileRead;
		}
		
	
}
