package com.kp.beautytips.data.eyes

import android.content.Context
import com.kp.beautytips.R
import com.kp.beautytips.model.ListModel

object SunkenEyes {

    fun remedy(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.coconut_oil)
        listModel.descriptionName = context.getString(R.string.five_min)
        listModel.image = R.drawable.coconutoil_hairfall_remedy
        listModel.details = context.getString(R.string.sunkeneyes_remedy_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.castor_oil)
        listModel1.descriptionName = context.getString(R.string.overnight)
        listModel1.image = R.drawable.eyebrows_castor
        listModel1.details = context.getString(R.string.sunkeneyes_remedy_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.cucumber)
        listModel2.descriptionName = context.getString(R.string.few_min)
        listModel2.image = R.drawable.darkcrcl_cucumber
        listModel2.details = context.getString(R.string.sunkeneyes_remedy_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.olive_oil)
        listModel3.descriptionName = context.getString(R.string.overnight)
        listModel3.image = R.drawable.lice_oliveoil
        listModel3.details = context.getString(R.string.sunkeneyes_remedy_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.fish_oil)
        listModel4.descriptionName = context.getString(R.string.overnight)
        listModel4.image = R.drawable.sunken_fishoil
        listModel4.details = context.getString(R.string.sunkeneyes_remedy_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.almond_oil)
        listModel5.descriptionName = context.getString(R.string.two_min)
        listModel5.image = R.drawable.darkcrcl_almondoil
        listModel5.details = context.getString(R.string.sunkeneyes_remedy_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.avocado_oil)
        listModel6.descriptionName = context.getString(R.string.overnight)
        listModel6.image = R.drawable.avocado_split
        listModel6.details = context.getString(R.string.sunkeneyes_remedy_seven)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.potato)
        listModel7.descriptionName = context.getString(R.string.thirty_min)
        listModel7.image = R.drawable.darkcrcl_potato
        listModel7.details = context.getString(R.string.sunkeneyes_remedy_eight)
        list.add(listModel7)
        val listModel8 = ListModel()
        listModel8.title = context.getString(R.string.vaseline)
        listModel8.descriptionName = context.getString(R.string.overnight)
        listModel8.image = R.drawable.sunken_vaseline
        listModel8.details = context.getString(R.string.sunkeneyes_remedy_nine)
        list.add(listModel8)
        return list
    }

    fun diet(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.eyes_sunkeneyes_main_one)
        listModel.descriptionName = context.getString(R.string.eyes_sunkeneyes_detail_one)
        listModel.image = R.drawable.darkcrcl_vitamink
        listModel.details = context.getString(R.string.sunkeneyes_diet_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.eyes_sunkeneyes_main_two)
        listModel1.descriptionName = context.getString(R.string.eyes_sunkeneyes_detail_two)
        listModel1.image = R.drawable.dandruff_water
        listModel1.details = context.getString(R.string.sunkeneyes_diet_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.eyes_sunkeneyes_main_three)
        listModel2.descriptionName = context.getString(R.string.eyes_sunkeneyes_detail_three)
        listModel2.image = R.drawable.eyebrows_iron
        listModel2.details = context.getString(R.string.sunkeneyes_diet_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.eyes_sunkeneyes_main_four)
        listModel3.descriptionName = context.getString(R.string.eyes_sunkeneyes_detail_four)
        listModel3.image = R.drawable.beautifuleyes_vitaminc
        listModel3.details = context.getString(R.string.sunkeneyes_diet_four)
        list.add(listModel3)
        return list
    }

    fun exercise(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.dancing_eyes)
        listModel.descriptionName = context.getString(R.string.moving_eyeballs)
        listModel.image = R.drawable.eyebrows_exercise3
        listModel.details = context.getString(R.string.sunkeneyes_exercise_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.eye_squeeze)
        listModel1.descriptionName = context.getString(R.string.squeeze_eyes)
        listModel1.image = R.drawable.beautifuleyes_exercisesqueeze
        listModel1.details = context.getString(R.string.sunkeneyes_exercise_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.closed_eye_squeeze)
        listModel2.descriptionName = context.getString(R.string.hands_under_eyelid_squeeze_eyes)
        listModel2.image = R.drawable.darkcrcl_exercise3
        listModel2.details = context.getString(R.string.sunkeneyes_exercise_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.sound_sleep)
        listModel3.descriptionName = context.getString(R.string.sleep_eight_hrs_atleast)
        listModel3.image = R.drawable.beautifuleyes_sleep
        listModel3.details = context.getString(R.string.sunkeneyes_exercise_four)
        list.add(listModel3)
        return list
    }
}