package org.gt.undo_redo

class CommandController(private val undoStack : MutableList<Command<*>>,
                        private val redoStack : MutableList<Command<*>>) {
    fun <R> execute(command : Command<R>) : Result<R> {
        redoStack.clear()
        val executionResult = command.execute()
        undoStack.add(0, command)
        return executionResult
    }

    fun undo() {
        if (undoStack.size > 0) {
            val command = undoStack.removeAt(0)
            redoStack.add(0, command)
            command.undo()
        }
    }

    fun redo() {
        if (redoStack.size > 0) {
            val command = redoStack.removeAt(0)
            execute(command)
        }
    }
}