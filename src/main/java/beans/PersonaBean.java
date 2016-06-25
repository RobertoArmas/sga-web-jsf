package beans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;
import mx.com.gm.sga.domain.Persona;
import mx.com.gm.sga.servicio.PersonaService;

@ManagedBean
@RequestScoped
public class PersonaBean implements Serializable{
	@EJB
	private PersonaService personaService;
	List<Persona> personas;

	private Persona persona;

	public Persona getPersona() {
		if(persona == null){
			persona = new Persona();
		}
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public PersonaService getPersonaService() {
		return personaService;
	}

	public void setPersonaService(PersonaService personaService) {
		this.personaService = personaService;
	}




	public Persona getPersonaSeleccionada() {
		return personaSeleccionada;
	}

	public void setPersonaSeleccionada(Persona personaSeleccionada) {
		this.personaSeleccionada = personaSeleccionada;
	}



	private Persona personaSeleccionada;

	public PersonaBean() {

	}


	@PostConstruct
	public void inicializar() {
		personas = personaService.listarPersonas();
	}

	public void editListener(RowEditEvent event) {
		Persona persona = (Persona) event.getObject();
		personaService.modificarPersona(persona);
	}

	public void deletePersona()
	{
		this.personaService.eliminarPersona(personaSeleccionada);
		personaSeleccionada = null;
	}

	public void ingresarPersona() {
		try {
			personaService.registrarPersona(persona);
		} catch (Exception e) {

		}

	}

	public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}
}