package cl.wom.middleware.vo;

import java.util.List;

public class Subscriber {
	private String subscriberId;
	private List <ProductOffer> productOffer;
	private List <ProductBundle> productBundle;
	
	public Subscriber(String subscriberId, List<ProductOffer> productOffer, List<ProductBundle> productBundle) {
		super();
		this.subscriberId = subscriberId;
		this.productOffer = productOffer;
		this.productBundle = productBundle;
	}
	
	

	public Subscriber() {
		super();
	}



	public String getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(String subscriberId) {
		this.subscriberId = subscriberId;
	}

	public List<ProductOffer> getProductOffer() {
		return productOffer;
	}

	public void setProductOffer(List<ProductOffer> productOffer) {
		this.productOffer = productOffer;
	}

	public List<ProductBundle> getProductBundle() {
		return productBundle;
	}

	public void setProductBundle(List<ProductBundle> productBundle) {
		this.productBundle = productBundle;
	}

	@Override
	public String toString() {
		return "Subscriber [subscriberId=" + subscriberId + "]";
	}
	
	
	

}
