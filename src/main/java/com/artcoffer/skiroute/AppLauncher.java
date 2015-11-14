package main.java.com.artcoffer.skiroute;

import java.util.Optional;

import main.java.com.artcoffer.skiroute.model.MountainFeature;
import main.java.com.artcoffer.skiroute.model.SkiLift;
import main.java.com.artcoffer.skiroute.model.SkiRun;

import com.artcoffer.utilities.graph.Edge;
import com.artcoffer.utilities.graph.Graph;
import com.artcoffer.utilities.graph.Vertex;
import com.artcoffer.utilities.graph.search.BreadthFirstVertexSearch;
import com.artcoffer.utilities.graph.search.Path;
import com.artcoffer.utilities.graph.search.PathSearch;

public class AppLauncher {
	
	public static void main(String[] args){
		Vertex<MountainFeature> mcConkeysBowl = new Vertex<MountainFeature>(new SkiRun("McConkey’s Bowl"));
		Vertex<MountainFeature> buckeye = new Vertex<MountainFeature>(new SkiRun("Buckeye"));
		Vertex<MountainFeature> woodside = new Vertex<MountainFeature>(new SkiRun("woodside"));
		Vertex<MountainFeature> mcConkeysExpress = new Vertex<MountainFeature>(new SkiLift("mcConkeysExpress"));
		
		Edge<MountainFeature> mcConkeysBowlToBuckeye = new Edge<>(mcConkeysBowl, buckeye);
		Edge<MountainFeature> buckeyeToWoodside = new Edge<>(buckeye, woodside);
		
		mcConkeysBowl.addEdge(mcConkeysBowlToBuckeye);
		buckeye.addEdge(buckeyeToWoodside);
		
		Graph<MountainFeature> canyonsRuns = new Graph<MountainFeature>();
		
		canyonsRuns.addVertex(mcConkeysBowl)
			.addVertex(buckeye)
			.addVertex(woodside)
			.addVertex(mcConkeysExpress);
		
		 canyonsRuns.addEdge(mcConkeysBowlToBuckeye).addEdge(buckeyeToWoodside);
		 
		 PathSearch<MountainFeature> search = new BreadthFirstVertexSearch<>();
		 Optional<Path<MountainFeature>> path = search.findFirst(canyonsRuns, mcConkeysBowl, woodside);
		 
		 System.out.println(canyonsRuns);
		 
		 if(path.isPresent()){
		 System.out.println(path.get().getPath());
		 }else{
			 System.out.println("No route found");
		 }
		
	}

}
