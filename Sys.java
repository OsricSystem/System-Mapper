package tester;

public class Sys{
	private String name, desc;
	private Headmate[] list;

	public Sys(String name){
		this.name=name;
		list = new Headmate[0];
	}

	// GETTERS \\
	public String getName(){ return name; }
	public String getDesc(){ return desc; }
	public Headmate[] getList(){ return list; }
	public String getListString(){
		String out="";
		for(Headmate h : getList()) out+="\n"+h.toString();
		return out;
	}

	// SETTERS \\
	public void setName(String name){ this.name=name; }
	public void setDesc(String desc){ this.desc=desc; }

	// other
	public void add(Headmate h){
		Headmate[] list=new Headmate[this.list.length+1];
		for(int i=0; i<this.list.length; i++) list[i]=this.list[i];
		list[list.length-1]=h;
		this.list=list;
	}
	public String toString(){
		String out="";

		out+="System Name: "+ (name!=null? getName(): "");
		out+="\nSystem Bio: "+ (desc!=null? getDesc(): "");
		out+="\nHeadmate List:"+ (list!=null? getListString(): "");

		return out;
	}
}
