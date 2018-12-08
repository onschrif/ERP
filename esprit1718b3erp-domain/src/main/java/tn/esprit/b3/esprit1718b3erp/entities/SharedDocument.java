package tn.esprit.b3.esprit1718b3erp.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: SharedDocument
 *
 */
@Entity

public class SharedDocument implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idSharedDocument;
    private String path;
    private Date date;
    private String comment;
    private String title;
    
	private static final long serialVersionUID = 1L;

	public SharedDocument() {
		super();
	}   
	
	public SharedDocument(String path, Date date, String comment, String title) {
		super();
		this.path = path;
		this.date = date;
		this.comment = comment;
		this.title = title;
	}

	public int getIdSharedDocument() {
		return this.idSharedDocument;
	}

	public void setIdSharedDocument(int idSharedDocument) {
		this.idSharedDocument = idSharedDocument;
	}
	

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "SharedDocument [idSharedDocument=" + idSharedDocument + ", path=" + path + ", date=" + date
				+ ", comment=" + comment + ", title=" + title + "]";
	}
   
}
