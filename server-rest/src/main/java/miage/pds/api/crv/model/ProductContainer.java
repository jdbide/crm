package miage.pds.api.crv.model;

import java.util.List;

public class ProductContainer {
	List<Product> list;
	

	public void setList(List<Product> list) {
		this.list = list;
	}

	public List<Product> getProducts(){
		return list;
	}

}
