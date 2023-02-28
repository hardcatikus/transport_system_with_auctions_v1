// CArtAgO artifact code for project transport_org

package tools_of_bus;

import cartago.*;

public class PathBuilder extends Artifact {
	
	public static Path[] paths = new Path[20];
	
	static {
		buildAllPaths();
		System.out.println("[PATH BUILDER] all paths have been builded");
	}
	
	void init() {
		
	}
	
	public static void buildAllPaths() {
		paths[0] = new Path("A","D",300);
		paths[1] = new Path("D","A",300);
		paths[2] = new Path("D","B",200);
		paths[3] = new Path("B","D",200);
		paths[4] = new Path("D","C",100);
		paths[5] = new Path("C","D",100);
		paths[6] = new Path("D","E",200);
		paths[7] = new Path("E","D",200);
		paths[8] = new Path("C","E",100);
		paths[9] = new Path("E","C",100);
		paths[10] = new Path("C","B",100);
		paths[11] = new Path("B","C",100);
		paths[12] = new Path("C","A",100);
		paths[13] = new Path("A","C",100);
		paths[14] = new Path("E","B",200);
		paths[15] = new Path("B","E",200);
		paths[16] = new Path("E","A",300);
		paths[17] = new Path("A","E",300);
		paths[18] = new Path("A","B",100);
		paths[19] = new Path("B","A",100);
	}
	
	public static int getLengthOfPath(String pathName) {
		int result=0;
		for(Path path : paths) {
			if(path.getName().equals(pathName)) {
				return path.getLength();
			}
		}
		return result;
	}
	

}

