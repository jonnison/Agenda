package com.institutomulticom.agenda

/**
 * Request Map domain class.
 */
class Request {

	String url
	String configAttribute

	static constraints = {
		url(blank: false, unique: true)
		configAttribute(blank: false)
	}
}
