package main.java.com.artcoffer.skiroute;

import java.io.File;
import java.util.Optional;

import main.java.com.artcoffer.skiroute.model.FileGraphLoader;
import main.java.com.artcoffer.skiroute.model.MountainFeature;

import com.artcoffer.utilities.graph.Graph;
import com.artcoffer.utilities.graph.Vertex;
import com.artcoffer.utilities.graph.search.BreadthFirstVertexSearch;
import com.artcoffer.utilities.graph.search.Path;
import com.artcoffer.utilities.graph.search.PathSearch;

public class AppLauncher {
	
	public static void main(String[] args){
		 Graph<MountainFeature> mountain = FileGraphLoader.loadGraphFromFile(new File(args[0]));
		 
		 PathSearch<MountainFeature> search = new BreadthFirstVertexSearch<>();
			Optional<Vertex<MountainFeature>> mcConkeys = mountain.getVerticies().stream().filter(v->v.getValue().getName().equalsIgnoreCase("mcconkeys bowl")).findFirst();
			Optional<Vertex<MountainFeature>> woodside = mountain.getVerticies().stream().filter(v->v.getValue().getName().equalsIgnoreCase("woodside")).findFirst();
			if(woodside.isPresent() && mcConkeys.isPresent()){
				
				Optional<Path<MountainFeature>> path = search.findFirst(mountain, mcConkeys.get(), woodside.get());
				if(path.isPresent()){
					System.out.println(path.get().getPath());
				}
			}
		
	}

}
