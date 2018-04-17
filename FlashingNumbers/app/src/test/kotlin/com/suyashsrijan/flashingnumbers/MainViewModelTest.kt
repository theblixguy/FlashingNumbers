package com.suyashsrijan.flashingnumbers

import com.suyashsrijan.flashingnumbers.app.repository.NumbersRepositoryImpl
import com.suyashsrijan.flashingnumbers.main.viewmodel.MainViewModel
import io.kotlintest.shouldBe
import io.kotlintest.specs.FunSpec

class MainViewModelTest : FunSpec() {

    init {
        val mainRepository = NumbersRepositoryImpl()
        val mainViewModel = MainViewModel(mainRepository)

        test("Sum of numbers when the view model is created for the first time must be 21.00") {
            mainViewModel.sumOfNumbers.get() shouldBe "21.00"
        }

        test("The view model must contain the numbers 1f - 6f when its created for the first time") {
            mainViewModel.getNumberForIndex(0) shouldBe 1f
            mainViewModel.getNumberForIndex(1) shouldBe 2f
            mainViewModel.getNumberForIndex(2) shouldBe 3f
            mainViewModel.getNumberForIndex(3) shouldBe 4f
            mainViewModel.getNumberForIndex(4) shouldBe 5f
            mainViewModel.getNumberForIndex(5) shouldBe 6f
        }

        test("The first number must now be 10f and the sum must now be 30.00f") {
            mainViewModel.updateNumber(0, "10.0")
            mainViewModel.getNumberForIndex(0) shouldBe 10f
            mainViewModel.sumOfNumbers.get() shouldBe "30.00"
        }

        test("The view model must now contain the numbers 5f, 10f, 15f, 20f, 25f, 30f") {
            mainViewModel.updateNumber(0, "5.0")
            mainViewModel.updateNumber(1, "10.0")
            mainViewModel.updateNumber(2, "15.0")
            mainViewModel.updateNumber(3, "20.0")
            mainViewModel.updateNumber(4, "25.0")
            mainViewModel.updateNumber(5, "30.0")

            mainViewModel.getNumberForIndex(0) shouldBe 5f
            mainViewModel.getNumberForIndex(1) shouldBe 10f
            mainViewModel.getNumberForIndex(2) shouldBe 15f
            mainViewModel.getNumberForIndex(3) shouldBe 20f
            mainViewModel.getNumberForIndex(4) shouldBe 25f
            mainViewModel.getNumberForIndex(5) shouldBe 30f
        }

        test("The sum of numbers must now be 105.00") {
            mainViewModel.sumOfNumbers.get() shouldBe "105.00"
        }

        test("The view model's internal repository must now be empty") {
            mainRepository.deleteAllNumbers()
            mainRepository.getAllNumbers() shouldBe emptyList()
            mainRepository.getSumOfAllNumbers() shouldBe 0f
        }

        test("The view model must now throw an IllegalArgumentException when accessing the first number") {
            mainViewModel.getNumberForIndex(0) shouldBe 5f
        }
    }
}