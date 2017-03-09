package gerenciador;

import java.time.*;
import java.util.HashSet;

import javafx.util.converter.LocalDateStringConverter;
import projeto.Projeto;

public class GerenciadorProjeto {
	
	HashSet<Projeto> projetos;
	
	public Projeto buscaProjeto (String codigo) throws Exception {
		for (Projeto projeto : projetos) {
			if (projeto.getCodigo().equals(codigo)) {
				return projeto;
			}
		}
		throw new Exception ("Erro na consulta de Projeto: Projeto nao encontrado");
	}
	
	public GerenciadorProjeto () {
		projetos = new HashSet<>();
	}
	
	public void iniciaSistema() {}
	public void fechaSistema() {}
	
	public boolean validaData(String dataInicio) throws Exception {
		if (dataInicio.trim().equals("") || dataInicio == null) {
			throw new Exception("Erro no cadastro de projeto: Data nula ou vazia");
		}
		if (dataInicio.length() != 10) {
			return false;
		}
		if (dataInicio.charAt(3) != '/' || dataInicio.charAt(6) != '/') {
			return false;
		}
		
		LocalDateStringConverter conversor = new LocalDateStringConverter();
		LocalDate data = conversor.fromString(dataInicio);
		if (data.getDayOfMonth() > data.lengthOfMonth() || data.getDayOfMonth() < 1){
			return false;
		}
		if (data.getMonthValue() > 12 || data.getMonthValue() < 1) {
			return false;
		}
		return true;
	}	
}
