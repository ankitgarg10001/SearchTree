package com.ankit.driver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.ankit.model.CustomerListDetailsVO;
import com.ankit.tree.AbstractTree;
import com.ankit.tree.impl.ContainsTree;

public class Driver {
	/**
	 * For demo of search tree features
	 */
	public static void main(String[] args) {
		List<CustomerListDetailsVO> customerList = new ArrayList<CustomerListDetailsVO>();
		AbstractTree<CustomerListDetailsVO> searchTree = new ContainsTree<CustomerListDetailsVO>(false);
		for (int i = 0; i < 100000; i++) {
			CustomerListDetailsVO customerListDetailsVO = new CustomerListDetailsVO(i);
			customerList.add(customerListDetailsVO);
		}
		long time = new Date().getTime();
		customerList.forEach(t -> searchTree.add(t));
		time = (new Date().getTime() - time);
		System.out.println("Tree Made In ms: " + time);
		System.out.println("-------Search this as prefix and exact word in all attributes----------");
		time = new Date().getTime();
		System.out.println(searchTree.searchObjectsContainingString("Za", true, true, null).size());
		time = (new Date().getTime() - time);
		System.out.println("Output in ms: " + time);
		time = new Date().getTime();
		System.out.println("-------Search this as prefix but not exact word in all attributes----------");
		System.out.println(new Date());
		System.out.println(searchTree.searchObjectsContainingString("Z", true, false, null).size());
		time = (new Date().getTime() - time);
		System.out.println("Output in ms: " + time);
		time = new Date().getTime();
		System.out.println("-------Search this as just contains in all attributes----------");
		time = new Date().getTime();
		System.out.println(searchTree.searchObjectsContainingString("d", false, false, null).size());
		time = (new Date().getTime() - time);
		System.out.println("Output in ms: " + time);
		time = new Date().getTime();
		System.out.println("-------Search this as just contains in selected attributes----------");
		time = new Date().getTime();
		System.out.println(searchTree.searchObjectsContainingString("files", false, false,
				new ArrayList<String>(Arrays.asList("customerId", "downloadUrl"))).size());
		time = (new Date().getTime() - time);
		System.out.println("Output in ms: " + time);

		System.out.println("-------------starting normal search----------------");
		System.out.println("-------Search this as prefix and exact word in all attributes----------");
		int count = 0;
		time = new Date().getTime();
		for (CustomerListDetailsVO customerListDetailsVO : customerList) {
			if (customerListDetailsVO.getCustomerId() != null && customerListDetailsVO.getCustomerId().equals("Za")) {
				++count;
				continue;
			}

			if (customerListDetailsVO.getCustomerName() != null
					&& customerListDetailsVO.getCustomerName().equals("Za")) {
				++count;
				continue;
			}

			if (customerListDetailsVO.getDownloadUrl() != null && customerListDetailsVO.getDownloadUrl().equals("Za")) {
				++count;
				continue;
			}
			if (customerListDetailsVO.getStatus() != null && customerListDetailsVO.getStatus().equals("Za")) {
				++count;
				continue;
			}
			if (customerListDetailsVO.getViewUrl() != null && customerListDetailsVO.getViewUrl().equals("Za")) {
				++count;
				continue;
			}
		}
		time = (new Date().getTime() - time);
		System.out.println("Output in ms: " + time);
		System.out.println(count);
		time = new Date().getTime();

		System.out.println("-------Search this as prefix but not exact word in all attributes----------");
		count = 0;
		System.out.println(new Date());
		for (CustomerListDetailsVO customerListDetailsVO : customerList) {
			if (customerListDetailsVO.getCustomerId() != null
					&& customerListDetailsVO.getCustomerId().startsWith("d:/files/Complete_final_file1-out.pdf")) {
				++count;
				continue;
			}

			if (customerListDetailsVO.getCustomerName() != null
					&& customerListDetailsVO.getCustomerName().startsWith("d:/files/Complete_final_file1-out.pdf")) {
				++count;
				continue;
			}

			if (customerListDetailsVO.getDownloadUrl() != null
					&& customerListDetailsVO.getDownloadUrl().startsWith("d:/files/Complete_final_file1-out.pdf")) {
				++count;
				continue;
			}
			if (customerListDetailsVO.getStatus() != null
					&& customerListDetailsVO.getStatus().startsWith("d:/files/Complete_final_file1-out.pdf")) {
				++count;
				continue;
			}
			if (customerListDetailsVO.getViewUrl() != null
					&& customerListDetailsVO.getViewUrl().startsWith("d:/files/Complete_final_file1-out.pdf")) {
				++count;
				continue;
			}
		}
		time = (new Date().getTime() - time);
		System.out.println("Output in ms: " + time);
		System.out.println(count);
		count = 0;
		time = new Date().getTime();
		System.out.println("-------Search this as just contains in all attributes----------");
		time = new Date().getTime();
		for (CustomerListDetailsVO customerListDetailsVO : customerList) {
			if (customerListDetailsVO.getCustomerId() != null
					&& customerListDetailsVO.getCustomerId().contains("d:/files/Complete_final_file1-out.pdf")) {
				++count;
				continue;
			}

			if (customerListDetailsVO.getCustomerName() != null
					&& customerListDetailsVO.getCustomerName().contains("d:/files/Complete_final_file1-out.pdf")) {
				++count;
				continue;
			}

			if (customerListDetailsVO.getDownloadUrl() != null
					&& customerListDetailsVO.getDownloadUrl().contains("d:/files/Complete_final_file1-out.pdf")) {
				++count;
				continue;
			}
			if (customerListDetailsVO.getStatus() != null
					&& customerListDetailsVO.getStatus().contains("d:/files/Complete_final_file1-out.pdf")) {
				++count;
				continue;
			}
			if (customerListDetailsVO.getViewUrl() != null
					&& customerListDetailsVO.getViewUrl().contains("d:/files/Complete_final_file1-out.pdf")) {
				System.out.println(customerListDetailsVO);
				++count;
			}
		}
		time = (new Date().getTime() - time);
		System.out.println("Output in ms: " + time);
		System.out.println(count);
		count = 0;
		time = new Date().getTime();
		System.out.println("-------Search this as just contains in selected attributes----------");
		time = new Date().getTime();
		for (CustomerListDetailsVO customerListDetailsVO : customerList) {
			if (customerListDetailsVO.getCustomerId() != null
					&& customerListDetailsVO.getCustomerId().contains("d:/files/Complete_final_file1-out.pdf")) {
				++count;
				continue;
			}

			if (customerListDetailsVO.getDownloadUrl() != null
					&& customerListDetailsVO.getDownloadUrl().contains("d:/files/Complete_final_file1-out.pdf")) {
				++count;
				continue;
			}
		}
		time = (new Date().getTime() - time);
		System.out.println("Output in ms: " + time);
		System.out.println(count);
	}
}
