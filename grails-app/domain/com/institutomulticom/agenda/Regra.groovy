package com.institutomulticom.agenda

import com.institutomulticom.agenda.Usuario

/**
 * Authority domain class.
 */
class Regra {

	static hasMany = [people: Usuario]

	/** description */
	String description
	/** ROLE String */
	String authority

	static constraints = {
		authority(blank: false, unique: true)
		description()
	}
}
