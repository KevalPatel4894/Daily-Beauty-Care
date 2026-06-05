package com.kp.beautytips.data.eyes

import android.content.Context
import com.kp.beautytips.R
import com.kp.beautytips.model.ListModel

object BeautifulEyes {

    fun remedy(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.tea_bags)
        listModel.descriptionName = context.getString(R.string.five_min)
        listModel.image = R.drawable.darkcrcl_teabag
        listModel.details = context.getString(R.string.beautifuleyes_remedy_five)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.almond_paste)
        listModel1.descriptionName = context.getString(R.string.forty_min)
        listModel1.image = R.drawable.beautyeye_almon
        listModel1.details = context.getString(R.string.beautifuleyes_remedy_four)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.cucumber)
        listModel2.descriptionName = context.getString(R.string.fifteen_min)
        listModel2.image = R.drawable.darkcrcl_cucumber
        listModel2.details = context.getString(R.string.beautifuleyes_remedy_seven)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.keep_eyes_hydrated)
        listModel3.descriptionName = context.getString(R.string.hydrate_eyes)
        listModel3.image = R.drawable.beauteye_hydrateeyes
        listModel3.details = context.getString(R.string.beautifuleyes_remedy_eight)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.milk)
        listModel4.descriptionName = context.getString(R.string.fifteen_min)
        listModel4.image = R.drawable.darkcrcl_milk
        listModel4.details = context.getString(R.string.beautifuleyes_remedy_nine)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.indian_gooseberry_amla)
        listModel5.descriptionName = context.getString(R.string.overnight)
        listModel5.image = R.drawable.grey_amla_remedy
        listModel5.details = context.getString(R.string.beautifuleyes_remedy_ten)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.lukewarm_water)
        listModel6.descriptionName = context.getString(R.string.five_min)
        listModel6.image = R.drawable.beautyeye_lukewarmwater
        listModel6.details = context.getString(R.string.beautifuleyes_remedy_three)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.fennel_seeds)
        listModel7.descriptionName = context.getString(R.string.ten_min)
        listModel7.image = R.drawable.beautyeye_fennelseed
        listModel7.details = context.getString(R.string.beautifuleyes_remedy_one)
        list.add(listModel7)
        val listModel8 = ListModel()
        listModel8.title = context.getString(R.string.apple_juice)
        listModel8.descriptionName = context.getString(R.string.twenty_min)
        listModel8.image = R.drawable.beautyeye_applejuice
        listModel8.details = context.getString(R.string.beautifuleyes_remedy_two)
        list.add(listModel8)
        return list
    }

    fun diet(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.eyes_beautifuleyes_main_one)
        listModel.descriptionName = context.getString(R.string.eyes_beautifuleyes_detail_one)
        listModel.image = R.drawable.drydmg_vitamina
        listModel.details = context.getString(R.string.beautifuleyes_diet_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.eyes_beautifuleyes_main_two)
        listModel1.descriptionName = context.getString(R.string.eyes_beautifuleyes_detail_two)
        listModel1.image = R.drawable.beautifuleyes_vitaminc
        listModel1.details = context.getString(R.string.beautifuleyes_diet_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.eyes_beautifuleyes_main_three)
        listModel2.descriptionName = context.getString(R.string.eyes_beautifuleyes_detail_three)
        listModel2.image = R.drawable.dandruff_water
        listModel2.details = context.getString(R.string.beautifuleyes_diet_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.eyes_beautifuleyes_main_four)
        listModel3.descriptionName = context.getString(R.string.eyes_beautifuleyes_detail_four)
        listModel3.image = R.drawable.beautifuleyes_herbaltea
        listModel3.details = context.getString(R.string.beautifuleyes_diet_four)
        list.add(listModel3)
        return list
    }

    fun exercise(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.exercise_one)
        listModel.descriptionName = context.getString(R.string.focusing_moving_pen)
        listModel.image = R.drawable.beautifuleyes_exercisepen
        listModel.details = context.getString(R.string.beautifuleyes_exercise_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.exercise_two)
        listModel1.descriptionName = context.getString(R.string.place_warm_hands_eyelids)
        listModel1.image = R.drawable.beautifuleyes_exercisepalms
        listModel1.details = context.getString(R.string.beautifuleyes_exercise_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.exercise_three)
        listModel2.descriptionName = context.getString(R.string.shut_eyes_squeeze)
        listModel2.image = R.drawable.beautifuleyes_exercisesqueeze
        listModel2.details = context.getString(R.string.beautifuleyes_exercise_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.eye_massage)
        listModel3.descriptionName = context.getString(R.string.massage_eyes)
        listModel3.image = R.drawable.darkcrcl_exercise3
        listModel3.details = context.getString(R.string.beautifuleyes_exercise_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.enjoy_sound_sleep)
        listModel4.descriptionName = context.getString(R.string.sleep_properly)
        listModel4.image = R.drawable.beautifuleyes_sleep
        listModel4.details = context.getString(R.string.beautifuleyes_exercise_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.additional_tips)
        listModel5.descriptionName = context.getString(R.string.more_tips)
        listModel5.image = R.drawable.beautifuleyes_title
        listModel5.details = context.getString(R.string.beautifuleyes_exercise_six)
        list.add(listModel5)
        return list
    }
}