import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ConsoleTesting {

	public static void main(String[] args) throws FileNotFoundException {
		MorseCodeTree tree = new MorseCodeTree(); 
		
//		System.out.println(tree.fetch("..."));
		ArrayList<String> resultList = tree.toArrayList(); 
		
		File file = new File("text.txt"); 
		System.out.println(MorseCodeConverter.convertToEnglish(file));
		
		
	}

}
