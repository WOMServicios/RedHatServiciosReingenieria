package cl.wom.middleware.vo;

import java.util.List;

public class AccountInformation
{
    private String rut;

    private List<Account> account;

    public String getRut ()
    {
        return rut;
    }

    public void setRut (String rut)
    {
        this.rut = rut;
    }

    public List<Account> getAccount ()
    {
        return account;
    }

    public void setAccount (List<Account> account)
    {
        this.account = account;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [rut = "+rut+", account = "+account+"]";
    }
}
			
	