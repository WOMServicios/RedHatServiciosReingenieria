package cl.wom.middleware.vo;

import java.util.List;

public class BundleProductOffering {
    private String minimumRequired;
    private String quantity;
    private String unitOfMeasure;
    private String isPromotionProduct;
    private List<Channel> channel;
    private String description;
    private String isOfferProduct;
    private String isOptionProduct;
    private String priority;
    private OneTime oneTime;
    private String occ;
    private String unitType;
    private String name;
    private String offerId;
    private String shDes;
    private String id;
    private String sku;
    private String maximunAllowed;
    private String status;
    private RecurringCharge recurringCharge;
	public BundleProductOffering() {
		super();
	}

	public String getMinimumRequired() {
		return minimumRequired;
	}
	public void setMinimumRequired(String minimumRequired) {
		this.minimumRequired = minimumRequired;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}
	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}
	public String getIsPromotionProduct() {
		return isPromotionProduct;
	}
	public void setIsPromotionProduct(String isPromotionProduct) {
		this.isPromotionProduct = isPromotionProduct;
	}
	public List<Channel> getChannel() {
		return channel;
	}
	public void setChannel(List<Channel> channel) {
		this.channel = channel;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIsOfferProduct() {
		return isOfferProduct;
	}
	public void setIsOfferProduct(String isOfferProduct) {
		this.isOfferProduct = isOfferProduct;
	}
	public String getIsOptionProduct() {
		return isOptionProduct;
	}
	public void setIsOptionProduct(String isOptionProduct) {
		this.isOptionProduct = isOptionProduct;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public OneTime getOneTime() {
		return oneTime;
	}
	public void setOneTime(OneTime oneTime) {
		this.oneTime = oneTime;
	}
	public String getOcc() {
		return occ;
	}
	public void setOcc(String occ) {
		this.occ = occ;
	}
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOfferId() {
		return offerId;
	}
	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}
	public String getShDes() {
		return shDes;
	}
	public void setShDes(String shDes) {
		this.shDes = shDes;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getMaximunAllowed() {
		return maximunAllowed;
	}
	public void setMaximunAllowed(String maximunAllowed) {
		this.maximunAllowed = maximunAllowed;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public RecurringCharge getRecurringCharge() {
		return recurringCharge;
	}
	public void setRecurringCharge(RecurringCharge recurringCharge) {
		this.recurringCharge = recurringCharge;
	}
	@Override
	public String toString() {
		return "BundleProductOffering [minimumRequired=" + minimumRequired + ", quantity=" + quantity
				+ ", unitOfMeasure=" + unitOfMeasure + ", isPromotionProduct=" + isPromotionProduct + ", channel="
				+ channel + ", description=" + description + ", isOfferProduct=" + isOfferProduct + ", isOptionProduct="
				+ isOptionProduct + ", priority=" + priority + ", oneTime=" + oneTime + ", occ=" + occ + ", unitType="
				+ unitType + ", name=" + name + ", offerId=" + offerId + ", shDes=" + shDes + ", id=" + id + ", sku="
				+ sku + ", maximunAllowed=" + maximunAllowed + ", status=" + status + ", recurringCharge="
				+ recurringCharge + "]";
	}

}
