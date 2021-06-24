package ru.pnzgu.iqorganizer

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import ru.pnzgu.iqorganizer.model.SimpleEvent
import ru.pnzgu.iqorganizer.ui.calendar.recycler.FullEvent
import ru.pnzgu.iqorganizer.ui.dialogs.CreateEventDialog

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private val db = (application as IQOrganizerApplication).database

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment)
            .navController
    }

    private val createEventButton: FloatingActionButton by lazy {
        findViewById(R.id.createEventButton)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        createEventButton.setOnClickListener {
            val dialog = CreateEventDialog.newInstance()
            supportFragmentManager.setFragmentResultListener(
                "create_event_result",
                this
            ) { key, bundle ->
                if (key == "create_event_result") {
                    val createdEvent: FullEvent = bundle.getParcelable("event")!!
                    val (masterEvent, simpleData) = createdEvent.toModels()
                    db.eventInfoDao().insert(masterEvent)
                    val simpleEvent = SimpleEvent(simpleData.id, masterEvent.id, simpleData.time)
                    db.simpleEventDao().insert(simpleEvent)
                }
            }
            dialog.show(supportFragmentManager, CreateEventDialog.TAG)
        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_calendar, R.id.nav_stats, R.id.nav_group
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}