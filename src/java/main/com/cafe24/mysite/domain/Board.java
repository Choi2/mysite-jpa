package com.cafe24.mysite.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="board")
public class Board {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long no;
	
	@Column(nullable=false)
	private String title;
	
	@Lob
	@Column(nullable=true)
	private String content;
	
	@Column(name="read_count")
	private int readCount;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="reg_date")
	private Date regDate;
	
	
	@ManyToOne
	@JoinColumn(name="user_no")
	private User user;

	private long groupNo;
	private long orderNo;
	private long parentNo;
	private long depth;
	
	@Column(name="board_delete")
	private boolean boardDelete;

	@OneToMany(fetch = FetchType.EAGER, mappedBy="board")
	private List<Comment> comments = new ArrayList<Comment>();
	
	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(long groupNo) {
		this.groupNo = groupNo;
	}

	public long getOrderNo() {
		return orderNo;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

	public long getParentNo() {
		return parentNo;
	}

	public void setParentNo(long parentNo) {
		this.parentNo = parentNo;
	}

	public long getDepth() {
		return depth;
	}

	public void setDepth(long depth) {
		this.depth = depth;
	}

	public boolean getBoardDelete() {
		return boardDelete;
	}

	public void setBoardDelete(boolean boardDelete) {
		this.boardDelete = boardDelete;
	}

	@Override
	public String toString() {
		return "Board [no=" + no + ", title=" + title + ", content=" + content + ", readCount=" + readCount
				+ ", regDate=" + regDate + ", user=" + user + ", groupNo=" + groupNo + ", orderNo=" + orderNo
				+ ", parentNo=" + parentNo + ", depth=" + depth + ", boardDelete=" + boardDelete + ", comments="
				+ comments + "]";
	}


}
