package com.cafe24.mysite.domain;

public class Pager {
	
	public static final int PAGE_SIZE = 5;
	public static final int GROUP_SIZE = 25;
	
	private long page;
	
	private long startPage; 
	private long endPage;
	
	private long startPageIndex; //한 페이지 기준으로 시작 row 번호
	
	private boolean leftArrow;
	private boolean rightArrow;
	
	private long totalCount; //총 게시물 수
	
	private String word;
	
	public void calculate(long page) {

		
		long totalPage = totalCount / PAGE_SIZE; //전체 게시글의 크기에 따른 페이지 수
		
		if(totalCount % PAGE_SIZE > 0) {
			
			totalPage++; // 나머지가 있을 경우에만 1을 더한다.
						 // 이렇게 안하면 마지막 페이지에 게시물이 나오지 않음
		}
		
		if(totalPage < this.page) {
			page = totalPage;
		}
		
		startPage = ((page - 1) / PAGE_SIZE) * PAGE_SIZE + 1;
		endPage = startPage + PAGE_SIZE - 1; 
		startPageIndex = (page - 1) * PAGE_SIZE;
		leftArrow = (startPage == 1) ? false : true;
		rightArrow = (totalCount > (((page - 1) / PAGE_SIZE) + 1) * GROUP_SIZE) ? true : false;
		
		//  여기서 마지막 페이지를 보정해줍니다.

		if (endPage > totalPage) {
		    endPage = totalPage;
		}
	}
	
	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public long getStartPageIndex() {
		return startPageIndex;
	}

	public void setStartPageIndex(int startPageIndex) {
		this.startPageIndex = startPageIndex;
	}

	public boolean getRightArrow() {
		return rightArrow;
	}

	public void setRightArrow(boolean rightArrow) {
		this.rightArrow = rightArrow;
	}

	public boolean getLeftArrow() {
		return leftArrow;
	}

	public void setLeftArrow(boolean leftArrow) {
		this.leftArrow = leftArrow;
	}

	public long getPage() {
		if(page == 0) {
			return 1;
		}
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public long getStartPage() {
		return startPage;
	}

	public void setStartPage(long startPage) {
		this.startPage = startPage;
	}

	public long getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	@Override
	public String toString() {
		return "Pager [page=" + page + ", startPage=" + startPage + ", endPage=" + endPage + ", startPageIndex="
				+ startPageIndex + ", leftArrow=" + leftArrow + ", rightArrow=" + rightArrow + ", totalCount="
				+ totalCount + ", word=" + word + "]";
	}

	
}