package com.org.ws;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import com.org.bo.CubeSummationBO;
import com.org.commons.BusinessException;
import com.org.dm.CubeSummationDTO;


@WebService(endpointInterface = "com.org.ws.CubeSummationWS", serviceName = "CubeSummationWS",
portName = "UsuarioWSPort",
targetNamespace = "http://sortedset.com/wsdl")
@Stateless
public class CubeSummationWSImpl implements CubeSummationWS {

	@EJB
	private CubeSummationBO cubeSummationBO;

	/**
	 * Consume la capacidad calcularSumaBloque de cubeSummationBO
	 */
	@Override
	public CubeSummationDTO calcularSumaBloque(CubeSummationDTO cubeSummationDTO) throws BusinessException {		
		return cubeSummationBO.calcularSumaBloque(cubeSummationDTO);		
	}
	
	
}
