package rconnect.retvens.technologies.PropertyDashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import rconnect.retvens.technologies.R
import rconnect.retvens.technologies.databinding.FragmentManageRoomBinding


class ManageRoomFragment : Fragment() {

    private lateinit var bindingTab:FragmentManageRoomBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        bindingTab = FragmentManageRoomBinding.inflate(layoutInflater,container,false)
        return bindingTab.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }
}