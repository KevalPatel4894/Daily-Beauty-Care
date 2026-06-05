package com.kp.beautytips.data.handsfeet

import android.content.Context
import com.kp.beautytips.R
import com.kp.beautytips.model.ListModel

object DryAndRoughHand {

    fun remedy(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.olive_oil)
        listModel.descriptionName = context.getString(R.string.ten_min)
        listModel.image = R.drawable.lice_oliveoil
        listModel.details = context.getString(R.string.dryroughhands_remedy_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.oatmeal)
        listModel1.descriptionName = context.getString(R.string.ten_min)
        listModel1.image = R.drawable.dryhands_oatmeal
        listModel1.details = context.getString(R.string.dryroughhands_remedy_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.coconut_oil)
        listModel2.descriptionName = context.getString(R.string.five_min)
        listModel2.image = R.drawable.coconutoil_hairfall_remedy
        listModel2.details = context.getString(R.string.dryroughhands_remedy_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.milk_cream)
        listModel3.descriptionName = context.getString(R.string.fifteen_min)
        listModel3.image = R.drawable.dryhands_milkcream
        listModel3.details = context.getString(R.string.dryroughhands_remedy_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.honey)
        listModel4.descriptionName = context.getString(R.string.ten_min)
        listModel4.image = R.drawable.honey
        listModel4.details = context.getString(R.string.dryroughhands_remedy_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.aloe_vera)
        listModel5.descriptionName = context.getString(R.string.ten_min)
        listModel5.image = R.drawable.aloevera_split
        listModel5.details = context.getString(R.string.dryroughhands_remedy_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.lemon_juice)
        listModel6.descriptionName = context.getString(R.string.five_min)
        listModel6.image = R.drawable.eyebrows_lemon
        listModel6.details = context.getString(R.string.dryroughhands_remedy_seven)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.yogurt)
        listModel7.descriptionName = context.getString(R.string.ten_min)
        listModel7.image = R.drawable.dryhands_yogurt
        listModel7.details = context.getString(R.string.dryroughhands_remedy_eight)
        list.add(listModel7)
        val listModel8 = ListModel()
        listModel8.title = context.getString(R.string.overripe_bananas)
        listModel8.descriptionName = context.getString(R.string.thirty_min)
        listModel8.image = R.drawable.banana_split
        listModel8.details = context.getString(R.string.dryroughhands_remedy_nine)
        list.add(listModel8)
        val listModel9 = ListModel()
        listModel9.title = context.getString(R.string.avocado)
        listModel9.descriptionName = context.getString(R.string.ten_min)
        listModel9.image = R.drawable.dryhands_avacado
        listModel9.details = context.getString(R.string.dryroughhands_remedy_ten)
        list.add(listModel9)
        return list
    }

    fun diet(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.handsfeet_dryroughhands_main_one)
        listModel.descriptionName = context.getString(R.string.handsfeet_dryroughhands_detail_one)
        listModel.image = R.drawable.omega3_dandruff
        listModel.details = context.getString(R.string.dryroughhands_diet_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.handsfeet_dryroughhands_main_two)
        listModel1.descriptionName = context.getString(R.string.handsfeet_dryroughhands_detail_two)
        listModel1.image = R.drawable.puffy_juices
        listModel1.details = context.getString(R.string.dryroughhands_diet_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.handsfeet_dryroughhands_main_three)
        listModel2.descriptionName = context.getString(R.string.handsfeet_dryroughhands_detail_three)
        listModel2.image = R.drawable.dryhand_leafyveg
        listModel2.details = context.getString(R.string.dryroughhands_diet_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.handsfeet_dryroughhands_main_four)
        listModel3.descriptionName = context.getString(R.string.handsfeet_dryroughhands_detail_four)
        listModel3.image = R.drawable.dryhand_liopicacid
        listModel3.details = context.getString(R.string.dryroughhands_diet_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.handsfeet_dryroughhands_main_five)
        listModel4.descriptionName = context.getString(R.string.handsfeet_dryroughhands_detail_five)
        listModel4.image = R.drawable.dryhand_fiber
        listModel4.details = context.getString(R.string.dryroughhands_diet_five)
        list.add(listModel4)
        return list
    }

    fun exercise(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.avoid_soap_if_can)
        listModel.descriptionName = context.getString(R.string.avoid_soap)
        listModel.image = R.drawable.handsfeet_avoidsoap
        listModel.details = context.getString(R.string.dryroughhands_exercise_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.avoid_hot_air_dryers)
        listModel1.descriptionName = context.getString(R.string.avoid_air_dryers_hands)
        listModel1.image = R.drawable.handsfeet_avoidhairdryer
        listModel1.details = context.getString(R.string.dryroughhands_exercise_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.wear_gloves)
        listModel2.descriptionName = context.getString(R.string.preffered_cotton_leather)
        listModel2.image = R.drawable.handsfeet_weargloves
        listModel2.details = context.getString(R.string.dryroughhands_exercise_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.use_humidifier)
        listModel3.descriptionName = context.getString(R.string.while_sleep)
        listModel3.image = R.drawable.drydmg_humidifier
        listModel3.details = context.getString(R.string.dryroughhands_exercise_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.additional_tips)
        listModel4.descriptionName = context.getString(R.string.more_tips)
        listModel4.image = R.drawable.dryhands_title
        listModel4.details = context.getString(R.string.dryroughhands_exercise_five)
        list.add(listModel4)
        return list
    }
}