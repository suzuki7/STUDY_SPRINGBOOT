package com.tuyano.springboot;

import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

@Repository
public class MyDataDaoImpl implements MyDataDao<MyData>{
	private static final long serialVaersionUID=1L;
	private EntityManager entityManager;
	
	public MyDataDaoImpl() {
		super();
	}
	public MyDataDaoImpl(EntityManager manager) {
		this();
		entityManager = manager;
	}
	@Override
	public List<MyData> getAll(){
//		Query query = entityManager.createQuery("from MyData");
//		@SuppressWarnings("unchecked")
//		List<MyData> list = query.getResultList();
//		entityManager.close();
//		return list;
		int offset = 1;
		int rimit =2;
		List<MyData> list = null;
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MyData> query = builder.createQuery(MyData.class);
		Root<MyData> root = query.from(MyData.class);
		query.select(root).orderBy(builder.asc(root.get("name")));
		list = (List<MyData>)entityManager.createQuery(query)
				.setFirstResult(offset)
				.setMaxResults(rimit)
				.getResultList();
		return list;
		}
	@Override
	public MyData findById(long id) {
		return (MyData)entityManager.createQuery("from MyData where id ="+id).getSingleResult();
	}
	@Override
	public List<MyData> findByName(String name) {
		return (List<MyData>)entityManager.createQuery("from MyDaya where name ="+name).getResultList();
	}
	
	
	
	@Override
	public List<MyData> find(String fstr) {
//		List<MyData> list = null;
//		Long fid = 0L;
//		try {
//			fid = Long.parseLong(fstr);
//		}catch(NumberFormatException e) {
//			//e.printStackTrace();
//		}
//		Query query = entityManager.createNamedQuery("findWithName")
//				.setParameter("fname","%" + fstr + "%");
//		list = query.getResultList();
//		return list;
		
		CriteriaBuilder bulider = entityManager.getCriteriaBuilder();
		CriteriaQuery<MyData> query = bulider.createQuery(MyData.class);
		Root<MyData> root = query.from(MyData.class);
		query.select(root).where(bulider.equal(root.get("name"), fstr));
		List <MyData> list = null;
		list = (List<MyData>)entityManager.createQuery(query).getResultList();
		return list;
	}
}
