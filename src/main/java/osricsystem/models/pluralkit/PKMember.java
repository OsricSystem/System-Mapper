package osricsystem.models.pluralkit;

import java.util.Date;

public class PKMember{
	public String id, name, display_name, description;
	public String birthday; // TODO: Is a string?
	public String pronouns, color, avatar_url;
	public PKProxyTag[] proxy_tags;
	public boolean keep_proxy;
	public long message_count;
	public Date created;

	public PKMember(){}
}
