package astronet.ec.vista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import astronet.ec.modelo.ActividadesTecnicas;
import astronet.ec.modelo.RegistroActividadesTecnicas;
import astronet.ec.on.RegistroActividadesTecnicasOn;

@ManagedBean
@ViewScoped
public class RegistroActividadesTecnicas_Controller implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<SelectItem> listaMeses;
	private List<SelectItem> listaYears;
	private List<SelectItem> listaDias;
	private List<RegistroActividadesTecnicas> listarRegistroActividadesTecnicas;
	private int year;
	private String mes;
	private int dia;
	@Inject
	private RegistroActividadesTecnicasOn regActTecON;
	

	@PostConstruct
	public void init() {
		try {
			listaMeses = new ArrayList<SelectItem>();
			listaYears = new ArrayList<SelectItem>();
			listaDias = new ArrayList<SelectItem>();
			Calendar fecha = Calendar.getInstance();
			int year_act = fecha.get(Calendar.YEAR);
			for(int i=2020;i<=year_act+5;i++) {
				listaYears.add(new SelectItem(i));
			}
			String month[] = {"ENERO","FEBRERO","MARZO","ABRIL","MAYO","JUNIO","JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE"};
			for(int i=0;i<month.length;i++) {
				listaMeses.add(new SelectItem(month[i]));
			}
			
			int num_mes = fecha.get(Calendar.MONTH);
			
			switch(num_mes) {
				case 0: case 2: case 4: case 6: case 7: case 9: case 11:
					for(int i=0;i<31;i++) {
						listaDias.add(new SelectItem(i+1));
					}
				break;
				case 3: case 5: case 8: case 10:
					for(int i=0;i<30;i++) {
						listaDias.add(new SelectItem(i+1));
					}
				break;
				case 1:
					if(year_act%4 == 0) {
						for(int i=0;i<29;i++) {
							listaDias.add(new SelectItem(i+1));
						}
					}else {
						for(int i=0;i<28;i++) {
							listaDias.add(new SelectItem(i+1));
						}
					}
				break;
			}
		}catch(Exception ex) {
			System.out.println("ERROR NO CARGA REGISTRO ACTIVIDAD CONTROLLER "+ex);
		}
		
		listarRegistroActividadesTecnicas = regActTecON.listarRegistrosActividades();
		System.out.println("numero de REGISTROS ----------------- " +listarRegistroActividadesTecnicas.size());
	}
	
	public List<SelectItem> getListaDias() {
		return listaDias;
	}


	public void setListaDias(List<SelectItem> listaDias) {
		this.listaDias = listaDias;
	}


	public int getDia() {
		return dia;
	}


	public void setDia(int dia) {
		this.dia = dia;
	}	

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public List<SelectItem> getListaMeses() {
		return listaMeses;
	}

	public void setListaMeses(List<SelectItem> listaMeses) {
		this.listaMeses = listaMeses;
	}

	public List<SelectItem> getListaYears() {
		return listaYears;
	}

	public void setListaYears(List<SelectItem> listaYears) {
		this.listaYears = listaYears;
	}

	public List<RegistroActividadesTecnicas> getListarRegistroActividadesTecnicas() {
		return listarRegistroActividadesTecnicas;
	}

	public void setListarRegistroActividadesTecnicas(List<RegistroActividadesTecnicas> listarRegistroActividadesTecnicas) {
		this.listarRegistroActividadesTecnicas = listarRegistroActividadesTecnicas;
	}
	
	
}
