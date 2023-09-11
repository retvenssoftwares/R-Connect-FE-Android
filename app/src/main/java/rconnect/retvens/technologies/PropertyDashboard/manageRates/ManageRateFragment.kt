package rconnect.retvens.technologies.PropertyDashboard.manageRates

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import rconnect.retvens.technologies.PropertyDashboard.manageRates.rateType.RateTypeFragment
import rconnect.retvens.technologies.PropertyDashboard.manageRates.roomRates.RoomRatesFragment
import rconnect.retvens.technologies.PropertyDashboard.manageRates.tax.TaxFragment
import rconnect.retvens.technologies.PropertyDashboard.manageRooms.bedType.BedTypeFragment
import rconnect.retvens.technologies.R
import rconnect.retvens.technologies.databinding.FragmentManageRateBinding

class ManageRateFragment : Fragment() {

    private lateinit var bindingTab: FragmentManageRateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bindingTab = FragmentManageRateBinding.inflate(layoutInflater, container, false)
        return bindingTab.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        replaceFragment(RateTypeFragment())

        bindingTab.cardRateTypes.setOnClickListener {
            replaceFragment(RateTypeFragment())
            bindingTab.cardRateTypes.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.primary))
            bindingTab.cardRoomRates.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            bindingTab.cardSeasonsRate.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            bindingTab.cardTax.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
        }

        bindingTab.cardRoomRates.setOnClickListener {
            replaceFragment(RoomRatesFragment())
            bindingTab.cardRateTypes.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            bindingTab.cardRoomRates.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.primary))
            bindingTab.cardSeasonsRate.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            bindingTab.cardTax.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
        }

        bindingTab.cardSeasonsRate.setOnClickListener {
            replaceFragment(BedTypeFragment())
            bindingTab.cardRateTypes.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            bindingTab.cardRoomRates.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            bindingTab.cardSeasonsRate.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.primary))
            bindingTab.cardTax.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
        }

        bindingTab.cardTax.setOnClickListener {
            replaceFragment(TaxFragment())
            bindingTab.cardRateTypes.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            bindingTab.cardRoomRates.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            bindingTab.cardSeasonsRate.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            bindingTab.cardTax.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.primary))
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        if (fragment !=null){
            val transaction = childFragmentManager.beginTransaction()
            transaction.replace(R.id.manageRateFragmentContainer,fragment)
            transaction.commit()
        }
    }
}