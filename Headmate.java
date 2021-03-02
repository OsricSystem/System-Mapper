package tester;

public class Headmate{
	private String name, pronouns, subsystem, gender, age, desc;
	private String[] jobs;

	public Headmate(String name){ this.name=name; }

	// GETTERS \\
	public String getName(){ return name; }
	public String getPronouns(){ return pronouns; }
	public String getSubsystem(){ return subsystem; }
	public String getGender(){ return gender; }
	public String getAge(){ return age; }
	public String getDesc(){ return desc; }
	public String[] getJobs(){ return jobs; }

	// SETTERS \\
	public void setName(String name){ this.name=name; }
	public void setPronouns(String pronouns){ this.pronouns=pronouns; }
	public void setSubsystem(String subsystem){ this.subsystem=subsystem; }
	public void setGender(String gender){ this.gender=gender; }
	public void setAge(String age){ this.age=age; }
	public void setDesc(String desc){ this.desc=desc; }
	public void setJobs(String... jobs){ this.jobs=jobs; }
	public void addJobs(String... jobs){ for(String job : jobs) addJob(job); }
	public void addJob(String job){
		String[] jobs=new String[this.jobs.length+1];
		for(int i=0; i<this.jobs.length; i++) jobs[i]=this.jobs[i];
		jobs[jobs.length-1]=job;
		this.jobs=jobs;
	}

	// CLEAR \\
	public void clearName(String name){ this.name=null; }
	public void clearPronouns(String pronouns){ this.pronouns=null; }
	public void clearSubsystem(String subsystem){ this.subsystem=null; }
	public void clearGender(String gender){ this.gender=null; }
	public void clearAge(String age){ this.age=null; }
	public void clearDesc(String desc){ this.desc=null; }
	public void clearJobs(String... jobs){ this.jobs=null; }

	// other
	public String toString(){
		String out="";

		out+="Name: "+ (name!=null? getName(): "");
		out+="\nPronouns: "+ (pronouns!=null? getPronouns(): "");
		out+="\nSubsystem: "+ (subsystem!=null? getSubsystem(): "");
		out+="\nGender: "+ (gender!=null? getGender(): "");
		out+="\nAge: "+ (age!=null? getAge(): "");
		out+="\nBio: "+ (desc!=null? getDesc(): "");

		return out;
	}
}
