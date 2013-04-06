package ejemplo.jsf;

import java.security.Principal;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class DashboardMB {
	
	private String userInfo;
	
	@PostConstruct
	public void init(){
		Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		setUserInfo("Usuario logeado "+principal.getName());
	}

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	
}
