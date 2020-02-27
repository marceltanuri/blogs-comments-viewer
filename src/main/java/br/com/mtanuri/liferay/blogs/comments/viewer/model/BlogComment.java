package br.com.mtanuri.liferay.blogs.comments.viewer.model;

public class BlogComment {

	private String id;
	private String userName;
	private String crateDate;
	private String message;
	private String answerOf;

	public BlogComment(String id, String userName, String crateDate, String message, String answerOf) {
		super();
		this.id = id;
		this.userName = userName;
		this.crateDate = crateDate;
		this.message = message;
		this.answerOf = answerOf;
	}

	public BlogComment(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCrateDate() {
		return crateDate;
	}

	public void setCrateDate(String crateDate) {
		this.crateDate = crateDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAnswerOf() {
		return answerOf;
	}

	public void setAnswerOf(String answerOf) {
		this.answerOf = answerOf;
	}

	@Override
	public String toString() {
		return "BlogComment [id=" + id + ", userName=" + userName + ", crateDate=" + crateDate + ", message=" + message
				+ ", answerOf=" + answerOf + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		BlogComment other = (BlogComment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
