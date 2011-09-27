package com.institutomulticom.agenda

class Tarefa {
	
    String descricao
    String prioridade
    Date data    
    Usuario usuario
    boolean status
    Date criado

    static constraints = {
	descricao(nullable:false,blank:false)
	prioridade(inList:["Informação","Baixa","Media","Alta","Urgente"])
	data(nullble:true)	
    }
}
