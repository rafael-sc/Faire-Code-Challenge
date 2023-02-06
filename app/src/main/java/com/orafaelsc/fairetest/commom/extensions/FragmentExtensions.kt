package com.orafaelsc.fairetest.commom.extensions // ktlint-disable filename

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.SharedFlow

fun <T> Fragment.setupObserver(
    pair: Pair<LiveData<T>, (T) -> Unit>
) {
    pair.first.observe(viewLifecycleOwner) { it?.let(pair.second) }
}

fun <T> Fragment.setupObserverOnCreated(
    pair: Pair<SharedFlow<T>, (T) -> Unit>,
) = viewLifecycleOwner.lifecycleScope.launchWhenCreated {
    pair.first.collect { it?.let(pair.second) }
}

fun <T> Fragment.setupObserverOnStarted(
    pair: Pair<SharedFlow<T>, (T) -> Unit>,
) = viewLifecycleOwner.lifecycleScope.launchWhenStarted {
    pair.first.collect { it?.let(pair.second) }
}