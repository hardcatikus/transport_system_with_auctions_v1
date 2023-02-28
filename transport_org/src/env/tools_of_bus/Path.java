package tools_of_bus;

public class Path {
	private String name;
	private String pointOne;
	private String pointTwo;
	private int length;
	
	Path(String pointOne,String pointTwo,int length){
		this.name = pointOne + pointTwo;
		this.pointOne = pointOne;
		this.pointTwo = pointTwo;		
		this.length = length;
	}
	
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPointOne() {
        return pointOne;
    }

    public void setPointOne(String pointOne) {
        this.pointOne = pointOne;
    }

    public String getPointTwo() {
        return pointTwo;
    }

    public void setPointTwo(String pointTwo) {
        this.pointTwo = pointTwo;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

}
