package HausaufgabeChatroom;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ServerMain { 
	
	boolean isRunning = true;
	
	public void createServer() { 
		
		try {
			
			BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
			ArrayList<PrintWriter> printWriterList = new ArrayList<PrintWriter>();
			
			Scanner scanner = new Scanner(System.in);
			
			ServerSocket server = new ServerSocket(3445, 2);
			System.out.println("Server gestartet...");
			
			WriterThread writerThread = new  WriterThread(queue, printWriterList);
			ConnectionThread conThread = new ConnectionThread(printWriterList, queue, server);
			
			writerThread.start();
			conThread.start();
			
			while (isRunning) {
				if (scanner.next().equalsIgnoreCase("quit")) {
					isRunning = false;
					writerThread.quit();
					conThread.quit();
					server.close();
				}
			}
			scanner.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ServerMain server = new ServerMain();
		server.createServer();
	}
}
