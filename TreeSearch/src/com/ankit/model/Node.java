package com.ankit.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Node<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1019432209163559256L;
	private Map<Character, Node<T>> subNodes = new HashMap<Character, Node<T>>();
	private Map<T, Set<SearchObjectData>> listOfObjects = new HashMap<T, Set<SearchObjectData>>();

	public Map<Character, Node<T>> getSubNodes() {
		return subNodes;
	}

	public void setSubNodes(Map<Character, Node<T>> words) {
		this.subNodes = words;
	}

	public Map<T, Set<SearchObjectData>> getListOfObjects() {
		return listOfObjects;
	}

	public void setListOfObjects(Map<T, Set<SearchObjectData>> listOfObjects) {
		this.listOfObjects = listOfObjects;
	}

}
