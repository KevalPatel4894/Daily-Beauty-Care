package com.kp.beautytips.data.eyes

import android.content.Context
import com.kp.beautytips.R
import com.kp.beautytips.model.ListModel

object BetterEyeBrows {

    fun remedy(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.castor_oil)
        listModel.descriptionName = context.getString(R.string.overnight)
        listModel.image = R.drawable.eyebrows_castor
        listModel.details = context.getString(R.string.bettereyebrows_remedy_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.coconut_oil)
        listModel1.descriptionName = context.getString(R.string.overnight)
        listModel1.image = R.drawable.coconutoil_hairfall_remedy
        listModel1.details = context.getString(R.string.bettereyebrows_remedy_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.olive_oil)
        listModel2.descriptionName = context.getString(R.string.thirty_min)
        listModel2.image = R.drawable.lice_oliveoil
        listModel2.details = context.getString(R.string.bettereyebrows_remedy_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.onion_juice)
        listModel3.descriptionName = context.getString(R.string.fifteen_min)
        listModel3.image = R.drawable.onion_juice
        listModel3.details = context.getString(R.string.bettereyebrows_remedy_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.fenugreek_seeds)
        listModel4.descriptionName = context.getString(R.string.overnight)
        listModel4.image = R.drawable.fenugreekseeds
        listModel4.details = context.getString(R.string.bettereyebrows_remedy_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.petroleum_jelly)
        listModel5.descriptionName = context.getString(R.string.overnight)
        listModel5.image = R.drawable.lice_petroleum
        listModel5.details = context.getString(R.string.bettereyebrows_remedy_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.egg_yolk)
        listModel6.descriptionName = context.getString(R.string.twenty_min)
        listModel6.image = R.drawable.eggmask_split
        listModel6.details = context.getString(R.string.bettereyebrows_remedy_seven)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.aloe_vera)
        listModel7.descriptionName = context.getString(R.string.thirty_min)
        listModel7.image = R.drawable.aloevera_split
        listModel7.details = context.getString(R.string.bettereyebrows_remedy_eight)
        list.add(listModel7)
        val listModel8 = ListModel()
        listModel8.title = context.getString(R.string.lemon)
        listModel8.descriptionName = context.getString(R.string.thirty_min)
        listModel8.image = R.drawable.eyebrows_lemon
        listModel8.details = context.getString(R.string.bettereyebrows_remedy_nine)
        list.add(listModel8)
        val listModel9 = ListModel()
        listModel9.title = context.getString(R.string.milk)
        listModel9.descriptionName = context.getString(R.string.till_it_dry)
        listModel9.image = R.drawable.darkcrcl_milk
        listModel9.details = context.getString(R.string.bettereyebrows_remedy_ten)
        list.add(listModel9)
        return list
    }

    fun diet(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.eyes_bettereyebrows_main_one)
        listModel.descriptionName = context.getString(R.string.eyes_bettereyebrows_detail_one)
        listModel.image = R.drawable.eyebrows_iron
        listModel.details = context.getString(R.string.bettereyebrows_diet_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.eyes_bettereyebrows_main_two)
        listModel1.descriptionName = context.getString(R.string.eyes_bettereyebrows_detail_two)
        listModel1.image = R.drawable.lice_protein
        listModel1.details = context.getString(R.string.bettereyebrows_diet_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.eyes_bettereyebrows_main_three)
        listModel2.descriptionName = context.getString(R.string.eyes_bettereyebrows_detail_three)
        listModel2.image = R.drawable.eyebrows_nutrients
        listModel2.details = context.getString(R.string.bettereyebrows_diet_three)
        list.add(listModel2)
        return list
    }

    fun exercise(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.exercise_one)
        listModel.descriptionName = context.getString(R.string.hands_underneath_each_eyebrow)
        listModel.image = R.drawable.eyebrows_exercise1
        listModel.details = context.getString(R.string.bettereyebrows_exercise_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.exercise_two)
        listModel1.descriptionName = context.getString(R.string.closed_eyes_workout)
        listModel1.image = R.drawable.eyebrows_exercise2
        listModel1.details = context.getString(R.string.bettereyebrows_exercise_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.exercise_three)
        listModel2.descriptionName = context.getString(R.string.look_upward_downward)
        listModel2.image = R.drawable.eyebrows_exercise3
        listModel2.details = context.getString(R.string.bettereyebrows_exercise_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.exercise_four)
        listModel3.descriptionName = context.getString(R.string.squeeze_muscle_face)
        listModel3.image = R.drawable.eyebrows_exercise4
        listModel3.details = context.getString(R.string.bettereyebrows_exercise_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.exercise_five)
        listModel4.descriptionName = context.getString(R.string.close_eyes_tightly)
        listModel4.image = R.drawable.eyebrows_exercise5_small
        listModel4.details = context.getString(R.string.bettereyebrows_exercise_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.additional_tips)
        listModel5.descriptionName = context.getString(R.string.more_tips)
        listModel5.image = R.drawable.eyebrows_title
        listModel5.details = context.getString(R.string.bettereyebrows_exercise_six)
        list.add(listModel5)
        return list
    }
}