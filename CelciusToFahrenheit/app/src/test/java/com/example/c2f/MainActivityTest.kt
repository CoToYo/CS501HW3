//import androidx.appcompat.app.AppCompatActivity
//import androidx.test.core.app.ActivityController
import com.example.c2f.MainActivity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import com.example.c2f.R
import org.junit.Assert.assertNotNull

@RunWith(RobolectricTestRunner::class)
class MainActivityTest {
    private lateinit var activityController: ActivityController<MainActivity>
    private lateinit var activity: MainActivity

    @Before
    fun setUp() {
        activityController = Robolectric.buildActivity(MainActivity::class.java)
        println(activityController)
        activity = activityController.get()
        println(activity)
        activityController.create().start().resume()
//        activity.setTheme(R.style.Theme_AppCompatzz) // set the theme before running the test
    }

    @Test
    fun testMainActivity() {
        val activity = Robolectric.buildActivity(MainActivity::class.java).create().get()
//        val main = Robolectric.setupActivity(MainActivity::class.java)
//        assertNotNull(main)
        // Add your test logic here
//        assertEquals(1,1)
//        assertEquals(4,4)
        assertEquals(100,activity.toCelsius(212))
        assertEquals(60,activity.toCelsius(140))
        assertEquals(25,activity.toCelsius(77))
        assertEquals(0,activity.toCelsius(32))

        assertEquals(212,activity.toFahrenheit(100))
        assertEquals(140,activity.toFahrenheit(60))
        assertEquals(77,activity.toFahrenheit(25))
        assertEquals(32,activity.toFahrenheit(0))

    }
}