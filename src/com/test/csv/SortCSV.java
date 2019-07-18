package com.test.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SortCSV {
private static BufferedReader in;
	
	private static String fileName;
	
	public static void main(String args[]) throws IOException{
		
		in = new BufferedReader(new InputStreamReader(System.in));
		if (args.length<1){
		
			System.out.println("Enter path to .CSV file: ");
			fileName = in.readLine();
		} else
		{
			fileName = args[0];
		}	
		
		try {
			CSVFile csv = new CSVFile(fileName);
		
		
			csv.print();
			
			int maxcol = csv.getColsCount();
			
			int sortCol = 1;
			String res =in.readLine();
			
		
			if (res.trim().length() != 0){
				sortCol = Integer.parseInt(res);
				if ((sortCol<1) || (sortCol >maxcol)){
					System.out.println("Incorect column number");
					System.exit(0);
				}
			}
			
			String sortOrder = "1";
			System.out.println("Select sorting order");
			System.out.println("1. ASC");
			System.out.println("2. DESC");
			System.out.print("[1]:");
			sortOrder = in.readLine();
			if (sortOrder == "2")
				csv.setSortOrder(csv.SortDESC);
			
			csv.sortByCol(sortCol-1); 
			csv.save();
			System.out.println("Sorted and saved to file");
			System.out.println("Would you like to see result before exit? (yes/no)[no]");
			if (in.readLine().trim().equals("yes"))
				csv.print();
		} catch(IOException e) {
			System.out.println("File doesn't exist");
			System.exit(0);
		}
	}
}
