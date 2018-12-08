package tn.esprit.b3.esprit1718b3erp.entities;



import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class ProductCk {

	
	private CheckBox selectck = new CheckBox();
	private int idProduct;
	private String name;
	private String ref;
	private double price;
	private double priceT;
	private TextField tf = new TextField();
	
	
	public ProductCk() {
		super();
	}



	public ProductCk(int idProduct, String name, String ref,double price) {
		super();
		this.idProduct = idProduct;
		this.name = name;
		this.ref = ref;
		this.price=price;
	}



	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	
	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public double getPriceT() {
		return priceT;
	}



	public void setPriceT(double priceT) {
		this.priceT = priceT;
	}



	public CheckBox getSelectck() {
		return selectck;
	}

	public void setSelectck(CheckBox selectck) {
		this.selectck = selectck;
	}



	public TextField getTf() {
		return tf;
	}



	public void setTf(TextField tf) {
		this.tf = tf;
	}




	@Override
	public String toString() {
		return "ProductCk [selectck=" + selectck + ", idProduct=" + idProduct + ", name=" + name + ", ref=" + ref
				+ ", price=" + price + ", priceT=" + priceT + ", tf=" + tf.getText() + "]";
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductCk other = (ProductCk) obj;
		if (idProduct != other.idProduct)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (ref == null) {
			if (other.ref != null)
				return false;
		} else if (!ref.equals(other.ref))
			return false;
		return true;
	} 
	
	

}
