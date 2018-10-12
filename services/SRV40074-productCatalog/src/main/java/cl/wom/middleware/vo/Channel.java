package cl.wom.middleware.vo;

public class Channel {
	private String legacySystem;
	private String Name;
	public Channel() {
		super();
	}
	public Channel(String legacySystem, String name) {
		super();
		this.legacySystem = legacySystem;
		Name = name;
	}
	public String getLegacySystem() {
		return legacySystem;
	}
	public void setLegacySystem(String legacySystem) {
		this.legacySystem = legacySystem;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	@Override
	public String toString() {
		return "Channel [legacySystem=" + legacySystem + ", Name=" + Name + "]";
	}

}
