package main.java.com.artcoffer.skiroute.model;


/**
 * Represents the top of a ski run
 */
public class SkiRun implements MountainFeature {

	private String name;
	
	public SkiRun(String name){
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getType() {
		return "SkiRun";
	}
	
	@Override
	public String toString(){
		return this.name;
	}
	
	@Override
	public int hashCode(){
		return this.name.hashCode();
	}
	
	@Override
	public boolean equals(Object other){
		if((other instanceof SkiRun)){
			if ( ((SkiRun)other).name.equals(this.name)){
	            return true;
	        }
			return false;
		}
		return false;
		
	}

}
