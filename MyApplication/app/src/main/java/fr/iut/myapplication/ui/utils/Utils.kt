package fr.iut.myapplication.ui.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 *  Fonction factory permettant de créer des instances de ViewModelFactory pour
 *  alléger l'écriture lors de la récuperation d'une ViewModel qui prend des paramètres.
 */
@Suppress("UNCHECKED_CAST")
inline fun <VM : ViewModel> viewModelFactory(crossinline f: () -> VM) = object : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = f() as T
}