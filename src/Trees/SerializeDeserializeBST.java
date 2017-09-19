package Trees;

import java.util.LinkedList;
import java.util.Queue;

public class SerializeDeserializeBST {

	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		if (root == null) {
			return null;
		}
		
		StringBuilder sb = new StringBuilder();
		preOrderSerialize(root, sb);
		return sb.toString();
	}

	public void preOrderSerialize(TreeNode root, StringBuilder sb) {
		if (root == null) {
			return;
		}

		sb.append(root.val + ",");
		preOrderSerialize(root.left, sb);
		preOrderSerialize(root.right, sb);
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		if (data == null) {
			return null;
		}

		String[] split = data.split(",");
		Queue<Integer> queue = new LinkedList<>();
		for (String s : split) {
			queue.offer(Integer.parseInt(s));
		}
		return preOrderDeserialize(queue);
	}

	public TreeNode preOrderDeserialize(Queue<Integer> queue) {
		if (queue.isEmpty()) {
			return null;
		}
		
		TreeNode root = new TreeNode(queue.poll());
		Queue<Integer> smallerQ = new LinkedList<>();
		while (!queue.isEmpty() && queue.peek() < root.val) {
			smallerQ.offer(queue.poll());
		}
		root.left = preOrderDeserialize(smallerQ);
		root.right = preOrderDeserialize(queue);
		return root;
	}
	
	public void printPreOrder(TreeNode root) {
		if(root == null) {
			return;
		}
		
		System.out.print(root.val + " ");
		printPreOrder(root.left);
		printPreOrder(root.right);
	}

	public static void main(String[] args) {
		SerializeDeserializeBST obj = new SerializeDeserializeBST();
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(2);
		root.right = new TreeNode(4);
		String data = obj.serialize(root);
		TreeNode node = obj.deserialize(data);
		obj.printPreOrder(node);
	}

}
