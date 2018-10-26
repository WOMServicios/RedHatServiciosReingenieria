package cl.wom.middleware.vo;
public class RecurringCharge
{
    private String frecuency;

    private String amount;

    private String type;

    private String unitOfMeasure;

    private String currency;

    public String getFrecuency ()
    {
        return frecuency;
    }

    public void setFrecuency (String frecuency)
    {
        this.frecuency = frecuency;
    }

    public String getAmount ()
    {
        return amount;
    }

    public void setAmount (String amount)
    {
        this.amount = amount;
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
        return "ClassPojo [frecuency = "+frecuency+", amount = "+amount+", type = "+type+", unitOfMeasure = "+unitOfMeasure+", currency = "+currency+"]";
    }
}