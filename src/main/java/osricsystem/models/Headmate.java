package osricsystem.models;

public class Headmate{
	private static int nextId=0;
	private String color, displayName, id;

	public Headmate(String displayName){ this.displayName=displayName; }

	// GETTERS \\
	public String getColor(){ return this.color; }
	public String getDisplayName(){ return displayName; }
	public String getId(){ return id; }

	// SETTERS \\
	public void setColor(String color){ this.color=color; }
	public void setDisplayName(String displayName){ this.displayName=displayName; }
	public void setId(String id){ this.id=id; }

	// other
	public void generateNewId(){ this.id=String.format("%05d", nextId++); }
	public String toString(){
		StringBuilder sb=new StringBuilder();

		sb.append("\n\tId: "+ (id!=null? getId(): ""));
		sb.append("\n\tDisplay Name: "+ (displayName!=null? getDisplayName(): ""));
		// sb.append("\n\tPronouns:"+ (pronouns!=null? getPronouns(): ""));
		// sb.append("\n\tSubsystem:"+ (subsystem!=null? getSubsystem(): ""));
		// sb.append("\n\tGender:"+ (gender!=null? getGender(): ""));
		sb.append("\n\tColor: "+ (color!=null? getColor(): ""));
		// sb.append("\n\tAge:"+ (age!=null? getAge(): ""));
		// sb.append("\n\tBio:"+ (desc!=null? getDesc(): ""));

		return sb.toString();
	}
}
