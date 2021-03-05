package osricsystem.models;

import java.util.LinkedList;

public class Sys{
	private static int nextId=0;
	private String desc, icon, id, name, tag;
	private final LinkedList<Headmate> headmates;

	public Sys(String name){
		this.name=name;
		headmates=new LinkedList<Headmate>();
	}

	// GETTERS \\
	public String getDesc(){ return desc; }
	public String getIcon(){ return icon; }
	public String getId(){ return id; }
	public String getName(){ return name; }
	public String getTag(){ return tag; }
	public Headmate[] getHeadmates(){ return headmates.toArray(new Headmate[headmates.size()]); }
	public String getHeadmatesString(){
		StringBuilder sb=new StringBuilder();
		for(Headmate h : getHeadmates()) sb.append("\n"+h.toString());
		return sb.toString();
	}

	// SETTERS \\
	public void setDesc(String desc){ this.desc=desc; }
	public void setIcon(String icon){ this.icon=icon; }
	public void setId(String id){ this.id=id; }
	public void setName(String name){ this.name=name; }
	public void setTag(String tag){ this.tag=tag; }

	// other
	public void add(Headmate h){ this.headmates.add(h); }
	public void generateNewId(){ this.id=String.format("%05d", nextId++); }
	public String toString(){
		StringBuilder sb=new StringBuilder();

		sb.append("System Name: "+ (name!=null? getName(): ""));
		sb.append("\nSystem Bio: "+ (desc!=null? getDesc(): ""));
		sb.append("\nSystem Id: "+ (id!=null? getId(): ""));
		sb.append("\nSystem Icon: "+ (icon!=null? getIcon(): ""));
		sb.append("\nSystem Tag: "+ (tag!=null? getTag(): ""));
		sb.append("\nHeadmate List:"+getHeadmatesString());

		return sb.toString();
	}
}
