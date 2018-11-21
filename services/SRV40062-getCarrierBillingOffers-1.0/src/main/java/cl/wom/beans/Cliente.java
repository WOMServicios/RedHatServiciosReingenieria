package cl.wom.beans;

import java.io.Serializable;
import java.sql.Date;


public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	private String rut;
	private String customerId;
	private String customerIdHigh;
	private String contractId;
	private String numCelular;
	private String tipoContrato;
	private String rateplan;
	private String antiguedad;
	private String ciclo;
	private String estadoContrato;
	private Date fechaActivacion;
	private String mercado;
	private String cargoBasico;
	private String dnNum;
	private String contador;
	private String idOferta;
	private String desOferta;
	private String idOccBscs;
	private String mesesAntiguedad;
	private String valorMinimo;
	private Date fecDesde;
	private Date fecHasta;
	private String paymentProviderTransaction;
    private String secuencia;

	public Cliente() {

	}
	
	public String getSecuencia() {
        return secuencia;
    }    public void setSecuencia(String secuencia) {
        this.secuencia = secuencia;
    }    public String getPaymentProviderTransaction() {
        return paymentProviderTransaction;
    }    public void setPaymentProviderTransaction(String paymentProviderTransaction) {
        this.paymentProviderTransaction = paymentProviderTransaction;
    }

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerIdHigh() {
		return customerIdHigh;
	}

	public void setCustomerIdHigh(String customerIdHigh) {
		this.customerIdHigh = customerIdHigh;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getNumCelular() {
		return numCelular;
	}

	public void setNumCelular(String numCelular) {
		this.numCelular = numCelular;
	}

	public String getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	public String getRateplan() {
		return rateplan;
	}

	public void setRateplan(String rateplan) {
		this.rateplan = rateplan;
	}

	public String getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(String antiguedad) {
		this.antiguedad = antiguedad;
	}

	public String getCiclo() {
		return ciclo;
	}

	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}

	

	public String getEstadoContrato() {
		return estadoContrato;
	}

	public void setEstadoContrato(String estadoContrato) {
		this.estadoContrato = estadoContrato;
	}

	public Date getFechaActivacion() {
		return fechaActivacion;
	}

	public void setFechaActivacion(Date fechaActivacion) {
		this.fechaActivacion = fechaActivacion;
	}

	public String getMercado() {
		return mercado;
	}

	public void setMercado(String mercado) {
		this.mercado = mercado;
	}

	public String getCargoBasico() {
		return cargoBasico;
	}

	public void setCargoBasico(String cargoBasico) {
		this.cargoBasico = cargoBasico;
	}

	public String getDnNum() {
		return dnNum;
	}

	public void setDnNum(String dnNum) {
		this.dnNum = dnNum;
	}

	public String getContador() {
		return contador;
	}

	public void setContador(String contador) {
		this.contador = contador;
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

	public String getMesesAntiguedad() {
		return mesesAntiguedad;
	}

	public void setMesesAntiguedad(String mesesAntiguedad) {
		this.mesesAntiguedad = mesesAntiguedad;
	}

	public String getValorMinimo() {
		return valorMinimo;
	}

	public void setValorMinimo(String valorMinimo) {
		this.valorMinimo = valorMinimo;
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

	@Override
	public String toString() {
		return "Cliente [rut=" + rut + ", customerId=" + customerId + ", customerIdHigh=" + customerIdHigh
				+ ", contractId=" + contractId + ", numCelular=" + numCelular + ", tipoContrato=" + tipoContrato
				+ ", rateplan=" + rateplan + ", antiguedad=" + antiguedad + ", ciclo=" + ciclo + ", estadoContrato="
				+ estadoContrato + ", fechaActivacion=" + fechaActivacion + ", mercado=" + mercado + ", cargoBasico="
				+ cargoBasico + ", dnNum=" + dnNum + ", contador=" + contador + ", idOferta=" + idOferta
				+ ", desOferta=" + desOferta + ", idOccBscs=" + idOccBscs + ", mesesAntiguedad=" + mesesAntiguedad
				+ ", valorMinimo=" + valorMinimo + ", fecDesde=" + fecDesde + ", fecHasta=" + fecHasta + "]";
	}

}
