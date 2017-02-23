public class Search{
	/*
	public static void main(String[] args){
		//create 50 gridworlds

		GridWorld[] grids = new GridWorld[50];
		for(int i = 0; i < 50; i++){
			grids[i] = new GridWorld();
			// System.out.println(i);
		}

		System.out.println("Comparing Repeated Forward A* with ties");
		//run repeated forward a * where small g favored & time
		//run repeated forward a * where large g favored & time
		System.out.println("Average time to find target in mazes:");
		System.out.println("Repeated Forward A* where small G favored: ");
		System.out.println("Repeated Forward A* where large G favored: ");
		System.out.println();
		System.out.println();
		System.out.println("Forward vs Backward Repeated A*");
		//run forward a * and time
		//run backward a* and time
		System.out.println("Average time to find target in mazes:");
		System.out.println("Repeated Forward A* : ");
		System.out.println("Repeated Backward A* : ");
		System.out.println();
		System.out.println();
		System.out.println("Repeated Forward vs Adaptive.");
		//run repeated forward a*
		//run adaptive a*
		System.out.println("Average time to find target in mazes:");
		System.out.println("Repeated Forward A* : ");
		System.out.println("Adaptive A* : ");
		System.out.println();
		System.out.println();


		//GridWorld temp = new GridWorld();
		return;
	}*/
  static GridWorld gw = new GridWorld();
  static BinaryHeap heap = new BinaryHeap();
  static int COST = 1; //currently
  static int MAXINDEX = gw.CAPACITY-1;

public static void traverseTree(Square goal, Square start){
  Square curr;
  curr = goal;
  while(curr && curr!=start){
  curr.tree.branch = curr;
  curr = curr.tree;
  }
}


public static Square traverseBranch(Grid ngw, Square start, Square goal){
  Square curr;
  curr = start;
  while(curr && curr!=goal){
    if(gw.grid[curr.branch.x][curr.branch.y].isBlocked){
      gw.grid[curr.branch.x][curr.branch.y].isBlocked = true;
    } 
  }
}

  public static void addFour(Square current,int counter, char ordering){
    int x = current.x;
    int y = current.y;
    int pg = 0;
    Square  right, up, down;

//full grid and current grid

//assume there is a main heap called heap

System.out.println("Starting addFour at indices " + current.x + "," + current.y);

    pg = current.g_value + COST;

    if(current.x!=0){
  //	System.out.println("Checking left");
      if(!gw.grid[x-1][y].isClosed){
        if(gw.grid[x-1][y].search < counter){
          gw.grid[x-1][y].search = counter;
        }
        if(gw.grid[x-1][y].g_value > pg){
          gw.grid[x-1][y].g_value = pg;
          gw.grid[x-1][y].tree = gw.grid[x][y];
          if(gw.grid[x-1][y].inHeap){
            heap.remove(ordering);
          }
          gw.grid[x-1][y].f_value = gw.grid[x-1][y].g_value + gw.grid[x-1][y].h_value;
          heap.add(gw.grid[x-1][y],ordering);
          gw.grid[x-1][y].inHeap = true;
        }
      }
    }

    if(current.x!=MAXINDEX){
  //	System.out.println("Checking right");
      if(!gw.grid[x+1][y].isClosed){
        if(gw.grid[x+1][y].search < counter){
          gw.grid[x+1][y].search = counter;
        }
        if(gw.grid[x+1][y].g_value > pg){
          gw.grid[x+1][y].g_value = pg;
          gw.grid[x+1][y].tree = gw.grid[x][y];
          if(gw.grid[x+1][y].inHeap){
            heap.remove(ordering);
          }
          gw.grid[x+1][y].f_value = gw.grid[x+1][y].g_value + gw.grid[x+1][y].h_value;
             //     System.out.println("adding right to heap NOWWW");
          heap.add(gw.grid[x+1][y],ordering);
          gw.grid[x+1][y].inHeap = true;
            //      System.out.println("Right value");
        }
      }
   }

   if(current.y!=0){
  	//System.out.println("Checking down");
    if(!gw.grid[x][y-1].isBlocked && !gw.grid[x][y-1].isClosed){
      if(gw.grid[x][y-1].search < counter){
        gw.grid[x][y-1].search = counter;
      }
      if(gw.grid[x][y-1].g_value > pg){
           //   System.out.println("down g value success");
        gw.grid[x][y-1].g_value = pg;
        gw.grid[x][y-1].tree = gw.grid[x][y];
        if(gw.grid[x][y-1].inHeap){
          heap.remove(ordering);
        }
        gw.grid[x][y-1].f_value = gw.grid[x][y-1].g_value + gw.grid[x][y-1].h_value;
                //  System.out.println("adding down to heap NOWWW");
        heap.add(gw.grid[x][y-1],ordering);
        gw.grid[x][y-1].inHeap = true;
      }
    }
  }


  if(current.y!=MAXINDEX){
  	//System.out.println("Checking up");
    if(!gw.grid[x][y+1].isBlocked && !gw.grid[x][y+1].isClosed){
      if(gw.grid[x][y+1].search < counter){
        gw.grid[x][y+1].search = counter;
      }
      if(gw.grid[x][y+1].g_value > pg){
        gw.grid[x][y+1].g_value = pg;
        gw.grid[x][y+1].tree = gw.grid[x][y];
        if(gw.grid[x][y+1].inHeap){
          heap.remove(ordering);
        }
        gw.grid[x][y+1].f_value = gw.grid[x][y+1].g_value + gw.grid[x][y+1].h_value;
                //  System.out.println("adding up to heap NOWWW");
        heap.add(gw.grid[x][y+1], ordering);
        gw.grid[x][y+1].inHeap = true;
      }
    }
  }

    //heap.currentMembers();
  return;
  }


  public static Square Astar(Grid ngw, Square goal, int counter, char ordering){
    Square current;
    while(gw.grid[goal.x][goal.y].g_value > (current = heap.peek()).g_value){
     System.out.println("currently at the indices (" +  current.x +"," + current.y + ")");
     heap.remove(ordering);
     gw.grid[current.x][current.y].inHeap = false;
     gw.grid[current.x][current.y].isClosed = true;
     addFour(gw.grid[current.x][current.y],counter,ordering);
      if(heap.isEmpty()){
        return null;
     }
   }
   return gw.grid[current.x][current.y];
  }

  public static void repeatedAStar(Square start, Square goal, char ordering){
    int counter = 0;
    Square current;
    while(!sqEquals(start,goal)){
      counter++;
        System.out.println("Counter = " + counter + " Currently at indices ( " + start.x + "," + start.y + ")" );
      gw.grid[start.x][start.y].g_value = 0;
      gw.grid[start.x][start.y].search = counter;
      gw.grid[start.x][start.y].inHeap = true;
      gw.grid[start.x][start.y].f_value = start.g_value + start.calculate_h(goal);
      heap.add(gw.grid[start.x][start.y], ordering);
      current = Astar(goal,counter,ordering);
      if(heap.isEmpty()){
       System.out.println("I cannot reach the target.");
       return;
     }
     start = current;
   }
   //System.out.println("Arrived at " + start.x + "," + start.y);
   System.out.println("I reached the target.");
   return;
  }

  public static boolean sqEquals(Square a, Square b){
    return ((a.x == b.x) && (a.y == b.y));
  }

  public static void main(String[] args){	
    // for(int i = 0; i<DIM; i++){
  	// 	for(int j = 0; j<DIM; j++){
  	// 			gw.grid[i][j] = new Square();
  	// 			gw.grid[i][j].x = i;
  	// 			gw.grid[i][j].y = j;
  	// 	}
  	// }
    /*long timesum = 0;
    long currtime = 0;
    for(int i = 0; i<50; i++){
      gw = new GridWorld();
      gw.populate();
      System.out.println(gw.CAPACITY);
      long startTime = System.currentTimeMillis();
      repeatedAStar(gw.grid[gw.agentx][gw.agenty], gw.grid[gw.targetx][gw.targety],'s');
      long endTime = System.currentTimeMillis();
      currtime = endTime - startTime;
      timesum = timesum + currtime;
    }*/

    gw.generate();
    System.out.println(gw.CAPACITY);
    long startTime = System.currentTimeMillis();
    repeatedAStar(gw.grid[gw.agentx][gw.agenty], gw.grid[gw.targetx][gw.targety],'s');
    long endTime = System.currentTimeMillis();
    System.out.println("That took " + (endTime - startTime) + " milliseconds");
    return;
  	//Built basic 3x3 for testing
  }


  }