package com.ankit.tree.impl;

import java.beans.Introspector;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import com.ankit.common.Utils;
import com.ankit.tree.AbstractTree;

public class PrefixTree<T> extends AbstractTree<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8927034306694637546L;

	public PrefixTree(boolean isCaseSensitive) {
		super(isCaseSensitive);
	}

	public void add(T input) {
		add(input, Arrays.asList(input.getClass().getMethods()));
	}

	@Override
	public void add(T input, List<Method> methods) {
		add(input, methods, null);
	}

	@Override
	public void add(T input, List<Method> methods, List<String> dateTypes) {
		for (Method method : methods) {
			if (Utils.isGetter(method)) {
				try {
					String parameterName = Introspector
							.decapitalize(method.getName().substring(method.getName().startsWith("is") ? 2 : 3));

					String value = String.valueOf(method.invoke(input));
					if (dateTypes != null && dateTypes.contains(parameterName)) {
						value = Utils.toString(Utils.toTimestamp(value));
					}
					addStringToTree(value, input, true, parameterName);
				} catch (IllegalAccessException e) {
					System.out.println("Unable to access field \"" + method.getName()
							+ "'\", please check that this metohd is accesible");
				} catch (InvocationTargetException e) {
					System.out.println(
							"Method \"" + method.getName() + "\" threw an exception for objects " + input.toString());
				}
			}
		}
	}

}
