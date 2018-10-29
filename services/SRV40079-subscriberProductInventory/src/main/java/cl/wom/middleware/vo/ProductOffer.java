
package cl.wom.middleware.vo;


public class ProductOffer {

private String subscriberId;
private String offerId;
private String name;
private String shortDescription;
private String atsPrepaidInd;
private String seqNo;
private String startOffer;
private String requestId;
private String transactionNo;
private String userLastMod;

public ProductOffer() {
}


public ProductOffer(String subscriberId, String offerId, String name, String shortDescription, String atsPrepaidInd, String seqNo, String startOffer, String requestId, String transactionNo, String userLastMod) {
super();
}

public String getSubscriberId() {
return subscriberId;
}

public void setSubscriberId(String subscriberId) {
this.subscriberId = subscriberId;
}

public String getOfferId() {
return offerId;
}

public void setOfferId(String offerId) {
this.offerId = offerId;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getShortDescription() {
return shortDescription;
}

public void setShortDescription(String shortDescription) {
this.shortDescription = shortDescription;
}

public String getAtsPrepaidInd() {
return atsPrepaidInd;
}

public void setAtsPrepaidInd(String atsPrepaidInd) {
this.atsPrepaidInd = atsPrepaidInd;
}

public String getSeqNo() {
return seqNo;
}

public void setSeqNo(String seqNo) {
this.seqNo = seqNo;
}

public String getStartOffer() {
return startOffer;
}

public void setStartOffer(String startOffer) {
this.startOffer = startOffer;
}

public String getRequestId() {
return requestId;
}

public void setRequestId(String requestId) {
this.requestId = requestId;
}

public String getTransactionNo() {
return transactionNo;
}

public void setTransactionNo(String transactionNo) {
this.transactionNo = transactionNo;
}

public String getUserLastMod() {
return userLastMod;
}

public void setUserLastMod(String userLastMod) {
this.userLastMod = userLastMod;
}

}