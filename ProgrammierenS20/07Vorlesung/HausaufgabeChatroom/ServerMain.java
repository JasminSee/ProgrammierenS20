package HausaufgabeChatroom;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ServerMain { 
	
	// Konstanten (final) immer mit Groﬂbuchstaben
	private static final int NUM_CONNECTIONS = 42;
	
	private boolean isRunning = true;
	
	public void createServer() { 
		
		try {
			
			ServerSocket server = new ServerSocket(3445, NUM_CONNECTIONS);
			System.out.println("Server gestartet...");
			
			BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
			ArrayList<PrintWriter> printWriterList = new ArrayList<PrintWriter>();
			ArrayList<ReaderThread> readerThreadList = new ArrayList<>();
			
			WriterThread writerThread = new  WriterThread(queue, printWriterList);
			writerThread.start();
			
			for (int i = 0; i < NUM_CONNECTIONS; i++) {
				ConnectionThread conThread = new ConnectionThread(server, readerThreadList, queue, printWriterList);
				conThread.start();
			}
			
			Scanner scanner = new Scanner(System.in);
			
			while (isRunning) {
				if (scanner.nextLine().equalsIgnoreCase("quit")) {
					isRunning = false;
					writerThread.quit();
					for (ReaderThread readerThread : readerThreadList) {
						readerThread.quit();
					}
					for (PrintWriter printWriter : printWriterList) {
						printWriter.close();
					}
					server.close();
					scanner.close();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Fehler ServerMain");
		}
	}
	
	public static void main(String[] args) {
		ServerMain server = new ServerMain();
		server.createServer();
	}
}
