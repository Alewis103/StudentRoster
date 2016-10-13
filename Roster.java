import java.io.PrintWriter;

public class Roster {
	private RosterNode root;

	
	public Roster() {
		root = null;
	}

	public boolean isEmpty() {
		return root == null;
	}


	public void insert(Student data) {
		root = insert(root, data);
	}

	
	private RosterNode insert(RosterNode node, Student data) {
		if (node == null)
			node = new RosterNode(data);
		else {
			if (data.getFirst().compareTo(node.getData().getFirst()) < 0)
				node.left = insert(node.left, data);
			else
				node.right = insert(node.right, data);
		}
		return node;
	}

	
	public void delete(Student k) {
		if (isEmpty())
			System.out.println("Tree Empty");
		else if (search(k) == false)
			System.out.println(k + " is not found");
		else {
			root = delete(root, k);
			System.out.println(k + " removed");
		}
	}

	private RosterNode delete(RosterNode root, Student k) {
		RosterNode p, p2, n;
		if (root.getData().equals(k)) {
			RosterNode lt, rt;
			lt = root.getLeft();
			rt = root.getRight();
			if (lt == null && rt == null)
				return null;
			else if (lt == null) {
				p = rt;
				return p;
			} else if (rt == null) {
				p = lt;
				return p;
			} else {
				p2 = rt;
				p = rt;
				while (p.getLeft() != null)
					p = p.getLeft();
				p.setLeft(lt);
				return p2;
			}
		}
		if (k.getFirst().compareTo(root.getData().getFirst()) < 0) {
			n = delete(root.getLeft(), k);
			root.setLeft(n);
		} else {
			n = delete(root.getRight(), k);
			root.setRight(n);
		}
		return root;
	}

	
	public int countNodes() {
		return countNodes(root);
	}


	private int countNodes(RosterNode r) {
		if (r == null)
			return 0;
		else {
			int l = 1;
			l += countNodes(r.getLeft());
			l += countNodes(r.getRight());
			return l;
		}
	}

	
	public boolean search(Student val) {
		return search(root, val);
	}


	private boolean search(RosterNode r, Student val) {
		boolean found = false;
		while ((r != null) && !found) {
			Student rval = r.getData();
			if (val.getFirst().compareTo(rval.getFirst()) < 0)
				r = r.getLeft();
			else if (val.getFirst().compareTo(rval.getFirst()) > 0)
				r = r.getRight();
			else {
				found = true;
				break;
			}
			found = search(r, val);
		}
		return found;
	}
	
	public RosterNode get(String studentId) {
		RosterNode current = root;
		while (current != null) {
			if (current.data.getId().equals(studentId)) {
				return current;
			} else if (current.data.getId().compareTo(studentId) < 0) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		return current;
	}		

	
	public void inorder() {
		inorder(root);
	}

	private void inorder(RosterNode r) {
		if (r != null) {
			inorder(r.getLeft());
			System.out.println(r.getData() + " ");
			inorder(r.getRight());
		}
	}


	public void preorder() {
		preorder(root);
	}

	private void preorder(RosterNode r) {
		if (r != null) {
			System.out.print(r.getData() + " ");
			preorder(r.getLeft());
			preorder(r.getRight());
		}
	}

	
	public void postorder() {
		postorder(root);
	}

	private void postorder(RosterNode r) {
		if (r != null) {
			postorder(r.getLeft());
			postorder(r.getRight());
			System.out.print(r.getData() + " ");
		}
	}
	

	public void saveData(PrintWriter out) {
		saveData(root, out);
	}

	private void saveData(RosterNode r, PrintWriter out) {
		if (r != null) {
			saveData(r.getLeft(), out);
			
			Student s = r.getData();
			String saveStr= "";
			saveStr+= s.getId()+";"+s.getFirst()+" "+s.getLast()+";";
			Grade[] grades = s.getGrades();
			for(int i=0; i<grades.length; i++) {
				saveStr+= grades[i].getCourse() + ":";
				saveStr+= grades[i].getCredit() + ":";
				int[] credits = grades[i].getGrades();
				for(int c=0; c<credits.length; c++) {
					saveStr+= credits[c];
					if(c == credits.length - 1) {
						continue;
					}
					saveStr+= ",";
				}
				
				if(i == grades.length - 1) {
					continue;
				}
				saveStr+= ";";
			}
			
			out.println(saveStr);
			saveData(r.getRight(), out);
		}
	}
}