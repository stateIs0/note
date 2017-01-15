package cn.tedu.note.entity;

import java.io.Serializable;
import java.util.List;

public class Post implements Serializable {
	private static final long serialVersionUID = -929206547879400506L;

	private Integer id;
	private String content;
	
	private Person person;
	private List<Comment> comments;
	
	public Post() {
	}

	public Post(Integer id, String content, Person person) {
		super();
		this.id = id;
		this.content = content;
		this.person = person;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
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
		Post other = (Post) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", content=" + content + ", person=" + person + ", comments=" + comments + "]";
	}
	
}
