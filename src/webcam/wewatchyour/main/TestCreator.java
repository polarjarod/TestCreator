package webcam.wewatchyour.main;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import webcam.wewatchyour.gui.*;

/**
 * Main class of the TestCreator project
 * 
 * @author Jarod Norwood
 * @author Nicholas Roth
 *
 */
public class TestCreator {
	String pdfName;
	
	/**
	 * Main method
	 * @param args command line arguments unused
	 */
	public static void main(String[] args){
		String[] authors = {"Jarod Norwood", "Nicholas Roth"};
		TestCreator program = new TestCreator(authors);
		program.init();
	}
	/**
	 * 
	 * @param authors - Project creators
	 */
	public TestCreator(String[] authors){
		System.out.println("Thank you for using TestCreator! This product was brought to you by " + authors[0] + " and " + authors[1] + " Please understand that this is not a finished build and we at seeyes4prawjekt are always working to improve your experience! Have a nice day!");
	}
	/**
	 * This method invokes the commands to create the TexWriter and PDFWriter Threads
	 * it also initializes the Semaphore for resource management of the temporary .tex file
	 */
	public void init(){
		Lock WorkLock = new ReentrantLock();
		UIManage gui = new UIManage(WorkLock);
		Thread Gui = new Thread(gui);
		
		String name = "temp";
		
		Lock TeXLock = new ReentrantLock();
		CurrentWorkingDirectory pathFinder = new CurrentWorkingDirectory();
		
		TexWriter texWriter = new TexWriter(TeXLock, pathFinder, name);
		Thread texMaker = new Thread(texWriter);
		texMaker.start();

		PDFWriter pdfWriter = new PDFWriter(name, TeXLock, pathFinder);
		Thread pdfMaker = new Thread(pdfWriter);
		pdfMaker.start();
	}

}