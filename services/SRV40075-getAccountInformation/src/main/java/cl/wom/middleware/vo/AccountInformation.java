package cl.wom.middleware.vo;
public class AccountInformation
{
    private String rut;

    private Account[] account;

    public String getRut ()
    {
        return rut;
    }

    public void setRut (String rut)
    {
        this.rut = rut;
    }

    public Account[] getAccount ()
    {
        return account;
    }

    public void setAccount (Account[] account)
    {
        this.account = account;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [rut = "+rut+", account = "+account+"]";
    }
}
			
	