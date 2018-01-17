package com.library.config;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * Class responsible for creating pagination.
 *
 * 
 */
public class PageWrapper<T> {

	private int currentPage;
	private List<T> items;
	private int totalPages;
	private int pageSize;

	public PageWrapper(List<T> list, int currentPage, int pageSize) {
		this.setItems(paginate(list, currentPage, pageSize));
		this.currentPage = currentPage;
		this.totalPages = (list.size() + pageSize - 1) / pageSize;
		this.pageSize = pageSize;

	}

	@SuppressWarnings("hiding")
	public <T> List<T> paginate(List<T> collection, int page, int pageSize) {
		int index = page * pageSize;
		return collection.stream().skip(index).limit(pageSize).collect(Collectors.toList());
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
