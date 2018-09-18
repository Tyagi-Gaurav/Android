package org.gt.undo_redo

sealed class Either<A, B> {
    data class Left<A, B>(val la: A) : Either<A, B>()
    data class Right<A, B>(val lb: B) : Either<A, B>()
}
