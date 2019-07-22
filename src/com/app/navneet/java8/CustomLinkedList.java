package com.app.navneet.java8;

public class CustomLinkedList {
	Node first;
	Node last;
	int size = 0;

	public void addLast(Object data) {
		Node l=last;
		Node node=new Node();
		node.data=data;
		node.prev=l;
		last=node;
		if(l==null) {
			first=node;
		}else {
			l.next=node;
		}
		
		size++;
	}
	
	public void addFirst(Object data) {
		Node f=first;
		Node node=new Node();
		node.data=data;
		node.next=f;
		first=node;
		if(f==null) {
			last=node;
		}else {
			f.prev=node;
		}
	}

	public void print() {
		Node head = first;
		while (head != null) {
			System.out.println(head.data);
			head = head.next;
		}
	}

	class Node {
		Object data;
		Node next;
		Node prev;
	}

	public static void main(String[] args) {
		CustomLinkedList c = new CustomLinkedList();
//		LinkedList list=new LinkedList<>();
//		list.add(23);
//		list.add(35);
		// System.out.println(list.toString());
		c.addLast(23);
		c.addLast(35);
		c.addLast(56);
		//c.addFirst(6);

		c.print();
	}

}
