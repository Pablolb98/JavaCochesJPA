package model.controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Coche;
import model.Fabricante;
import model.Venta;

public class ControladorVenta {
	private static ControladorVenta instance = null;

	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("JavaCochesJPA"); 
	
	/**
	 * 
	 * @return
	 */
	public static ControladorVenta getInstance () {
		if (instance == null) {
			instance = new ControladorVenta();
		}
		return instance;
	}
	
	/**
	 * 
	 */
	public ControladorVenta() {
	}
	
	
	/**
	 * 
	 * @return
	 */
	public Venta findPrimero () {
		Venta c = null;
		
		EntityManager em = factory.createEntityManager();
		Query q = em.createNativeQuery("select * from tutorialjavacoches.venta order by id limit 1", Venta.class);
		c = (Venta) q.getSingleResult();
		em.close();
		
		return c;
	}
	

	/**
	 * 
	 * @return
	 */
	public Venta findUltimo () {
		Venta c = null;
		
		EntityManager em = factory.createEntityManager();
		Query q = em.createNativeQuery("select * from tutorialjavacoches.venta order by id desc limit 1", Venta.class);
		c = (Venta) q.getSingleResult();
		em.close();
		
		return c;
	}
	

	/**
	 * 
	 * @return
	 */
	public Venta findSiguiente (int idActual) {
		Venta c = null;
		
		EntityManager em = factory.createEntityManager();
		Query q = em.createNativeQuery("select * from tutorialjavacoches.venta where id > ? order by id limit 1", Venta.class);
		q.setParameter(1, idActual);
		c = (Venta) q.getSingleResult();
		em.close();
		
		return c;
	}
	

	/**
	 * 
	 * @return
	 */
	public Venta findAnterior (int idActual) {
		Venta c = null;
		
		EntityManager em = factory.createEntityManager();
		Query q = em.createNativeQuery("select * from tutorialjavacoches.venta where id < ? order by id desc limit 1", Venta.class);
		q.setParameter(1, idActual);
		c = (Venta) q.getSingleResult();
		em.close();
		
		return c;		
	}
	

	public boolean guardar (Venta v) {
		try {
			EntityManager em = factory.createEntityManager();
			em.getTransaction().begin();
			if (v.getId() == 0) {
				em.persist(v);
			}
			else {
				em.merge(v);
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
	public void borrar(Venta v) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		v=em.merge(v);
		em.remove(v);
		em.getTransaction().commit();
		em.close();
	}

	
	/**
	 * 
	 * @return
	 */


}
