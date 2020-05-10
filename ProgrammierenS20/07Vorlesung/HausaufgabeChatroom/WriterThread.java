package HausaufgabeChatroom;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

public class WriterThread extends Thread {
	
	private BlockingQueue<String> queue;
	private ArrayList<PrintWriter> printWriterList;
	boolean isRunning = true;
	
	WriterThread (BlockingQueue<String> queMessages, ArrayList<PrintWriter> quePrintwriter) {
		super();
		this.queue = queMessages;
		this.printWriterList = quePrintwriter;
	}
	
	@Override
	public void run() {
		while (isRunning) {
			try {
				if(!queue.isEmpty()) {
					for (PrintWriter printWriter: printWriterList) {
						printWriter.println(queue.take());
						printWriter.flush();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		for (PrintWriter printWriter: printWriterList) {
			printWriter.close();
		}
	}
	
	public void quit() {
		isRunning = false;
	}
}
