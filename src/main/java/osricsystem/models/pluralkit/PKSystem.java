package osricsystem.models.pluralkit;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import osricsystem.models.Headmate;
import osricsystem.models.Sys;

// TODO: Finish implementing models
// https://pluralkit.me/api/#models

public class PKSystem{
	private String name, description;
	private PKMember[] members;

	public PKSystem(){}
	public static PKSystem fromNative(Sys sys){
		PKSystem result=new PKSystem();

		result.setName(sys.getName());
		result.setDescription(sys.getDesc());

		Headmate[] headmates=sys.getList();
		PKMember[] members=new PKMember[headmates.length];
		for(int i=0; i<headmates.length; i++) members[i]=PKMember.fromNative(headmates[i]);
		result.setMembers(members);

		return result;
	}

	// GETTERS \\
	@JsonGetter
	public String getName(){ return this.name; }
	@JsonGetter
	public String getDescription(){ return this.description; }
	@JsonGetter
	public PKMember[] getMembers(){ return this.members; }

	// SETTERS \\
	@JsonSetter
	public void setName(String name){ this.name=name; }
	@JsonSetter
	public void setDescription(String description){ this.description=description; }
	@JsonSetter
	public void setMembers(PKMember[] members){ this.members=members; }
}
