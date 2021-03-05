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

	public static void main(String[] args){
		try{
			// pull existing PK file if present (and app output not found)
			Path exportedJsonFile=Paths.get("temp", "system.json");
			if(!jsonFile.exists() && exportedJsonFile.toFile().exists()){
				System.out.println("Copying exported json file");
				// Files.copy(exportedJsonFile, jsonFileInputFile.toPath());
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

	// read the system from a file
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
