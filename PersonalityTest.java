// Elizabeth Koshelev
// COSI 12B, Spring 2015 
// Programming Assignment #1, 1/29/16
// This program returns the personality results of several characters that took a personality test.
import java.io.*; 
import java.util.*; 
public class PersonalityTest{
	public static void main(String[] args) throws FileNotFoundException {
		useData(); //This calls other methods to return the results of a personality test.
	}
	public static void useData() throws FileNotFoundException {//This finds the file from the user and calls other methods to present the data accordingly.
		int n=1;
		while (n==1){
			System.out.print("Input File Name:"); 
			Scanner console = new Scanner(System.in);
			String input = console.next();
			File check = new File(input);
			if (check.exists()){
				n=0;
				Scanner checkFile = new Scanner(new File(input));
				System.out.print("Output File Name:"); 
				String output = console.next();
				PrintStream out = new PrintStream(new File(output));
				lookupStats(checkFile, out);
			} else{
				System.out.println("Please put in a valid file name.");
			}
		}
		
	}
	public static void lookupStats(Scanner first, PrintStream out){//This sorts and prints the data.
		int r=1;
		while(first.hasNextLine()) {
			String line = first.nextLine();
			Scanner lineScan = new Scanner(line);
			while(lineScan.hasNext()){ 
				if (r%2!=0){
				String name = lineScan.nextLine();//This finds and prints the name, if the line number is odd.
				out.println(name);
				}else{
					String results = lineScan.next();
					int end = results.length(); //This finds how many questions.
					int arr[] = new int[end]; //This creates the array to store the answers.
					for ( int i=0; i<end; i++){
						if (results.charAt(i) == 'A' || results.charAt(i) == 'a'){
							arr[i] = 0;
						}else if (results.charAt(i) == 'B'|| results.charAt(i) == 'b'){
							arr[i] =1;
						}else if (results.charAt(i) == '-' ){
							arr[i] =2;
						} 
					}	
					printResults(calculateType(arr, end), out);
				} 
				r++; //This counts the lines.
			}
		}
	}
	public static int[][] calculateType(int [] oldarr, int length){//This sorts the data into a 2d array.
		int runs = length/7;
		int arr[][] = new int[4][2];
		for (int j=0; j<runs; j++){
			for (int i=0; i<7; i++){
				if (i==0){
					if (oldarr[j*7+i]==0){
						arr[0][0]++;
					} else if (oldarr[j*7+i]==1){
						arr[0][1]++;
					}
				} else if (i== 1 || i==2){
					if (oldarr[j*7+i]==0){
						arr[1][0]++;
					} else if (oldarr[j*7+i]==1){
						arr[1][1]++;
					}
				} else if (i==3 || i==4){
					if (oldarr[j*7+i]==0){
						arr[2][0]++;
					} else if (oldarr[j*7+i]==1){
						arr[2][1]++;
					}				
				} else if (i==5 || i==6){
					if (oldarr[j*7+i]==0){
						arr[3][0]++;
					} else if (oldarr[j*7+i]==1){
						arr[3][1]++;
					}				
				}
			}
		}
			return arr;
	}
	public static void printResults(int [][] arr, PrintStream out){//This method uses the array of sorted data to print the results.
		out.println(arr[0][0] + "A-" + arr[0][1] + "B " + arr[1][0] + "A-" + arr[1][1] + "B " + arr[2][0]+ "A-" + arr[2][1] + "B " + arr[3][0]+ "A-" + arr[3][1] + "B");
		int first = (int)((double)arr[0][1]/((double)arr[0][0]+(double)arr[0][1])*100);
		int second = (int)((double)arr[1][1]/((double)arr[1][0]+(double)arr[1][1])*100);
		int third = (int)((double)arr[2][1]/((double)arr[2][0]+(double)arr[2][1])*100);
		int fourth = (int)((double)arr[3][1]/((double)arr[3][0]+(double)arr[3][1])*100);
		out.print("[" + first + "%, " + second + "%, " + third + "%, " + fourth + "%]  =  ");
		if (first>50){
			out.print("I");
		} else if (first==50){
			out.print("X");
		}else{
			out.print("E");
		}
		if (second>50){
			out.print("N");
		} else if (second==50){
			out.print("X");
		}else{
			out.print("S");
		}
		if (third>50){
			out.print("F");
		} else if (third==50){
			out.print("X");
		}else{
			out.print("T");
		}
		if (fourth>50){
			out.println("P");
				out.println();
		} else if (fourth==50){
			out.println("X");
				out.println();
		}else{
			out.println("J");
				out.println();
		}
	}
}



