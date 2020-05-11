package HausaufgabeChatroom;

import java.io.PrintWriter; 
import java.net.Socket; 
import java.util.Scanner; 
  
public class ClientMain { 
	
 	private boolean isRunning = true; 
 	private String userName; 
 	
 	public void createClient(){ 
 		
 		try { 
 			
 			Socket client = new Socket("127.0.0.1", 3445); 
 			 			
			PrintWriter printWriter = new PrintWriter(client.getOutputStream());
			Scanner scanner = new Scanner(client.getInputStream());
 			 
 			writeMessage(printWriter); 
 			 
 			while (isRunning) { 
 				System.out.println(scanner.nextLine());  
 			} 
 			 
 			scanner.close(); 
 			printWriter.close(); 
 			client.close(); 
 			 
 		} catch (Exception e) { 
 			e.printStackTrace(); 
 			System.out.println("Fehler ClientMain");
 		}
 	} 
 
 	private void writeMessage (PrintWriter printWriter) { 
 		new Thread(new Runnable() { 
 			
 			@Override 
 			public void run() {
 				
 				Scanner keyboard = new Scanner(System.in); 
 				System.out.print("Geben Sie Ihren Username ein: "); 
 				userName = keyboard.nextLine(); 
 				
 				while (isRunning) {
 					
 					System.out.println("Geben Sie Ihre Nachricht ein: ");
 					String text = userName + ": " + keyboard.nextLine(); 
 					
 					if (text.equalsIgnoreCase("quit")) { 
 						isRunning = false; 
 						
 					} else { 
 						printWriter.println(text); 
 						printWriter.flush(); 
 					} 
 				} 
 				keyboard.close();
 			} 
 		}).start(); 
 	} 
 	 
 	public static void main(String[] args) { 
 		ClientMain client = new ClientMain(); 
 		client.createClient(); 
 	} 
} 

