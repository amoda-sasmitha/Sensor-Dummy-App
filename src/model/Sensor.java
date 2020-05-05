package model;

public class Sensor {

	private int id;
	private int smoke_level;
	private int co2_level;
	
	public Sensor() {
		this.id = 0;
		this.smoke_level = 0;
		this.co2_level = 0;
	}

	public Sensor(int id, int smoke_level, int co2_level) {
		this.id = id;
		this.smoke_level = smoke_level;
		this.co2_level = co2_level;
	}
	
	public Sensor(int id) {
		this.id = id;
		this.smoke_level = 0;
		this.co2_level = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSmoke_level() {
		return smoke_level;
	}

	public void setSmoke_level(int smoke_level) {
		this.smoke_level = smoke_level;
	}

	public int getCo2_level() {
		return co2_level;
	}

	public void setCo2_level(int co2_level) {
		this.co2_level = co2_level;
	}
	

}
