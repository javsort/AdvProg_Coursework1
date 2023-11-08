import java.util.*;

public class PathFinder {

    // Both path lists to store the path being currently stored, and the one saved as the shortest one
    private ArrayList<MazeSquare> currentPath = new ArrayList<>();
    private ArrayList<MazeSquare> shortestPath = new ArrayList<>();

    // MazeSquare objects used in the class
    public MazeSquare current;                                      // Current MazeSquare being analized
    public MazeSquare source;                                       // Saved into a PathFinder object as source MazeSquare
    public MazeSquare target;                                       // Saved into a Pathfinder object as target MazeSquare

    // String to print path
    private String finalPath = "Go through: ";

    // Board to be saved into a PathFinder object
    private GameBoard board;

    /* PathFinder Constructor. This object is created to help the rest of the classes find the shortest path 
    based on a DFS algorithm saving values such as a source MazeSquare, a target MazeSquare, distance and Board. */
    public PathFinder(MazeSquare s, MazeSquare t, GameBoard b){
        this.source = s;
        this.target = t;
        this.board = b;
        
    }

    /* Method called when there is a right click in the MazeSquare.java class, the method is called after creating a PathFinder object.
    It calls the recursive function, and then, it unscrolls the shortestPath list to increase the distance, and print the values into 
    the terminal through the addString() method. Also, on the ladder of the if statement, if there is no shortestPath at all, then there's no
    path available, so the terminal prints the lack of this and sets the target on false.
    */
    public void doSearch(){

        // Call recursive method
        findPath(source, currentPath);

        // After a successful call, the shortest path will be in the shortestPath list so,
        if(!shortestPath.isEmpty()){

            // Iterate the list to add to print string, and increase distance
            for(int i = 0; i < shortestPath.size(); i++){
                current = shortestPath.get(i);
                addString(current);

            }

            System.out.println("Distance to target from source: " + shortestPath.size() + ".");    // Print the distance
            System.out.println(getPath());                                                             // Print the final track path to be followed
            System.out.println("Shortest path has been found :).\n");

            // An unsuccessful call means there's no path available, so avoid everything else
        } else {
            System.out.println("There is no path available to get to (" + target.getXLocation() + ", " + target.getYLocation() + ") from (" + source.getXLocation() + ", " +   source.getYLocation() + "). :(\n");

        }
        
        // Reset the value as target
        target.target = false;
    }


    /*
     * Recursive method called to find the shortest path based on a DFS algorithm adaptation. Takes in as signature a currentPath (which
     * on first call should be empty),  and a MazeSquare "current" to move through all the map. The method checks for the squares through
     * the map and keeps iterating until it runs into a wall. Each of these directions traverse the entire map recursively.
     */
    public boolean findPath(MazeSquare current, ArrayList<MazeSquare> currentPath){
        // First, mark the node as visited and add to currentPath
		current.setVisited();
        currentPath.add(current);

		// Then, check if current square is the target
		if(current.target){

            // If there is no previous shortestPath or if the current one is shorter, clear the shortest path and add the 'new' shortest path
			if(shortestPath.isEmpty() || currentPath.size() < shortestPath.size()){
                shortestPath.clear();
                shortestPath.addAll(currentPath);
            }
            currentPath.remove(current);

			return true;
		}

        // UP direction
        if(current.getYLocation() <= 9 && !current.getWall(GameSquare.WALL_TOP)){
            int xTop = current.getXLocation();
            int yTop = current.getYLocation() - 1;

            // Set new selected square to temporary MazeSquare as 'aboveSq'
            MazeSquare aboveSq = (MazeSquare) board.getSquareAt(xTop,yTop);

            // if the aboveSq isn't in current path, call recursion, but now "current" will be aboveSq
            if(!currentPath.contains(aboveSq)){
                findPath(aboveSq, currentPath);
            }

        }

        // RIGHT direction
        if(current.getXLocation() <= 9 && !current.getWall(GameSquare.WALL_RIGHT)){
            int xTop = current.getXLocation() + 1;
            int yTop = current.getYLocation();

            // Set new selected square to temporary MazeSquare as 'rightSq'
            MazeSquare rightSq = (MazeSquare) board.getSquareAt(xTop,yTop);

            // if the rightSq isn't in current path, call recursion, but now "current" will be rightSq
            if(!currentPath.contains(rightSq)){
                findPath(rightSq, currentPath);
            }

        }

        // DOWN direction
        if(current.getYLocation() >= 0 && !current.getWall(GameSquare.WALL_BOTTOM)){
            int xTop = current.getXLocation();
            int yTop = current.getYLocation() + 1;

            // Set new selected square to temporary MazeSquare as 'downSq'
            MazeSquare downSq = (MazeSquare) board.getSquareAt(xTop,yTop);

            // if the downSq isn't in current path, call recursion, but now "current" will be downSq
            if(!currentPath.contains(downSq)){
                findPath(downSq, currentPath);
            }

        }

        // LEFT direction
        if(current.getXLocation() >= 0 && !current.getWall(GameSquare.WALL_LEFT)){
            int xTop = current.getXLocation() - 1;
            int yTop = current.getYLocation();

            // Set new selected square to temporary MazeSquare as 'leftSq'
            MazeSquare leftSq = (MazeSquare) board.getSquareAt(xTop,yTop);

            // if the leftSq isn't in current path, call recursion, but now "current" will be leftSq
            if(!currentPath.contains(leftSq)){
                findPath(leftSq, currentPath);
            }

        }

		// If no path found, return false and remove the current MazeSquare from currentPath
        currentPath.remove(current);
		return false;
    }

    // Method that returns the string with the final path to follow
    public String getPath(){
        finalPath = finalPath.substring(0, finalPath.length() - 2);
        finalPath = finalPath.concat(".");

        return finalPath;
    }
    
    // Method that adds the current makeString MazeSquare to edit the finalPath string by getting its coordinates
    public void addString(MazeSquare makeString){
        finalPath = finalPath.concat("(" + makeString.getXLocation() + ", " + makeString.getYLocation() + "), ");
    }
}


