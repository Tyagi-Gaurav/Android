package org.gt.undo_redo

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class CommandControllerTest {

    private val undoQueue = mutableListOf<Command<*>>()
    private val redoQueue = mutableListOf<Command<*>>()
    private val commandController = CommandController(undoQueue, redoQueue)
    private val command = mockk<Command<Int>>()

    @Before
    fun setUp() {
        every { command.execute() }.answers{ Success(1)}
        every { command.undo() }.answers{ Success(0)}
    }

    @Test
    fun shouldExecuteCommand() {
        //when
        val result = commandController.execute(command)

        //then
        assertThat(result).isNotNull
        assertThat(result).isEqualTo(Success(1))
        verify {command.execute()}
    }

    @Test
    fun shouldClearTheRedoQueueWhenCommandExecutes() {
        //given
        redoQueue.add(command)
        val tempCommandController = CommandController(undoQueue, redoQueue)

        //when
        tempCommandController.execute(command)

        //then
        assertThat(redoQueue.size).isEqualTo(0)
    }

    @Test
    fun postExecutionShouldEnqueueCommandOnUndoQueue() {
        //when
        commandController.execute(command)

        //then
        assertThat(undoQueue.size).isEqualTo(1)
        assertThat(undoQueue.get(0)).isEqualTo(command)
    }

    @Test
    fun undoShouldGetCommandFromQueueAndExecute() {
        //when
        commandController.execute(command)
        commandController.undo()

        //then
        assertThat(undoQueue.size).isEqualTo(0)
        assertThat(redoQueue.size).isEqualTo(1)
        verify {command.undo()}
    }

    @Test
    fun redoShouldGetCommandOffRedoQueueAndExecute() {
        //when
        commandController.execute(command)
        commandController.undo()
        commandController.redo()

        //then
        assertThat(undoQueue.size).isEqualTo(1)
        assertThat(redoQueue.size).isEqualTo(0)
        verify(exactly = 2) {command.execute()}
    }

    @Test
    fun executeAndUndoShouldLeaveModelStateConsistent() {
        var sum = 40
        object: Command<Int> {
            var oldSum = 0
            override fun execute(): Result<Int> {
                oldSum = sum
                sum += 20
                return Success(sum)
            }

            override fun undo(): Result<Int> {
                sum = oldSum
                return Success(sum)
            }
        }

        //when
        commandController.execute(command)
        commandController.undo()

        //then
        assertThat(sum).isEqualTo(40)
    }
}