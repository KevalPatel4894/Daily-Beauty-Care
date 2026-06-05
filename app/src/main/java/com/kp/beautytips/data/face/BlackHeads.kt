package com.kp.beautytips.data.face

import android.content.Context
import com.kp.beautytips.R
import com.kp.beautytips.model.ListModel

object BlackHeads {

    fun remedy(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.baking_soda)
        listModel.descriptionName = context.getString(R.string.few_min)
        listModel.image = R.drawable.baking_soda_dandruff
        listModel.details = context.getString(R.string.blackheads_remedy_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.cinnamon)
        listModel1.descriptionName = context.getString(R.string.fifteen_min)
        listModel1.image = R.drawable.blackheads_cinamon
        listModel1.details = context.getString(R.string.blackheads_remedy_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.mint_toothpaste)
        listModel2.descriptionName = context.getString(R.string.few_min)
        listModel2.image = R.drawable.blackheads_toothpaste
        listModel2.details = context.getString(R.string.blackheads_remedy_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.oatmeal)
        listModel3.descriptionName = context.getString(R.string.ten_min)
        listModel3.image = R.drawable.dryhands_oatmeal
        listModel3.details = context.getString(R.string.blackheads_remedy_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.lemon_juice)
        listModel4.descriptionName = context.getString(R.string.ten_min)
        listModel4.image = R.drawable.eyebrows_lemon
        listModel4.details = context.getString(R.string.blackheads_remedy_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.green_tea)
        listModel5.descriptionName = context.getString(R.string.three_min)
        listModel5.image = R.drawable.eyelashes_greentea
        listModel5.details = context.getString(R.string.blackheads_remedy_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.honey)
        listModel6.descriptionName = context.getString(R.string.ten_min)
        listModel6.image = R.drawable.honey
        listModel6.details = context.getString(R.string.blackheads_remedy_seven)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.turmeric)
        listModel7.descriptionName = context.getString(R.string.ten_min)
        listModel7.image = R.drawable.thighs_turmeric
        listModel7.details = context.getString(R.string.blackheads_remedy_eight)
        list.add(listModel7)
        val listModel8 = ListModel()
        listModel8.title = context.getString(R.string.epsom_salt)
        listModel8.descriptionName = context.getString(R.string.till_it_dry)
        listModel8.image = R.drawable.blackheads_epsomsalt
        listModel8.details = context.getString(R.string.blackheads_remedy_nine)
        list.add(listModel8)
        val listModel9 = ListModel()
        listModel9.title = context.getString(R.string.cornmeal)
        listModel9.descriptionName = context.getString(R.string.few_min)
        listModel9.image = R.drawable.blackheads_cornmeal
        listModel9.details = context.getString(R.string.blackheads_remedy_ten)
        list.add(listModel9)
        val listModel10 = ListModel()
        listModel10.title = context.getString(R.string.fenugreek)
        listModel10.descriptionName = context.getString(R.string.ten_min)
        listModel10.image = R.drawable.fenugreekseeds
        listModel10.details = context.getString(R.string.blackheads_remedy_eleven)
        list.add(listModel10)
        val listModel11 = ListModel()
        listModel11.title = context.getString(R.string.clay_mask)
        listModel11.descriptionName = context.getString(R.string.few_min)
        listModel11.image = R.drawable.blackheads_bentonite
        listModel11.details = context.getString(R.string.blackheads_remedy_twelve)
        list.add(listModel11)
        val listModel12 = ListModel()
        listModel12.title = context.getString(R.string.egg_whites)
        listModel12.descriptionName = context.getString(R.string.till_it_dry)
        listModel12.image = R.drawable.puffy_eggwhite
        listModel12.details = context.getString(R.string.blackheads_remedy_thirteen)
        list.add(listModel12)
        val listModel13 = ListModel()
        listModel13.title = context.getString(R.string.apple_cider_vinegar)
        listModel13.descriptionName = context.getString(R.string.till_it_dry)
        listModel13.image = R.drawable.apple_vinegar_dandruff
        listModel13.details = context.getString(R.string.blackheads_remedy_forteen)
        list.add(listModel13)
        return list
    }

    fun diet(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.face_blackheads_main_one)
        listModel.descriptionName = context.getString(R.string.face_blackheads_detail_one)
        listModel.image = R.drawable.blackheads_probiotic
        listModel.details = context.getString(R.string.blackheads_diet_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.face_blackheads_main_two)
        listModel1.descriptionName = context.getString(R.string.face_blackheads_detail_two)
        listModel1.image = R.drawable.heels_zinc
        listModel1.details = context.getString(R.string.blackheads_diet_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.face_blackheads_main_three)
        listModel2.descriptionName = context.getString(R.string.face_blackheads_detail_three)
        listModel2.image = R.drawable.drydmg_vitamina
        listModel2.details = context.getString(R.string.blackheads_diet_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.face_blackheads_main_four)
        listModel3.descriptionName = context.getString(R.string.face_blackheads_detail_four)
        listModel3.image = R.drawable.beautifuleyes_vitaminc
        listModel3.details = context.getString(R.string.blackheads_diet_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.face_blackheads_main_five)
        listModel4.descriptionName = context.getString(R.string.face_blackheads_detail_five)
        listModel4.image = R.drawable.dryhand_fiber
        listModel4.details = context.getString(R.string.blackheads_diet_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.face_blackheads_detail_six)
        listModel5.descriptionName = context.getString(R.string.face_blackheads_detail_six)
        listModel5.image = R.drawable.nailsgrowth_protein
        listModel5.details = context.getString(R.string.blackheads_diet_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.face_blackheads_main_seven)
        listModel6.descriptionName = context.getString(R.string.face_blackheads_detail_seven)
        listModel6.image = R.drawable.ic_food_avoide
        listModel6.details = context.getString(R.string.blackheads_diet_seven)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.face_blackheads_detail_eight)
        listModel7.descriptionName = context.getString(R.string.face_blackheads_detail_eight)
        listModel7.image = R.drawable.dandruff_water
        listModel7.details = context.getString(R.string.blackheads_diet_eight)
        list.add(listModel7)
        return list
    }

    fun exercise(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.never_pick_blackheads)
        listModel.descriptionName = context.getString(R.string.by_your_own)
        listModel.image = R.drawable.blackheads_pick
        listModel.details = context.getString(R.string.blackheads_exercise_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.avoid_ultra_abrasive_exfoliants)
        listModel1.descriptionName = context.getString(R.string.use_natural_exfoliants)
        listModel1.image = R.drawable.blackheads_exfoliate
        listModel1.details = context.getString(R.string.blackheads_exercise_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.wash_face_twice)
        listModel2.descriptionName = context.getString(R.string.wash_face_twice_daily)
        listModel2.image = R.drawable.blackheads_washface
        listModel2.details = context.getString(R.string.blackheads_exercise_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.wash_pillowcases)
        listModel3.descriptionName = context.getString(R.string.once_a_week)
        listModel3.image = R.drawable.blackheads_pillow
        listModel3.details = context.getString(R.string.blackheads_exercise_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.dont_touch_face)
        listModel4.descriptionName = context.getString(R.string.dont_touch)
        listModel4.image = R.drawable.blackheads_donttouch
        listModel4.details = context.getString(R.string.blackheads_exercise_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.exercise)
        listModel5.descriptionName = context.getString(R.string.exercise_regularly)
        listModel5.image = R.drawable.blackheads_exercise
        listModel5.details = context.getString(R.string.blackheads_exercise_six)
        list.add(listModel5)
        val listModel6= ListModel()
        listModel6.title = context.getString(R.string.additional_tips)
        listModel6.descriptionName = context.getString(R.string.more_tips)
        listModel6.image = R.drawable.blackheads_title
        listModel6.details = context.getString(R.string.blackheads_exercise_seven)
        list.add(listModel6)
        return list
    }
}