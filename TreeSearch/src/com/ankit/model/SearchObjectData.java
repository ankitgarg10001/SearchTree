package com.ankit.model;

import java.io.Serializable;

public class SearchObjectData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3466732551980379170L;
	private boolean isPrefix;
	private String attribute;

	public SearchObjectData(boolean isPrefix, String attribute) {
		super();
		this.isPrefix = isPrefix;
		this.attribute = attribute;
	}

	public boolean isPrefix() {
		return isPrefix;
	}

	public void setPrefix(boolean isPrefix) {
		this.isPrefix = isPrefix;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	@Override
	public String toString() {
		return "SearchObjectData [isPrefix=" + isPrefix + ", attribute=" + attribute + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attribute == null) ? 0 : attribute.hashCode());
		result = prime * result + (isPrefix ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SearchObjectData other = (SearchObjectData) obj;
		if (attribute == null) {
			if (other.attribute != null)
				return false;
		} else if (!attribute.equals(other.attribute))
			return false;
		if (isPrefix != other.isPrefix)
			return false;
		return true;
	}

}
