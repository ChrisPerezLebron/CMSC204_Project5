import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * this class serves as a static utility class providing morse code translation 
 * methods. We translate according to the MorseCodeTree field 
 * @author Christopher Perez Lebron
 *
 */
public class MorseCodeConverter {
	
	private static MorseCodeTree mTree = new MorseCodeTree(); 

	/**
	 * converts a string of morse code into English
	 * @param code a string of morse code to be translated
	 * @return a string containing the message correlated with the given 
	 * morse code string
	 */
	public static String convertToEnglish(String code) {
		PrintWriter pWriter = null; 
		
		try {
		pWriter = new PrintWriter("log.txt");
		} catch (FileNotFoundException e) {
			/*
			 * no error should occur. I surround this in try catch so I do 
			 * not have to add throws clause which would make my method 
			 * differentiate from the requirements of the java doc
			 */
		}
		
		Scanner stringScanner = new Scanner(code); 
		String result = "";
		while(stringScanner.hasNext()) {
			String currentToken = stringScanner.next(); 
			
			if(!currentToken.equals("/")) {
			
				/*
				 * this is placed in a try catch block so that the IllegalArgumentException 
				 * that gets thrown for illegal morse code characters does not cause termination 
				 * of the program. 
				 */
				try {
					result += mTree.fetch(currentToken); 
				} catch(IllegalArgumentException e) {
					
					/*
					 * I have two different types of IllegalArgumentExceptions one labeled "char" is 
					 * for an illegal character within the morse code sequence (EX: .^-) the second 
					 * labeled "token" means that the token maps to an element that does not exist 
					 * within our tree. 
					 * 
					 * I handle each of these events slightly differently. 
					 */
					if(e.getMessage().equals("char")) {
						System.out.println("WARNING: Unknown character encountered"); 
						pWriter.println("WARNING: the following morse token contains an illegal morse code character: " + currentToken);
					}
					else if(e.getMessage().equals("token")) {
						System.out.println("WARNING: Unknown token encountered");
						pWriter.println("WARNING: the following morse token is illegal: " + currentToken);
					}
				}
				
				
			}
			else //end of word delimiter add a space
				result += " ";
				
		}
		
		pWriter.close();
		return result;
	}
	
	/**
	 * converts a file containing morse code into english. It returns a string 
	 * containing the english text correlated with the morse code provided in the file. 
	 * NOTE: the morse code in the file MUST be contained within 1 line and 1 line only
	 * @param file a file object linked to a file containing the morse code. 
	 * @return a string representing the translated morse code
	 * @throws FileNotFoundException if the file is not found
	 */
	public static String convertToEnglish(File file) throws FileNotFoundException {
		Scanner fileScanner = new Scanner(file); 
		
		/*
		 * ASSUMPTION: morse code in the file will be given in a single line. 
		 * Thus, for file processing I open the file and pass the one and only 
		 * line of input to the convertToEnglish(String) method. 
		 */
		return convertToEnglish(fileScanner.nextLine());
	}
	
	/**
	 * returns a string containing the in-order traversal of our morse code tree field
	 * @return a string containing the in-order traversal
	 */
	public static String printTree() {
		ArrayList<String> resultList = mTree.toArrayList(); 
		String resultStr = ""; 
		
		for(String str : resultList)
			resultStr += str + " ";
		
		return resultStr.trim(); //get rid of the trailing space
	}
}
