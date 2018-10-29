package cl.wom.middleware.vo;
public class SubscriberResources
{
    private String resourceId;

    private String resource;

    private String subscriberId;

    private String resourceActivate;

    private String resourceState;

    private String resourceDescription;

    private String resourceDeactivate;

    private String resourceType;

    public String getResourceId ()
    {
        return resourceId;
    }

    public void setResourceId (String resourceId)
    {
        this.resourceId = resourceId;
    }

    public String getResource ()
    {
        return resource;
    }

    public void setResource (String resource)
    {
        this.resource = resource;
    }

    public String getSubscriberId ()
    {
        return subscriberId;
    }

    public void setSubscriberId (String subscriberId)
    {
        this.subscriberId = subscriberId;
    }

    public String getResourceActivate ()
    {
        return resourceActivate;
    }

    public void setResourceActivate (String resourceActivate)
    {
        this.resourceActivate = resourceActivate;
    }

    public String getResourceState ()
    {
        return resourceState;
    }

    public void setResourceState (String resourceState)
    {
        this.resourceState = resourceState;
    }

    public String getResourceDescription ()
    {
        return resourceDescription;
    }

    public void setResourceDescription (String resourceDescription)
    {
        this.resourceDescription = resourceDescription;
    }

    public String getResourceDeactivate ()
    {
        return resourceDeactivate;
    }

    public void setResourceDeactivate (String resourceDeactivate)
    {
        this.resourceDeactivate = resourceDeactivate;
    }

    public String getResourceType ()
    {
        return resourceType;
    }

    public void setResourceType (String resourceType)
    {
        this.resourceType = resourceType;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [resourceId = "+resourceId+", resource = "+resource+", subscriberId = "+subscriberId+", resourceActivate = "+resourceActivate+", resourceState = "+resourceState+", resourceDescription = "+resourceDescription+", resourceDeactivate = "+resourceDeactivate+", resourceType = "+resourceType+"]";
    }
}
	