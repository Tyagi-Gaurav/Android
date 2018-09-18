package org.gt.undo_redo

interface Command<R> {
    fun execute() : Result<R>

    fun undo() : Result<R>
}


