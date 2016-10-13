
public class RosterNode {
	RosterNode left, right;
	Student data;

	
	public RosterNode() {
		left = null;
		right = null;
		data = new Student();
	}

	public RosterNode(Student s) {
		left = null;
		right = null;
		data = s;
	}

	
	public void setLeft(RosterNode n) {
		left = n;
	}

	
	public void setRight(RosterNode n) {
		right = n;
	}


	public RosterNode getLeft() {
		return left;
	}

	
	public RosterNode getRight() {
		return right;
	}

	
	public void setData(Student s) {
		data = s;
	}

	
	public Student getData() {
		return data;
	}
}
