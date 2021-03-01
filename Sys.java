public class Sys{
	String name;
	String desc;
	Headmate[] list;
	
	public Sys(String name){
		this.name = name;
		write(this.name, "newSystem");
	}
	
	private void write(String name, String... types){Main.write(name, types);}
}
