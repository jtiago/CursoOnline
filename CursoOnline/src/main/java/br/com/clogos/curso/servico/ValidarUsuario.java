package br.com.clogos.curso.servico;

import br.com.clogos.curso.dao.GenericDAO;
import br.com.clogos.curso.dao.impl.GenericDAOImpl;
import br.com.clogos.curso.entidades.Usuario;
import br.com.clogos.curso.seguranca.CriptografiaBase64;

public class ValidarUsuario {
	
	private static ValidarUsuario instance;
	private GenericDAO genericDAO;
	
	public static ValidarUsuario getInstance() {
		if( instance == null ) {
			instance = new ValidarUsuario();
		}
		return instance;
	}
	
	public Boolean validarCredenciais(Usuario usuario) {
		Usuario userValido = (Usuario) getGenericDAO().findString(Usuario.class, "emailUsuario", usuario.getEmailUsuario());	
		Boolean isValiso = Boolean.FALSE;
		
		if(userValido != null) {
			if(userValido.getSenhaUsuario().equals(CriptografiaBase64.encrypt(usuario.getSenhaUsuario()))) {
				isValiso = Boolean.TRUE;
			}
		}
		return isValiso;
	}
	
	@SuppressWarnings("rawtypes")
	public GenericDAO getGenericDAO() {
		return genericDAO == null ? genericDAO = new GenericDAOImpl() : genericDAO;
	}

}