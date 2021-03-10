package osricsystem;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import osricsystem.converters.JsonConverter;
import osricsystem.converters.PKConverter;
import osricsystem.models.Headmate;
import osricsystem.models.Sys;
import osricsystem.models.pluralkit.PKSystem;

public class Main{
	private static final Scanner in=new Scanner(System.in);

	private static final File datFile=Paths.get("temp", "data.dat").toAbsolutePath().toFile();
	private static final File jsonFile=Paths.get("temp", "data.json").toAbsolutePath().toFile();
	@SuppressWarnings("unused")
	private static final Path dataPackTemplateDir=Paths.get("MC-Plural-Proxy").toAbsolutePath();
	private static final Path dataPackOutputDir=Paths.get("output").toAbsolutePath();
	private static Sys system;
	private static Headmate dummy;

	public static void main(String[] args){
		try{
			// pull existing PK file if present (and app output not found)
			Path exportedJsonFile=Paths.get("temp", "system.json");
			if(!jsonFile.exists() && exportedJsonFile.toFile().exists()){
				System.out.println("Copying exported json file");
				Files.copy(exportedJsonFile, jsonFile.toPath());
			}

			// initialize the system
			if(jsonFile.exists() && jsonFile.length()>0) loadSystemJson();
			else{
				createNewSystem();
				if(system.getDesc()==null) system.setDesc("Sample description for "+system.getName());
				if(system.getHeadmates().length==0){
					Headmate h=new Headmate("Tayto");
					h.generateNewId();
					system.add(h);
				}
			}

			// dummy = new Headmate("Tayto");
			// dummy.generateNewId();
			// dummy.setAge("18");
			// dummy.setDesc("cat");
			// dummy.setGender("catgender");
			// dummy.setPronouns("Nya/Nyam");
			// system.add(dummy);

			// edit the system
			// updateSystem();

			// save and log the system file
			saveSystemDat();
			printSystemFile();
			saveSystemJson();

			// create the data pack
			// generateDataPack();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	// delete every file in a directory
	private static void clearDirectory(File root){
		Queue<File> queue=new LinkedList<File>();
		File[] files=root.listFiles();
		if(files!=null) for(File f : files) queue.add(f);
		while(!queue.isEmpty()){
			File dir=queue.poll();

			files=dir.listFiles();
			if(files!=null) for(File f : files){
				if(f.isDirectory()) queue.add(f);
				else f.delete();
			}

			dir.delete();
		}
	}

	// creates a new system and initializes some properties
	private static void createNewSystem(){
		System.out.println("Enter System Name:");
		system=new Sys(in.nextLine().strip());
	}

	// generate the data pack directory
	@SuppressWarnings("unused")
	private static void generateDataPack() throws IOException{
		// ensure output directory exists and is empty
		Files.createDirectories(dataPackOutputDir);
		clearDirectory(dataPackOutputDir.toFile());

		// TODO: copy/generate files
	}

	// read the system from a DAT file
	@SuppressWarnings("unused")
	private static void loadSystemDat(){
		try(Scanner reader=new Scanner(datFile)){
			while(reader.hasNextLine()){

				// reads in a line from data.dat, skipping if blank
				String line=reader.nextLine();
				int index=line.indexOf(':');
				if(index==-1) continue;
				String label=line.substring(0, index);
				String data=line.substring(index+1);

				// enters file data into program memory
				switch(label.trim()){
				case "System Name":
					system=new Sys(data);
					break;
				case "System Bio":
					if(data.contains("\\n")) data=data.replace("\\n", "\n");
					system.setDesc(data);
					break;
				case "System Id":
					system.setId(data);
					break;
				case "Display Name":
					dummy=new Headmate(data);
					system.add(dummy);
					break;
				case "Color":
					dummy.setColor(data);
					break;
				// case "Pronouns":
				// dummy.setPronouns(data);
				// break;
				// case "Subsystem":
				// // implement subsystem handler
				// break;
				// case "Gender":
				// dummy.setGender(data);
				// break;
				// case "Age":
				// dummy.setAge(data);
				// break;
				// case "Bio":
				// dummy.setDesc(data);
				// break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	// read the system from a JSON file
	private static void loadSystemJson() throws JsonMappingException, JsonProcessingException, IOException{
		PKSystem deserialized=JsonConverter.fromJson(Files.readString(jsonFile.toPath()), PKSystem.class);
		system=PKConverter.toNative(deserialized);
	}

	// log the contents of the DAT file
	private static void printSystemFile(){
		try(Scanner out=new Scanner(datFile)){
			while(out.hasNextLine()) System.out.println(out.nextLine());
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	// write the system to a file
	private static void saveSystemDat() throws IOException{
		// ensure output directory exists
		Files.createDirectories(datFile.getParentFile().toPath());

		// serialize and save
		Files.writeString(datFile.toPath(), system.toString());
	}

	// write the system to a file
	private static void saveSystemJson() throws JsonMappingException, JsonProcessingException, IOException{
		// ensure output directory exists
		Files.createDirectories(jsonFile.getParentFile().toPath());

		// serialize and save
		PKSystem forSerializing=PKConverter.fromNative(system);
		Files.writeString(jsonFile.toPath(), JsonConverter.toJson(forSerializing, true));
	}

	// update the system
	@SuppressWarnings("unused")
	private static void updateSystem(){
		// TODO: Prompt user for edits
	}
}
