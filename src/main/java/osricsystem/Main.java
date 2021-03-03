package osricsystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;

import osricsystem.models.Headmate;
import osricsystem.models.Sys;
import osricsystem.models.pluralkit.PKSystem;

public class Main{
	private static Scanner in;
	private static File file;
	private static Sys system;

	public static void main(String[] args){
		in=new Scanner(System.in);
		file=new File("data.dat").getAbsoluteFile();

		try{
			if(!file.exists() || file.length()==0) init();
			else setup();

			system.add(new Headmate("Tayto"));
			// edit();

			// XML formatter
			ObjectMapper mapper=new ObjectMapper();
			DefaultIndenter indenter=new DefaultIndenter("\t", DefaultIndenter.SYS_LF);
			DefaultPrettyPrinter printer=new DefaultPrettyPrinter();
			printer.indentObjectsWith(indenter);
			printer.indentArraysWith(indenter);

			// interacts with PK API and handles JSON
			PKSystem pkSystem=PKSystem.fromNative(system);
			File jsonFile=new File("data.json").getAbsoluteFile();
			mapper.writer(printer).writeValue(jsonFile, pkSystem);
			PKSystem deserialized=mapper.readValue(jsonFile, PKSystem.class);
			if(deserialized==null) throw new NullPointerException("Unable to deserialize as JSON");
		}catch(Exception e){
			e.printStackTrace();
		}

		end();
	}

	// initalises storage files if they do not exist or are empty
	private static void init(){
		System.out.println("Enter System Name:");
		system=new Sys(in.nextLine());
	}

	// reads system data into program memory
	private static void setup(){
		try(Scanner reader=new Scanner(file)){
			while(reader.hasNextLine()){
				String line=reader.nextLine();
				int index=line.indexOf(':');
				String label=line.substring(0, index);
				String data=line.substring(index+1);
				switch(label){
				case "System Name":
					system=new Sys(data);
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	// main way end user interacts with program
	@SuppressWarnings("unused")
	private static void edit(){
		System.out.println("Entering Edit Mode\ntype 'EXIT' to quit");
		while(true){
			String inp=in.nextLine();
			if(inp.equals("EXIT")) break;
		}
	}

	// end of program handler
	private static void end(){
		if(!file.exists()){
			System.out.println("Unable to read generated file");
			return;
		}

		store();

		try(Scanner out=new Scanner(file)){
			while(out.hasNextLine()) System.out.println(out.nextLine());
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	// potentially used in the future to handle writing to files
	private static void store(){
		clear();
		try(FileWriter w=new FileWriter(file.getPath())){
			w.write(system.toString());
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	// clears files to have a clean start, useful for debugging
	private static void clear(){
		try{
			new FileWriter(file.getAbsoluteFile(), false).close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
