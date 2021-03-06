package edu.hm.foodweek.plans.persistence

import android.app.Application
import edu.hm.foodweek.inject.appModule
import edu.hm.foodweek.plans.persistence.model.MealPlan
import edu.hm.foodweek.util.UserProvider
import edu.hm.foodweek.util.amplify.FoodWeekClient
import io.mockk.MockKAnnotations
import io.mockk.mockkClass
import org.junit.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.mock.MockProviderRule

class MealPlanRepositoryTest : KoinTest, Application() {

    lateinit var mockMealPlanDao: MealPlanDao
    lateinit var mealPlanRepository: MealPlanRepository
    lateinit var mockFoodWeekClient: FoodWeekClient
    lateinit var mockUserProvider: UserProvider

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger(Level.DEBUG)
        androidContext(this@MealPlanRepositoryTest)
        modules(appModule)
    }

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        mockkClass(type = clazz, relaxed = true)
    }

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
//        mockFoodWeekClient = declareMock { }
//        mockUserProvider = declareMock {
//            every { getUserID() } returns "ID"
//        }
//        mockMealPlanDao = declareMock {
//            every { getAllMealPlans() } returns MutableLiveData(
//                createMealPlans()
//            )
//            every { getMealPlan(1) } returns MutableLiveData(mealplan1)
//            every { getMealPlanCreatedByUser(userId) } returns MutableLiveData(
//                listOf(mealplan2, mealplan3)
//            )
//            coJustRun { createMealPlan(any()) }
//        }
//        mealPlanRepository =
//            MealPlanRepository(mockMealPlanDao, mockUserProvider, mockFoodWeekClient)
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun testGetLiveDataAllMealPlans() {
        // Needs refactoring for retrofit
    }


    @Test
    fun testCreateMealPlan() {
        //runBlocking {
        //    mealPlanRepository.createMealPlan(mealplan1)
        //}

        //coVerify(atLeast = 1) { mockMealPlanDao.createMealPlan(mealplan1) }
    }

    private fun testEqualityOfMealPlans(
        expected: List<MealPlan>,
        actual: List<MealPlan>?
    ) {
        val expectedIterator = expected.iterator()
        val actualIterator = actual!!.iterator()

        while (expectedIterator.hasNext() && actualIterator.hasNext()) {
            val currentExpected = expectedIterator.next()
            val currentActual = actualIterator.next()
            if (currentActual != currentExpected) {
                Assert.assertTrue(false)
            }
        }

        Assert.assertTrue(true)
    }
}