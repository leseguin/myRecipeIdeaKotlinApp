package fr.iut.myapplication.ui.event

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import fr.iut.myapplication.R
import fr.iut.myapplication.data.Event
import fr.iut.myapplication.data.NEW_EVENT_ID
import fr.iut.myapplication.data.NEW_RECIPE_ID
import fr.iut.myapplication.data.persistance.database.EventDatabase
import fr.iut.myapplication.ui.recipe.RecipeFragment
import kotlinx.android.synthetic.main.fragment_event.view.*
import kotlinx.android.synthetic.main.item_list_event.view.*

class EventFragment : Fragment() {

    companion object {
        private const val EXTRA_EVENT_ID = "extra_eventid"


        fun newInstance(eventId: Long) = EventFragment().apply {
            arguments = bundleOf(EXTRA_EVENT_ID to eventId)
        }

    }

    private lateinit var event : Event
    private var eventId: Long = NEW_EVENT_ID


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        eventId = savedInstanceState?.getLong(EXTRA_EVENT_ID) ?: arguments?.getLong(EXTRA_EVENT_ID) ?: NEW_EVENT_ID

        event = if (eventId == NEW_EVENT_ID) {
            activity?.setTitle(R.string.add_recipe_title)
            Event()
        } else {
            EventDatabase.getInstance().eventDao().findById(eventId)
        }

        setHasOptionsMenu(true)
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(EXTRA_EVENT_ID, eventId)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_event, container, false)

        view.name_event.setText(event.name)

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != AppCompatActivity.RESULT_OK)
            return


    }
}