package com.kp.beautytips.data.hair

import android.content.Context
import com.kp.beautytips.R
import com.kp.beautytips.model.ListModel

object HeadLice {

    fun remedy(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.garlic)
        listModel.descriptionName = context.getString(R.string.thirty_min)
        listModel.image = R.drawable.garlic_hairfall_remedy
        listModel.details = context.getString(R.string.headlice_remedy_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.baby_oil)
        listModel1.descriptionName = context.getString(R.string.overnight)
        listModel1.image = R.drawable.lice_babyoil
        listModel1.details = context.getString(R.string.headlice_remedy_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.olive_oil)
        listModel2.descriptionName = context.getString(R.string.overnight)
        listModel2.image = R.drawable.lice_oliveoil
        listModel2.details = context.getString(R.string.headlice_remedy_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.salt_vinegar)
        listModel3.descriptionName = context.getString(R.string.two_hr)
        listModel3.image = R.drawable.lice_salt
        listModel3.details = context.getString(R.string.headlice_remedy_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.petroleum_jelly)
        listModel4.descriptionName = context.getString(R.string.overnight)
        listModel4.image = R.drawable.lice_petroleum
        listModel4.details = context.getString(R.string.headlice_remedy_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.tea_tree_oil)
        listModel5.descriptionName = context.getString(R.string.thirty_min)
        listModel5.image = R.drawable.teatreeoil_dandruff
        listModel5.details = context.getString(R.string.headlice_remedy_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.coconut_oil)
        listModel6.descriptionName = context.getString(R.string.overnight)
        listModel6.image = R.drawable.coconutoil_hairfall_remedy
        listModel6.details = context.getString(R.string.headlice_remedy_seven)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.white_vinegar)
        listModel7.descriptionName = context.getString(R.string.two_hr)
        listModel7.image = R.drawable.lice_whitevinegar
        listModel7.details = context.getString(R.string.headlice_remedy_eight)
        list.add(listModel7)
        val listModel8 = ListModel()
        listModel8.title = context.getString(R.string.sesame_oil)
        listModel8.descriptionName = context.getString(R.string.overnight)
        listModel8.image = R.drawable.puffy_eggwhite
        listModel8.details = context.getString(R.string.headlice_remedy_nine)
        list.add(listModel8)
        val listModel9 = ListModel()
        listModel9.title = context.getString(R.string.mayonnaise)
        listModel9.descriptionName = context.getString(R.string.overnight)
        listModel9.image = R.drawable.mayonnaise_split
        listModel9.details = context.getString(R.string.headlice_remedy_ten)
        list.add(listModel9)
        return list
    }

    fun diet(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.hair_headlice_main_one)
        listModel.descriptionName = context.getString(R.string.hair_headlice_detail_one)
        listModel.image = R.drawable.lice_vitamineral
        listModel.details = context.getString(R.string.headlice_diet_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.hair_headlice_main_two)
        listModel1.descriptionName = context.getString(R.string.hair_headlice_detail_two)
        listModel1.image = R.drawable.lice_protein
        listModel1.details = context.getString(R.string.headlice_diet_two)
        list.add(listModel1)
        return list
    }

    fun exercise(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.wet_combing)
        listModel.descriptionName = context.getString(R.string.using_neat_comb)
        listModel.image = R.drawable.dandruff_adhomukh
        listModel.details = context.getString(R.string.headlice_exercise_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.sanitize_brushes)
        listModel1.descriptionName = context.getString(R.string.washing_brushes)
        listModel1.image = R.drawable.lice_sanatizebrushes
        listModel1.details = context.getString(R.string.headlice_exercise_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.get_out_vacuum)
        listModel2.descriptionName = context.getString(R.string.vacuuming_room)
        listModel2.image = R.drawable.lice_vacuumcleaner
        listModel2.details = context.getString(R.string.headlice_exercise_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.wash_risk_items)
        listModel3.descriptionName = context.getString(R.string.clothing_bedding_blankets)
        listModel3.image = R.drawable.lice_washing
        listModel3.details = context.getString(R.string.headlice_exercise_four)
        list.add(listModel3)
        return list
    }
}