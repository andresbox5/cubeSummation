package com.org.ws;

import javax.ejb.Local;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.org.commons.BusinessException;
import com.org.dm.CubeSummationDTO;


@WebService(targetNamespace = "http://sortedset.com/wsdl")
@SOAPBinding(style = SOAPBinding.Style.RPC, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
@Local
public interface CubeSummationWS {

	/**
	 * Calcula la suma por cada bloque ingresado
	 * @param cubeSummationDTO
	 * @return CubeSummationDTO
	 * @throws BusinessException
	 */
	@WebMethod
	CubeSummationDTO calcularSumaBloque(@WebParam(name = "cubeSummationDTO") CubeSummationDTO cubeSummationDTO) throws BusinessException;
    	
}
