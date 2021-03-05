package osricsystem.converters;

import osricsystem.models.Headmate;
import osricsystem.models.Sys;
import osricsystem.models.pluralkit.PKMember;
import osricsystem.models.pluralkit.PKSystem;

public class PKConverter{
    private PKConverter(){}

    // Native to PK
    public static PKSystem fromNative(Sys sys){
        PKSystem result=new PKSystem();

        result.avatar_url=sys.getIcon();
        result.description=sys.getDesc();
        result.id=sys.getId();
        result.name=sys.getName();
        result.tag=sys.getTag();

        Headmate[] headmates=sys.getHeadmates();
        PKMember[] members=new PKMember[headmates.length];
        for(int i=0; i<headmates.length; i++) members[i]=fromNative(headmates[i]);
        result.members=members;

        return result;
    }
    private static PKMember fromNative(Headmate headmate){
        PKMember result=new PKMember();

        result.color=headmate.getColor();
        result.id=headmate.getId();
        result.name=headmate.getDisplayName();

        return result;
    }

    // PK to native
    public static Sys toNative(PKSystem system){
        Sys result=new Sys(system.name);

        result.setDesc(system.description);
        result.setIcon(system.avatar_url);
        result.setId(system.id);
        result.setTag(system.tag);

        for(PKMember member : system.members) result.add(toNative(member));

        return result;
    }
    private static Headmate toNative(PKMember member){
        Headmate result=new Headmate(
                member.display_name!=null && member.display_name.length()>0? member.display_name: member.name);

        result.setColor(member.color);
        result.setId(member.id);

        return result;
    }
}
