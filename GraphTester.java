
/**
 *GraphTester tests the methods in UndirectedGraph
 * @author Alan Yao
 */
import java.util.ArrayDeque;
import java.util.Queue;

public class GraphTester {


	/**
	 * Returns true if a path exists on graph, from startVertex to endVertex; 
	 * otherwise returns false. Uses breadth-first search algorithm.
	 * @param graph
	 * @param startVertex
	 * @param endVertex
	 * @return
	 */
	private static	 boolean isPathBF(GraphInterface<String> graph,
			String startVertex, 
			String endVertex    ) {

		Queue<String> queue = new ArrayDeque<String>();
		Queue<String> vertexQueue = new ArrayDeque<String>();

		boolean found = false;
		String currVertex;      // vertex being processed
		String adjVertex;       // adjacent to currVertex

		graph.clearMarks();
		graph.markVertex(startVertex);
		queue.add(startVertex);

		do
		{
			currVertex = queue.remove();
			// System.out.println(currVertex);
			if (currVertex.equals(endVertex))
				found = true;
			else
			{
				vertexQueue = graph.getToVertices(currVertex); 
				while (!vertexQueue.isEmpty())
				{
					adjVertex = vertexQueue.remove();
					if (!graph.isMarked(adjVertex))
					{
						graph.markVertex(adjVertex);
						queue.add(adjVertex);
					}
				}
			}
		} while (!queue.isEmpty() && !found);

		return found;
	}


	public static void main(String[] args) {

		UndirectedGraph<String> graph = new UndirectedGraph<>();
		//adding all vertices and edges
		graph.addVertex("Bob");
		graph.addVertex("Cynthia");
		graph.addVertex("Alex");
		graph.addVertex("Kevin");
		graph.addVertex("Cathy");
		graph.addVertex("Kevin");
		graph.addVertex("Colleen");
		graph.addVertex("George");
		graph.addVertex("Jack");
		graph.addEdge("Bob", "Jack");
		graph.addEdge("Bob", "Cynthia");
		graph.addEdge("Bob", "Alex");
		graph.addEdge("Cynthia", "Bob");
		graph.addEdge("Cynthia", "Alex");
		graph.addEdge("Alex", "Cynthia");
		graph.addEdge("Alex", "Cathy");
		graph.addEdge("Alex", "Bob");
		graph.addEdge("Kevin","Colleen");
		graph.addEdge("Kevin","George");

		//testing everything
		Queue<String> vertice1  = graph.getToVertices("Bob");
		System.out.println("Bob's friends are " + vertice1);
		Queue<String> vertice2  = graph.getToVertices("Alex");
		System.out.println("Alex's friends are " + vertice2);
		Queue<String> vertice3  = graph.getToVertices("George");
		System.out.println("George's friends are" + vertice3);
		System.out.println("Are Jack and Cathy indirect friends?: "+isPathBF(graph,"Jack","Cathy"));
		System.out.println("Are Jack and George indirect friends?: " + isPathBF(graph,"Jack","George"));
	}

}