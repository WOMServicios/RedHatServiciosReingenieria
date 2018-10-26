package cl.wom.middleware.vo;
public class OneTime
{
    private String amount;

    private String duration;

    private String type;

    private String unitOfMeasure;

    private String currency;

    public String getAmount ()
    {
        return amount;
    }

    public void setAmount (String amount)
    {
        this.amount = amount;
    }

    public String getDuration ()
    {
        return duration;
    }

    public void setDuration (String duration)
    {
        this.duration = duration;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getUnitOfMeasure ()
    {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure (String unitOfMeasure)
    {
        this.unitOfMeasure = unitOfMeasure;
    }

    public String getCurrency ()
    {
        return currency;
    }

    public void setCurrency (String currency)
    {
        this.currency = currency;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [amount = "+amount+", duration = "+duration+", type = "+type+", unitOfMeasure = "+unitOfMeasure+", currency = "+currency+"]";
    }
}
	