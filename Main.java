package tester;

import java.io.*;
import java.util.Scanner;

public class Main{
	static Sys system;
	static File file;
	static Scanner f;
	static Scanner in;

	public static void main(String[] args){
		in=new Scanner(System.in);
		try{
			file=new File("C:\\Users\\Morgan\\Desktop\\SystemMapper\\SystemMapper\\src\\tester\\data.dat");
			f=new Scanner(file);

			if(file.length()==0) init();
			else setup();
			system.add(new Headmate("Tayto"));
			//edit();

			f.close();
		}catch(Exception e){
			e.printStackTrace();
		}

		end();
	}
	public static void init(){
		System.out.println("Enter System Name:");
		system=new Sys(in.nextLine());
		try{
			FileWriter w=new FileWriter(file.getAbsolutePath());
			w.write("System Name:"+system.getName());
			w.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public static void setup() {
		try{
			Scanner reader=new Scanner(file.getAbsolutePath());
			while(reader.hasNextLine()) {
				String line = reader.nextLine();
				String label = line.split(":")[0];
				String data = line.split(":")[1];
				if(label.equals("System Name")) system = new Sys(data);
			}
			reader.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void edit(){
		System.out.println("Entering Edit Mode\ntype 'EXIT' to quit");
		while(true){
			String inp=in.nextLine();
			if(inp.equals("EXIT")) break;
		}
	}
	public static void end(){
		try{
			File end=new File("C:\\Users\\Morgan\\Desktop\\SystemMapper\\SystemMapper\\src\\tester\\data.dat");
			Scanner out=new Scanner(end);
			while(out.hasNextLine()) System.out.println(out.nextLine());
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/*public static void write(String name, String... types){
		try{
			FileWriter w=new FileWriter("data.dat");
			if(types.length==1 && types[0].equals("newSystem")){
				w.write(String.format("systemname:%s\ndesc:\nheadmates:", name));
			}
			w.close();
		}catch(IOException e){
	
		}
	}*/
}
