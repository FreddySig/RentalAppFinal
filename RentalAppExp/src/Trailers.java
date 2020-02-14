public class Trailers {
	private int trailerId;
	private String type;
	private String trailerStatus;
	
	public void Trailer(int trailerId, String type, String trailerStatus) {
		this.trailerId = trailerId;
		this.type = type;
		this.trailerStatus = trailerStatus;
	}

	public int getTrailerId() {
		return trailerId;
	}

	public String getType() {
		return type;
	}

	public String getTrailerStatus() {
		return trailerStatus;
	}

	
	
}
