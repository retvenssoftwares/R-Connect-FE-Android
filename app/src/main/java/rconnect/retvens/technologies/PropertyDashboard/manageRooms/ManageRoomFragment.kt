package rconnect.retvens.technologies.PropertyDashboard.manageRooms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import rconnect.retvens.technologies.PropertyDashboard.manageRooms.rooms.RoomsFragment
import rconnect.retvens.technologies.PropertyDashboard.manageRooms.bedType.BedTypeFragment
import rconnect.retvens.technologies.PropertyDashboard.manageRooms.amenities.AminitiesFragment
import rconnect.retvens.technologies.PropertyDashboard.manageRooms.roomType.RoomTypeFragment
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

        replaceFragment(AminitiesFragment())

        bindingTab.cardAminities.setOnClickListener {
            replaceFragment(AminitiesFragment())
            bindingTab.cardAminities.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.primary))
            bindingTab.cardRoomType.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            bindingTab.cardBedType.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            bindingTab.cardRoom.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
        }

        bindingTab.cardRoomType.setOnClickListener {
            replaceFragment(RoomTypeFragment())
            bindingTab.cardAminities.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            bindingTab.cardRoomType.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.primary))
            bindingTab.cardBedType.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            bindingTab.cardRoom.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
        }

        bindingTab.cardBedType.setOnClickListener {
            replaceFragment(BedTypeFragment())
            bindingTab.cardAminities.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            bindingTab.cardRoomType.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            bindingTab.cardBedType.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.primary))
            bindingTab.cardRoom.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
        }

        bindingTab.cardRoom.setOnClickListener {
            replaceFragment(RoomsFragment())
            bindingTab.cardAminities.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            bindingTab.cardRoomType.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            bindingTab.cardBedType.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            bindingTab.cardRoom.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.primary))
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        if (fragment !=null){
            val transaction = childFragmentManager.beginTransaction()
            transaction.replace(R.id.manageRoomFragmentContainer,fragment)
            transaction.commit()
        }
    }
}