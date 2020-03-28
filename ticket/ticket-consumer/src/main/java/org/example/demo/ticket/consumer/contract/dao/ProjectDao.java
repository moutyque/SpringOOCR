package org.example.demo.ticket.consumer.contract.dao;

import java.util.List;

import org.example.demo.ticket.model.bean.projet.Projet;

public interface ProjectDao {

	Projet getProject(Long id);

	List<Projet> getListProjet();

}
