package cl.wom.middleware.vo;

import java.util.List;

public class ProductOffering
{
    private List <BundleProductOffering> bundleProductOffering;

    private OneTime oneTime;

    private String status;

    private String lastUpdate;

    private String typeOffer;

    private String isSellable;

    private String version;

    private String marketSegment;

    private String shortDescription;

    private String familyOffer;

    private String name;

    private String validFor;

    private String layoutTypeAPP;

    private List<DeviceOffering> deviceOffering;

    private List<Channel> channel;

    private RecurringCharge recurringCharge;

    private String offerId;

    public List<BundleProductOffering> getBundleProductOffering ()
    {
        return bundleProductOffering;
    }

    public void setBundleProductOffering (List<BundleProductOffering> bundleProductOffering)
    {
        this.bundleProductOffering = bundleProductOffering;
    }

    public OneTime getOneTime ()
    {
        return oneTime;
    }

    public void setOneTime (OneTime oneTime)
    {
        this.oneTime = oneTime;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getLastUpdate ()
    {
        return lastUpdate;
    }

    public void setLastUpdate (String lastUpdate)
    {
        this.lastUpdate = lastUpdate;
    }

    public String getTypeOffer ()
    {
        return typeOffer;
    }

    public void setTypeOffer (String typeOffer)
    {
        this.typeOffer = typeOffer;
    }

    public String getIsSellable ()
    {
        return isSellable;
    }

    public void setIsSellable (String isSellable)
    {
        this.isSellable = isSellable;
    }

    public String getVersion ()
    {
        return version;
    }

    public void setVersion (String version)
    {
        this.version = version;
    }

    public String getMarketSegment ()
    {
        return marketSegment;
    }

    public void setMarketSegment (String marketSegment)
    {
        this.marketSegment = marketSegment;
    }

    public String getShortDescription ()
    {
        return shortDescription;
    }

    public void setShortDescription (String shortDescription)
    {
        this.shortDescription = shortDescription;
    }

    public String getFamilyOffer ()
    {
        return familyOffer;
    }

    public void setFamilyOffer (String familyOffer)
    {
        this.familyOffer = familyOffer;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getValidFor ()
    {
        return validFor;
    }

    public void setValidFor (String validFor)
    {
        this.validFor = validFor;
    }

    public String getLayoutTypeAPP ()
    {
        return layoutTypeAPP;
    }

    public void setLayoutTypeAPP (String layoutTypeAPP)
    {
        this.layoutTypeAPP = layoutTypeAPP;
    }

    public List<DeviceOffering> getDeviceOffering ()
    {
        return deviceOffering;
    }

    public void setDeviceOffering (List<DeviceOffering> deviceOffering)
    {
        this.deviceOffering = deviceOffering;
    }

    public List<Channel> getChannel ()
    {
        return channel;
    }

    public void setChannel (List<Channel> channel)
    {
        this.channel = channel;
    }

    public RecurringCharge getRecurringCharge ()
    {
        return recurringCharge;
    }

    public void setRecurringCharge (RecurringCharge recurringCharge)
    {
        this.recurringCharge = recurringCharge;
    }

    public String getOfferId ()
    {
        return offerId;
    }

    public void setOfferId (String offerId)
    {
        this.offerId = offerId;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [bundleProductOffering = "+bundleProductOffering+", oneTime = "+oneTime+", status = "+status+", lastUpdate = "+lastUpdate+", typeOffer = "+typeOffer+", isSellable = "+isSellable+", version = "+version+", marketSegment = "+marketSegment+", shortDescription = "+shortDescription+", familyOffer = "+familyOffer+", name = "+name+", validFor = "+validFor+", layoutTypeAPP = "+layoutTypeAPP+", deviceOffering = "+deviceOffering+", channel = "+channel+", recurringCharge = "+recurringCharge+", offerId = "+offerId+"]";
    }
}
