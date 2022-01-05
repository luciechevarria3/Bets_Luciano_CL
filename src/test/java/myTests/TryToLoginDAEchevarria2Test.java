package myTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import dataAccess.DataAccessEchevarria2;

/**
 * JUnit test class to test the DataAccess' "TryToLogin" method with Mockito if neccesary
 * @author Luciano Echevarria
 * @version 02/10/2021
 */
//Comentario2
class TryToLoginDAEchevarria2Test {
	// System under test
	private static DataAccessEchevarria2 sut;
	
	@BeforeAll
	static void initializeDB() {
		sut = new DataAccessEchevarria2(true);
	}
	/**
	 * Test1: A correct run on the method.
	 */
	@Test
	@DisplayName("Test 1: A correct run on the method")
	void test1AllCorrect() {
		// Preparing variables
		String username = "Samu11";
		String password = "123456X";
		assertTrue(sut.tryToLogin(username, password));
	}
	
	/**
	 * Test2: Username and password are blank strings
	 */
	@Test
	@DisplayName("Test 2: Username and password are blank strings('') ")
	void test2UsernamePasswdBlank() {
		// Preparing variables
		String username = "";
		String password = "";
		assertThrows(RuntimeException.class, () -> sut.tryToLogin(username, password));
	}
	
	/**
	 * Test3: Username is a blank string
	 */
	@Test
	@DisplayName("Test 3: Username is a blank string('') ")
	void test3UsernameBlank() {
		// Preparing variables
		String username = "";
		String password = "257785";
		assertThrows(RuntimeException.class, () -> sut.tryToLogin(username, password));
	}
	
	/**
	 * Test4: Password is a blank string
	 */
	@Test
	@DisplayName("Test 4: Password is a blank string('') ")
	void test4PasswdBlank() {
		// Preparing variables
		String username = "Julio10";
		String password = "";
		assertThrows(RuntimeException.class, () -> sut.tryToLogin(username, password));
	}
	
	/**
	 * Test5: User doesn't exist in DB
	 */
	@Test
	@DisplayName("Test 5: The user doesn't exist in DB ")
	void test5UserNotFoundInDB() {
		// Preparing variables
		String username = "Ana20";
		String password = "202020";
		System.out.println("Test5: ");
		assertFalse(sut.tryToLogin(username, password));
	}
	
	/**
	 * Test6: Wrong password
	 */
	@Test
	@DisplayName("Test 6: Wrong password ")
	void test6WrongPasswd() {
		// Preparing variables
		String username = "Ane20";
		String password = "202122";
		System.out.println("Test6: ");
		assertFalse(sut.tryToLogin(username, password));
	}
}
