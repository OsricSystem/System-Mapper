package osricsystem.models.pluralkit;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import osricsystem.models.Headmate;

public class PKMember{
    private String name;

    public PKMember(){}
    public static PKMember fromNative(Headmate headmate){
        PKMember result=new PKMember();

        result.setName(headmate.getName());

        return result;
    }

    // GETTERS \\
    @JsonGetter
    public String getName(){ return this.name; }

    // SETTERS \\
    @JsonSetter
    public void setName(String name){ this.name=name; }
}
