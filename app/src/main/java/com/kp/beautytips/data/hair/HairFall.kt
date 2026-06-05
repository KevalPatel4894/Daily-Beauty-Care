package com.kp.beautytips.data.hair

import android.content.Context
import com.kp.beautytips.R
import com.kp.beautytips.model.ListModel

object HairFall {

    fun remedy(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.coconut_oil)
        listModel.descriptionName = context.getString(R.string.one_hr)
        listModel.image = R.drawable.coconutoil_hairfall_remedy
        listModel.details = context.getString(R.string.hairfall_remedy_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.licorice_root)
        listModel1.descriptionName = context.getString(R.string.overnight)
        listModel1.image = R.drawable.licorice_hairfall_remedy
        listModel1.details = context.getString(R.string.hairfall_remedy_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.aloe_vera)
        listModel2.descriptionName = context.getString(R.string.few_hours)
        listModel2.image = R.drawable.aloevera_split
        listModel2.details = context.getString(R.string.hairfall_remedy_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.fresh_onion_juice)
        listModel3.descriptionName = context.getString(R.string.fifteen_min)
        listModel3.image = R.drawable.grey_onion_remedy
        listModel3.details = context.getString(R.string.hairfall_remedy_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.garlic)
        listModel4.descriptionName = context.getString(R.string.thirteen_min)
        listModel4.image = R.drawable.garlic_hairfall_remedy
        listModel4.details = context.getString(R.string.hairfall_remedy_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.henna)
        listModel5.descriptionName = context.getString(R.string.one_hr)
        listModel5.image = R.drawable.grey_heena_remedy
        listModel5.details = context.getString(R.string.hairfall_remedy_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.hibiscus)
        listModel6.descriptionName = context.getString(R.string.few_hours)
        listModel6.image = R.drawable.hibiscus_hairfall_remedy
        listModel6.details = context.getString(R.string.hairfall_remedy_seven)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.indian_gooseberry_amla)
        listModel7.descriptionName = context.getString(R.string.till_it_dry)
        listModel7.image = R.drawable.grey_amla_remedy
        listModel7.details = context.getString(R.string.hairfall_remedy_eight)
        list.add(listModel7)
        val listModel8 = ListModel()
        listModel8.title = context.getString(R.string.egg)
        listModel8.descriptionName = context.getString(R.string.twenty_min)
        listModel8.image = R.drawable.puffy_eggwhite
        listModel8.details = context.getString(R.string.hairfall_remedy_nine)
        list.add(listModel8)
        return list
    }

    fun diet(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.hair_hairfall_main_one)
        listModel.descriptionName = context.getString(R.string.hair_hairfall_detail_one)
        listModel.image = R.drawable.honey
        listModel.details = context.getString(R.string.hairfall_diet_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.hair_hairfall_main_two)
        listModel1.descriptionName = context.getString(R.string.hair_hairfall_detail_two)
        listModel1.image = R.drawable.hairfall_carrot_diet
        listModel1.details = context.getString(R.string.hairfall_diet_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.hair_hairfall_main_three)
        listModel2.descriptionName = context.getString(R.string.hair_greying_detail_three)
        listModel2.image = R.drawable.eggmask_split
        listModel2.details = context.getString(R.string.hairfall_diet_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.hair_hairfall_main_four)
        listModel3.descriptionName = context.getString(R.string.hair_hairfall_detail_four)
        listModel3.image = R.drawable.hairfall_oats_diet
        listModel3.details = context.getString(R.string.hairfall_diet_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.hair_hairfall_main_five)
        listModel4.descriptionName = context.getString(R.string.hair_hairfall_detail_five)
        listModel4.image = R.drawable.hairfall_lentis_diet
        listModel4.details = context.getString(R.string.hairfall_diet_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.hair_hairfall_main_six)
        listModel5.descriptionName = context.getString(R.string.hair_hairfall_detail_six)
        listModel5.image = R.drawable.hairfall_salmon_diet
        listModel5.details = context.getString(R.string.hairfall_diet_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.hair_hairfall_main_seven)
        listModel6.descriptionName = context.getString(R.string.hair_hairfall_detail_seven)
        listModel6.image = R.drawable.hairfall_dry_fruit
        listModel6.details = context.getString(R.string.hairfall_diet_seven)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.hair_hairfall_main_eight)
        listModel7.descriptionName = context.getString(R.string.hair_hairfall_detail_eight)
        listModel7.image = R.drawable.spinach_dandruff
        listModel7.details = context.getString(R.string.hairfall_diet_eight)
        list.add(listModel7)
        val listModel8 = ListModel()
        listModel8.title = context.getString(R.string.hair_hairfall_main_nine)
        listModel8.descriptionName = context.getString(R.string.hair_hairfall_detail_nine)
        listModel8.image = R.drawable.hairfall_oyster_diet
        listModel8.details = context.getString(R.string.hairfall_diet_nine)
        list.add(listModel8)
        val listModel9 = ListModel()
        listModel9.title = context.getString(R.string.hair_hairfall_main_ten)
        listModel9.descriptionName = context.getString(R.string.hair_hairfall_detail_ten)
        listModel9.image = R.drawable.hairfall_oils_diet
        listModel9.details = context.getString(R.string.hairfall_diet_ten)
        list.add(listModel9)
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
        listModel1.title = context.getString(R.string.vajrasana)
        listModel1.descriptionName = context.getString(R.string.vajrasana)
        listModel1.image = R.drawable.vajrasana
        listModel1.details = context.getString(R.string.hairfall_exercise_one)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.ustrasana)
        listModel2.descriptionName = context.getString(R.string.ustrasana)
        listModel2.image = R.drawable.ustarasana
        listModel2.details = context.getString(R.string.hairfall_exercise_two)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.utthanasana)
        listModel3.descriptionName = context.getString(R.string.utthanasana)
        listModel3.image = R.drawable.utthanasana
        listModel3.details = context.getString(R.string.hairfall_exercise_three)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.shoulder_stand_yoga)
        listModel4.descriptionName = context.getString(R.string.sarvangasana)
        listModel4.image = R.drawable.dandruff_shoulderstand
        listModel4.details = context.getString(R.string.hairfall_exercise_three)
        list.add(listModel4)
        return list
    }
}