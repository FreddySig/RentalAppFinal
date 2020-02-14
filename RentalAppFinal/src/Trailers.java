import java.io.Serializable;

public class Trailers implements Serializable {
	private static final long serialVersionUID = 1L;
	private int trailerId;
	private TrailerType type;
	private String trailerStatus;
	
	public void Trailer(int trailerId, TrailerType type, String trailerStatus) {
		this.trailerId = trailerId;
		this.type = type;
		this.trailerStatus = trailerStatus;
	}

	public int getTrailerId() {
		return trailerId;
	}

	public TrailerType getType() {
		return type;
	}

	public String getTrailerStatus() {
		return trailerStatus;
	}

	
	
}
