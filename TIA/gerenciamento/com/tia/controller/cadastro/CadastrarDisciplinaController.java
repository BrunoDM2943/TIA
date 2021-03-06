package com.tia.controller.cadastro;

import javax.swing.JOptionPane;

import alocacaoDinamica.tabelaEspalhamento.TabelaEspalhamento;

import com.tia.controller.constantes.Persistencia;
import com.tia.dao.DisciplinaDataAccess;
import com.tia.model.Curso;
import com.tia.model.Disciplina;

/**
 * Classe responsável por controlar a gravação de uma nova disciplina
 * 
 * @author bruno.martins
 * @since 19/05/2014
 * 
 */
public class CadastrarDisciplinaController implements ValidarCadastro {

	StringBuilder msg = new StringBuilder();

	@Override
	public boolean validaEntradas(TabelaEspalhamento<String, Object> parametros) {
		if (parametros.get("nome").toString().isEmpty())
			msg.append("-Nome \n");
		if (parametros.get("curso").toString().isEmpty())
			msg.append("-Curso \n");
		return msg.toString().isEmpty();
	}

	@Override
	public Persistencia persistir(TabelaEspalhamento<String, Object> parametros) {
		Disciplina disc = new Disciplina();
		Curso curso = null;
		DisciplinaDataAccess dao = new DisciplinaDataAccess();
		disc.setIdDisciplina();
		disc.setNome(parametros.get("nome").toString());
		curso = (Curso) parametros.get("curso");
		disc.setIdCurso(curso.getIdCurso());
		disc.setNomeCurso(curso.getNome());
		disc.setSemestre((int) parametros.get("semestre"));
		return dao.gravar(disc);
	}

	@Override
	public void validaPersistencia(Persistencia response) {
		if (response == Persistencia.GRAVADO)
			JOptionPane.showMessageDialog(null,
					"A disciplina foi gravada com sucesso!", "Gravado!",
					JOptionPane.INFORMATION_MESSAGE);
		if (response == Persistencia.ERRO)
			JOptionPane.showMessageDialog(null, "Erro na gravação!", "Erro!",
					JOptionPane.ERROR_MESSAGE);
		if (response == Persistencia.DUPLICADO)
			JOptionPane.showMessageDialog(null, "A disciplina já existe!",
					"Duplicado!", JOptionPane.WARNING_MESSAGE);
		if (response == null)
			JOptionPane.showMessageDialog(null, msg.toString(), "Erro!",
					JOptionPane.WARNING_MESSAGE);
	}

}
