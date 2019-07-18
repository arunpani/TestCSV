package com.test.csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CSVFile {
private List<String[]> records;
	
	//constants for sorting Order
	//1 for ASC, -1 for DESC
	final public int SortASC = 1;
	final public int SortDESC = -1;
	private int sortOrder = SortASC; 	
	String fileName;
	
	private int colsCount=0;
	
	public CSVFile(String file) throws IOException{
		records = new ArrayList<String[]>();
		fileName = file;
		try(BufferedReader in = new BufferedReader(new FileReader(file)))
		{
			String ln;
			while( (ln = in.readLine()) !=null) {
				colsCount = ln.split("\t").length;
				records.add(ln.split("\t"));
			}
		}
	}
	
	public void print(){
		for(String[] arr : records){
			for (String s:arr) {
				System.out.print(s+"\t");
			}
			System.out.println();
		}
	}
	
	public void save() throws IOException{
		try(BufferedWriter out = new BufferedWriter(new FileWriter(fileName)))
		{
			for(String[] arr : records){
				for (String s:arr) {
					out.write(s+"\t");
				}
				out.write("\n");
			}
		}
	}
	
	public void setSortOrder(int order){
		sortOrder = order;
	}
	
	public void sortByCol(final int i){
		
		
		Comparator<String[]> comp = new Comparator<String[]>(){
			public int compare(String[] a, String[] b){
				
				return sortOrder * a[i].compareTo(b[i]);
			}
		};
		
		Collections.sort(records, comp);
	}
	
	public int getColsCount(){
		return colsCount;
	}
}
