package com.suyashsrijan.flashingnumbers.app.repository

interface NumbersRepository<N : Number> {

    fun appendNumber(number: N)
    fun appendNumbers(numbers: Collection<N>)
    fun getNumber(index: Int): N
    fun getAllNumbers(): List<N>
    fun updateNumber(index: Int, newNumber: N)
    fun deleteNumber(index: Int)
    fun deleteAllNumbers()
    fun getSumOfAllNumbers(): N

}