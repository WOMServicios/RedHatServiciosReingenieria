package cl.wom.middleware.bean;
import java.sql.SQLException;
import org.apache.camel.Exchange;
import com.google.gson.Gson;
import cl.wom.middleware.dao.SubscriberDAOImpl;
import cl.wom.middleware.proxy.ServiceError;
import cl.wom.middleware.util.ValidationUtil;
import cl.wom.middleware.vo.Subscriber;

public class SqlFormatterProcessor {
	
	public String sqlParserProductInventory(Exchange ex) throws SQLException, ClassNotFoundException, ServiceError {
		
		
		String subscriberId   = (String)ex.getIn().getHeader("subscriberId");

		if(!ValidationUtil.isLong(subscriberId)) {
			throw new ServiceError("454");
		}
		
 		SubscriberDAOImpl subscriberDAOImpl = new SubscriberDAOImpl();
 		Subscriber subscriber = subscriberDAOImpl.getSubscriber(subscriberId);

 		
 		if(subscriber.getProductOffer().isEmpty() || subscriber.getProductOffer().size() == 0) {
 			return "";
 		}

 		Gson gson = new Gson();
 		String jsonObj = gson.toJson(subscriber);
 		
 		if (jsonObj.length() > 0)
 			return jsonObj.toString();
 		else
 			return "";
	}
	
}
