package cl.wom.middleware.vo;
public class BillCycle
{
    private String bchRunDate;

    private String intervalType;

    private String accountId;

    private String billCycle;

    private String lastRunDate;

    private String billCycleDes;

    public String getBchRunDate ()
    {
        return bchRunDate;
    }

    public void setBchRunDate (String bchRunDate)
    {
        this.bchRunDate = bchRunDate;
    }

    public String getIntervalType ()
    {
        return intervalType;
    }

    public void setIntervalType (String intervalType)
    {
        this.intervalType = intervalType;
    }

    public String getAccountId ()
    {
        return accountId;
    }

    public void setAccountId (String accountId)
    {
        this.accountId = accountId;
    }

    public String getBillCycle ()
    {
        return billCycle;
    }

    public void setBillCycle (String billCycle)
    {
        this.billCycle = billCycle;
    }

    public String getLastRunDate ()
    {
        return lastRunDate;
    }

    public void setLastRunDate (String lastRunDate)
    {
        this.lastRunDate = lastRunDate;
    }

    public String getBillCycleDes ()
    {
        return billCycleDes;
    }

    public void setBillCycleDes (String billCycleDes)
    {
        this.billCycleDes = billCycleDes;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [bchRunDate = "+bchRunDate+", intervalType = "+intervalType+", accountId = "+accountId+", billCycle = "+billCycle+", lastRunDate = "+lastRunDate+", billCycleDes = "+billCycleDes+"]";
    }
}
