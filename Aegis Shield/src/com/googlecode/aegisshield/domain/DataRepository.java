/*
 * Mar 1, 2009
 *
 * This source code is provided under the GPL v3 Open Source license agreement.
 */
package com.googlecode.aegisshield.domain;

import java.util.List;

import android.net.Uri;

/**
 * 	Interface for defining the contract of domain repositories. All repositories must implement this
 * interface. This is also a generic type interface, so it can be used for all types of domain objects
 * 
 * @author Mihai Campean
 */
public interface DataRepository<E> {
	/**
	 * 	The save method for data repositories.
	 * 
	 * @param domainObject
	 * @return
	 */
	public Uri save(E domainObject);
	
	/**
	 * 	Loads all the objects from the database.
	 * 
	 * @return
	 */
	public List<E> loadAll();
	
	/**
	 * 	Deletes the specified domain object from the database.
	 * 
	 * @param domainObject
	 * @return the the number of deleted rows
	 */
	public int delete(E domainObject);
	
	/**
	 * 	Updates the specified domain object in the database.
	 * @param domainObject
	 * @return
	 */
	public int update(E domainObject);
}
