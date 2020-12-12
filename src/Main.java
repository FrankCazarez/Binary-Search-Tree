//Name of The Student: 				Frank Cazarez
//NedID:							fac160030
//Class:							CE 3345.03 Data Structures and Introduction to Algorithmic Analysis
//Section:							003
//Semester:							Spring 2019
//Project Number/Description:		

import java.util.Scanner;
import java.io.*;
public class Main{
   public static void main(String[] args){
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
			//Make input and output files
			scannedFile = new Scanner(new File(args[0]));
			writtenFile = new PrintWriter(new File(args[1]));
			//Variables to go through and search for keywords
			String line;
		
//-------------------------------------------------------------------------------			
				//Scans 1st line and saves it to determine if integer or string
				line = scannedFile.nextLine();
				System.out.println(line);
//------------------------------------------------------------------------------------------------------------------------------   INTEGER
			//If integer is selected
			if (line.equals("Integer")) {
				//Variables
				int number = 0;
				String keyword = null;
				String[] array;
				String line2;
				//Initialize integer tree
			    RedBlackTree<Integer> RBT = new RedBlackTree<Integer>();
				//Scans input file
				while (scannedFile.hasNextLine()) {
					//Scans line and saves it
					line2 = scannedFile.nextLine();
					//Split before ":"
					array = line2.split(":");
					//If insert or contains doesn't have value after ':' 
					if (array.length == 1) {
						if (line2.equals("Insert") || line2.equals("Contains")) {
							//Error Message
							writtenFile.println("Error in Line: " + line2);
							//Move to next line
							continue;
						}
					}
				
					//Check that length is long enough; word and integer
					if (array.length > 1) {
						//saves integer to be used
						number = Integer.parseInt(array[1]);
						//saves keyword; e.g. Insert
						keyword = array[0];
					}
					//Only keyword is present
					else {
						keyword = line2;
					}
//-------------------------------------------------------------------------------	
					//Keyword Insert is found in input file
					if (keyword.equals("Insert")) {
						try {
							//Run insert method from RedBlackTree
							writtenFile.println(RBT.insert(number));
						}
						//Error for Contains due to one of the requirements not met
						catch(IllegalArgumentException e) {
							writtenFile.println(e.getMessage());
						}
					}
//-------------------------------------------------------------------------------			      
					//Keyword Contains is found in input file
					else if (keyword.equals("Contains")) {
						//Calls Contains method
						try {
							//Run contains method from RedBlackTree
							writtenFile.println(RBT.contains(number));
						}
						//Error for Contains due to one of the requirements not met
						catch(IllegalArgumentException e) {
							writtenFile.println(e.getMessage());
						}
					}			      
//-------------------------------------------------------------------------------
					//Keyword PrintTree is found in input file
					else if (keyword.equals("PrintTree")) {
						//Calls toString method, with Pre-Order Traversal
			    	 	try {
			    	 		writtenFile.println(RBT.toString());
			    	 		//Displays in console what output file will show
			    	 		System.out.println(RBT.toString());
			    	 	}
			    	 	//Error for PrintTree due to one of the requirements not met
			    	 	catch(IllegalArgumentException e) {
			    	 		writtenFile.println(e.getMessage());
			    	 	}
					}
//-------------------------------------------------------------------------------
					//Keyword not found in input file
					else {
						writtenFile.println("Error in Line: " + line2);
					}
				}
				
			}
//------------------------------------------------------------------------------------------------------------------------------   STRING
			//If string is selected
			else if (line.equals("String")) {
				//Variables
				String name = null;
				String keyword = null;
				String[] array;
				String line3;
				//Initialize string tree
			    RedBlackTree<String> RBT2 = new RedBlackTree<String>();
				//Scans input file
				while (scannedFile.hasNextLine()) {
					//Scans line and saves it
					line3 = scannedFile.nextLine();
					//Split before ":"
					array = line3.split(":");
					//If insert or contains doesn't have value after ':' 
					if (array.length == 1) {
						if (line3.equals("Insert") || line3.equals("Contains")) {
							//Error Message
							writtenFile.println("Error in Line: " + line3);
							//Move to next line
							continue;
						}
					}
				
					//Check that length is long enough; word and integer
					if (array.length > 1) {
						//saves integer to be used
						name = (array[1]);
						//saves keyword; e.g. Insert
						keyword = array[0];
					}
					//Only keyword is present
					else {
						keyword = line3;
					}
//-------------------------------------------------------------------------------
					//Keyword Insert is found in input file
					if (keyword.equals("Insert")) {
						try {
							//Run insert method from RedBlackTree
							writtenFile.println(RBT2.insert(name));
						}
						//Error for Contains due to one of the requirements not met
						catch(IllegalArgumentException e) {
							writtenFile.println(e.getMessage());
						}
					}
//-------------------------------------------------------------------------------			      
					//Keyword Contains is found in input file
					else if (keyword.equals("Contains")) {
						//Calls Contains method
						try {
							//Run contains method from RedBlackTree
							writtenFile.println(RBT2.contains(name));
						}
						//Error for Contains due to one of the requirements not met
						catch(IllegalArgumentException e) {
							writtenFile.println(e.getMessage());
						}
					}			      
//-------------------------------------------------------------------------------
					//Keyword PrintTree is found in input file
					else if (keyword.equals("PrintTree")) {
						//Calls toString method, with Pre-Order Traversal
						try {
							writtenFile.println(RBT2.toString());
							//Displays in console what output file will show
							System.out.println(RBT2.toString());
						}
						//Error for PrintTree due to one of the requirements not met
						catch(IllegalArgumentException e) {
							writtenFile.println(e.getMessage());
						}
					}
//-------------------------------------------------------------------------------
					//Keyword not found in input file
					else {
						writtenFile.println("Error in Line: " + line3);
					}
				}				
			}
			//If input.txt has unknown object type
			else {
				writtenFile.println("Only works for objects Integers and Strings");
			}

		}
		//Catch statement
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//Close access to input and output files
		finally {
			scannedFile.close();
			writtenFile.close();
		}
   }
} // end of Main class