package fr.iut.myapplication.ui.disconnect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import fr.iut.myapplication.R
import fr.iut.myapplication.data.WebService.Connexion
import kotlinx.android.synthetic.main.fragment_disconnect.*

class DisconnectFragment : Fragment() {


    private  lateinit var disconnectVM : DisconnectViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_disconnect, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        disconnectVM = DisconnectViewModel(requireContext())

        disconnectVM.jokeLV.observe(viewLifecycleOwner, Observer { text_joke.text = it })


    }







}