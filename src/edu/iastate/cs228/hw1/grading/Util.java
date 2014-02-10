package edu.iastate.cs228.hw1.grading;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

/**
 * Singleton class used to handle redirecting standard output
 * @author Brandon
 *
 */
public class Util {

	/**
	 * Singleton stuff
	 */
	private static Util instance;
	
	public static Util getInstance() {
		if (instance == null) {
			instance = new Util();
		}
		return instance;
	}
	
	// Reference to standard out so we can restore it
	private static PrintStream STD_OUT;
	
	private Util() {
		STD_OUT = System.out;
	}
	
	/**
	 * Method used to redirect standard output. 
	 * @return
	 * 		The buffer where output is stored
	 */
	public ByteArrayOutputStream redirectStdOut() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		System.setOut(ps);
		return baos;
	}
	
	/**
	 * Restores standard output to normal
	 */
	public void restoreStdOut() {
		System.setOut(STD_OUT);
	}
	
	/**
	 * Method that uses Reflection to grab the value of the given field.
	 * @param object
	 * @param fieldName
	 * @return
	 * @throws NoSuchFieldException
	 * 							If the student changed the specs this exception will be thrown
	 * @throws SecurityException
	 * @throws IllegalAccessException 
	 * 							Not possible since I set accessible to true
	 * @throws IllegalArgumentException 
	 */
	public static Object getPrivateField(Object object, String fieldName) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field field = object.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		return field.get(object);
	}

}
