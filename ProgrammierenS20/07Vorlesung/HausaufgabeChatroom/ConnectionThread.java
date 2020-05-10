package HausaufgabeChatroom;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class ConnectionThread extends Thread {
	
	private ArrayList<PrintWriter> printWriterList;
	private BlockingQueue<String> queue;
	private ArrayList<ReaderThread> readerThreadList;
	private ServerSocket server;
	
	boolean isRunning = true;
	
	public ConnectionThread(ArrayList<PrintWriter> printWriterList, BlockingQueue<String> queue, ServerSocket server) {
		this.printWriterList = printWriterList;
		this.queue = queue;
		this.server = server;
		readerThreadList = new ArrayList<ReaderThread>();
	}
	
	@Override
	public void run() {
		
		ReaderThread readerThread = null;
		Scanner scanner = null;
		
		while(isRunning) {
			try {
				System.out.println("Warten auf Client...");
				Socket client = server.accept();
				System.out.println("Client verbunden...");
				
				scanner = new Scanner(client.getInputStream());
				
				readerThread = new ReaderThread(scanner, queue);
				
				readerThreadList.add(readerThread);
				printWriterList.add(new PrintWriter(client.getOutputStream()));
				
				readerThread.start();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		readerThread.quit();
		scanner.close();
	}
	public void quit() {
		isRunning = false;
	}
}
