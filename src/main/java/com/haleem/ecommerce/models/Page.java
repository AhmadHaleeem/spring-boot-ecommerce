package com.haleem.ecommerce.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "pages")
// from Lombok dependency to activate constructor and getters/setters
//@Data
public class Page {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Size(min = 2, message = "Title must be a least 2 characters long")
	private String title;

	private String slug;

	@Size(min = 5, message = "Content must be a least 5 characters long")
	private String content;

	private int sorting;

	public Page() {
	}

	public Page(String title, String slug, String content, int sorting) {
		this.title = title;
		this.slug = slug;
		this.content = content;
		this.sorting = sorting;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getSorting() {
		return sorting;
	}

	public void setSorting(int sorting) {
		this.sorting = sorting;
	}

}
