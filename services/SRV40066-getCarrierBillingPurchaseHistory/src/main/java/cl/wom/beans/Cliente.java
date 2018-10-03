package cl.wom.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	
	private String mercado;
	private String idOferta;
	private String desOferta;
	private String idOccBscs;
	private String idOccCancel; 
	private String idOccRefund; 
	private BigDecimal mesesAntiguedad;
	private BigDecimal valorMinimoPlan;
	private Date fecDesde;
	private Date fecHasta;

	public Cliente() {

	}

	public String getMercado() {
		return mercado;
	}

	public void setMercado(String mercado) {
		this.mercado = mercado;
	}

	public String getIdOferta() {
		return idOferta;
	}

	public void setIdOferta(String idOferta) {
		this.idOferta = idOferta;
	}

	public String getDesOferta() {
		return desOferta;
	}

	public void setDesOferta(String desOferta) {
		this.desOferta = desOferta;
	}

	public String getIdOccBscs() {
		return idOccBscs;
	}

	public void setIdOccBscs(String idOccBscs) {
		this.idOccBscs = idOccBscs;
	}

	public String getIdOccCancel() {
		return idOccCancel;
	}

	public void setIdOccCancel(String idOccCancel) {
		this.idOccCancel = idOccCancel;
	}

	public String getIdOccRefund() {
		return idOccRefund;
	}

	public void setIdOccRefund(String idOccRefund) {
		this.idOccRefund = idOccRefund;
	}

	public BigDecimal getMesesAntiguedad() {
		return mesesAntiguedad;
	}

	public void setMesesAntiguedad(BigDecimal mesesAntiguedad) {
		this.mesesAntiguedad = mesesAntiguedad;
	}

	public BigDecimal getValorMinimoPlan() {
		return valorMinimoPlan;
	}

	public void setValorMinimoPlan(BigDecimal valorMinimoPlan) {
		this.valorMinimoPlan = valorMinimoPlan;
	}

	public Date getFecDesde() {
		return fecDesde;
	}

	public void setFecDesde(Date fecDesde) {
		this.fecDesde = fecDesde;
	}

	public Date getFecHasta() {
		return fecHasta;
	}

	public void setFecHasta(Date fecHasta) {
		this.fecHasta = fecHasta;
	}
	
	


	
}
