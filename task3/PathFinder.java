import java.util.*;

public class PathFinder {

    // List to store the path being currently traversed
    private ArrayList<MazeSquare> currentPath = new ArrayList<>();

    // ArrayLists of ArrayLists containing lists of MazeSquares to store all paths found
    private ArrayList<ArrayList<MazeSquare>> allPaths = new ArrayList<>();
    

    // MazeSquare objects used in the class
    public MazeSquare current;                                      // Current MazeSquare being analized
    public MazeSquare source;                                       // Saved into a PathFinder object as source MazeSquare
    public MazeSquare target;                                       // Saved into a Pathfinder object as target MazeSquare

    // Starting prompt for the path to print
    private String finalPath = "Go through (x,y): ";

    // Board to be saved into a PathFinder object
    private GameBoard board;

    /* PathFinder Constructor. This object is created to help the rest of the classes find the shortest path 
    based on a DFS algorithm saving values such as a source MazeSquare, a target MazeSquare, and Board. */
    public PathFinder(MazeSquare s, MazeSquare t, GameBoard b){
        this.source = s;
        this.target = t;
        this.board = b;
        
    }

    /* 
    * Method that calls the recursive method findPath() to find the shortest paths from source to target. 
    * It also prints the multiple paths to follow and highlights the first path found.
    */
    public void doSearch(){
        // Call recursive method
        System.out.println("Searching for paths... (May take a while)\n");
        findPath(source, currentPath);

        // After a successful call, the shortest paths will be in the allPaths list so,
        if(!allPaths.isEmpty()){
            // Print all the shortest paths found with their distance
            System.out.println("All paths found: " + allPaths.size() + ", with distance: " + allPaths.get(0).size() + ".");
            System.out.println("Highlighting first path...\n");

            // Iterate the maze to set highlight, add to print string and print full string
            for(int i = 0; i < allPaths.size(); i++){
                ArrayList<MazeSquare> currentList = allPaths.get(i);
                String thisPath = finalPath;

                for(int j = 0; j < currentList.size(); j++){
                    current = currentList.get(j);

                    // Only highlight the first path, so check for the first currentlist
                    if(currentList.equals(allPaths.get(0))){
                        current.setHighlight(true);
                    }

                    thisPath = addString(current, thisPath);

                }
                System.out.println("Path #" + (i+1));               // Print the path number
                System.out.println(getPath(thisPath));              // Get and print the final path
            }
            
            System.out.println("All shortest paths have been printed :).\n");

            // An unsuccessful call means there's no path available, so avoid everything else
        } else {
            System.out.println("There is no path available to get to (" + target.getXLocation() + ", " + target.getYLocation() + ") from (" + source.getXLocation() + ", " +   source.getYLocation() + "). :(\n");

        }
        
        // Reset the value as target
        target.target = false;
    }


    /*
     * Recursive method called to find the shortest paths based on a DFS algorithm adaptation. Takes in as signature a currentPath (which
     * on first call should be empty),  and a MazeSquare "current" to move through all the map. The method checks for the squares through
     * the map and keeps iterating until it runs into a wall. Each of these directions traverse the entire map recursively.
     */
    public void findPath(MazeSquare current, ArrayList<MazeSquare> currentPath){
        // First, mark the node as visited and add to currentPath
		current.setVisited();
        currentPath.add(current);

		// Then, check if current square is the target
		if(current.target){
            // If there are no previous shortest paths or if the current one is shorter, clear the allPaths list and add the 'new' shortest path as the first one
			if(allPaths.isEmpty() || currentPath.size() < allPaths.get(0).size()){
                allPaths.clear();
                allPaths.add(new ArrayList<>(currentPath));

            // If the current path is the same size as the first shortest path, add it to the list
            } else if(currentPath.size() == allPaths.get(0).size()){
                allPaths.add(new ArrayList<>(currentPath));

            }
		}

        // Check for squares ABOVE
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

        // Check for squares on the RIGHT
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

        // Check for squares BELOW
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

        // Check for squares on the LEFT
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

		// If no path found, remove the current MazeSquare from currentPath
        currentPath.remove(current);
    }

    // Method that returns the string with the final path to follow and corrects it for presentation
    public String getPath(String pathToCut){
        pathToCut = pathToCut.substring(0, pathToCut.length() - 2);
        pathToCut = pathToCut.concat(".\n");

        return pathToCut;
    }
    
    // Method that adds the current makeString MazeSquare with its previous path to edit the finalPath string by getting its coordinates
    public String addString(MazeSquare makeString, String currentString){
        currentString = currentString.concat("(" + makeString.getXLocation() + ", " + makeString.getYLocation() + "), ");
        return currentString;
    }
}

