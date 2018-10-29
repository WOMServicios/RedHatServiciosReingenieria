package cl.wom.middleware.bean;
import java.sql.SQLException;
import org.apache.camel.Exchange;
import org.json.JSONArray;
import cl.wom.middleware.dao.SubscriberDAOImpl;
import cl.wom.middleware.vo.Subscriber;

public class SqlFormatterProcessor {
	
	public String sqlParserProductInventory(Exchange ex) throws SQLException {
		
		String subscriberId   = (String)ex.getIn().getHeader("subscriberId");
		
 		SubscriberDAOImpl subscriberDAOImpl = new SubscriberDAOImpl();
 		Subscriber subscriber = subscriberDAOImpl.getSubscriber(subscriberId);

 		
 		JSONArray jsonObj = new org.json.JSONArray(subscriber);
 		
 		if (jsonObj.length() > 0)
 			return jsonObj.toString();
 		else
 			return "";
	}
	
}
