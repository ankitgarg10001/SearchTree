package com.ankit.tree.impl;

import java.beans.Introspector;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import com.ankit.common.Utils;
import com.ankit.tree.AbstractTree;

public class ContainsTree<T> extends AbstractTree<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3965269297688523646L;

	public ContainsTree(boolean isCaseSensitive) {
		super(isCaseSensitive);
	}

	@Override
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
					Object attrValue = method.invoke(input);
					String value = String.valueOf(attrValue);

					if (attrValue == null || value.isEmpty()) {
						addStringToTree("", input, true, parameterName);
					} else {
						if (dateTypes != null && dateTypes.contains(parameterName)) {
							value = Utils.toString(Utils.toTimestamp(value));
						}
						for (int i = 0; i < value.length(); i++)
							addStringToTree(value.substring(i), input, i == 0 ? true : false, parameterName);
					}
				} catch (IllegalAccessException e) {
					System.out.println("Unable to access field \"" + method.getName()
							+ "\", please check that this metohd is accesible");
				} catch (InvocationTargetException e) {
					System.out.println("Method \"" + method.getName() + "\" threw an exception for objects "
							+ input.toString() + "");
				}
			}
		}
	}

}
