import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A JUnit test for the methods of the MorseCodeCoverter class
 * @author Christopher Perez Lebron
 *
 */
class MorseCodeConverterTest_STUDENT {

	/**
	 * test the convertToEnglish method accepting String
	 */
	@Test
	void testConvertToEnglishString() {
		String code = "-.- .- -. -.-- . / --- -. -.-. . / ... .- .. -.. / --. . --- .-. --. . / -... ..- "
				+ "... .... / -.. --- . ... / -. --- - / -.-. .- .-. . / .- -... --- ..- - / -... .-.. ."
				+ "- -.-. -.- / .--. . --- .--. .-.. ."; 
		String result = "kanye once said george bush does not care about black people";
		assertTrue(MorseCodeConverter.convertToEnglish(code).equals(result));
		
	}
	
	/**
	 * test the convertToEnglish method accepting file objects. 
	 */
	@Test
	void testConvertToEnglishFile() {
		
		String result = "sometimes i say things just to make myself sound more photosynthesis sometimes "
				+ "it works other times it does not that just kind of makes me sad you know like i work "
				+ "so hard to sound smart and sometimes it just does not work like i would hope honestly "
				+ "this is just a test to ensure that the code is working properly i am trying to write "
				+ "enough to the the point where it would be statistically unlikely that i would not "
				+ "encounter every letter in my tree just incase that i missed a few qwertyuiopasdfghjklzxcvbnm"
				+ " just to be safe ya know";
		
		File file = new File("text.txt");
		
		try {
			assertTrue(MorseCodeConverter.convertToEnglish(file).equals(result));
		} catch (FileNotFoundException e) {
			fail("No exception should of been thrown");
		}
	}

	/**
	 * test the print tree method. See note inside the method. 
	 */
	@Test
	void testPrintTree() {
		/*
		 * I do not really see a purpose in implementing this method b/c 
		 * the provided JUnit test already tests that the traversal order is correct.
		 * However, I will write a test anyways. 
		 */
		
		assertEquals("h s v i f u e l r a p w j  b d x n c k y t z g q m o", MorseCodeConverter.printTree());
	}

}
