package com.ankit.tree;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.ankit.model.Node;
import com.ankit.model.SearchObjectData;

import java.util.Set;

/**
 * for searchTree to work with column(Attribute) wise search, never use it with
 * an object with object having attribute with name starting with caps
 * 
 * @author ankitg
 *
 * @param <T>
 */
public abstract class AbstractTree<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 991419895130709910L;
	private Map<Character, Node<T>> root = new HashMap<Character, Node<T>>();
	private final boolean isCaseSensitive;
	private HashSet<T> result = new HashSet<T>();

	private List<String> columns;

	public AbstractTree(boolean isCaseSensitive) {
		super();
		this.isCaseSensitive = isCaseSensitive;
	}

	/**
	 * add object to tree for future searches
	 * 
	 * @param input
	 *            : input object
	 */
	public abstract void add(T input);

	/**
	 * add object to tree for future searches
	 * 
	 * @param input
	 *            : input object
	 * @param methods
	 *            : list of methods to add to searching
	 */
	public abstract void add(T input, List<Method> methods);

	/**
	 * add object to tree for future searches
	 * 
	 * @param input
	 *            : input object
	 * @param methods
	 *            : list of methods to add to searching
	 * @param dateTypes
	 *            : attributes of <T> to treat as date type format MM/DD/yyyy
	 *            instead of yyyy-MM-dd HH:mm:ss
	 */
	public abstract void add(T input, List<Method> methods, List<String> dateTypes);

	protected void addStringToTree(String inputString, T input, boolean isPrefix, String attribute) {
		if (inputString.isEmpty())
			return;
		if (!isCaseSensitive)
			inputString = inputString.toLowerCase();
		char[] charArray = inputString.toCharArray();
		Node<T> currentNode = root.get(charArray[0]);
		if (currentNode == null) {
			currentNode = new Node<T>();
			root.put(charArray[0], currentNode);
		}
		for (int i = 1; i < charArray.length; i++) {
			currentNode = getNodeWithChar(charArray[i], currentNode);
		}
		if (currentNode.getListOfObjects().get(input) == null) {
			currentNode.getListOfObjects().put(input, new HashSet<SearchObjectData>());
		}
		currentNode.getListOfObjects().get(input).add(new SearchObjectData(isPrefix, attribute));

	}

	private Node<T> getNodeWithChar(Character c, Node<T> currentNode) {
		Node<T> resultantNode = currentNode.getSubNodes().get(c);
		if (resultantNode == null) {
			resultantNode = new Node<T>();
			currentNode.getSubNodes().put(c, resultantNode);
		}
		return resultantNode;
	}

	/**
	 * search string from tree
	 * 
	 * @param inputString
	 *            : string to search
	 * @param asPrefix
	 *            : to search if this string is a prefix in attribute or just
	 *            search for contains
	 * @param asExactWord
	 *            : to search if this string is a exact word in attribute
	 * @param columns
	 *            : list of columns to search inputString in
	 * @return set of objects from search criteria
	 */
	public HashSet<T> searchObjectsContainingString(String inputString, boolean asPrefix, boolean asExactWord,
			final List<String> columns) {
		result = new HashSet<T>();
		this.columns = columns;
		if (asExactWord)
			asPrefix = true;
		if (inputString == null || inputString.isEmpty()) {
			if (asExactWord)
				showDataWithFullWordsInCurrentNode(root.get(""));
			else {
				for (Node<T> node : root.values()) {
					showAllSubsequestNodesData(node, asPrefix);
				}
			}
			return result;
		}
		if (!isCaseSensitive)
			inputString = inputString.toLowerCase();
		char[] charArray = inputString.toCharArray();

		Node<T> currentNode = root.get(charArray[0]);

		if (currentNode == null || !getSubsequentNodesForWordSearch(charArray, 1, currentNode, asPrefix, asExactWord)) {
			 System.out.println("word not found");
		}
		return result;
	}

	private boolean getSubsequentNodesForWordSearch(char[] charArray, int currentIndex, Node<T> currentNode,
			boolean isPrefix, boolean isExactWord) {
		if (currentIndex == charArray.length) {
			if (isExactWord)
				showDataWithFullWordsInCurrentNode(currentNode);
			else
				showAllSubsequestNodesData(currentNode, isPrefix);
			return true;
		}
		Node<T> nextNode = (Node<T>) currentNode.getSubNodes().get(charArray[currentIndex]);
		if (nextNode == null) {
			return false;
		} else {
			return getSubsequentNodesForWordSearch(charArray, ++currentIndex, nextNode, isPrefix, isExactWord);
		}
	}

	private void showAllSubsequestNodesData(Node<T> currentNode, boolean isPrefix) {
		if (currentNode == null)
			return;
		showDataList(currentNode.getListOfObjects(), isPrefix);
		for (Node<T> nextNode : currentNode.getSubNodes().values()) {
			showAllSubsequestNodesData(nextNode, isPrefix);
		}
	}

	private void showDataList(Map<T, Set<SearchObjectData>> map, boolean isPrefix) {
		Iterator<Entry<T, Set<SearchObjectData>>> entries = map.entrySet().iterator();

		while (entries.hasNext()) {
			Entry<T, Set<SearchObjectData>> thisEntry = (Entry<T, Set<SearchObjectData>>) entries.next();
			if (thisEntry.getValue() != null) {
				for (SearchObjectData currentEntry : thisEntry.getValue()) {
					if (isPrefix == true && isPrefix == currentEntry.isPrefix()
							&& (isValidColumn(currentEntry.getAttribute()))) {
						result.add(thisEntry.getKey());
						// System.out.println(thisEntry.getKey().toString());
					} else if (isPrefix == false && (isValidColumn(currentEntry.getAttribute()))) {
						result.add(thisEntry.getKey());
						// System.out.println(thisEntry.getKey().toString());
					}
				}
			}
		}
	}

	private boolean isValidColumn(String attribute) {
		return columns == null || columns.isEmpty() || columns.contains(attribute);
	}

	private void showDataWithFullWordsInCurrentNode(Node<T> searchTreeNode) {
		Iterator<Entry<T, Set<SearchObjectData>>> entries = searchTreeNode.getListOfObjects().entrySet().iterator();
		while (entries.hasNext()) {
			Entry<T, Set<SearchObjectData>> thisEntry = (Entry<T, Set<SearchObjectData>>) entries.next();
			if (thisEntry.getValue() != null) {
				for (SearchObjectData currentEntry : thisEntry.getValue()) {
					if (true == currentEntry.isPrefix() && isValidColumn(currentEntry.getAttribute())) {
						result.add(thisEntry.getKey());
						// System.out.println(thisEntry.getKey().toString());
					}
				}
			}
		}
	}

}
