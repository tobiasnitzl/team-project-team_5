package edu.hm.foodweek

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import edu.hm.foodweek.plans.persistence.MealPlanDao
import edu.hm.foodweek.plans.persistence.RecipeDao
import edu.hm.foodweek.plans.persistence.model.MealPlan
import edu.hm.foodweek.recipes.persistence.model.Recipe
import edu.hm.foodweek.users.persistence.UserDao
import edu.hm.foodweek.users.persistence.model.User
import edu.hm.foodweek.util.Converters
import java.util.concurrent.Executors


@Database(
    entities = [MealPlan::class,User::class,Recipe::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class FoodWeekDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun mealPlanDao(): MealPlanDao
    abstract fun recipeDao(): RecipeDao

    companion object {
        @Volatile
        private var INSTANCE: FoodWeekDatabase? = null
        fun getInstance(context: Context): FoodWeekDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FoodWeekDatabase::class.java,
                        "food_week_database"
                    )
                        .addCallback(object : RoomDatabase.Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                //pre-populate data
                                Executors.newSingleThreadExecutor().execute {
                                    instance?.let {
                                        // Insert data using daos
                                        // -> it.recipeDao().createRecipe()...
                                    }
                                }
                            }
                        })
                        .fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}

