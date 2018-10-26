package cl.wom.middleware.vo;
public class DeviceOffering
{
    private String deviceName;

    private String partNum;

    private String modality;

    private String sku;

    private String shDes;

    private String cost;

    private String offerId;

    public String getDeviceName ()
    {
        return deviceName;
    }

    public void setDeviceName (String deviceName)
    {
        this.deviceName = deviceName;
    }

    public String getPartNum ()
    {
        return partNum;
    }

    public void setPartNum (String partNum)
    {
        this.partNum = partNum;
    }

    public String getModality ()
    {
        return modality;
    }

    public void setModality (String modality)
    {
        this.modality = modality;
    }

    public String getSku ()
    {
        return sku;
    }

    public void setSku (String sku)
    {
        this.sku = sku;
    }

    public String getShDes ()
    {
        return shDes;
    }

    public void setShDes (String shDes)
    {
        this.shDes = shDes;
    }

    public String getCost ()
    {
        return cost;
    }

    public void setCost (String cost)
    {
        this.cost = cost;
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
        return "ClassPojo [deviceName = "+deviceName+", partNum = "+partNum+", modality = "+modality+", sku = "+sku+", shDes = "+shDes+", cost = "+cost+", offerId = "+offerId+"]";
    }
}