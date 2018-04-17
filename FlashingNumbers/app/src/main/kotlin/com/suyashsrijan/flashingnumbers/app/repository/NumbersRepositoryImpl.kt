package com.suyashsrijan.flashingnumbers.app.repository

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NumbersRepositoryImpl @Inject constructor() : NumbersRepository<Float> {

    private val numbersList: MutableList<Float> = mutableListOf()

    override fun appendNumber(number: Float) {
        numbersList.add(number)
    }

    override fun appendNumbers(numbers: Collection<Float>) {
        numbersList.addAll(numbers)
    }

    override fun getNumber(index: Int): Float {
        return numbersList[index]
    }

    override fun getAllNumbers(): List<Float> {
        return numbersList.toList()
    }

    override fun updateNumber(index: Int, newNumber: Float) {
        numbersList[index] = newNumber
    }

    override fun deleteNumber(index: Int) {
        numbersList.removeAt(index)
    }

    override fun deleteAllNumbers() {
        numbersList.clear()
    }

    override fun getSumOfAllNumbers(): Float {
        return numbersList.sum()
    }
}