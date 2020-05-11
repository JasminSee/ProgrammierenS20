package HausaufgabeChatroom;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class ConnectionThread extends Thread {
	
	private ServerSocket serverSocket;
	private ArrayList<ReaderThread> readerThreadList;
	private BlockingQueue<String> queue;
	private ArrayList<PrintWriter> printWriterList;
	
	public ConnectionThread(ServerSocket serverSocket, ArrayList<ReaderThread> readerThreadList,
			BlockingQueue<String> queue, ArrayList<PrintWriter> printWriterList) {
		super();
		this.serverSocket = serverSocket;
		this.readerThreadList = readerThreadList;
		this.queue = queue;
		this.printWriterList = printWriterList;
	}
	
	@Override
	public void run() {
		try {
			Socket client = serverSocket.accept();
			System.out.println("Client verbunden...");
			
			Scanner scanner = new Scanner(client.getInputStream());
			PrintWriter printWriter = new PrintWriter(client.getOutputStream());
			
			ReaderThread readerThread = new ReaderThread(scanner, queue);
			synchronized (readerThreadList) {
				readerThreadList.add(readerThread);
			}
			readerThread.start();
			
			synchronized (printWriterList) {
				printWriterList.add(printWriter);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Fehler ConnectionThread");
		}
	}
}
