package tn.esprit.b3.esprit1718b3erp.app.client.contactmangment;

import java.util.Date;

public class History {
private String name;
private int quantity;
private Date date;
public History(String name, int quantity, Date date) {
	super();
	this.name = name;
	this.quantity = quantity;
	this.date = date;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}

}
