package com.kp.beautytips.data.hair

import android.content.Context
import com.kp.beautytips.R
import com.kp.beautytips.model.ListModel

object Greying {

    fun remedy(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.indian_gooseberry_amla)
        listModel.descriptionName = context.getString(R.string.overnight)
        listModel.image = R.drawable.grey_amarnath_remedy
        listModel.details = context.getString(R.string.greyhair_remedy_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.onion_juice)
        listModel1.descriptionName = context.getString(R.string.one_hr)
        listModel1.image = R.drawable.grey_onion_remedy
        listModel1.details = context.getString(R.string.greyhair_remedy_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.black_tea)
        listModel2.descriptionName = context.getString(R.string.twenty_min)
        listModel2.image = R.drawable.grey_blacktea_remedy
        listModel2.details = context.getString(R.string.greyhair_remedy_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.coconutmilk_lemonjuice)
        listModel3.descriptionName = context.getString(R.string.one_hr)
        listModel3.image = R.drawable.coconutoil_lemon_dandruff
        listModel3.details = context.getString(R.string.greyhair_remedy_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.rosemary_sage)
        listModel4.descriptionName = context.getString(R.string.twenty_min)
        listModel4.image = R.drawable.grey_rosemary_remdy
        listModel4.details = context.getString(R.string.greyhair_remedy_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.curry_leaves)
        listModel5.descriptionName = context.getString(R.string.fortyfive_min)
        listModel5.image = R.drawable.grey_curryleaves_remedy
        listModel5.details = context.getString(R.string.greyhair_remedy_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.henna_pack)
        listModel6.descriptionName = context.getString(R.string.fortyfive_min)
        listModel6.image = R.drawable.grey_heena_remedy
        listModel6.details = context.getString(R.string.greyhair_remedy_seven)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.amaranth_leaves_extract)
        listModel7.descriptionName = context.getString(R.string.fifteen_min)
        listModel7.image = R.drawable.grey_amarnath_remedy
        listModel7.details = context.getString(R.string.greyhair_remedy_eight)
        list.add(listModel7)
        val listModel8 = ListModel()
        listModel8.title = context.getString(R.string.bhringraj_eclipta_alba)
        listModel8.descriptionName = context.getString(R.string.thirty_min)
        listModel8.image = R.drawable.grey_bhringraj
        listModel8.details = context.getString(R.string.greyhair_remedy_nine)
        list.add(listModel8)
        return list
    }

    fun diet(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.hair_greying_main_one)
        listModel.descriptionName = context.getString(R.string.hair_greying_detail_one)
        listModel.image = R.drawable.grey_wheatgrass_diet
        listModel.details = context.getString(R.string.greyinghair_diet_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.hair_greying_main_two)
        listModel1.descriptionName = context.getString(R.string.hair_greying_detail_two)
        listModel1.image = R.drawable.grey_blackmolasses_diet
        listModel1.details = context.getString(R.string.greyinghair_diet_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.hair_greying_main_three)
        listModel2.descriptionName = context.getString(R.string.hair_greying_detail_three)
        listModel2.image = R.drawable.grey_cataselle_diet
        listModel2.details = context.getString(R.string.greyinghair_diet_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.hair_greying_main_four)
        listModel3.descriptionName = context.getString(R.string.hair_greying_detail_four)
        listModel3.image = R.drawable.grey_ashwagandha_diet
        listModel3.details = context.getString(R.string.greyinghair_diet_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.hair_greying_main_five)
        listModel4.descriptionName = context.getString(R.string.hair_greying_detail_five)
        listModel4.image = R.drawable.grey_blackseasme_diet
        listModel4.details = context.getString(R.string.greyinghair_diet_five)
        list.add(listModel4)
        return list
    }

    fun exercise(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.downward_facing_dog_pose)
        listModel.descriptionName = context.getString(R.string.adho_mukha_svanasana)
        listModel.image = R.drawable.dandruff_adhomukh
        listModel.details = context.getString(R.string.dandruff_exercise_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.lying_down_Body_twist_yoga)
        listModel1.descriptionName = context.getString(R.string.chakrasana)
        listModel1.image = R.drawable.dandruff_chakrasana
        listModel1.details = context.getString(R.string.dandruff_exercise_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.rubbing_nails)
        listModel2.descriptionName = context.getString(R.string.balyam_yoga)
        listModel2.image = R.drawable.dandruff_nailrubbing
        listModel2.details = context.getString(R.string.dandruff_exercise_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.shoulder_stand_yoga)
        listModel3.descriptionName = context.getString(R.string.sarvangasana)
        listModel3.image = R.drawable.dandruff_shoulderstand
        listModel3.details = context.getString(R.string.dandruff_exercise_four)
        list.add(listModel3)
        return list
    }
}