package com.amardeep.blog.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractEntityRepository<T> implements EntityRepository<T>{
	
	private final Class<T> type;
	
	public AbstractEntityRepository(Class<T> type) {
		this.type=type;
	}
	
	public Class<T> getType(){
		return this.type;
	}
	@PersistenceContext	
	private EntityManager entityManager;
	
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public T getEntity(Long id){
		return entityManager.find(getType(),id);
	}
	
	public T createEntity(T entity){
		entityManager.persist(entity);
		entityManager.flush();
		return entity;
	}
	
	public boolean updateEntity(T entity){
		return entityManager.merge(entity)!=null;
	}
	
	public boolean deleteEntity(Long id){
		T entity=entityManager.find(getType(),id);
		entityManager.remove(entity);
		return entity!=null;
	}
}
