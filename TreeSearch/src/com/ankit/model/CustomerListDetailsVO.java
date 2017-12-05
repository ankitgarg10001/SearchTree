package com.ankit.model;

import java.io.Serializable;

public class CustomerListDetailsVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 978292703105147490L;
	private String customerId;
	private String customerName;
	private String downloadUrl;
	private String viewUrl;
	private String status;

	public CustomerListDetailsVO(int customerInt) {
		super();
		this.customerId = String.valueOf(customerInt);
		this.customerName = String.valueOf((char) ('A' + (customerInt % 26)));
		this.downloadUrl = String.valueOf("d:/files/Complete_final_file1-out.pdf");
		this.viewUrl = String.valueOf(("Z" + (char) ((customerInt % 26)+'a')));
		// this.status = String.valueOf((char) ('z' + (customerInt % 26)));
		if (customerInt % 2 == 0)
			this.status = String.valueOf("|");
		else if (customerInt % 3 == 0)
			this.status = "?{\\";
		else
			this.status = String.valueOf((char) ('a' + (customerInt % 26)));

	}

	@Override
	public String toString() {
		return "CustomerListDetailsVO [customerId=" + customerId + ", customerName=" + customerName + ", downloadUrl="
				+ downloadUrl + ", viewUrl=" + viewUrl + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result + ((downloadUrl == null) ? 0 : downloadUrl.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((viewUrl == null) ? 0 : viewUrl.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof CustomerListDetailsVO)) {
			return false;
		}
		CustomerListDetailsVO other = (CustomerListDetailsVO) obj;
		if (customerId == null) {
			if (other.customerId != null) {
				return false;
			}
		} else if (!customerId.equals(other.customerId)) {
			return false;
		}
		if (customerName == null) {
			if (other.customerName != null) {
				return false;
			}
		} else if (!customerName.equals(other.customerName)) {
			return false;
		}
		if (downloadUrl == null) {
			if (other.downloadUrl != null) {
				return false;
			}
		} else if (!downloadUrl.equals(other.downloadUrl)) {
			return false;
		}
		if (status == null) {
			if (other.status != null) {
				return false;
			}
		} else if (!status.equals(other.status)) {
			return false;
		}
		if (viewUrl == null) {
			if (other.viewUrl != null) {
				return false;
			}
		} else if (!viewUrl.equals(other.viewUrl)) {
			return false;
		}
		return true;
	}

	public CustomerListDetailsVO() {
		super();
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public String getViewUrl() {
		return viewUrl;
	}

	public void setViewUrl(String viewUrl) {
		this.viewUrl = viewUrl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
