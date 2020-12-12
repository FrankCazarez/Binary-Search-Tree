//Name of The Student: 				Frank Cazarez
//NedID:							fac160030
//Class:							CE 3345.03 Data Structures and Introduction to Algorithmic Analysis
//Section:							003
//Semester:							Spring 2019
//Project Number/Description:		Project # 3; This project consist of the creation and implementation of a Binary Search Tree with lazy deletion. This project involves 2 java classes/files called, Main.java and LazyBinarySearchTree.java. The LazyBinarySearchTree class consist of 1 subclass called TreeNode which is used to traverse the Binary Search Tree, and being able to set/get the key, status of deletion, height, left/right children, and print the values using toString. The way that the TreeNode class is manipulated is by the Checking methods ( Illegal Argument blockers) and the recursive methods tied to their respective checker method. The recursive methods are: Insert, Delete, FindMin, FindMax, Contains, toString (For PrintTree command), Height, and Size in that respective order. The Main class consist of the interaction the class has with the desired input/output files that the argument line is set to (In this case input file is "input.txt" and output file is "output.txt"). After the class checks the validity of the arguments command line (Checking if both the input/output files are there in command line (If not error is displayed). A try command is executed, this prompts a message telling the user what the output file will be called ("output.txt" in this case). After it creates an instance of the LazyBinarySearchTree class. Then the input file is ran through a while loop to be able to read the keywords with their respective integer values (If not display error). After keywords/values are read they are matched with their own respective if statement: Insert, Delete, FindMin, FindMax, Contains, PrintTree, Height, Size. Each of those methods are also ran through their own try/catch cases to be able to find errors in the input file. Lastly access to the input/output file is closed.

//IDE Used: Eclipse
//-------------------------------------------------------------------------------		
import java.util.Scanner;
import java.io.*;

public class Main2 {
	public static void main(String[] args) {
		//Initiate to null variables that will read and write files
 		Scanner scannedFile = null;
		PrintWriter writtenFile = null;
		//Check if argument in command line is set
		if (args.length < 1) {
			//Message to user to set Arguments Line
			System.out.println("Set command Line; input.txt output.txt");
			System.exit(0);
		}
		
		try {
			//Print out the name of the output file
			System.out.println("Output File Created: Name of File - " + args[1]);
			
			//Initialize LazyBinarySearchTree
			LazyBinarySearchTree Binary = new LazyBinarySearchTree();
			//Make input and output files
			scannedFile = new Scanner(new File(args[0]));
			writtenFile = new PrintWriter(new File(args[1]));
			//Variables to go through and search for keywords
			int number = 0;
			String line;
			String keyword;
			String[] array;
//-------------------------------------------------------------------------------			
			//Scans input file
			while (scannedFile.hasNextLine()) {
				//Scans line and saves it
				line = scannedFile.nextLine();
				//Split before ":"
				array = line.split(":");
				
				//Check that length is long enough; word and integer
				if (array.length > 1) {
					//saves integer to be used
					number = Integer.parseInt(array[1]);
					//saves keyword; e.g. Insert
					keyword = array[0];
				}
				//Only keyword is present
				else {
					keyword = line;
				}
			
//-------------------------------------------------------------------------------		
				//Keyword Insert is found in input file
				if (keyword.equals("Insert")) {
					//Calls Insert method
					try {
						writtenFile.println(Binary.Insert(number));
						//Reset number integer; In case 2 or more insert lines preset and one doesn't have number value
						number = 0;
					}
					//Error for Insert due to one of the requirements not met
					catch(IllegalArgumentException e) {
						//If Insert is present without number value
						if (line.equals("Insert")) {
							writtenFile.println("Error in Line: " + line);
						}
						//If insert has out-of-bounds integer
						else {
							writtenFile.println(e.getMessage());
						}
					}
				}
//-------------------------------------------------------------------------------
				//Keyword Delete is found in input file
				else if (keyword.equals("Delete")) {
					//Calls Delete method
					try {
						writtenFile.println(Binary.Delete(number));
						//Reset number integer; In case 2 or more Delete lines preset and one doesn't have number value
						number = 0;
					}
					//Error for Delete due to one of the requirements not met
					catch(IllegalArgumentException e) {
						//If Delete is present without number value
						if (line.equals("Delete")) {
							writtenFile.println("Error in Line: " + line);
						}
						//If Delete has out-of-bounds integer
						else {
							writtenFile.println(e.getMessage());
						}
					}
				}
//-------------------------------------------------------------------------------
				//Keyword FindMin is found in input file
				else if (keyword.equals("FindMin")) {
					//Calls FindMin method
					try {
						writtenFile.println(Binary.FindMin());
					}
					//Error for FindMin due to one of the requirements not met
					catch(IllegalArgumentException e) {
						writtenFile.println(e.getMessage());
					}
				}
//-------------------------------------------------------------------------------
				//Keyword FindMax is found in input file
				else if (keyword.equals("FindMax")) {
					//Call FindMax method
					try {
						writtenFile.println(Binary.FindMax());
					}
					//Error for FindMax due to one of the requirements not met
					catch(IllegalArgumentException e) {
						writtenFile.println(e.getMessage());
					}
				}
//-------------------------------------------------------------------------------
				//Keyword Contains is found in input file
				else if (keyword.equals("Contains")) {
					//Calls Contains method
					try {
						writtenFile.println(Binary.Contains(number));
						//Reset number integer; In case 2 or more Contains lines preset and one doesn't have number value
						number = 0;
					}
					//Error for Contains due to one of the requirements not met
					catch(IllegalArgumentException e) {
						//If Contains is present without number value
						if (line.equals("Contains")) {
							writtenFile.println("Error in Line: " + line);
						}
						//If Contains has out-of-bounds integer
						else {
							writtenFile.println(e.getMessage());
						}
					}
				}
//-------------------------------------------------------------------------------
				//Keyword PrintTree is found in input file
				else if (keyword.equals("PrintTree")) {
					//Calls toString method, with Pre-Order Traversal
					try {
						writtenFile.println(Binary.toString());
						System.out.println(Binary.toString());
					}
					//Error for PrintTree due to one of the requirements not met
					catch(IllegalArgumentException e) {
						writtenFile.println(e.getMessage());
					}
				}
//-------------------------------------------------------------------------------
				//Keyword Height is found in input file
				else if (keyword.equals("Height")) {
					//Calls Height method
					try {
						writtenFile.println(Binary.Height());
					}
					//Error for Height due to one of the requirements not met
					catch(IllegalArgumentException e) {
						writtenFile.println(e.getMessage());
					}
				}
//-------------------------------------------------------------------------------
				//Keyword Size is found in input file
				else if (keyword.equals("Size")) {
					//Calls Size method
					try {
						writtenFile.println(Binary.Size());
					}
					//Error for Size due to one of the requirements not met
					catch(IllegalArgumentException e) {
						writtenFile.println(e.getMessage());
					}
				}
//-------------------------------------------------------------------------------
				//Keyword not found in input file
				else {
					writtenFile.println("Error in Line: " + line);
				}
			}
		} 
		//Catch statement for error
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Close access to the input/output files
		finally {
			scannedFile.close();
			writtenFile.close();
		}
	}
}