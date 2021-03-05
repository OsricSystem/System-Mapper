package osricsystem.models.pluralkit;

import java.util.Date;

public class PKSystem{
	public String version, id, name, description, tag, avatar_url, timezone;
	public PKMember[] members;
	public PKSwitch[] switches;
	public long[] accounts;
	public Date created;

	public PKSystem(){}
}
