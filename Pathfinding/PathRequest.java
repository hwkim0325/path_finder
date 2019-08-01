package Pathfinding;

public class PathRequest {

	public WayPoints pathStart;
	public WayPoints pathEnd;
	public boolean success;

	public PathRequest() {}
		
	public PathRequest(WayPoints pathStart, WayPoints pathEnd) {
		this.pathStart = pathStart;
		this.pathEnd = pathEnd;
	}
}
