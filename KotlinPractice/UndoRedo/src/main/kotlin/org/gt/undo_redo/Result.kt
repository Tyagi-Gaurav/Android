package org.gt.undo_redo

typealias Result<T> = Either<T, Throwable>
fun <A> Success(a : A) : Result<A> = Either.Left(a)
fun <B> error(e : Exception) : Result<B> = Either.Right(e)
