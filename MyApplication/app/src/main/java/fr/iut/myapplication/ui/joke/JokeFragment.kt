package fr.iut.myapplication.ui.joke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.iut.myapplication.R
import kotlinx.android.synthetic.main.fragment_joke.*

class JokeFragment : Fragment() {

    private  lateinit var jokeVM : JokeViewModel

    @Suppress("UNCHECKED_CAST")
    inline fun <VM : ViewModel> viewModelFactory(crossinline f: () -> VM) = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = f() as T
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_joke, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        jokeVM = JokeViewModel(requireContext())
        jokeVM.newJoke()
        button_joke.setOnClickListener { jokeVM.newJoke() }
        jokeVM.jokeLV.observeForever { text_joke.text = it}
    }


}