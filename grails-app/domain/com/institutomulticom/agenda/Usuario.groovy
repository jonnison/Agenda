package com.institutomulticom.agenda

import com.institutomulticom.agenda.Regra

/**
 * User domain class.
 */
class Usuario {
	static transients = ['pass']
	static hasMany = [authorities: Regra]
	static belongsTo = Regra

	/** Username */
	String username
	/** User Real Name*/
	String userRealName
	/** MD5 Password */
	String passwd
	/** enabled */
	boolean enabled

	String email
	boolean emailShow

	/** description */
	String description = ''

	/** plain password to create a MD5 password */
	String pass = '[secret]'

	static constraints = {
		username(blank: false, unique: true)
		userRealName(blank: false)
		passwd(blank: false)
		enabled()
	}
}
