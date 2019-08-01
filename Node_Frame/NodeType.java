package Node_Frame;

public enum NodeType {
	
	GRID("grid"),
	BARRIER("barrier"),
	WARNING("warning"),
	START("start"),
	GOAL("goal"),
	SEARCHING("searching"),
	STORED_PATH("stored_path"),
	PATH("path");
	
	String textureName;
	
	NodeType(String textureName){
		this.textureName = textureName;
	}
}