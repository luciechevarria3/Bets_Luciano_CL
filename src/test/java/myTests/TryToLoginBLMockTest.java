package myTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;



/**
 * Class to test the "tryToLogin()" method in BLFacadeImplementation using a mocked DataAccess class.
 * @author Luciano Echevarria
 * @version 02/10/2021
 */
class TryToLoginBLMockTest {

	private DataAccess mockDA = Mockito.mock(DataAccess.class);
	private BLFacadeImplementation sut = new BLFacadeImplementation(mockDA);
	
	/**
	 * Test1: loggedUser is not null. Should throw exception.
	 */
	@Test
	@DisplayName("Test 1: loggedUser is not null")
	void test1LoggedUserNull() {
		BLFacadeImplementation.loggedUser = "prueba";
		String username = "Samu11";
		String password = "123456X";
		boolean result = sut.tryToLogin(username, password);
		Mockito.verify(mockDA, Mockito.times(2)).close();
		assertFalse(result);
	}
	
	/**
	 * Test2: loggedUser null, username and passwd blank strings
	 */
	@Test
	@DisplayName("Test 2: loggedUser null, username and password are blank strings")
	void test2LUNullblanks() {
		BLFacadeImplementation.loggedUser = null;
		String username = "";
		String password = "";
		Mockito.doThrow(RuntimeException.class).when(mockDA).tryToLogin(username, password);
		boolean result = sut.tryToLogin(username, password);
		Mockito.verify(mockDA, Mockito.times(1)).open(false);
		Mockito.verify(mockDA, Mockito.times(2)).close();
		assertFalse(result);
	}
	
	/**
	 * Test3: loggedUser null, username blank string
	 */
	@Test
	@DisplayName("Test 3: loggedUser null, username blank string")
	void test3usernameBlank() {
		BLFacadeImplementation.loggedUser = null;
		String username = "";
		String password = "257785";
		Mockito.doThrow(RuntimeException.class).when(mockDA).tryToLogin(username, password);
		boolean result = sut.tryToLogin(username, password);
		Mockito.verify(mockDA, Mockito.times(1)).open(false);
		Mockito.verify(mockDA, Mockito.times(2)).close();
		assertFalse(result);
	}
	
	/**
	 * Test4: loggedUser null, password blank string
	 */
	@Test
	@DisplayName("Test 4: loggedUser null, password blank string")
	void test4passwordBlank() {
		BLFacadeImplementation.loggedUser = null;
		String username = "Julio10";
		String password = "";
		Mockito.doThrow(RuntimeException.class).when(mockDA).tryToLogin(username, password);
		boolean result = sut.tryToLogin(username, password);
		Mockito.verify(mockDA, Mockito.times(1)).open(false);
		Mockito.verify(mockDA, Mockito.times(2)).close();
		assertFalse(result);
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
		Mockito.doReturn(false).when(mockDA).tryToLogin(username, password);
		boolean result = sut.tryToLogin(username, password);
		Mockito.verify(mockDA, Mockito.times(1)).open(false);
		Mockito.verify(mockDA, Mockito.times(2)).close();
		assertFalse(result);
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
		Mockito.doReturn(false).when(mockDA).tryToLogin(username, password);
		boolean result = sut.tryToLogin(username, password);
		Mockito.verify(mockDA, Mockito.times(1)).open(false);
		Mockito.verify(mockDA, Mockito.times(2)).close();
		assertFalse(result);
	}
	
	/**
	 * Test7: Successful login
	 */
	@Test
	@DisplayName("Test 7: Successful login ")
	void test7SuccessfulLogin() {
		// Preparing variables
		BLFacadeImplementation.loggedUser = null;
		String username = "Ane20";
		String password = "202020";
		Mockito.doReturn(true).when(mockDA).tryToLogin(username, password);
		boolean result = sut.tryToLogin(username, password);
		Mockito.verify(mockDA, Mockito.times(1)).open(false);
		Mockito.verify(mockDA, Mockito.times(2)).close();
		assertTrue(result);
	}
}
