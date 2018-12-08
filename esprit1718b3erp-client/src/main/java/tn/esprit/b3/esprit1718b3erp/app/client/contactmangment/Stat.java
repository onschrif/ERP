package tn.esprit.b3.esprit1718b3erp.app.client.contactmangment;

public class Stat {
	String statname;
	int nbr;
	public Stat(String statname, int nbr) {
		super();
		this.statname = statname;
		this.nbr = nbr;
	}
	public String getStatname() {
		return statname;
	}
	public void setStatname(String statname) {
		this.statname = statname;
	}
	public int getNbr() {
		return nbr;
	}
	public void setNbr(int nbr) {
		this.nbr = nbr;
	}
	
}
