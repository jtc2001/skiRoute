package main.java.com.artcoffer.skiroute.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.artcoffer.utilities.graph.Edge;
import com.artcoffer.utilities.graph.Graph;
import com.artcoffer.utilities.graph.Vertex;
import com.artcoffer.utilities.graph.search.BreadthFirstVertexSearch;
import com.artcoffer.utilities.graph.search.Path;
import com.artcoffer.utilities.graph.search.PathSearch;

public class GraphLoader {
	public static void main(String[] args) throws IOException{
		
		Graph<MountainFeature> mountain = new Graph<MountainFeature>();
		Map<Vertex<MountainFeature>, Vertex<MountainFeature>> mountainFeatures = new HashMap<>();
		try(Stream<String> lines = Files.lines(Paths.get("/Users/john/Documents/graphEntries.txt"))){
			List<String[]> features = lines.map((s -> s.split("->"))).collect(Collectors.toList());
			
			for(String[] mountainFeature : features){
				String[] fromVertexVal = mountainFeature[0].split(":");
				String[] toVertexVal = mountainFeature[1].split(":");
				
				MountainFeature fromFeatureType = MountainFeature.fromValue(fromVertexVal[0], fromVertexVal[1]);
				MountainFeature toFeatureType = MountainFeature.fromValue(toVertexVal[0], toVertexVal[1]);
				Vertex<MountainFeature> fromVertex = new Vertex<MountainFeature>(fromFeatureType);
				Vertex<MountainFeature> toVertex = new Vertex<MountainFeature>(toFeatureType);
				
				if(mountainFeatures.get(fromVertex) == null){
					mountainFeatures.put(fromVertex, fromVertex);
				}else{
					fromVertex = mountainFeatures.get(fromVertex);
				}
				
				if(mountainFeatures.get(toVertex) == null){
					mountainFeatures.put(toVertex, toVertex);
				}else{
					toVertex = mountainFeatures.get(toVertex);
				}
				
				Edge<MountainFeature> edge = new Edge<>(fromVertex, toVertex);
				
				mountain.addVertex(fromVertex);
				mountain.addVertex(toVertex);
				fromVertex.addEdge(edge);
				mountain.addEdge(edge);
				
			}
			
		}
		
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
