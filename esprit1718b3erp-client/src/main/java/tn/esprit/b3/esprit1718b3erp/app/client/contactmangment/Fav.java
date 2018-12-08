package tn.esprit.b3.esprit1718b3erp.app.client.contactmangment;

public class Fav {
private String name;
private Integer count;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Integer getCount() {
	return count;
}
public void setCount(Integer count) {
	this.count = count;
}
public Fav(String name, Integer count) {
	super();
	this.name = name;
	this.count = count;
}
public Fav() {
	super();
}
}
