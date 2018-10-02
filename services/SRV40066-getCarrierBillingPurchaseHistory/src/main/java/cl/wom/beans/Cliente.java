package cl.wom.beans;

import java.io.Serializable;
import java.math.BigDecimal;
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
	private Timestamp fecDesde;
	private Timestamp fecHasta;

	public Cliente() {

	}
	
	

	public String getIdOccBscs() {
		return idOccBscs;
	}



	public void setIdOccBscs(String idOccBscs) {
		this.idOccBscs = idOccBscs;
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

	public Timestamp getFecDesde() {
		return fecDesde;
	}

	public void setFecDesde(Timestamp fecDesde) {
		this.fecDesde = fecDesde;
	}

	public Timestamp getFecHasta() {
		return fecHasta;
	}

	public void setFecHasta(Timestamp fecHasta) {
		this.fecHasta = fecHasta;
	}



	@Override
	public String toString() {
		return "Cliente [mercado=" + mercado + ", idOferta=" + idOferta + ", desOferta=" + desOferta + ", idOccBscs="
				+ idOccBscs + ", idOccCancel=" + idOccCancel + ", idOccRefund=" + idOccRefund + ", mesesAntiguedad="
				+ mesesAntiguedad + ", valorMinimoPlan=" + valorMinimoPlan + ", fecDesde=" + fecDesde + ", fecHasta="
				+ fecHasta + "]";
	}
	


	
}
