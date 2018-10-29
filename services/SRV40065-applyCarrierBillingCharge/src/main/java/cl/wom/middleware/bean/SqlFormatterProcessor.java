package cl.wom.middleware.bean;

import org.apache.camel.Exchange;

import cl.wom.middleware.dao.CarrierChargeDAO;
import cl.wom.middleware.vo.FacturacionCharge;

public class SqlFormatterProcessor {

    public String sqlParserCarrierCharge(Exchange ex) {

        String userId = (String) ex.getIn().getHeader("userId");
        String payment = (String) ex.getIn().getHeader("paymet");

        System.out.println(userId);
        System.out.println(payment);

        System.out.println("CARRIER");

        CarrierChargeDAO carrierChargeDAO = new CarrierChargeDAO();
        FacturacionCharge facturacionCharge = carrierChargeDAO.getFacturacionCharge(userId, payment);

        return null;

    }

}