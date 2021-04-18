package model.controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Coche;
import model.Concesionario;
import model.Venta;

public class ControladorConcesionario {
	private static ControladorConcesionario instance = null;

	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("JavaCochesJPA"); 
	
	/**
	 * 
	 * @return
	 */
	public static ControladorConcesionario getInstance () {
		if (instance == null) {
			instance = new ControladorConcesionario();
		}
		return instance;
	}
	
	/**
	 * 
	 */
	public ControladorConcesionario() {
	}
	
	
	/**
	 * 
	 * @return
	 */
	public Concesionario findPrimero () {
		Concesionario c = null;
		
		EntityManager em = factory.createEntityManager();
		Query q = em.createNativeQuery("select * from tutorialjavacoches.concesionario order by id limit 1", Concesionario.class);
		c = (Concesionario) q.getSingleResult();
		em.close();
		
		return c;
	}
	

	/**
	 * 
	 * @return
	 */
	public Concesionario findUltimo () {
		Concesionario c = null;
		
		EntityManager em = factory.createEntityManager();
		Query q = em.createNativeQuery("select * from tutorialjavacoches.concesionario order by id desc limit 1", Concesionario.class);
		c = (Concesionario) q.getSingleResult();
		em.close();
		
		return c;
	}
	
	
	public List<Concesionario> findAll () {
		EntityManager em = factory.createEntityManager();
		
		Query q = em.createNativeQuery("SELECT * FROM concesionario", Concesionario.class);
		
		List<Concesionario> list = (List<Concesionario>) q.getResultList();
		em.close();
		return list;
	}

	/**
	 * 
	 * @return
	 */
	public Concesionario findSiguiente (int idActual) {
		Concesionario c = null;
		
		EntityManager em = factory.createEntityManager();
		Query q = em.createNativeQuery("select * from tutorialjavacoches.concesionario where id > ? order by id limit 1", Concesionario.class);
		q.setParameter(1, idActual);
		c = (Concesionario) q.getSingleResult();
		em.close();
		
		return c;
	}
	

	/**
	 * 
	 * @return
	 */
	public Concesionario findAnterior (int idActual) {
		Concesionario c = null;
		
		EntityManager em = factory.createEntityManager();
		Query q = em.createNativeQuery("select * from tutorialjavacoches.concesionario where id < ? order by id desc limit 1", Concesionario.class);
		q.setParameter(1, idActual);
		c = (Concesionario) q.getSingleResult();
		em.close();
		
		return c;		
	}
	

	
	/**
	 * 
	 * @return
	 */
	public boolean guardar (Concesionario c) {
		try {
			EntityManager em = factory.createEntityManager();
			em.getTransaction().begin();
			if (c.getId() == 0) {
				em.persist(c);
			}
			else {
				em.merge(c);
			}
			em.getTransaction().commit();
			em.close();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}



	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public void borrar(Concesionario c) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		c=em.merge(c);
		em.remove(c);
		em.getTransaction().commit();
		em.close();
	}

}
