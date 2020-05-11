package HausaufgabeChatroom;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

public class WriterThread extends Thread {
	
	private BlockingQueue<String> queue;
	private ArrayList<PrintWriter> printWriterList;
	boolean isRunning = true;
	
	WriterThread (BlockingQueue<String> queue, ArrayList<PrintWriter> printWriterList) {
		super();
		this.queue = queue;
		this.printWriterList = printWriterList;
	}
	
	@Override
	public void run() {
		while (isRunning) {
			try {
				//queue blockiert, wenn keine Nachricht enthalten ist, daher Abfrage, ob queue != 0 ist, nicht noetig
				String message = queue.take();
				synchronized (printWriterList) {
					for (PrintWriter printWriter: printWriterList) {
						try {
							printWriter.println(message);
							printWriter.flush();
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("Fehler WriterThread");
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Fehler WriterThread");
			}
		}
	}
	
	public void quit() {
		isRunning = false;
		interrupt();
	}
}
