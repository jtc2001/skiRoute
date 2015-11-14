package main.java.com.artcoffer.skiroute.model;

public class SkiLift implements MountainFeature {

	private String NAME;
	
	public SkiLift(String name){
		this.NAME = name;
	}
	
	@Override
	public String getName() {
		 return NAME;
	}

	@Override
	public String getType() {
		return "SkiLift";
	}
	
	@Override
	public String toString(){
		return this.NAME;
	}

}
