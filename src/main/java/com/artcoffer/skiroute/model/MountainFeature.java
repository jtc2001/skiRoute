package main.java.com.artcoffer.skiroute.model;

public interface MountainFeature {
	
	enum Feature{SKI_LIFT, SKI_RUN};
	
	public String getName();
	
	public String getType();
	
	static MountainFeature fromValue(String featureValue, String value){
		Feature type = Feature.valueOf(featureValue);
		if(type==Feature.SKI_RUN){
			return new SkiRun(value);
		}else if(type == Feature.SKI_LIFT){
			return new SkiLift(value);
		}
		
		return null;
	}

}
