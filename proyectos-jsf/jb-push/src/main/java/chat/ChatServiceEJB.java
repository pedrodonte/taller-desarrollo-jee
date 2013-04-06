package chat;

import java.util.List;

import javax.ejb.Local;

@Local
public interface ChatServiceEJB {
	public void usuarioIniciaSession(Usuario usuario) throws Exception;

	public void usuarioMensajePublico(Usuario usuario, String mensaje)
			throws Exception;

	public void usuarioMensajePrivado(Usuario usuarioEnvia, String mensaje,
			Usuario usuarioRecibe) throws Exception;

	public void usuarioCierraSession(Usuario usuario) throws Exception;
	
	public List<Usuario> listaUsuariosConectados() throws Exception;
}
