package com.kp.beautytips.data.eyes

import android.content.Context
import com.kp.beautytips.R
import com.kp.beautytips.model.ListModel

object PuffyEyes {

    fun remedy(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.cold_spoons)
        listModel.descriptionName = context.getString(R.string.few_min)
        listModel.image = R.drawable.puffy_coldspoon
        listModel.details = context.getString(R.string.puffyeyes_remedy_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.tea_bags)
        listModel1.descriptionName = context.getString(R.string.fifteen_min)
        listModel1.image = R.drawable.darkcrcl_teabag
        listModel1.details = context.getString(R.string.puffyeyes_remedy_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.cucumber)
        listModel2.descriptionName = context.getString(R.string.ten_min)
        listModel2.image = R.drawable.darkcrcl_cucumber
        listModel2.details = context.getString(R.string.puffyeyes_remedy_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.egg_whites)
        listModel3.descriptionName = context.getString(R.string.fifteen_min)
        listModel3.image = R.drawable.puffy_eggwhite
        listModel3.details = context.getString(R.string.puffyeyes_remedy_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.potato)
        listModel4.descriptionName = context.getString(R.string.few_min)
        listModel4.image = R.drawable.darkcrcl_potato
        listModel4.details = context.getString(R.string.puffyeyes_remedy_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.cold_water)
        listModel5.descriptionName = context.getString(R.string.few_min)
        listModel5.image = R.drawable.puffy_coldwater
        listModel5.details = context.getString(R.string.puffyeyes_remedy_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.salt_water)
        listModel6.descriptionName = context.getString(R.string.twenty_min)
        listModel6.image = R.drawable.lice_salt
        listModel6.details = context.getString(R.string.puffyeyes_remedy_seven)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.strawberries)
        listModel7.descriptionName = context.getString(R.string.few_min)
        listModel7.image = R.drawable.puffy_strawberries
        listModel7.details = context.getString(R.string.puffyeyes_remedy_eight)
        list.add(listModel7)
        val listModel8 = ListModel()
        listModel8.title = context.getString(R.string.aloe_vera)
        listModel8.descriptionName = context.getString(R.string.fifteen_min)
        listModel8.image = R.drawable.aloevera_split
        listModel8.details = context.getString(R.string.puffyeyes_remedy_nine)
        list.add(listModel8)
        return list
    }

    fun diet(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.eyes_puffyeyes_main_one)
        listModel.descriptionName = context.getString(R.string.eyes_puffyeyes_detail_one)
        listModel.image = R.drawable.puffy_parsley
        listModel.details = context.getString(R.string.puffyeyes_diet_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.eyes_puffyeyes_main_two)
        listModel1.descriptionName = context.getString(R.string.eyes_puffyeyes_detail_two)
        listModel1.image = R.drawable.beautifuleyes_herbaltea
        listModel1.details = context.getString(R.string.puffyeyes_diet_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.eyes_puffyeyes_main_three)
        listModel2.descriptionName = context.getString(R.string.eyes_puffyeyes_detail_three)
        listModel2.image = R.drawable.lice_salt
        listModel2.details = context.getString(R.string.puffyeyes_diet_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.eyes_puffyeyes_main_four)
        listModel3.descriptionName = context.getString(R.string.eyes_puffyeyes_detail_four)
        listModel3.image = R.drawable.puffy_horseradish
        listModel3.details = context.getString(R.string.puffyeyes_diet_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.eyes_puffyeyes_main_five)
        listModel4.descriptionName = context.getString(R.string.eyes_puffyeyes_detail_five)
        listModel4.image = R.drawable.banana_split
        listModel4.details = context.getString(R.string.puffyeyes_diet_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.eyes_puffyeyes_main_six)
        listModel5.descriptionName = context.getString(R.string.eyes_puffyeyes_detail_six)
        listModel5.image = R.drawable.puffy_juices
        listModel5.details = context.getString(R.string.puffyeyes_diet_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.eyes_puffyeyes_main_seven)
        listModel6.descriptionName = context.getString(R.string.eyes_puffyeyes_detail_seven)
        listModel6.image = R.drawable.puffy_alcohaldrink
        listModel6.details = context.getString(R.string.puffyeyes_diet_seven)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.eyes_puffyeyes_main_eight)
        listModel7.descriptionName = context.getString(R.string.eyes_puffyeyes_detail_eight)
        listModel7.image = R.drawable.dandruff_water
        listModel7.details = context.getString(R.string.puffyeyes_diet_eight)
        list.add(listModel7)
        return list
    }

    fun exercise(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.seated_forward_bend)
        listModel.descriptionName = context.getString(R.string.paschimottanasana)
        listModel.image = R.drawable.puffy_standingforwardbend
        listModel.details = context.getString(R.string.puffyeyes_exercise_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.plow_pose)
        listModel1.descriptionName = context.getString(R.string.halasana)
        listModel1.image = R.drawable.puffy_halasana
        listModel1.details = context.getString(R.string.puffyeyes_exercise_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.wheel_pose)
        listModel2.descriptionName = context.getString(R.string.chakrasana)
        listModel2.image = R.drawable.dandruff_chakrasana
        listModel2.details = context.getString(R.string.puffyeyes_exercise_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.shoulder_stand_yoga)
        listModel3.descriptionName = context.getString(R.string.salamba_sarvangasana)
        listModel3.image = R.drawable.dandruff_shoulderstand
        listModel3.details = context.getString(R.string.puffyeyes_exercise_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.head_stand)
        listModel4.descriptionName = context.getString(R.string.salamba_sirsasana)
        listModel4.image = R.drawable.puffy_headstand
        listModel4.details = context.getString(R.string.puffyeyes_exercise_five)
        list.add(listModel4)
        return list
    }
}