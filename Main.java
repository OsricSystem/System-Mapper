import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main{
	File f;
	
	public static void main(String[] args){
		try{
			f = new File("data.dat");
		}catch (IOException e){
			break;
		}
		
	}
	
	public static void write(String name, String... types){
		FileWriter w = new FileWriter("data.dat");
		if(types.length == 1 && types[0].euqals("newSystem")){
			w.write(String.format("systemname:%s\ndesc:\nheadmates:", name));
		}
		w.close();
	}
}
