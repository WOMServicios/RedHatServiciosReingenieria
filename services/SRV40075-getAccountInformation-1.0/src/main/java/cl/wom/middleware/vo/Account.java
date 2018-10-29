package cl.wom.middleware.vo;

import java.util.List;

public class Account
{
    private String accountId;

    private String accountIdHigh;

    private String state;

    private String csLevel;

    private String externalAccountId;

    private BillCycle billCycle;

    private String docTypeOutputCode;

    private String custCode;

    private String rut;

    private String accountType;

    private List<Subscribers> subscribers;

    private String docTypeDesc;

    private String accountDeactivate;

    private String accountActivate;

    private String docTypeId;

    public String getAccountId ()
    {
        return accountId;
    }

    public void setAccountId (String accountId)
    {
        this.accountId = accountId;
    }

    public String getAccountIdHigh ()
    {
        return accountIdHigh;
    }

    public void setAccountIdHigh (String accountIdHigh)
    {
        this.accountIdHigh = accountIdHigh;
    }

    public String getState ()
    {
        return state;
    }

    public void setState (String state)
    {
        this.state = state;
    }

    public String getCsLevel ()
    {
        return csLevel;
    }

    public void setCsLevel (String csLevel)
    {
        this.csLevel = csLevel;
    }

    public String getExternalAccountId ()
    {
        return externalAccountId;
    }

    public void setExternalAccountId (String externalAccountId)
    {
        this.externalAccountId = externalAccountId;
    }

    public BillCycle getBillCycle ()
    {
        return billCycle;
    }

    public void setBillCycle (BillCycle billCycle)
    {
        this.billCycle = billCycle;
    }

    public String getDocTypeOutputCode ()
    {
        return docTypeOutputCode;
    }

    public void setDocTypeOutputCode (String docTypeOutputCode)
    {
        this.docTypeOutputCode = docTypeOutputCode;
    }

    public String getCustCode ()
    {
        return custCode;
    }

    public void setCustCode (String custCode)
    {
        this.custCode = custCode;
    }

    public String getRut ()
    {
        return rut;
    }

    public void setRut (String rut)
    {
        this.rut = rut;
    }

    public String getAccountType ()
    {
        return accountType;
    }

    public void setAccountType (String accountType)
    {
        this.accountType = accountType;
    }

    public List<Subscribers> getSubscribers ()
    {
        return subscribers;
    }

    public void setSubscribers (List<Subscribers> subscribers)
    {
        this.subscribers = subscribers;
    }

    public String getDocTypeDesc ()
    {
        return docTypeDesc;
    }

    public void setDocTypeDesc (String docTypeDesc)
    {
        this.docTypeDesc = docTypeDesc;
    }

    public String getAccountDeactivate ()
    {
        return accountDeactivate;
    }

    public void setAccountDeactivate (String accountDeactivate)
    {
        this.accountDeactivate = accountDeactivate;
    }

    public String getAccountActivate ()
    {
        return accountActivate;
    }

    public void setAccountActivate (String accountActivate)
    {
        this.accountActivate = accountActivate;
    }

    public String getDocTypeId ()
    {
        return docTypeId;
    }

    public void setDocTypeId (String docTypeId)
    {
        this.docTypeId = docTypeId;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [accountId = "+accountId+", accountIdHigh = "+accountIdHigh+", state = "+state+", csLevel = "+csLevel+", externalAccountId = "+externalAccountId+", billCycle = "+billCycle+", docTypeOutputCode = "+docTypeOutputCode+", custCode = "+custCode+", rut = "+rut+", accountType = "+accountType+", subscribers = "+subscribers+", docTypeDesc = "+docTypeDesc+", accountDeactivate = "+accountDeactivate+", accountActivate = "+accountActivate+", docTypeId = "+docTypeId+"]";
    }
}