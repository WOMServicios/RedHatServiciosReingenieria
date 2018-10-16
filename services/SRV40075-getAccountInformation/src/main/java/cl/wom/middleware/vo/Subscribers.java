package cl.wom.middleware.vo;

import java.util.List;

public class Subscribers
{
    private String subscriberType;

    private List<SubscriberResources> subscriberResources;

    private String accountId;

    private String subscriberExpired;

    private String rut;

    private String state;

    private String subscriberId;

    private String subscriberActivate;

    private String subscriberIdContract;

    public String getSubscriberType ()
    {
        return subscriberType;
    }

    public void setSubscriberType (String subscriberType)
    {
        this.subscriberType = subscriberType;
    }

    public List<SubscriberResources> getSubscriberResources ()
    {
        return subscriberResources;
    }

    public void setSubscriberResources (List<SubscriberResources> subscriberResources)
    {
        this.subscriberResources = subscriberResources;
    }

    public String getAccountId ()
    {
        return accountId;
    }

    public void setAccountId (String accountId)
    {
        this.accountId = accountId;
    }

    public String getSubscriberExpired ()
    {
        return subscriberExpired;
    }

    public void setSubscriberExpired (String subscriberExpired)
    {
        this.subscriberExpired = subscriberExpired;
    }

    public String getRut ()
    {
        return rut;
    }

    public void setRut (String rut)
    {
        this.rut = rut;
    }

    public String getState ()
    {
        return state;
    }

    public void setState (String state)
    {
        this.state = state;
    }

    public String getSubscriberId ()
    {
        return subscriberId;
    }

    public void setSubscriberId (String subscriberId)
    {
        this.subscriberId = subscriberId;
    }

    public String getSubscriberActivate ()
    {
        return subscriberActivate;
    }

    public void setSubscriberActivate (String subscriberActivate)
    {
        this.subscriberActivate = subscriberActivate;
    }

    public String getSubscriberIdContract ()
    {
        return subscriberIdContract;
    }

    public void setSubscriberIdContract (String subscriberIdContract)
    {
        this.subscriberIdContract = subscriberIdContract;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [subscriberType = "+subscriberType+", subscriberResources = "+subscriberResources+", accountId = "+accountId+", subscriberExpired = "+subscriberExpired+", rut = "+rut+", state = "+state+", subscriberId = "+subscriberId+", subscriberActivate = "+subscriberActivate+", subscriberIdContract = "+subscriberIdContract+"]";
    }
}