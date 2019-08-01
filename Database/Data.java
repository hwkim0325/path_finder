package Database;

import java.util.Date;

public class Data {
	private String startPoint;
	private String endPoint;
	private String distance;
	private String path;

	private Date searchDate;
	private String id;
	private String password;
	
	
	
	public Data(String startPoint, String endPoint, String distance,
				String path, Date searchDate, String id) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.distance = distance;
		this.path = path;
		this.searchDate = searchDate;
		this.id = id;		
	}
	
	public Data(String id, String password) {
		this.id = id;
		this.password = password;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(String startPoint) {
		this.startPoint = startPoint;
	}
	public String getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public Date getSearchDate() {
		return searchDate;
	}
	public void setSearchDate(Date searchDate) {
		this.searchDate = searchDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
