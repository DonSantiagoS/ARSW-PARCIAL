package eci.arsw.covidanalyzer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * ---------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------
 * 													Cache
 * ---------------------------------------------------------------------------------------------------------------------------
 * 
 * ---------------------------------------------------------------------------------------------------------------------------
 * @author Santiago Buitrago
 * ---------------------------------------------------------------------------------------------------------------------------
 */
public class Cache <E> implements CacheInterface <E>{

	private List<E> cacheTotal;
	
	public Cache(){
		cacheTotal = new ArrayList<E>();
	}
	
	/**
     * añadir un elemento
	 * @param E elemento a añadir
     */
	@Override
	public void addElement(E elem) {
		cacheTotal.add(elem);
	}
	
	/**
     * obtener el elemento
	 * @param UUID id a consultar
     */
	@Override
	public E getUUID(UUID id) {
		E elem = null;
		for (E e: cacheTotal) {
			if (e.equals(id)) elem = e;
		}
		return elem;
	}
	
	/**
     * obtener todos los elementos
     */
	@Override
	public List<E> getAll() {
		return cacheTotal;
	}
	
	
}