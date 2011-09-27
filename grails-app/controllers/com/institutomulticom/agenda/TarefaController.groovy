package com.institutomulticom.agenda

class TarefaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [tarefaInstanceList: Tarefa.list(params), tarefaInstanceTotal: Tarefa.count()]
    }

    def create = {
        def tarefaInstance = new Tarefa()
        tarefaInstance.properties = params
        return [tarefaInstance: tarefaInstance]
    }

    def save = {
        def tarefaInstance = new Tarefa(params)
        if (tarefaInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'tarefa.label', default: 'Tarefa'), tarefaInstance.id])}"
            redirect(action: "show", id: tarefaInstance.id)
        }
        else {
            render(view: "create", model: [tarefaInstance: tarefaInstance])
        }
    }

    def show = {
        def tarefaInstance = Tarefa.get(params.id)
        if (!tarefaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tarefa.label', default: 'Tarefa'), params.id])}"
            redirect(action: "list")
        }
        else {
            [tarefaInstance: tarefaInstance]
        }
    }

    def edit = {
        def tarefaInstance = Tarefa.get(params.id)
        if (!tarefaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tarefa.label', default: 'Tarefa'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [tarefaInstance: tarefaInstance]
        }
    }

    def update = {
        def tarefaInstance = Tarefa.get(params.id)
        if (tarefaInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (tarefaInstance.version > version) {
                    
                    tarefaInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'tarefa.label', default: 'Tarefa')] as Object[], "Another user has updated this Tarefa while you were editing")
                    render(view: "edit", model: [tarefaInstance: tarefaInstance])
                    return
                }
            }
            tarefaInstance.properties = params
            if (!tarefaInstance.hasErrors() && tarefaInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'tarefa.label', default: 'Tarefa'), tarefaInstance.id])}"
                redirect(action: "show", id: tarefaInstance.id)
            }
            else {
                render(view: "edit", model: [tarefaInstance: tarefaInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tarefa.label', default: 'Tarefa'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def tarefaInstance = Tarefa.get(params.id)
        if (tarefaInstance) {
            try {
                tarefaInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'tarefa.label', default: 'Tarefa'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'tarefa.label', default: 'Tarefa'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tarefa.label', default: 'Tarefa'), params.id])}"
            redirect(action: "list")
        }
    }
}
