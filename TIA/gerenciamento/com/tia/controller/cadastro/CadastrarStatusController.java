package com.tia.controller.cadastro;

import javax.swing.JOptionPane;

import alocacaoDinamica.tabelaEspalhamento.TabelaEspalhamento;

import com.tia.controller.constantes.Persistencia;
import com.tia.dao.StatusDataAccess;
import com.tia.model.Status;

/**
 * Cadastrar um novo status no sistemas
 * @author bruno.martins
 *
 */
public class CadastrarStatusController implements ValidarCadastro{

	StringBuilder msg = new StringBuilder();
	
	@Override
	public boolean validaEntradas(TabelaEspalhamento<String, Object> parametros) {
		if(parametros.get("status").toString().isEmpty())
			msg.append("-O Status não pode ser nulo!");
		return msg.toString().isEmpty();
	}

	@Override
	public Persistencia persistir(TabelaEspalhamento<String, Object> parametros) {
		StatusDataAccess dao = new StatusDataAccess();
		Status status = new Status();
		status.setId();
		status.setStatus(parametros.get("status").toString());
		return dao.gravar(status);
	}

	@Override
	public void validaPersistencia(Persistencia response) {
		if(response == Persistencia.GRAVADO)
		    JOptionPane.showMessageDialog(null, "O status foi gravado com sucesso!", "Gravado!", JOptionPane.INFORMATION_MESSAGE);
		if(response == Persistencia.ERRO)
		    JOptionPane.showMessageDialog(null, "Erro na gravação!", "Erro!", JOptionPane.ERROR_MESSAGE);
		if(response == Persistencia.DUPLICADO)
		    JOptionPane.showMessageDialog(null, "O status já existe!", "Duplicado!", JOptionPane.WARNING_MESSAGE);
		if(response == null)
		    JOptionPane.showMessageDialog(null, msg.toString(),"Erro!",JOptionPane.WARNING_MESSAGE);
		
	}

}
