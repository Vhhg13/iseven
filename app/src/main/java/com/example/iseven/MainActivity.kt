package com.example.iseven

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import com.example.iseven.data.model.KnownListItem
import com.example.iseven.ui.CheckFragment
import com.example.iseven.ui.KnownNumbersFragment
import com.example.iseven.ui.ViewKnownNumberFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val manualNavigation = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(!manualNavigation)
            setContentView(R.layout.activity_main)
        else {
            setContentView(R.layout.activity_main_manual)
            supportFragmentManager.commit {
                replace(R.id.container, CheckFragment())
            }
        }
    }

    fun toKnownNumbers(): Boolean{
        if(!manualNavigation) return manualNavigation

        supportFragmentManager.commit {
            replace(R.id.container, KnownNumbersFragment())
            addToBackStack(null)
        }
        return manualNavigation
    }

    fun toViewKnownNumber(item: KnownListItem): Boolean{
        if(!manualNavigation) return manualNavigation

        supportFragmentManager.commit {
            replace(R.id.container, ViewKnownNumberFragment().apply{arguments = Bundle().apply{putInt("int", item.number)}})
            addToBackStack(null)
        }
        return manualNavigation
    }
}