package osricsystem.models;

import java.util.HashMap;
import java.util.LinkedList;

public class Sys{
	private String name, desc;
	private final LinkedList<Headmate> headmates;
	@SuppressWarnings("unused")
	private final HashMap<String, Sys> subsystems;

	public Sys(String name){
		this.name=name;
		headmates=new LinkedList<Headmate>();
		subsystems=new HashMap<String, Sys>();
	}

	// GETTERS \\
	public String getName(){ return name; }
	public String getDesc(){ return desc; }
	public Headmate[] getList(){ return headmates.toArray(new Headmate[headmates.size()]); }
	public String getListString(){
		StringBuilder sb=new StringBuilder();
		for(Headmate h : getList()) sb.append("\n"+h.toString());
		return sb.toString();
	}

	// SETTERS \\
	public void setName(String name){ this.name=name; }
	public void setDesc(String desc){ this.desc=desc; }

	// other
	public void add(Headmate h){ this.headmates.add(h); }
	public String toString(){
		StringBuilder sb=new StringBuilder();

		sb.append("System Name: "+ (name!=null? getName(): ""));
		sb.append("\nSystem Bio: "+ (desc!=null? getDesc(): ""));
		sb.append("\nHeadmate List:"+getListString());

		return sb.toString();
	}
}
