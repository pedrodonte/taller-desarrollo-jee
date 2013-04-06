package chat;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;

@Singleton
public class ChatServiceEJBImpl implements ChatServiceEJB{
	
	List<Usuario> usuariosConectados = new ArrayList<Usuario>();

	@Override
	public void usuarioIniciaSession(Usuario usuario) throws Exception {
		for(Usuario u : usuariosConectados){
			if(u.getUsername().equals(usuario.getUsername())){
				throw new Exception("El usuario ya esta conectado.");
			}
		}
		usuariosConectados.add(usuario);
	}

	@Override
	public void usuarioMensajePublico(Usuario usuario, String mensaje)
			throws Exception {
		
	}

	@Override
	public void usuarioMensajePrivado(Usuario usuarioEnvia, String mensaje,
			Usuario usuarioRecibe) throws Exception {
		
	}

	@Override
	public void usuarioCierraSession(Usuario usuario) throws Exception {
		for(Usuario u : usuariosConectados){
			if(u.getUsername().equals(usuario.getUsername())){
				usuariosConectados.remove(u);
			}
		}
	}

	@Override
	public List<Usuario> listaUsuariosConectados() throws Exception {
		return usuariosConectados;
	}

}
