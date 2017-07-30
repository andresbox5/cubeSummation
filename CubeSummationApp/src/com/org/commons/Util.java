/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.org.commons;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

/**
 *
 * @author fabianandres
 */
public final class Util {
	
	private final static String JS_TRY = "try{";
    private final static String JS_CATH = " }catch(err){console.info('ERROR [com.hds.commons.controlador.util.Utilidad] '+err)}";
    
       
    public static void ejecutarJavaScript(String js) {
        final RequestContext context = RequestContext.getCurrentInstance();
        context.execute(JS_TRY + js + JS_CATH);
    }
    
    public static void ejecutarUpdate(String id) {
        final RequestContext context = RequestContext.getCurrentInstance();
        context.update(id);
    }
   
    public static void lanzaMensajeInfo(String mensaje) {
    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", mensaje));
    }

    public static void lanzaMensajeWarn(String mensaje) {
    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Info", mensaje));
    }
    
}
