package cl.wom.middleware.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import cl.wom.middleware.util.ConnectionFactory;
import cl.wom.middleware.util.ConnectionFactory.DataBaseSchema;
import cl.wom.middleware.util.PropertiesUtil;
import cl.wom.middleware.vo.FacturacionCharge;

public class CarrierChargeDAO {

    static PropertiesUtil util = new PropertiesUtil();
    static Properties prop = util.getProperties("APP_ENV");

    public FacturacionCharge getFacturacionCharge(String userId, String payment) {

        userId = userId == null ? "" : userId;
        payment = payment == null ? "" : payment;

        FacturacionCharge facturacionCharge = null;
        Connection conn = null;
        Statement stmt;

        try {

            conn = ConnectionFactory.getConnection(DataBaseSchema.WAPPL, prop);
            stmt = conn.createStatement();

            String queryCharge = "SELECT COUNT(USER_ID) AS CONTADOR FROM CARRIERBILLING.RECEP_PAGOS_CARRIER_BILING_TO WHERE USER_ID = "
                    + userId + " AND PAYMENT_PROVIDER_TRANSACTION = " + payment
                    + " AND RESPONSE_PAY ='OK' AND ACTION ='AUTORIZED'";
            ResultSet rsCont = stmt.executeQuery(queryCharge);

            if (rsCont.next()) {

                String cont = rsCont.getString("CONTADOR");

                System.out.println("Contador: " + cont);

            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }

        return facturacionCharge;
    }

}