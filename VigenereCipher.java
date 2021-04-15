
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

interface EncryptDecrypt {
public void encrypt(String inputFileName, String key);
public void decrypt(String inputFileName, String key, String outputFileName);
}
public class VigenereCipher implements EncryptDecrypt {
	
	public void encrypt(String inputFileName, String key) { 
		
		key = key.toUpperCase();
		File file = new File(inputFileName); 
		Scanner scan; 
		try {
			scan = new Scanner(file); 
			int indexOfKey=0;
			String result = ""; 
			while(scan.hasNextLine()) {
				String line = scan.nextLine(); 
				line = line.toUpperCase();
				String newLine =""; 
				for ( int i =0;i<line.length();i++) { 
					if( (33 < (int) (line.charAt(i)) && (int) (line.charAt(i)) <65 ) || ( (int) (line.charAt(i)) <97 && (int) (line.charAt(i)) > 90  ) ) {
						newLine+=line.charAt(i)+"";
						continue; //noktalama isaretleri icin
					}	
					if(line.charAt(i)==' ') { // bosluk bosluk olarak kalsin diye farz ettim. 
						newLine += " ";
						continue;
					}
					newLine += (char) (((line.charAt(i) + (int)key.charAt(indexOfKey)) % 26) +'A'); //topla
					//newLine += (char) (((line.charAt(i) + (int)key.charAt(indexOfKey)) % 94) +'!');
					indexOfKey++;
					if(indexOfKey ==key.length()) 
						indexOfKey=0; 
				} 
				//System.out.println(newLine); 
				result += newLine + "\n";
			}
			
			inputFileName=inputFileName.replace(".txt", "");
			PrintWriter printWriter = new PrintWriter(inputFileName+".encr"); 
			printWriter.print(result); 
			printWriter.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace(); 
			} catch (IOException e) { 
				e.printStackTrace();
			} 
		
		//read
		File reading = new File(inputFileName+".encr"); 
		
		try {	
			scan = new Scanner(reading); 
			while(scan.hasNextLine()) { 
				System.out.println(scan.nextLine()); 
			} 
			scan.close();
		}catch (FileNotFoundException e) { 
			e.printStackTrace();
		} 
		
		
		
		
	}

		
		
		
		
		
	

	
	public void decrypt(String inputFileName, String key, String outputFileName) {
		
		key = key.toUpperCase();
		File file = new File(inputFileName);
		Scanner scan;
		try {
			scan = new Scanner(file);
			int indexOfKey=0; 
			String result = ""; 
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				line=line.toUpperCase();
				String newLine = "";
				for ( int i =0;i<line.length();i++) {
					
					if( (33 < (int) (line.charAt(i)) && (int) (line.charAt(i)) <65 ) || ( (int) (line.charAt(i)) <97 && (int) (line.charAt(i)) > 90  ) ) {
						newLine+=line.charAt(i)+"";
						continue; //noktalama isaretleri icin
					}	
					
					if(line.charAt(i)==' ') {
						newLine += " "; 
						continue;
					} 
					newLine += (char) (((line.charAt(i) - (int)key.charAt(indexOfKey)+ 26) %26) +'A' ); 
					//newLine += (char) (((line.charAt(i) - (int)key.charAt(indexOfKey)+ 94) % 94) +'!' ); 
					indexOfKey++;
					if(indexOfKey == key.length()) 
						indexOfKey=0; 
				} 
				result += newLine + "\n"; 
			}
			
			PrintWriter printWriter = new PrintWriter(outputFileName);
			printWriter.print(result); 
			printWriter.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace(); 
			} catch (IOException e) {
				e.printStackTrace(); 
			} 
		
		//read
			
			File reading = new File(outputFileName); 
				
			try {	
				scan = new Scanner(reading); 
				while(scan.hasNextLine()) { 
					System.out.println(scan.nextLine()); 
				} 
				scan.close();
			}catch (FileNotFoundException e) { 
				e.printStackTrace();
			} 
		
		
		
		} 
		
		
	
	
	/*public static void main(String []args ) { 
		TextFileUtil t=new TextFileUtil();
		t.encrypt("A.txt", "CATCAT");
		t.decrypt("A.encr", "CATCAT", "B.txt");
		
		
	} */
	
}

